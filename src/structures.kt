import ConsList.Cons
import ConsList.Nil
import ZipperList.Cell
import ZipperList.Empty

// Cons
sealed class ConsList<out A> {
    object Nil : ConsList<Nothing>() {
        override fun toString(): String = "Nil"
    }

    class Cons<A>(val head: A, val tail: ConsList<A>) : ConsList<A>() {
        override fun toString(): String {
            fun loop(consList: ConsList<A>): String = when (consList) {
                Nil -> "Nil"
                is Cons -> "${consList.head}, " + loop(consList.tail)
            }
            return "[${loop(this)}]"
        }
    }
}

fun <A> List<A>.toConsList(): ConsList<A> = foldRight(Nil, ::Cons)
fun <A> ConsList<A>.toList(): List<A> {
    val list = mutableListOf<A>()
    fun loop(consList: ConsList<A>): List<A> = when (consList) {
        Nil -> emptyList()
        is Cons -> list.apply { add(consList.head) } + loop(consList.tail)
    }
    loop(this)
    return list
}

fun <A> ConsList<A>.size(): Int {
    fun loop(consList: ConsList<A>): Int = when (consList) {
        Nil -> 0
        is Cons -> 1 + loop(consList.tail)
    }
    return loop(this)
}

fun <A> ConsList<A>.reverse(): ConsList<A> {
    tailrec fun loop(consList: ConsList<A>, acc: ConsList<A>): ConsList<A> = when (consList) {
        is Nil -> acc
        is Cons -> loop(consList.tail, Cons(consList.head, acc))
    }
    return loop(this, Nil)
}

fun <A> ConsList<A>.split(n: Int): Pair<ConsList<A>, ConsList<A>> {

    tailrec fun loop(left: ConsList<A>, right: ConsList<A>, count: Int): Pair<ConsList<A>, ConsList<A>> =
        when {
            count == 0 || right !is Cons -> Pair(left, right)
            else -> loop(Cons(right.head, left), right.tail, count - 1)
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