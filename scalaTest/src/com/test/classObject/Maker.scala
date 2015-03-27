package com.test.classObject

//构造函数声明为private
class Maker private(val color : String){

  println("Creating " + this)
  
  override def toString : String = "make color " + color
}

//object Maker为class Maker的伴生对象，名称相同(Maker)
object Maker{
  private val makers = Map(
  "red" -> new Maker("red"),
  "blue" -> new Maker("blue"),
  "green" -> new Maker("green")
  )
  
  //定义方法primaryColors()，没有参数，可以忽略括号
  def primaryColors = "red,bule,green"
  
  //相当于静态方法
  def getMaker(color : String) = {
    if(makers.contains(color)) makers(color) else null
  }
  
  //创建伴生对象不用new，直接调用Maker("red")，会调用apply方法
  def apply(color : String) = if(makers.contains(color)) makers(color) else null
  
}