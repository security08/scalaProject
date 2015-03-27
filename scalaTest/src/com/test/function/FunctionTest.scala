package com.test.function

import org.junit.Test
import scala.math._
//函数，两种使用方法：
//1.调用它
//2.传递它，存放在变量中，或者作为参数传递给另一个函数。
class FunctionTest {
  //ceilFun是一个包含函数的变量，而不是一个固定的函数
  val ceilFun = ceil _
  
   @Test
  def functionTest2{
    val num = 3.14
    val array = Array(2.1,3.14,4)
    //普通的函数调用方法
    println(ceilFun(num))
    //将ceilFun传递给另一个函数
    //map方法接受一个函数参数，将它应用到数组中的所有值，然后返回结果的数组。
    println(array.map(ceilFun).mkString(","))
    
    println
    
    //(x : Double) => x - 1 为匿名函数，没有定义函数名称
    println(array.map((x : Double) => x - 1).mkString(","))
    //将匿名函数传递给另一函数或方法时，会进行类型推断，此处x可不声明为Double
    println(array.map((x) => x - 1).mkString(","))
    //可以省略圆括号"(x)" 改为 "x"
    println(array.map(x => x - 1).mkString(","))
    //如果参数在 => 右侧只出现一次，可以用 "_" 替换
    println(array.map(_ - 1).mkString(","))
    
    //val fun = _ - 1 //错误，没法推断 _ 的类型
    val fun = (_ : Double) - 1 //OK
      
    //也可以将函数参数包在花括号"{}"当中而不是用圆括号"()"
    println(array.map{(x : Double) => x - 1}.mkString(","))
    //忽略"."
    println(array map {(x : Double) => x - 1} mkString(","))
    
    println
    
    //将匿名函数赋值给triple变量，效果跟 def triple(x : Double) = x * 3 一样
    val triple = (x : Double) => x * 3
    println(array.map(triple).mkString(","))
    
  }
  
  @Test
  def functionTest{
    println(decorate("hello"))
    //right用默认参数值
    println(decorate("hello" , "<<<["))
    println(decorate("hello" , "<<<", ">>>"))
    //带名参数，参数顺序可以任意
    println(decorate(left = "*", right = "*" , str = "hello"))
    //left用默认参数值
    println(decorate("hello",right = ">>>" ))
    
    println(sum(1,2,3,4,5))
    // _* 表示参数序列
    println(sum(1 to 5 : _*))
    println(recursiveSum(1 to 5 : _*))
    
    box("hello")
  }
  
  
  //过程(procedure)：不返回值的函数表示法，没有等号“=”，返回类型是Unit。
  def box(s : String)/*这里没有等号 =*/{
    var border = "-" * s.length() + "-\n"
    println(border + "|" + s + "|\n" + border)
  }
  
  //变长参数，函数得到的是类型为Seq的参数
  def sum(num : Int *) = {
    var result = 0
    for(i <- num) result += i
    result
  }

  //递归
  def recursiveSum(numbers : Int *) : Int = {
    if(numbers.length == 0) 0
    else
      numbers.head + recursiveSum(numbers.tail : _*)
    
  }
  
  //显式给出left和right默认参数值
  def decorate(str : String ,left : String = "[" , right : String = "]") = {
    left + str + right
  }
  
  //可以不使用return，可不声明返回类型(Int)，scala自动推断
  def abs(x : Double) = if(x >= 0) x else -x
  
  //函数的最后一个值即为返回值
  def fac(n : Int) = {
    var r = 1
    for(i <- 1 to n) r = r * i
    r
  }
  
  //递归调用必须声明返回类型
  def fac2(n : Int) : Int = if(n <= 0) 1 else n * fac2(n - 1)
}