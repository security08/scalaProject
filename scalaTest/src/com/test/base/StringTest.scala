package com.test.base

import org.junit.{Assert, Test}

/**
 * Created by kinwu on 2015/3/4.
 */
class StringTest {

  @Test
  def extractTest: Unit ={
    val uidRex = """app_id":(\d+)""".r
    val line = """{"app_id":660,"date":"2015-03-03 12:04:58","pk":"54f519249490cbcd35000078"}"""
    println(uidRex.findFirstMatchIn(line).get.group(1))


    val regex = "(ud?id)_(.+)".r
//    val regex(uid,value) = "udid_VQRxeq6vCLbr2uy6W8tAE68HVyA="
    val regex(uid,value) = "uid_10393261"
    uid match {
      case "uid" => println("uid")
      case "udid" => println("udid")
      case _ => println("not match")
    }
    println(value)
  }


  @Test
  def splitTest: Unit ={
    val str = ",1,2,3,".split(",").filter(_.length > 0).mkString(",")
    Assert.assertEquals("1,2,3",str)
  }
}
