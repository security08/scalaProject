package com.test.function

import org.junit.Test
//闭包
//you can create code blocks with variables that are not bound.
//You will have to bind them before you can invoke the function; however,
//they could bind to, or close over, variables outside of their local scope
//and parameter list. That’s why they’re called closures.
class Colsure {
  /**
   * 闭包由代码和代码用到的任何非局部变量定义构成。
   */
  def mulby(factor : Double) = (x : Double) => factor * x

  def loopThrough(number : Int)(closure : Int => Unit){
    for(i <- 1 to number) closure(i)
  }
  
  @Test
  def closureTest{
    var result = 0
    //closure
    val add = {value : Int => result += value}
    
    loopThrough(10)(add)
    println("the result is " + result)
    
    result = 0
    loopThrough(5)(add)
    println("the result is " + result)
    
    var product = 1
	  loopThrough(5) { product *= _ }
    println("Product of values from 1 to 5 is " + product)

    var triple = mulby(3)
    println(triple)
    var half = mulby(0.5)
    println(triple(14) + " " + half(14))
  }
}