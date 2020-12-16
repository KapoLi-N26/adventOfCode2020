package Day15Part01to02

fun main(args: Array<String>) {
    var spokenToTurnMap = mutableMapOf<Long, Long>()

    /*
    * 12,20,0,6,1,17,7
    * */
    spokenToTurnMap[12L] = 1L
    spokenToTurnMap[20L] = 2L
    spokenToTurnMap[0L] = 3L
    spokenToTurnMap[6L] = 4L
    spokenToTurnMap[1L] = 5L
    spokenToTurnMap[17L] = 6L
    var lastSpoken = 7L
    var atTurn = 7L

    //part 1: set atTurn to 2019
    //part 2: set atTurn to 29999999
    for (i in atTurn..29999999) {
        val seen = spokenToTurnMap[lastSpoken] ?: -1L
        spokenToTurnMap[lastSpoken] = atTurn
        if (seen == -1L) {
            lastSpoken = 0
        } else {
            lastSpoken = atTurn - seen
        }
        atTurn++
    }
    println("turn: $atTurn , spoken: $lastSpoken")
}
