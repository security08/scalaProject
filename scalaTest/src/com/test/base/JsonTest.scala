package com.test.base

import org.junit.Test
import play.api.libs.json.{JsString, JsObject}

/**
 * Created by kinwu on 2015/3/25.
 */
class JsonTest {

  @Test
  def removeTest: Unit ={
    val removingKey = "k1,k2,k5".split(",")
    var json = JsObject(Seq(
      "k1" -> JsString("v1"),
      "k2" -> JsString("v2"),
      "k3" -> JsString("v3"),
      "k4" -> JsString("v4"),
      "k5" -> JsString("v5")
       )
      )

    removingKey.foreach{
      key => json -= key
    }

    println(json)
  }
}
