package com.test.collection

import org.junit.Test

/**
 * Created by kinwu on 2015/3/23.
 */
class SeqTest {

  @Test
  def addTest: Unit ={
    var seq = Seq.empty[(String,Int)]
    val ids = Set("1","2","3")
    ids.foreach {
      id =>
        seq :+= (id,1)
    }
    println(seq)
  }
}
