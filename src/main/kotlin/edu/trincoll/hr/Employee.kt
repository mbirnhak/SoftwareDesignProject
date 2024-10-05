package edu.trincoll.hr

// Abstract class Employee should have:
//   - a name of type String
//   - an id of type Int.
// It should implement the Comparable interface with the
// compareTo method.
//
// It should include an abstract method pay()
// that returns a Double.
//
// It should override the toString method to
// return a string with the name and id of the employee.
abstract class Employee(open val name : String, open val id : Int) : Comparable<Employee> {
    abstract fun pay() : Double
    override fun toString(): String {
        return "Employee(name=$name, id=$id)"
    }
    override fun compareTo(other: Employee): Int {
        val nameComparison = this.name.compareTo(other.name)
        return if (nameComparison == 0) {
            this.id.compareTo(other.id)  // Compare by id if names are equal
        } else {
            nameComparison  // Return name comparison result
        }
    }
}