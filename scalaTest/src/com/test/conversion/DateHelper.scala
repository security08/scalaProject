package com.test.conversion

import java.util.{Calendar, Date}

class DateHelper(number: Int) {
  
  def days(when: String): Date = {
    val date = Calendar.getInstance()
    when match {
      case DateHelper.ago => date.add(Calendar.DAY_OF_MONTH, -number)
      case DateHelper.from_now => date.add(Calendar.DAY_OF_MONTH, number)
      case _ =>
    }
    date.getTime()
  }
  
  def hours(when: String): Date = {
    val date = Calendar.getInstance()
    when match {
      case DateHelper.ago => date.add(Calendar.HOUR_OF_DAY, -number)
      case DateHelper.from_now => date.add(Calendar.HOUR_OF_DAY, number)
      case _ =>
    }
    date.getTime()
  }
  
}

//伴生对象
object DateHelper {
  val ago = "ago"
  val from_now = "from_now"
  //隐式转换 implicit type conversions
  implicit def convertInt2DateHelper(number: Int) = new DateHelper(number)
}