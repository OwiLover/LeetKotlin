//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val l1 = ListNode(arrayOf(2,4,9))
    val l2 = ListNode(arrayOf(5,6,4))

    println(l1.nodesToString().contentToString())
    println(l2.nodesToString().contentToString())
    print(AddTwoNumbers().addTwoNumbers(l1, l2)?.nodesToString().contentToString())
}