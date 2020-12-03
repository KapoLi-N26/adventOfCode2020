package utils

import java.io.File

fun getResourceText(path: String): String {
    return File(ClassLoader.getSystemResource(path).file).readText()
}
