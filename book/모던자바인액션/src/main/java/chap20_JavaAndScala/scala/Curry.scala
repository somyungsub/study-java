package chap20_JavaAndScala.scala

object Curry {

  def multiply(x:Int, y:Int) = x * y
  def multiplyCurry(x:Int)(y:Int) = x * y

  def main(args: Array[String]): Unit = {

    val r = multiplyCurry(2)(10)
    val multiplyByTwo : Int => Int = multiplyCurry(2)
    val a = multiplyByTwo(10)

    println(r)
    println(a)

  }
}
