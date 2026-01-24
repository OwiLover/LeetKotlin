class StringToIntegerAtoi {
    fun myAtoi(s: String): Int {
        val s = s.trimStart()
        val isNegative: Boolean?
        val first = s.firstOrNull()

        if (first == '-' || first == '+') {
            isNegative = first == '-'
            return getNum(s.drop(1), isNegative)
        } else {
            return getNum(s)
        }
    }

    fun getNum(s: String, isNegative: Boolean? = null): Int {
        val isNegative = isNegative ?: false

        return if (isNegative) countNegative(s) else countPositive(s)
    }

    fun countPositive(s: String): Int {
        var result = 0

        val maxInt = Int.MAX_VALUE / 10

        for (i in s) {
            if (i.isDigit()) {
                val i = i.digitToInt()
                if (result > maxInt || (result == maxInt && i >= 7)) return Int.MAX_VALUE
                result = result * 10 + i
            } else {
                break
            }
        }
        return result
    }

    fun countNegative(s: String): Int {
        var result = 0
        val minInt = Int.MIN_VALUE / 10

        for (i in s) {
            if (i.isDigit()) {
                val i = i.digitToInt()
                if (result < minInt || (result == minInt && i >= 8)) return Int.MIN_VALUE
                result = result * 10 - i
            } else {
                break
            }
        }
        return result
    }
}