package chap20_JavaAndScala.scala

object Test {
  def main(args: Array[String]): Unit = {
    val book = (2018, "Modern Java In Action", "Manning")
    val numbers = (42, 10, 1, 2)

    println(book)
    println(numbers)

    println(book._1)
    println(numbers._3)

    val h = new Hello()
    h.sayThankYou()

    class Student(var name: String, var id: Int)
    val s = new Student("Raoul", 1)
    println(s)
    println(s.name)
    s.id = 1337
    println(s.id)

    class Empty extends Sized
    println(new Empty().isEmpty())

    class Box
    val b1 = new Box() with Sized
    println(b1.isEmpty())

    val b2 = new Box()
  }
}
