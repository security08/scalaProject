package com.test.function

import org.junit.{Assert,Test}

/**
 * Created by kinwu on 2015/3/10.
 */
class PartialFunctionTest {
  //case 是函数PartialFunction的一个子类
    val one: PartialFunction[Int, String] = { case 1 => "one" }
    val two: PartialFunction[Int, String] = { case 2 => "two" }
    val three: PartialFunction[Int, String] = { case 3 => "three" }
    val wildcard: PartialFunction[Int, String] = { case _ => "something else" }

  @Test
  def orElseTest: Unit ={
    val partial = one orElse two orElse three orElse wildcard
    Assert.assertEquals("something else",partial(5))
    Assert.assertEquals("one",partial(1))
    Assert.assertNotEquals("two",partial(1))

    Assert.assertTrue(one.isDefinedAt(1))
    Assert.assertFalse(one.isDefinedAt(2))
  }

  def addUmm(x: String) = x + " umm"

  def addAhem(x: String) = x + " ahem"

  @Test
  def composeTest: Unit ={
    //函数复合 f(g(x))=addAhem(addUmm(x:String))
    val ummThenAhem = addAhem _ compose addUmm _
    Assert.assertEquals("well umm ahem",ummThenAhem("well"))
  }

  @Test
  def andThenTest: Unit ={
    //函数复合 g(f(x))=addUmm(addAhem(x:String))
    val ahemThenUmm = addAhem _ andThen addUmm _
    Assert.assertEquals("well ahem umm",ahemThenUmm("well"))
  }
}
