fun String.isPalindrome(): Boolean =
    when {
        count() < 2 -> true
        first() != last() -> false
        else -> slice(1..count() - 2).isPalindrome()
    }
