package com.test.pattern

import org.junit.Test

//Pattern Matching 模式匹配
class PatternTest {

  //Matching Literals and Constants
  def activity(day: String) {
    day match {
      case "Sunday" => println("Sunday")
      case "Monday" => println("Monday")
      case "Friday" => println("Friday")
    }
  }

  //Matching a Wildcard
  def activity(day: DayOfWeek.Value) {
    day match {
      case DayOfWeek.SUNDAY => println("Eat, sleep, repeat...")
      case DayOfWeek.SATURDAY => println("Hangout with friends")
      //Wildcard
      case _ => println("...code for fun...")
    }
  }

  //Matching a Wildcard
  object DayOfWeek extends Enumeration {
    val SUNDAY = Value("Sunday")
    val MONDAY = Value("Monday")
    val TUESDAY = Value("Tuesday")
    val WEDNESDAY = Value("Wednesday")
    val THURSDAY = Value("Thursday")
    val FRIDAY = Value("Friday")
    val SATURDAY = Value("Saturday")
  }

  //Matching Tuples
  def processCoordinates(input: Any) {
    input match {
      case (a, b) => printf("Processing (%d, %d)... ", a, b)
      case "done" => println("done")
      case _ => null
    }
  }

  //Matching Lists
  def processItems(items: List[String]) {
    items match {
      case List("apple", "ibm") => println("Apples and IBMs")
      case List("red", "blue", "white") => println("Stars and Stripes...")
      case List("red", "blue", _*) => println("colors red, blue, ... ")
      case List("apple", "orange", otherFruits @ _*) =>
        println("apples, oranges, and " + otherFruits)
    }
  }

  val max = 100000
  val MIN = 0
  
  // handle a sequence of values that are not all of the same type
  //模式变量必有小写开头，常量必须大写开头
  def process(input: Any) {
    input match {
      case (a: Int, b: Int) => print("Processing (int, int)... ")
      case (a: Double, b: Double) => print("Processing (double, double)... ")
      //In addition to matching the pattern, the guard provided using the if clause
      //must also be satisfied for the expression to evaluate
      //卫述句：用if从句表示，对表达式求值前必须满足卫述句
      case msg: Int if (msg > 1000000) => println("Processing int > 1000000")
      case MIN => println("Processing match MIN")
      //msg变量必须小写开头
      case msg: Int => print("Processing int... ")
      case msg: String => println("Processing string... ")
      case _ => printf("Can't handle %s... ", input)
    }
  }
  
  @Test
  def machesTest {
    activity("Sunday")
    //match throw error
    //activity("notexist")
    List("Monday", "Friday").foreach(activity)

    println
    
    activity(DayOfWeek.SATURDAY)
    activity(DayOfWeek.MONDAY)

    println
    
    processCoordinates((39, -104))
    processCoordinates("done")

    println
    
    processItems(List("apple", "ibm"))
    processItems(List("red", "blue", "green"))
    processItems(List("red", "blue", "white"))
    processItems(List("apple", "orange", "grapes", "dates"))
    
    println
    
    process((34.2, -159.3))
    process(0)
    process("0")
    process(1000001)
    process(2.2)
  }
}