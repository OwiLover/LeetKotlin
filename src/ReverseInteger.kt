import kotlin.math.pow

class ReverseInteger {
    fun reverse(x: Int): Int {
        var x = x

        val maxInt = (2.0.pow(32)).toInt()
        var result = 0
        val isNegative: Boolean?

        if (x < 0) {
            isNegative = true
            x = -x
        } else isNegative = false

        while (x > 0) {
            val num = x % 10
            if ((result > maxInt / 10) || (result == maxInt / 10 && num > 7)) return 0
            result = result * 10 + num
            x /= 10
        }

        return result * (if (isNegative) -1 else 1)
    }
}