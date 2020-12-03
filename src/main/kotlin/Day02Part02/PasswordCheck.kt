package Day02Part02

data class PasswordCheck(val pos1: Int, val pos2: Int, val letter: String, val password: String) {
    companion object {
        fun from(entry: String): PasswordCheck {
            val parts = entry.split(": ", " ","-")
            return PasswordCheck(parts[0].toInt()-1, parts[1].toInt()-1, parts[2], parts[3])
        }
    }
    fun isPasswordValid(): Boolean {
        return (password[pos1].toString() == letter).xor(password[pos2].toString() == letter)
    }
}
