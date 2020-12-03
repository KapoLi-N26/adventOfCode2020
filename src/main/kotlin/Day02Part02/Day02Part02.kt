package Day02Part02

import utils.getResourceText

fun main(args: Array<String>) {
    val count = getResourceText("passwords.txt").split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .filter { PasswordCheck.from(it).isPasswordValid() }
        .count()

    println(count)
}
