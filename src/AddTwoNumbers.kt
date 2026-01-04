class AddTwoNumbers {
    private fun nextListNodes(l1: ListNode?, l2: ListNode?, left: Int): ListNode? {
        if ((l1 == null || l2 == null) && left == 0) {
            return l1 ?: l2
        }
        val newLeft = left + (l1?.`val` ?: 0) + (l2?.`val` ?: 0)
        val finalList = ListNode(newLeft % 10)
        finalList.next = nextListNodes(l1?.next, l2?.next, newLeft / 10)
        return finalList
    }
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) {
            return null
        }
        val left = (l1?.`val` ?: 0) + (l2?.`val` ?: 0)
        val finalList = ListNode(left % 10)

        finalList.next = nextListNodes(l1?.next, l2?.next, left / 10)
        return finalList
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    constructor(array: Array<Int>) : this(`val` = array.first()) {
        val droppedArray = array.drop(1)
        if (droppedArray.isNotEmpty()) {
            this.next = createNext(droppedArray.first(), droppedArray.drop(1).toTypedArray())
            this.`val` = array.first()
        } else {
            this.next = null
            this.`val` = 0
        }
    }
    fun createNext(element: Int, next: Array<Int>?): ListNode? {
        val newList = ListNode(element)
        next?.firstOrNull()?.let {
            newList.next = createNext(it, next.drop(1).toTypedArray())
        }
        return newList
    }
    fun nodesToString(): Array<String> {
        var node: ListNode? = this
        val nodes = mutableListOf<String>()
        while (node != null) {
            nodes.add(node.`val`.toString())
            node = node.next
        }
        return nodes.toTypedArray()
    }
}