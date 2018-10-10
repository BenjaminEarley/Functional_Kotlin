// Trees
sealed class Tree<out A>

data class Leaf<A>(val value: A) : Tree<A>()
data class Branch<A>(val left: Tree<A>, val right: Tree<A>) : Tree<A>()

fun <A> Tree<A>.size(): Int {
    fun loop(tree: Tree<A>): Int = when (tree) {
        is Leaf -> 1
        is Branch -> 1 + loop(tree.left) + loop(tree.right)
    }
    return loop(this)
}

fun Tree<Int>.max(): Int {
    fun loop(tree: Tree<Int>): Int = when (tree) {
        is Leaf -> tree.value
        is Branch -> Math.max(loop(tree.right), loop(tree.left))
    }
    return loop(this)
}

fun <A> Tree<A>.depth(): Int {
    fun loop(tree: Tree<A>): Int = when (tree) {
        is Leaf -> 1
        is Branch -> 1 + Math.max(loop(tree.right), loop(tree.left))
    }
    return loop(this)
}