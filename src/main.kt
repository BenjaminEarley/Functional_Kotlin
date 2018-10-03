fun main(args: Array<String>) {

    //Data
    val primes = arrayOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
    val numbers = arrayOf(70, 46, 51, 66, 9, 75, 43)

    log("Factorial") { fac(5) }
    log("Fibonacci") { fib(5) }
    log("Sorting Exercise 2.2") { isSorted(arrayOf(1, 2, 3, 2)) { a, b -> a <= b } }

    log("Binary Search") { binarySearch(primes.copyOf(), 73) }
    log("Selection Sort") { selectionSort(numbers.copyOf()).contentToString() }
    log("Insertion Sort") { insertionSort(numbers.copyOf()).contentToString() }
    log("Insertion Sort 2") { insertionSort2(numbers.copyOf().iterator()).asSequence().toList().toString() }
    log("Palindrome", { "RATER".isPalindrome() }, { "ROTOR".isPalindrome() })
}

fun log(action: String, f: () -> Any) =
    println("$action results in ${f()}")


fun log(action: String, vararg functions: () -> Any) =
    println("$action results in: \n     ${functions.fold("") { acc, f -> acc + f().toString() + "\n     " }}")

























