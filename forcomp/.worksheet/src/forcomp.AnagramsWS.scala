package forcomp

import Anagrams._

object AnagramsWS {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(97); 
  val sentence = List("I", "love", "you");System.out.println("""sentence  : List[String] = """ + $show(sentence ));$skip(111); 
  val valid = combinations(sentenceOccurrences(sentence)).filter(o => !dictionaryByOccurrences.get(o).isEmpty);System.out.println("""valid  : List[forcomp.Anagrams.Occurrences] = """ + $show(valid ));$skip(73); 
  val words = valid.map(o => dictionaryByOccurrences.get(o).get).flatten;System.out.println("""words  : List[forcomp.Anagrams.Word] = """ + $show(words ));$skip(430); 
  //val combi = for(i <- 1 to words.length) yield words.combinations(i)
  def combine(sentence: Sentence, dic: List[Word], result: Sentence) {
    if (dic.isEmpty) println(result)
    else {
      if (result.mkString.length == sentence.mkString.length) println(result)
      else if (result.mkString.length < sentence.mkString.length) ???
      else if (result.mkString.length > sentence.mkString.length) println("Nil")
    }
  };System.out.println("""combine: (sentence: forcomp.Anagrams.Sentence, dic: List[forcomp.Anagrams.Word], result: forcomp.Anagrams.Sentence)Unit""")}
}
