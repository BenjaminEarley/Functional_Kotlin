import ConsList.Cons
import ConsList.Nil
import ZipperList.Cell
import ZipperList.Empty

fun binarySearch(a: Array<Int>, target: Int): Int {
    tailrec fun loop(min: Int, max: Int): Int =
        if (max < min) -1
        else {
            val guess = listOf(min, max).average().toInt()
            when {
                a[guess] < target -> loop(guess + 1, max)
                a[guess] > target -> loop(min, guess - 1)
                else -> guess
            }
        }
    return loop(0, a.size - 1)
}

fun selectionSort(a: Array<Int>): Array<Int> {
    fun getSmallestIndex(b: Array<Int>): Int {
        var index = 0
        var x = b[0]
        for (i in 1 until b.size) {
            if (x > b[i]) {
                x = b[i]
                index = i
            }
        }
        return index
    }

    fun Array<Int>.swap(index1: Int, index2: Int): Array<Int> =
        let { val temp = it[index1]; it[index1] = it[index2]; it[index2] = temp; it }

    var ordered: Array<Int> = a
    for (i in 0 until ordered.size - 1) {
        val value = ordered[i]
        val potential = getSmallestIndex(ordered.sliceArray(i + 1 until ordered.size))
        if (ordered[potential + i + 1] < value) ordered = ordered.swap(i, potential + i + 1)
    }
    return ordered
}

fun insertionSort(a: Array<Int>): Array<Int> {
    for (j in 1 until a.size) {
        var i = j - 1
        val processedValue = a[j]
        while ((i >= 0) && (a[i] > processedValue)) {
            a[i + 1] = a[i]
            i--
        }
        a[i + 1] = processedValue
    }
    return a
}

fun insertionSort2(a: Iterator<Int>): Iterator<Int> {
    fun insert(left: ZipperList<Int>, focus: Int): ZipperList<Int> =
        when (left) {
            is Empty -> Cell(focus, Empty)
            is Cell -> {
                if (left.head > focus) Cell(left.head, insert(left.tail, focus))
                else Cell(focus, left)
            }
        }

    tailrec fun loop(c: ZipperCursor<Int>): ZipperList<Int> =
        when (c.right) {
            is Empty -> insert(c.left, c.focus)
            is Cell -> loop(ZipperCursor(insert(c.left, c.focus), c.right.head, c.right.tail))
        }

    return loop(a.toZipper() ?: return emptyArray<Int>().iterator())
        .toIterator().asSequence().toList().asReversed().iterator()
}

fun mergeSort(list: List<Int>): List<Int> {

    fun merge(left: ConsList<Int>, right: ConsList<Int>): ConsList<Int> =
        when {
            left is Nil -> right
            right is Nil -> left
            else -> {
                val leftHead = (left as Cons).head
                val rightHead = (right as Cons).head
                if (leftHead < rightHead) Cons(leftHead, merge(left.tail, right))
                else Cons(rightHead, merge(left, right.tail))
            }
        }

    fun sort(l: ConsList<Int>): ConsList<Int> {
        val n = l.size() / 2
        return if (n == 0) l
        else {
            val (left, right) = l.split(n)
            merge(sort(left), sort(right))
        }
    }

    return sort(list.toConsList()).toList()
}