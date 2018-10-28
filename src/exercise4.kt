sealed class Option<out A>
data class Some<A>(val value: A) : Option<A>()
object None : Option<Nothing>()

fun <A> some(value: A): Option<A> = Some(value)
inline fun <A> none(): Option<A> = None

// 4.1
infix fun <A, B> Option<A>.map(f: (A) -> B): Option<B> = when (this) {
    is Some -> Some(f(value))
    None -> None
}

infix fun <B, A : B> Option<A>.getOrElse(default: () -> B): B = when (this) {
    is Some -> value
    None -> default()
}

infix fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> = map(f) getOrElse { None }

infix fun <B, A : B> Option<A>.orElse(ob: () -> Option<B>): Option<B> = map { Some(it) } getOrElse { ob() }

infix fun <A> Option<A>.filter(f: (A) -> Boolean): Option<A> = flatMap { if (f(it)) Some(it) else None }

// 4.2
fun Sequence<Double>.mean(): Option<Double> =
    if (none()) None
    else Some(sum() / count())

fun Sequence<Double>.variance2(): Option<Double> =
    mean() flatMap { m -> map { Math.pow(it - m, 2.0) }.mean() }

// 4.3
fun <A, B> lift(f: (A) -> B): (Option<A>) -> Option<B> = { it map f }

fun <A> Try(get: () -> A): Option<A> =
    try {
        some(get())
    } catch (e: Throwable) {
        none()
    }

fun <A, B, C> map2(a: Option<A>, b: Option<B>, f: (A, B) -> C): Option<C> =
    a flatMap { aa -> b map { bb -> f(aa, bb) } }

// 4.4
fun <A> ConsList<Option<A>>.sequence(): Option<ConsList<A>> =
    foldRight(some(ConsList.Nil)) { x: Option<A>, y: Option<ConsList<A>> ->
        map2(x, y) { xx, yy -> cons(xx, yy) }
    }

// 4.5
fun <A, B> ConsList<A>.traverse(f: (A) -> Option<B>): Option<ConsList<B>> =
    foldRight(some(ConsList.Nil)) { x: A, y: Option<ConsList<B>> ->
        map2(f(x), y) { xx, yy -> cons(xx, yy) }
    }

fun <A> ConsList<Option<A>>.sequenceViaTraverse(): Option<ConsList<A>> = traverse { x -> x }