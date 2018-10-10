fun fac(n: Int): Int = if (n < 1) 1 else n * fac(n - 1)

//Exercise 2.1
fun fib(n: Int): Int = if (n < 2) n else fib(n - 2) + fib(n - 1)

//Exercise 2.2
fun <A> isSorted(a: Array<A>, ordered: (A, A) -> Boolean): Boolean {
    tailrec fun loop(n: Int): Boolean =
        if (n >= a.size) true
        else if (!ordered(a[n - 1], a[n])) false
        else loop(n + 1)
    return loop(1)
}

fun <A, B, C> partial1(a: A, f: (A, B) -> C): (B) -> C = { b -> f(a, b) }

//Exercise 2.3
fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C = { a -> { b -> f(a, b) } }

//Exercise 2.4
fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C = { a, b -> f(a)(b) }

//Exercise 2.5
fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = { a -> f(g(a)) }
