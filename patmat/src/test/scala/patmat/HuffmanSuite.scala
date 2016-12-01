package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val t3 = makeCodeTree(makeCodeTree(makeCodeTree(makeCodeTree(makeCodeTree(makeCodeTree(makeCodeTree(Leaf('a', 8), Leaf('b', 3)), Leaf('c', 1)), Leaf('d', 1)), Leaf('e',1 )), Leaf('f',1 )), Leaf('g', 1)), Leaf('h', 1))
    val t4 = List(Leaf('d', 1), Leaf('r', 1), Leaf('w', 1), Leaf('e', 1), Leaf('h', 1), Leaf('o', 2), Leaf('l', 3))
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
      assert(weight(t2) === 9)
      assert(weight(t3) === 17)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t1) === List('a','b'))
      assert(chars(t2) === List('a','b','d'))
      assert(chars(t3) === List('a','b','c', 'd', 'e', 'f', 'g', 'h'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("times") {
    assert(times(string2Chars("a")) === List(('a', 1)))
    assert(times(string2Chars("aaaaabaaaaa")) === List(('b', 1), ('a', 10)))
    assert(times(string2Chars("hello, world")) === List(('d', 1), ('r', 1), ('w', 1), ('o', 2), ('l', 3), ('e', 1), ('h', 1)))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("singleton") {
    new TestTrees {
      assert(singleton(List()) === false)
      assert(singleton(Nil) === false)
      assert(singleton(List(t1)) === true)
      assert(singleton(List(t1, t2, t3)) === false)
      assert(singleton(t3::Nil) === true)
    }
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
    assert(combine(List()) === Nil)
    assert(combine(List(Leaf('g', 1))) === List(Leaf('g', 1)))
    assert(combine(List(Leaf('g', 1), Leaf('a', 19))) === List(Fork(Leaf('g', 1), Leaf('a', 19), List('g', 'a'), 20)))
    new TestTrees {
      assert(combine(t4) === List(Leaf('w', 1), Leaf('e', 1), Leaf('h', 1), Fork(Leaf('d', 1), Leaf('r', 1), List('d', 'r'), 2), Leaf('o', 2), Leaf('l', 3)))
    }
  }

  test("update") {
    new TestTrees {
      assert(until(combine, singleton)(List(Leaf('a', 1))) === List(Leaf('a', 1)))
      assert(until(combine, singleton)(List(Leaf('a', 1), Leaf('z', 16))) === List(Fork(Leaf('a', 1), Leaf('z', 16), List('a', 'z'), 17)))
      assert(until(combine, singleton)(t4) === List(Fork(Fork(Fork(Leaf('d', 1), Leaf('r', 1), List('d', 'r'), 2), Leaf('o', 2), List('d', 'r', 'o'), 4), Fork(Fork(Leaf('h', 1), Fork(Leaf('w', 1), Leaf('e', 1), List('w', 'e'), 2), List('h', 'w', 'e'), 3), Leaf('l', 3), List('h', 'w', 'e', 'l'), 6), List('d', 'r', 'o', 'h', 'w', 'e', 'l'), 10)))
    }
  }

  test("createCodeTree") {
    assert(createCodeTree(string2Chars("hello, world")) === Fork(Fork(Fork(Leaf('d', 1), Leaf('r', 1), List('d', 'r'), 2), Leaf('o', 2), List('d', 'r', 'o'), 4), Fork(Fork(Leaf('h', 1), Fork(Leaf('w', 1), Leaf('e', 1), List('w', 'e'), 2), List('h', 'w', 'e'), 3), Leaf('l', 3), List('h', 'w', 'e', 'l'), 6), List('d', 'r', 'o', 'h', 'w', 'e', 'l'), 10))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
}
