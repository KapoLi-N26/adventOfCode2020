package Day04Part01

data class Passport(val fields: Map<String, String>) {
    companion object {

        private val REQUIRED_FIELDS = listOf(
            "byr",
            "iyr",
            "eyr",
            "hgt",
            "hcl",
            "ecl",
            "pid"
        )

        fun from(entry: String): Passport {
            return Passport(
                entry
                    .split("\n", " ")
                    .filter { it.isNotEmpty() }
                    .map {
                        val pair = it.split(":")
                        pair[0] to pair[1]
                    }.toMap()
            )
        }
    }
    fun isValidPassport(): Boolean = fields.keys.containsAll(REQUIRED_FIELDS)

}
