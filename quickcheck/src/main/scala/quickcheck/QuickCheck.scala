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

  // 1 & 2
  property("findMin") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  // 2
  property("insert") = forAll { (h: H) =>
    findMin(insert(Int.MinValue, h)) == Int.MinValue
  }

  property("deleteMin") = forAll { (n: Int) =>
    isEmpty(deleteMin(insert(n, empty)))
  }

  property("insert2") = forAll { (n: Int, m: Int) =>
    findMin(insert(m, insert(n, empty))) == Math.min(n, m)
  }

  property("meld") = forAll { (h1: H, h2: H) =>
    findMin(meld(h1, h2)) == Math.min(findMin(h1), findMin(h2))
  }

//  property("sorted sequence from heap") = forAll { (h: H) => 
//    def sort(h: H, sorted: List[A]):List[A] = if (isEmpty(h)) sorted.reverse else sort(deleteMin(h), findMin(h)::sorted)
//    val l = sort(h, Nil)
//    l == l.sorted
//  }
}
