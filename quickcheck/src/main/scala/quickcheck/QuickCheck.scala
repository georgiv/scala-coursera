package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {
  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[Int]
    h <- oneOf(const(empty), genHeap)
  } yield insert(a, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("insert two elements into empty heap") = forAll { (n: Int, m: Int) =>
    val h = insert(m, insert(n, empty))
    findMin(h) == Math.min(n, m)
  }

  property("insert an element into empty heap and delete the minimum") = forAll { (n: Int) =>
    val h = deleteMin(insert(n, empty))
    h == empty
  }
}
