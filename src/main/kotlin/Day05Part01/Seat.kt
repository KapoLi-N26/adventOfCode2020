package Day05Part01

data class Seat(val row: Int, val col: Int) {
    companion object {
        fun from(entry: String): Seat {
            val row = binary(127, entry.substring(0..6))
            val col = binary(7, entry.substring(7..9))
            return Seat(row, col)
        }

        fun binary(max: Int, input: String): Int {
            var rangeStart = 0;
            var rangeEnd = max;
            for (i in 0 until input.length - 1) {
                // F means to take the lower half, keeping rows 0 through 63.
                val diff = (rangeEnd - rangeStart + 1) / 2
                if (input[i] == 'F' || input[i] == 'L') {
                    rangeEnd = rangeStart + (diff - 1)
                } else { // B
                    rangeStart = rangeEnd - (diff - 1)
                }
            }

            return if (input[input.length - 1] == 'F' || input[input.length - 1] == 'L') {
                rangeStart
            } else {
                rangeEnd
            }
        }
    }

    fun getSeatId(): Int = (row * 8) + col
}
