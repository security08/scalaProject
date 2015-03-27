package com.test.classObject

import org.junit.Test
import com.test.traits.CriminalRecordCheck
import com.test.traits.Check
import com.test.traits.CreditCheck
import com.test.traits.EmploymentCheck

class ClassTest {

  @Test
  def carTest {
    val car = new Car(2015, 100)
    println("car made in " + car.year)
    println("car driven 200 miles")
    car.drive(200)

    var car2 = new Car(2014)
    println("car made in " + car2.year)
    car2.setPosition("gz")
    println(car2)

    val cars = Array(car, car2)
    car.playWithVehicle(cars)

    var cars1 = List(Car(2010, 2000), Car(2011, 1000), Car(2012, 500))
    var years = for (car <- cars1; year = car.year) yield year
    println(years.mkString(","))

    val miles = for (car <- cars1) yield car.miles
    println(miles.mkString(","))
  }

  @Test
  def makerTest {
    println(Maker.getMaker("red"))
    println(Maker.getMaker("blue"))
    println(Maker.getMaker("white"))
    println(Maker.getMaker("blue"))
    //调用apply方法
    println(Maker("blue"))
    println(Maker.primaryColors)
    //constructor Maker in class Maker cannot be accessed in class ClassTest
    //new Maker
  }

  @Test
  def resourceTest {
    Resource.use { resource =>
      resource.op1
      resource.op2
      resource.op3
      resource.op1
    }
  }


  @Test
  def checkTest {
    //从右至左调用check
    val apartmentApplication = new Check with CreditCheck with CriminalRecordCheck
    println(apartmentApplication check)

    val emplomentApplication = new Check with CriminalRecordCheck with EmploymentCheck
    println(emplomentApplication.check)
  }
}

