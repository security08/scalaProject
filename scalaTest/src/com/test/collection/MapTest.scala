package com.test.collection

import org.junit.Test


import scala.collection.immutable.{TreeMap, List}
import scala.collection.mutable
import scala.collection.parallel.immutable

//Map have mutable and immutable implementations
//Map与Set有可变和不可变实现，但List只有不变实现
class MapTest {
  val feeds = Map("Andy Hunt" -> "blog.toolshed.com",
    "Dave Thomas" -> "pragdave.pragprog.com",
    "Dan Steinberg" -> "dimsumthinking.com/blog")

  @Test
  def filterTest {
    val filterKey = feeds filterKeys (_ startsWith ("D"))
    println(filterKey)

    //返回Option[T]
    println(feeds get "Andy Hunt")
    //调用apply()，如果没有值则抛出异常
    println(feeds("Andy Hunt"))

    val filterNameStartWithDAndBlogInFeed = feeds filter { element =>
      val (key, value) = element
      (key startsWith "D") && (value contains "blog")
    }
    println("# of feeds with auth name D* and blog in URL: " +
      filterNameStartWithDAndBlogInFeed.size)

  }

  @Test
  def updateTest {
    //update，返回新的map，不修改旧的feeds
    val newFeeds = feeds.updated("newkey", "newValue")
    println(feeds)
    println(newFeeds)

    //mutable map
    val mutableFeeds = scala.collection.mutable.Map(
      "Scala Book Forum" -> "forums.pragprog.com/forums/87")
    mutableFeeds.update("newkey", "newvalue")
    mutableFeeds("Groovy Book Forum") = "forums.pragprog.com/forums/55"
    println("Number of forums: " + mutableFeeds.size)
    println(mutableFeeds)

    val numbers = collection.mutable.Map("1" -> 2,"3" -> 4)
    println(numbers.get("1"))
    println(numbers.getOrElse("1", 3))
    //存在键为“1”的值，直接返回：2
    println(numbers.getOrElseUpdate("1", 3))
    //不存在键为“2”的值，返回默认值：0
    println(numbers.getOrElse("2", 0))
    //不存在键为“2”的值，则保存"2" -> 3，并返回3
    println(numbers.getOrElseUpdate("2", 3))

    println(numbers)


  }

  @Test
  def mapTest: Unit ={
    println(Map("a" -> List(11,111), "b" -> List(22,222)).flatMap(_._2))
    println(Map("a" -> List(11,111), "b" -> List(22,222)).map(_._2))

    //The type of the resulting collection is guided by the static type of list.
    // This might cause unexpected results sometimes.
    println(Map("a" -> List(1 -> 11,1 -> 111), "b" -> List(2 -> 22,2 -> 222)).flatMap(_._2))
    println(Map("a" -> List(1 -> 11,1 -> 111), "b" -> List(2 -> 22,2 -> 222)).map(_._2))
  }

  @Test
  def sortByValueTest: Unit ={
    val grades1 = scala.collection.mutable.HashMap("Kim" -> 10,"Bush" -> 60,"Ken" -> 90,"Hannah" -> 6)
    val grades2 = scala.collection.mutable.HashMap("Kim" -> 90,"Al" -> 85,"Melissa" -> 95,"Emily" -> 91,"Hannah" -> 92)
    val sum = grades2 ++ grades1.map{
      case (k,v) =>
        val value = grades2.getOrElse(k,0)
        k -> (v + value)
    }
    sum.toSeq.sortBy(-_._2).take(5).foreach(println)
  }

  @Test
  def valueTest: Unit ={
    val map = scala.collection.mutable.Map("Kim" -> 40,"Bush" -> 60,"Ken" -> 90,"Hannah" -> 6)
    println(map.values.toSeq.sortBy(-_).take(3).sum/3)
  }
}