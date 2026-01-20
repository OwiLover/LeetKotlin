import kotlin.math.max
import kotlin.math.min

class LongestPalindromicSubstring {

    private val dictionary = mutableMapOf<String, Boolean>()

    fun longestPalindrome(s: String): String {
        var maxLengthSubstring = MaxLengthSubstring(1, 0, 0)

        val length = s.length

        if (length <= 1) return s.firstOrNull()?.toString() ?: ""

        for (i in 0..< length) {
            val left = s.getOrNull(i) ?: continue
            val right = s.getOrNull(i + 1)
            val rightUneven = s.getOrNull(i + 2)


            if (left == right) {
                dictionary[s.substring(i..i+1)] = true
                maxLengthSubstring = MaxLengthSubstring(2, i, i+1)
                break
            }

            if (left == rightUneven) {
                dictionary[s.substring(i..i+2)] = true
                maxLengthSubstring = MaxLengthSubstring(3, i, i+2)
                break
            }
        }

        if (maxLengthSubstring.length < 2) return s.substring(maxLengthSubstring.startIndex..maxLengthSubstring.endIndex)

        for (i in 0..< length) {

            val shift = maxLengthSubstring.length / 2

            val leftShift = i - shift
            var rightShift = i + shift

            if (leftShift<0 || rightShift >= length) continue

            val left = s.getOrNull(leftShift) ?: continue
            val rightUneven = s.getOrNull(rightShift)

            if (left == rightUneven) {
                val prevSubstring = s.substring(leftShift + 1 ..< rightShift)
                if (dictionary[prevSubstring] == true) {
                    dictionary[s.substring(leftShift..rightShift)] = true
                    checkMaxPalindrome(leftShift, rightShift, s).also {
                        if (maxLengthSubstring.length < it.length) maxLengthSubstring = it
                    }
                }
                else {
                    if (checkIsPalindrome(prevSubstring)) {
                        dictionary[s.substring(leftShift..rightShift)] = true
                        checkMaxPalindrome(leftShift, rightShift, s).also {
                            if (maxLengthSubstring.length < it.length) maxLengthSubstring = it
                        }
                    }
                }
            }

            rightShift += 1
            val rightEven = s.getOrNull(rightShift) ?: continue

            if (left == rightEven) {
                val prevSubstring = s.substring(leftShift + 1 ..< rightShift)

                if (dictionary[prevSubstring] == true) {
                    dictionary[s.substring(leftShift..rightShift)] = true
                    checkMaxPalindrome(leftShift, rightShift, s).also {
                        if (maxLengthSubstring.length < it.length) maxLengthSubstring = it
                    }
                }
                else {
                    if (checkIsPalindrome(prevSubstring)) {
                        dictionary[s.substring(leftShift..rightShift)] = true
                        checkMaxPalindrome(leftShift, rightShift, s).also {
                            if (maxLengthSubstring.length < it.length) maxLengthSubstring = it
                        }
                    }
                }
            }
        }
        val start = maxLengthSubstring.startIndex
        val end = maxLengthSubstring.endIndex
        return s.substring(start..end)
    }

    fun checkIsPalindrome(s: String): Boolean {
        return when (s.length % 2) {
            0 -> {
                val first = s.substring(0..<s.length / 2)
                val second = s.substring(s.length / 2..<s.length)
                return first == second.reversed()
            }
            1 -> {
                val first = s.substring(0..<s.length / 2)
                val second = s.substring(s.length / 2 + 1..<s.length)
                return first == second.reversed()
            }
            else -> false
        }
    }

    fun checkMaxPalindrome(left: Int, right: Int, s: String): MaxLengthSubstring {
        val newLeft = left - 1
        val newRight = right + 1

        var maxSubstring: MaxLengthSubstring? = null

        val leftChar = s.getOrNull(newLeft)
        val rightChar = s.getOrNull(newRight)

        if (leftChar == null || rightChar == null) {
            return MaxLengthSubstring(right - left + 1, left, right)
        }

        val innerSubstring = s.substring(left..right)

        if (leftChar == rightChar && dictionary[innerSubstring] == true) {
            dictionary[s.substring(newLeft..newRight)] = true
            maxSubstring = checkMaxPalindrome(newLeft, newRight, s)
        }

        return maxSubstring ?: MaxLengthSubstring(right - left + 1, left, right)
    }
}

data class MaxLengthSubstring(
    val length: Int,
    val startIndex: Int,
    val endIndex: Int
)