package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if ((r == 0) || (c == 0) || (c == r)) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */

  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def iterate(c: Int, remainder: List[Char]): Boolean = {
      if (c < 0) return false
      if (remainder.isEmpty) (c == 0)
      else if (remainder.head.equals('(')) iterate(c + 1, remainder.tail)
      else if (remainder.head.equals(')')) iterate(c - 1, remainder.tail)
      else iterate(c, remainder.tail)
    }
    iterate(0, chars)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def iterate(result: Int, sum: Int, remainder: List[Int]): Int = {
      if (sum == money) 
        result + 1
      else if (sum < money && !remainder.isEmpty)
        iterate(result, sum + remainder.head, remainder) + iterate(result, sum, remainder.tail)
      else 
        result
    }
    iterate(0, 0, coins)
  }
}
