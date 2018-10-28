import ConsList.Cons
import ConsList.Nil
import ZipperList.Cell
import ZipperList.Empty

// Cons
sealed class ConsList<out A> {
    object Nil : ConsList<Nothing>() {
        override fun toString(): String = "Nil"
    }

    class Cons<A> internal constructor(val head: A, val tail: ConsList<A>) : ConsList<A>() {
        override fun toString(): String = "[${this.foldLeft("") { acc, value -> "$acc$value, " }}Nil]"
    }
}

inline fun <A> nil(): ConsList<A> = ConsList.Nil
fun <A> cons(head: A, tail: ConsList<A>): ConsList<A> = ConsList.Cons(head, tail)

fun <A> Cons<A>.reduce(f: (A, A) -> A): A = tail.fold(this.head) { acc, value -> f(acc, value) }

fun <A : B, B> ConsList<A>.fold(z: B, f: (B, B) -> B): B = foldLeft(z) { acc, value -> f(acc, value) }

fun <A, B> ConsList<A>.foldLeft(z: B, f: (B, A) -> B): B {
    tailrec fun loop(list: ConsList<A>, z: B): B = when (list) {
        ConsList.Nil -> z
        is ConsList.Cons -> loop(list.tail, f(z, list.head))
    }
    return loop(this, z)
}

fun <A, B> ConsList<A>.foldRight(z: B, f: (A, B) -> B): B {
    fun loop(list: ConsList<A>, z: B): B = when (list) {
        ConsList.Nil -> z
        is ConsList.Cons -> f(list.head, loop(list.tail, z))
    }
    return loop(this, z)
}

fun <A> List<A>.toConsList(): ConsList<A> = foldRight(Nil, ::Cons)

//mutating for efficiency
fun <A> ConsList<A>.toList(): List<A> = foldLeft(mutableListOf()) { acc, value -> acc.add(value); acc }

fun <A> ConsList<A>.size(): Int = foldLeft(0) { acc, _ -> acc + 1 }

fun <A> ConsList<A>.reverse(): ConsList<A> = foldLeft(nil()) { acc, value -> cons(value, acc) }

fun <A> ConsList<A>.split(n: Int): Pair<ConsList<A>, ConsList<A>> {

    tailrec fun loop(left: ConsList<A>, right: ConsList<A>, count: Int): Pair<ConsList<A>, ConsList<A>> =
        when {
            count == 0 || right !is Cons -> Pair(left, right)
            else -> loop(cons(right.head, left), right.tail, count - 1)
        }

    return loop(Nil, this, n).let { (l, r) -> Pair(l.reverse(), r) }
}
// End Cons

// Zipper
sealed class ZipperList<out A> {
    object Empty : ZipperList<Nothing>()
    class Cell<A>(val head: A, val tail: ZipperList<A>) : ZipperList<A>()
}

data class ZipperCursor<out A>(val left: ZipperList<A>, val focus: A, val right: ZipperList<A>)

fun <A> Iterator<A>.toZipper(): ZipperCursor<A>? {
    val zipper = this.asSequence().toList().foldRight(Empty, ::Cell)
    return when (zipper) {
        is Cell -> ZipperCursor(Empty, zipper.head, zipper.tail)
        is Empty -> null
    }
}

inline fun <reified A> ZipperList<A>.toIterator(): Iterator<A> {
    val x = helper(emptyArray(), this)
    return x.iterator()
}

tailrec fun <A> helper(array: Array<A>, list: ZipperList<A>): Array<A> =
    when (list) {
        is Empty -> array
        is Cell -> {
            helper(array + list.head, list.tail)
        }
    }
// End Zipper