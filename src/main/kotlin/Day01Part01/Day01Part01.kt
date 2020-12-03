package Day01Part01
import utils.getResourceText
import java.io.File

fun main(args: Array<String>) {
    val seen = mutableSetOf<Int>()
    val list = getResourceText("input.txt").split("\n")
    list.forEach {
        try {
            val value = it.toInt()
            val need = 2020 - value;
            if (seen.contains(need)) {
                println(value * need)
                return@forEach
            } else {
                seen.add(value)
            }
        } catch (e: Exception) {}
    }
}
