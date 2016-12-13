package quickcheck

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

object QuickCheckWS {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(211); 
  val myGen = for {
    n <- Gen.choose(10, 20)
    m <- Gen.choose(2 * n, 500)
  } yield (n, m);System.out.println("""myGen  : org.scalacheck.Gen[(Int, Int)] = """ + $show(myGen ));$skip(28); 

  var rTup = myGen.sample;System.out.println("""rTup  : Option[(Int, Int)] = """ + $show(rTup ));$skip(56); 

  val vowel = Gen.oneOf('A', 'E', 'I', 'O', 'U', 'Y');System.out.println("""vowel  : org.scalacheck.Gen[Char] = """ + $show(vowel ));$skip(30); 

  val rVowel = vowel.sample;System.out.println("""rVowel  : Option[Char] = """ + $show(rVowel ));$skip(92); 

  val vowelFr = Gen.frequency((3, 'A'), (4, 'E'), (2, 'I'), (3, 'O'), (1, 'U'), (1, 'Y'));System.out.println("""vowelFr  : org.scalacheck.Gen[Char] = """ + $show(vowelFr ));$skip(32); 
  val rVowelFr = vowelFr.sample

  type H = Int;System.out.println("""rVowelFr  : Option[Char] = """ + $show(rVowelFr ));$skip(185); 

  lazy val genMap: Gen[Map[H,H]] = for {
    k <- arbitrary[Int]
    v <- arbitrary[Int]
    m <- oneOf(const(Map.empty[Int, Int]), genMap)
  } yield m.updated(k, v);System.out.println("""genMap: => org.scalacheck.Gen[Map[quickcheck.QuickCheckWS.H,quickcheck.QuickCheckWS.H]]""");$skip(16); val res$0 = 
  genMap.sample

  trait Abbb {
  };System.out.println("""res0: Option[Map[quickcheck.QuickCheckWS.H,quickcheck.QuickCheckWS.H]] = """ + $show(res$0));$skip(37); val res$1 = 

  Nil.reverse;System.out.println("""res1: List[Nothing] = """ + $show(res$1))}
}
