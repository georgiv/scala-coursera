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

  property("delete") = forAll { (n: Int) =>
    val h = deleteMin(insert(n, empty))
    isEmpty(h) == true
  }

  property("insert two elements into empty heap and find the minimum") = forAll { (n: Int, m: Int) =>
    val h = insert(m, insert(n, empty))
    findMin(h) == Math.min(n, m)
  }
//
//  property("insert an element into empty heap and delete the minimum") = forAll { (n: Int) =>
//    val h = deleteMin(insert(n, empty))
//    isEmpty(h) == true
//  }
//
//  property("sorted sequence from heap") = forAll { (h: H) => 
//    def sort(h: H, sorted: List[A]):List[A] = if (isEmpty(h)) sorted.reverse else sort(deleteMin(h), findMin(h)::sorted)
//    val l = sort(h, Nil)
//    l == l.sorted
//  }
//
//  property("find min after meld") = forAll { (h1: H, h2: H) =>
//    val h3 = meld(h1, h2)
//    if (isEmpty(h3)) throw new NoSuchElementException("min of empty heap")
//    else if (isEmpty(h1) && !isEmpty(h2)) findMin(h3) == findMin(h2)
//    else if (!isEmpty(h1) && isEmpty(h2)) findMin(h3) == findMin(h1)
//    else findMin(h3) == Math.min(findMin(h1), findMin(h2))
//  }
}
