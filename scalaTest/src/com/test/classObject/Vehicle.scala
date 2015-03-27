package com.test.classObject

abstract class Vehicle (val year : Int , private var miles : Int){
  //在类定义中任何表达式或可执行语句都会作为主构造函数的一部分执行
  printf("constructing Vehicle: year %d , miles %d.\n" , year ,miles)
  
   //重写必须使用override
  override def toString() : String = {
    "year " + year + ",miles " + miles 
  }
}