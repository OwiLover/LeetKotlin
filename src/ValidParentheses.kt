class ValidParentheses {
    private val stack = SubStack()
    fun isValid(s: String): Boolean {
        if (s.length % 2 != 0) {
            return false
        }
        for (character in s) {
            if (!stack.addCharacter(character)) {
                return false
            }
        }
        return stack.isEmpty()
    }
}

class SubStack {
    private val stack = mutableListOf<Char>()

    private val converter = mapOf<Char, Char>(
        '(' to ')',
        '{' to '}',
        '[' to ']'
    )

    fun addCharacter(character: Char): Boolean {
        when (character) {
            in "({[" -> {
                stack.add(character)
                return true
            }
            in ")}]" -> {
                val lastCharacter = stack.lastOrNull()?.let {
                    converter[it] == character }?.also {
                    if (it) {
                        stack.removeLast()
                        return true
                    }
                }
                return false
            }
            else -> {
                return false
            }
        }
    }
    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }
}