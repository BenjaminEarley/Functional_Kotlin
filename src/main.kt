fun main(args: Array<String>) {

    //Data
    val primes = arrayOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
    val numbers = arrayOf(70, 46, 51, 66, 9, 75, 43)

    //Execute and Print
    print("Factorial") { fac(5) }
    print("Fibonacci") { fib(5) }
    print("Sorting Exercise 2.2") { isSorted(arrayOf(1, 2, 3, 2)) { a, b -> a <= b } }

    print("Binary Search") { binarySearch(primes.copyOf(), 73) }
    print("Selection Sort") { selectionSort(numbers.copyOf()).contentToString() }
    print("Insertion Sort") { insertionSort(numbers.copyOf()).contentToString() }
    print("Insertion Sort Alt") { insertionSort2(numbers.copyOf().iterator()).asSequence().toList().toString() }
    print("Merge Sort") { mergeSort(numbers.copyOf().toList().toConsList() ?: ConsList.Nil) }

    print("Palindrome", { "RATER".isPalindrome() }, { "ROTOR".isPalindrome() })
    print("Power", { power(3, -2) }, { power(3, -1) }, { power(3, 0) }, { power(3, 1) }, { power(3, 2) })
}

fun print(action: String, f: () -> Any) =
    println("$action results in ${f()}")


fun print(action: String, vararg functions: () -> Any) =
    println("$action results in: ${functions.fold("") { acc, f -> acc + "\n     " + f().toString() }}")

























