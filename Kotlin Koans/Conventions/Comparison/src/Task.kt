data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) : Int {
        if(this.year < other.year ||
                (this.year == other.year && this.month < other.month) ||
                (this.year == other.year && this.month == other.month && this.dayOfMonth < other.dayOfMonth)) {
            return -1
        }
        if(this.year == other.year &&
                this.month == other.month &&
                this.dayOfMonth == other.dayOfMonth) {
            return 0
        }
        return 1
    }
}

fun test(date1: MyDate, date2: MyDate) {
    // this code should compile:
    println(date1 < date2)
}
