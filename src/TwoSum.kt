class TwoSum {
    private val dictionary = mutableMapOf<Int, Int>()
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for ((index, num) in nums.withIndex()) {
            val leftIndex = dictionary[num]
            leftIndex?.let {
                return intArrayOf(it, index)
            }
            dictionary[target - num] = index
        }
        return intArrayOf()
    }
}