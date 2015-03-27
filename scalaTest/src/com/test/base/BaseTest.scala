package com.test.base

import org.junit.Test
import org.junit.Assert

class BaseTest {

  @Test
  def playWithInt(){
    val capacity : Int = 10
    val list = new java.util.ArrayList[String]
    list.ensureCapacity(capacity)
    Assert.assertNotEquals(list.size, capacity)
  }
  
  def getPerson(key : Int) = {
    //tuple 元组
    ("bush" ,"fort" , "bush@qq.com")
  }
  
  @Test
  def tupleTest(){
    val (firstName , lastName , email) = getPerson(1)
    println(firstName)
    println(lastName)
    println(email)
  }
    
  @Test
  def multiplyLineTest() {
    val str = """ this is
		      a "multiply"
                	 	      |line
              		      """
    println(str)
    println(str.stripMargin)
  }
  
  @Test
  def assignTest(){
    var a ,b ,c = 1
    //type mismatch; found : Unit required: Int
    //a = b = c
  }
  
  @Test
  def equalityTest(){
    val str1 = "hello"
    val str2 = "hello"
    val str3 = new String("hello")
    println(str1 == str2)
    println(str1 eq str2)
    println(str1 == str3)
    println(str1 eq str3)
  }
  
  @Test
  def typeChangeTest{
    //类型转换
    var i = "9".toInt
    var s = 9.toString()
  }
  
}