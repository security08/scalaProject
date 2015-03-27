package com.test.function

import org.junit.Test

//函数值 function value (code block) 
class FunctionValue {

  /**
   * 使用具有多个参数的函数值：operation
   * 利用curry化可以把函数从接收多个参数转换成多个参数列表
   */
  def injectByCurry(arr : Array[Int] , initial : Int)
      (operation:(Int,Int) => Int) : Int = {
    var carryOver = initial
    arr.foreach(element => carryOver = operation(carryOver,element))
    carryOver
  }
  
  /**
   * 使用具有多个参数的函数值：operation
   */
  def inject(arr : Array[Int] , initial : Int,
      operation:(Int,Int) => Int) : Int = {
    var carryOver = initial
    arr.foreach(element => carryOver = operation(carryOver,element))
    carryOver
  }
  
  def maxFunctionValue(a : Int,b : Int) = {Math.max(a, b)}
  
  @Test
  def injectTest{
    val array = Array(3,1,5,7,9,8)
    //重用函数值，必有指明a，b的类型
    val sumFunctionValue = {(a : Int,b : Int) => a + b}
    
    val sum = inject(array, 0, sumFunctionValue)
    printf("Sum is %d \n" , sum)
    //引用方法名
    val max = inject(array, 0, maxFunctionValue)
    printf("Max is %d\n" , max)
    
    val sum1 = injectByCurry(array, 0)(sumFunctionValue)
    printf("curry Sum is %d \n" , sum1)
    
    //用_分别代替两个参数
    val max3 = injectByCurry(array, 0)(maxFunctionValue(_,_))
    printf("curry Max is %d \n" , max3)
    //用_代替参数列表
    val max4 = injectByCurry(array, 0)(maxFunctionValue _)
    printf("curry Max is %d \n" , max4)
    //可以不使用_，只有方法名
    val max1 = injectByCurry(array, 0)(maxFunctionValue)
    printf("curry Max is %d \n" , max1)
    
    
    // foldLeft() = "/:"
    //函数值放在了大括号里 
    val sum2 = (0 /: array){sumFunctionValue}
    printf("foldLeft Sum is %d\n" , sum2)
    //这里a,b可以不用定义Int，scala会根据上下文推演出类型
    val max2 = (Integer.MIN_VALUE /: array){(a , b) => Math.max(a , b)}
    printf("foldLeft Max is %d\n" , max2)
    
    println("Sum is " + (0 /: array){(a : Int , b : Int) => a + b})
    //下划线(_)可以表示函数值的参数。
    //如果某个参数在函数里仅使用一次，就可以用下划线表示
    //每次在函数里用下划线，都表示随后的参数
    //上面的a和b都只使用一次，可以省略名字
    //第一个_表示一个函数调用过程中持续存在的值
    //第二个_表示数组元素
    println("Sum is " + (0 /: array){ _+_ })
  }
  
  /**
   * 第二参数是一个函数值
   * 参数名codeBlock，类型是一个函数，接收一个Int，返回一个Int
   * 方法本身的结果也是Int
   */
  def totalResultOverRange(number : Int , codeBlock : Int => Int) : Int = {
    var result = 0
    for(i <- 1 to number){
      result += codeBlock(i)
    }
    //显式声明return时必须显式指明返回类型为Int
//    return result
    result
  }
  
  @Test
  def testTotalResultOverRange{
    val number = 100
    //计算和
    println(totalResultOverRange(number, i => i))
    //计算个数
    println(totalResultOverRange(number, i => 1))
    //计算偶数和
    println(totalResultOverRange(number, i => if(i%2 == 0) i else 0))
    //计算偶数的个数
    println(totalResultOverRange(number, i => if(i%2 == 0) 1 else 0))
     //计算奇数和
    println(totalResultOverRange(number, i => if(i%2 == 0) 0 else i))
    //计算奇数的个数
    println(totalResultOverRange(number, i => if(i%2 == 0) 0 else 1))
  }
}