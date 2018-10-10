fun main(args: Array<String>) {

    //Data
    val primes = arrayOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
    val numbers = arrayOf(70, 46, 51, 66, 9, 75, 43)
    val tree =
        Branch(
            Branch(Leaf(4), Leaf(9)),
            Branch(
                Branch(
                    Leaf(7),
                    Branch(Leaf(2), Leaf(1))
                ),
                Branch(
                    Leaf(5),
                    Branch(
                        Leaf(5),
                        Branch(Leaf(11), Leaf(8))
                    )
                )
            )
        )

    //Execute and Print
    println("\nExercises 2\n")
    print("Factorial") { fac(5) }
    print("Fibonacci") { fib(5) }
    print("Sorting") { isSorted(arrayOf(1, 2, 3, 2)) { a, b -> a <= b } }

    println("\nExercises 3\n")
    print("Tree Size") { tree.size() }
    print("Tree Max") { tree.max() }
    print("Tree Depth") { tree.depth() }

    println("\nAlgorithms\n")
    print("Binary Search") { binarySearch(primes.copyOf(), 73) }
    print("Selection Sort") { selectionSort(numbers.copyOf()).contentToString() }
    print("Insertion Sort") { insertionSort(numbers.copyOf()).contentToString() }
    print("Insertion Sort Alt") { insertionSort2(numbers.copyOf().iterator()).asSequence().toList().toString() }
    print("Merge Sort") { mergeSort(numbers.copyOf().toList()) }

    println("\nChallenges\n")
    print("Palindrome", { "RATER".isPalindrome() }, { "ROTOR".isPalindrome() })
    print("Power", { power(3, -2) }, { power(3, -1) }, { power(3, 0) }, { power(3, 1) }, { power(3, 2) })
    print("Hanoi") { solveHanoi(1, Tower.LEFT, Tower.MIDDLE); "above" }
    print("Fibonacci in Linear Time") { linFib(100) }
}

fun print(action: String, f: () -> Any) =
    println("$action results is ${f()}")


fun print(action: String, vararg functions: () -> Any) =
    println("$action results is: ${functions.fold("") { acc, f -> acc + "\n     " + f().toString() }}")

























