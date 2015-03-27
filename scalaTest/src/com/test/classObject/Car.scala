package com.test.classObject

import scala.beans.BeanProperty

//默认访问修饰符为public，java默认为包级
//year为public不可修改，miles为private可修改
class Car(override val year : Int , var miles : Int)
						extends Vehicle(year,miles){
  
  //在类定义中任何表达式或可执行语句都会作为主构造函数的一部分执行
  printf("constructing Car: year %d , miles %d.\n" , year ,miles)
  
  //自动生成getter,setter，字段必须为非private
  @scala.reflect.BeanProperty
  var position : String = _
  
  //year不能再用val或var修饰
  def this(year : Int){
    this(year,0)
  }
  
  //Vehicle是T的超类型
  def playWithVehicle[T <: Vehicle](vehicle : Array[T]){
     //do something
    printf("playWithVehicle:" , vehicle.mkString(","))
  }
  
  def copyVehicle[S,D >: S](fromVehicle : Array[S],toVehicle : Array[D]){
    //do something
  }
  
  //distance不能再用val或var修饰
  def drive(distance : Int){
    miles += Math.abs(distance)
    position = distance.toString
  }
  
  //重写必须使用override
  override def toString() : String = {
    "year " + year + ",miles " + miles + ",position " + position
  }
  
  
}

object Car{
  def apply(year : Int , miles : Int) : Car = {
    new Car(year,miles)
  }
}