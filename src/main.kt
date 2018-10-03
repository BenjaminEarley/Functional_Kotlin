fun main(args: Array<String>) {
    println(fib(2))
    println(isSorted(arrayOf(1, 2, 3, 2)) { a, b -> a <= b })
    val primes = arrayOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
    println(binarySearch(primes.copyOf(), 73))
    val numbers = arrayOf(70, 46, 51, 66, 9, 75, 43)
    println(selectionSort(numbers.copyOf()).contentToString())
    println(insertionSort(numbers.copyOf()).contentToString())
    println(insertionSort2(numbers.copyOf().iterator()).asSequence().toList().toString())
}
























