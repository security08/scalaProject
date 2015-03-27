package com.test.conversion

import org.junit.Test
import DateHelper._

/**
 * Created by kinwu on 2015/2/10.
 */
class ImplicitConversion {

  @Test
  def implicitConversionTest{
    //must import com.test.conversion.DateHelper._
    val day1 = 2 days ago
    val day2 = 3 days from_now
    println(day1)
    println(day2)
    val day3 = 2 hours ago
    val day4 = 3 hours from_now
    println(day3)
    println(day4)
  }
}
