package streams

object BloxorzWS {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  case class Pos(row: Int, col: Int) {
    /** The position obtained by changing the `row` coordinate by `d` */
    def deltaRow(d: Int): Pos = copy(row = row + d)

    /** The position obtained by changing the `col` coordinate by `d` */
    def deltaCol(d: Int): Pos = copy(col = col + d)
  }

  println(Pos(1, 1) == Pos(1, 1))                 //> true
}