package Day04Part01

import utils.getResourceText

fun main(args: Array<String>) {
    val passports = getResourceText("passports.txt").split("\n\n").filter { it.isNotEmpty() }

    val validPassports = passports
        .filter { Passport.from(it).isValidPassport() }
        .count()

    println(validPassports)
}

