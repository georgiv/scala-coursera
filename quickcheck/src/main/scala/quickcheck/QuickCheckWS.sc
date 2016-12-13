package quickcheck

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

object QuickCheckWS {
  val myGen = for {
    n <- Gen.choose(10, 20)
    m <- Gen.choose(2 * n, 500)
  } yield (n, m)                                  //> myGen  : org.scalacheck.Gen[(Int, Int)] = org.scalacheck.Gen$$anon$6@4361bd4
                                                  //| 8

  var rTup = myGen.sample                         //> rTup  : Option[(Int, Int)] = Some((11,301))

  val vowel = Gen.oneOf('A', 'E', 'I', 'O', 'U', 'Y')
                                                  //> vowel  : org.scalacheck.Gen[Char] = org.scalacheck.Gen$$anon$2@6a41eaa2

  val rVowel = vowel.sample                       //> rVowel  : Option[Char] = Some(A)

  val vowelFr = Gen.frequency((3, 'A'), (4, 'E'), (2, 'I'), (3, 'O'), (1, 'U'), (1, 'Y'))
                                                  //> vowelFr  : org.scalacheck.Gen[Char] = org.scalacheck.Gen$$anon$2@10bdf5e5
  val rVowelFr = vowelFr.sample                   //> rVowelFr  : Option[Char] = Some(A)

  type H = Int

  lazy val genMap: Gen[Map[H,H]] = for {
    k <- arbitrary[Int]
    v <- arbitrary[Int]
    m <- oneOf(const(Map.empty[Int, Int]), genMap)
  } yield m.updated(k, v)                         //> genMap: => org.scalacheck.Gen[Map[quickcheck.QuickCheckWS.H,quickcheck.Quick
                                                  //| CheckWS.H]]
  genMap.sample                                   //> res0: Option[Map[quickcheck.QuickCheckWS.H,quickcheck.QuickCheckWS.H]] = Som
                                                  //| e(Map(0 -> -1))

  trait Abbb {
  }
}