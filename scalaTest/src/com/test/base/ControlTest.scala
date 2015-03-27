package com.test.base

import org.junit.Test
/**
 * if,while,for
 */
class ControlTest {

 
  @Test
  def forTest(){
    //for格式：
    //for([pattern <- generator; definition*]+; filter*)  [yield] expression
    for(i <- 1 to 3){
      println(i)
    }
    
    //The yield keyword is optional and, if present, 
    //tells the expression to return a list of values instead of a Unit
    val result = for(i <- 1 to 3) yield i * 2
    println(result)
    val result2 = (1 to 3).map(_ * 2)
    println(result2)
    val doubleEven = for (i <- 1 to 5; if i % 2 == 0) yield i * 2
    println(doubleEven)
    val doubleEven2 = for {
      i <- 1 to 5
      if i % 2 == 0
      } 
    yield i * 2
    println(doubleEven2)
    println
    
    //两个生成器，最右边的生成器控制着最内的循环
    for(i <- 1 to 3 ; j <- 4 to 6){
      println("[" + i + "," + j + "]")
    }
    println
    
    //每个生成器可以带一个守卫，以if开头的Boolean的表达式
    for(i <- 1 to 5 ; j <- 1 to 5 if i == j){
      println("[" + i + "," + j + "]")
    }
    
    println
     
    for(i <- 1 to 5 ;from = 6 - i ; j <- from to 5 if i != j){
      println("[" + i + "," + j + "]")
    }
  }
  
  
  @Test
  def ifTest(){
    val x = 1;
    val s = if( x > 0 ) 1 else -1
    println(s)
  }

  @Test
  def readLineTest(){
    val name = readLine("Your name:")
    print("Your age:")
    val age = readInt
    printf("Hello,%s !,your age is %d.\n" , name , age)
  }
  
  @Test
  def foreachTest(){
    (1 to 3).foreach(i => print(i + " "))
  }
}