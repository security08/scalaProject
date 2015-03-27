package com.test.function

import org.junit.Test
import scala.math._

//higher-order function 高阶函数
class HigherOrderFunction {

  @Test
  def hofTest{
    println(oneQuarter(ceil _))
    println(oneQuarter(sqrt _))
    
    var triple = mulby(3)
	println(triple)
	var half = mulby(0.5)
	println(triple(14) + " " + half(14))
  }
  
  /**
   *oneQuarter函数接收另一个函数作为参数，
   * 此函数参数是接收Double并返回Double的函数
  */
  def oneQuarter(fun : (Double) => Double) = fun(0.25)
  
  
  /**
   * 产出另一个函数
   */
  def mulby(factor : Double) = (x : Double) => factor * x
}