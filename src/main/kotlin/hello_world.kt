import java.time.LocalDate
import java.time.Month

fun start(): String = "OK"

fun main() {
    assert(start() == "OK")

    val x = "Hello, world!"
    println("The string is ${x.length}")

    val p = Person("Matt", LocalDate.of(2003, Month.JULY, 17))
    println(p)
}

class Person(val name: String, val dob: LocalDate) {
    fun age(): Int {
        return LocalDate.now().year - dob.year
    }

    override fun toString(): String {
        return "$name is ${age()} years old"
    }
}