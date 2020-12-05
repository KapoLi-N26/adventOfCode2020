package Day04Part02

data class Passport(val fields: Map<String, String>) {
    companion object {
        private val PASSPORT_REGEX = "^[0-9]{9}$".toRegex()
        private val HAIR_COLOR_REGEX = "^#[0-9,a-f]{6}$".toRegex()
        private val EYE_COLORS = setOf<String>("amb","blu","brn","gry","grn","hzl","oth")
        private val REQUIRED_FIELDS = mapOf<String, (String)->(Boolean)>(
            "byr" to {i: String -> i.toInt() in 1920..2002},
            "iyr" to {i: String -> i.toInt() in 2010..2020},
            "eyr" to {i: String -> i.toInt() in 2020..2030},
            "hgt" to {i: String ->
                when {
                    i.endsWith("cm") -> {
                        i.substringBefore("cm").toInt() in 150..193
                    }
                    i.endsWith("in") -> {
                        i.substringBefore("in").toInt() in 59..76
                    }
                    else -> {
                        false
                    }
                }
            },
            "hcl" to {i: String -> HAIR_COLOR_REGEX.matches(i)},
            "ecl" to {i: String -> EYE_COLORS.contains(i)},
            "pid" to {i: String -> PASSPORT_REGEX.matches(i)}
        )

        fun from(entry: String): Passport {
            return Passport(
                entry
                    .split("\n", " ")
                    .filter { it.isNotEmpty() }
                    .map {
                        val pair = it.split(":")
                        pair[0] to pair[1]
                    }.filter {
                        (REQUIRED_FIELDS.containsKey(it.first) && REQUIRED_FIELDS[it.first]!!.invoke(it.second)) ||
                            !REQUIRED_FIELDS.containsKey(it.first)
                    }.toMap()
            )
        }
    }
    fun isValidPassport(): Boolean = fields.keys.containsAll(REQUIRED_FIELDS.keys)

}
