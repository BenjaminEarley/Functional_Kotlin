fun String.isPalindrome(): Boolean =
    when {
        count() < 2 -> true
        first() != last() -> false
        else -> slice(1..count() - 2).isPalindrome()
    }

fun Int.isEven(): Boolean = this % 2 == 0
fun Int.isOdd(): Boolean = this.isEven().not()

fun power(x: Int, n: Int): Double =
    when {
        n == 0 -> 1.0
        n < 0 -> 1.0 / power(x, -n)
        n.isOdd() -> x * power(x, n - 1)
        else -> power(x, n / 2) * power(x, n / 2)
    }


enum class Tower(val title: String) {
    LEFT("left"),
    MIDDLE("middle"),
    RIGHT("right")
}

fun moveDisk(source: Tower, destination: Tower) =
    println("Move ${source.title}'s top disk to ${destination.title}")

fun solveHanoi(size: Int, source: Tower, destination: Tower) {
    if (size > 0) {
        val tmp = (Tower.values().toList() - source - destination).first()
        solveHanoi(size - 1, source, tmp)
        moveDisk(source, destination)
        solveHanoi(size - 1, tmp, destination)
    }
}