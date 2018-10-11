// Trees
sealed class Tree<out A>

data class Leaf<A>(val value: A) : Tree<A>()
data class Branch<A>(val left: Tree<A>, val right: Tree<A>) : Tree<A>()

// 3.25
fun <A> Tree<A>.size(): Int = when (this) {
    is Leaf -> 1
    is Branch -> 1 + left.size() + right.size()
}

// 3.26
fun Tree<Int>.max(): Int = when (this) {
    is Leaf -> value
    is Branch -> Math.max(left.max(), right.max())
}

// 3.27
fun <A> Tree<A>.depth(): Int = when (this) {
    is Leaf -> 1
    is Branch -> 1 + Math.max(left.depth(), right.depth())
}

// 3.28
fun <A, B> Tree<A>.map(f: (A) -> B): Tree<B> = when (this) {
    is Leaf -> Leaf(f(value))
    is Branch -> Branch(left.map(f), right.map(f))
}

// 3.29
fun <A, B> Tree<A>.fold(f: (A) -> B, g: (B, B) -> B): B = when (this) {
    is Leaf -> f(value)
    is Branch -> g(left.fold(f, g), right.fold(f, g))
}

fun <A> Tree<A>.sizeViaFold(): Int = fold({ 1 }, { l, r -> 1 + l + r })
fun Tree<Int>.maxViaFold(): Int = fold({ it }, { l, r -> Math.max(l, r) })
fun <A> Tree<A>.depthViaFold(): Int = fold({ 1 }, { l, r -> 1 + Math.max(l, r) })
fun <A, B> Tree<A>.mapViaHold(f: (A) -> B): Tree<B> = fold({ Leaf(f(it)) as Tree<B> }, { l, r -> Branch(l, r) })