package com.test.base

import org.junit.Test

/**
 * Created by kinwu on 2015/3/6.
 */
class LongTest {

  @Test
  def scoreTest: Unit ={
    val lastUpdate = 1425511377
    val timestamp = System.currentTimeMillis() / 1000
    val exp = if(timestamp - lastUpdate > 0) Math.max(Math.round((timestamp - lastUpdate)/86400),0) else 0
    println(exp)
  }
}
