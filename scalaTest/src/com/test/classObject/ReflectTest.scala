package com.test.classObject

import org.junit.Test

class ReflectTest {

  def method1 {6}
  def method2 = {6}
  def method3() = 6
  def method4() : Double = {6}
  
  def printMethodInfo(method : String){
    printf("The return type of %s is %s \n",method,
        getClass().getDeclaredMethod(method).getReturnType().getName())
  }
  
  @Test
  def methodReturnTypeTest{
    printMethodInfo("method1")
    printMethodInfo("method2")
    printMethodInfo("method3")
    printMethodInfo("method4")
  }
}