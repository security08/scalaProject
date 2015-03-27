package com.test.pattern

import org.junit.Test

// regular expression
class RegularTest {

  //Regular Expressions as Extractors
  def process(input: String) {
    //正则表达式提取器，获取括号中的值
    val GoogStock = """^GOOG:(\d*\.\d+)""".r
    input match {
      //Scala regular expressions are extractors, so you can readily use them in case expressions
      case GoogStock(price) => println("Price of GOOG is " + price)
      case _ => println("not processing " + input)
    }
  }

  //Regular Expressions as Extractors
  def process2(input: String) {
    //正则表达式提取器，获取括号中的值
    val MatchStock = """^(.+):(\d*\.\d+)""".r
    input match {
      //分别匹配第一，第二个括号
      case MatchStock("GOOG", price) => println("Price of GOOG is " + price)
      case MatchStock("IBM", price) => println("IBM's trading at " + price)
      case MatchStock(symbol, price) => printf("Price of %s is %s\n", symbol, price)
      case _ => println("not processing " + input)
    }
}
  
  @Test
  def matchTest {
    val pattern = "(S|s)cala".r
    val str = "Scala is scalable and cool"
    println(pattern findFirstIn str)
    println((pattern findAllIn str).mkString(", "))
    
    println("cool".r replaceFirstIn(str, "awesome"))

    process("GOOG:310.84")
    process("GOOG:310")
    process("IBM:84.01")
    
    process2("GOOG:310.84")
    process2("IBM:84.01")
    process2("GE:15.96")
  }
}