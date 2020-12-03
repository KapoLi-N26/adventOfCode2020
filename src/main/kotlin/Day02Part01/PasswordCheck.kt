package Day02Part01

data class PasswordCheck(val range: IntRange, val letter: String, val password: String) {
    companion object {
        fun from(entry: String): PasswordCheck {
            val parts = entry.split(": ", " ","-")
            return PasswordCheck(parts[0].toInt()..parts[1].toInt(), parts[2], parts[3])
        }
    }
    fun isPasswordValid(): Boolean {
        return password.count { it.toString() == letter } in range
    }
}
