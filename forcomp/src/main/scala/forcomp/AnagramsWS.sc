package forcomp

import Anagrams._

object AnagramsWS {
  val sentence = List("I", "love", "you")         //> sentence  : List[String] = List(I, love, you)
  val valid = combinations(sentenceOccurrences(sentence)).filter(o => !dictionaryByOccurrences.get(o).isEmpty)
                                                  //> valid  : List[forcomp.Anagrams.Occurrences] = List(List((i,1), (o,1)), List(
                                                  //| (e,1), (i,1), (l,1)), List((e,1), (i,1), (v,1)), List((e,1), (l,1), (o,1)), 
                                                  //| List((e,1), (l,1), (v,1)), List((e,1), (l,1), (y,1)), List((i,1), (l,1), (o,
                                                  //| 1)), List((i,1), (v,1), (y,1)), List((l,1), (o,1), (u,1)), List((l,1), (o,1)
                                                  //| , (y,1)), List((o,1), (u,1), (y,1)), List((e,1), (i,1), (l,1), (u,1)), List(
                                                  //| (e,1), (i,1), (l,1), (v,1)), List((e,1), (l,1), (o,1), (v,1)), List((e,1), (
                                                  //| l,1), (v,1), (y,1)), List((i,1), (l,1), (o,1), (y,1)), List((e,1), (i,1), (l
                                                  //| ,1), (o,1), (v,1)))
  val words = valid.map(o => dictionaryByOccurrences.get(o).get).flatten
                                                  //> words  : List[forcomp.Anagrams.Word] = List(Io, Eli, lie, vie, Leo, Lev, Ely
                                                  //| , oil, ivy, Lou, Loy, you, lieu, evil, Levi, live, veil, vile, love, levy, o
                                                  //| ily, olive)
  //val combi = for(i <- 1 to words.length) yield words.combinations(i)
  def combine(sentence: Sentence, dic: List[Word], result: Sentence) {
    if (dic.isEmpty) println(result)
    else {
      if (result.mkString.length == sentence.mkString.length) println(result)
      else if (result.mkString.length < sentence.mkString.length) ???
      else if (result.mkString.length > sentence.mkString.length) println("Nil")
    }
  }                                               //> combine: (sentence: forcomp.Anagrams.Sentence, dic: List[forcomp.Anagrams.Wo
                                                  //| rd], result: forcomp.Anagrams.Sentence)Unit
}