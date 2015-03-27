package com.test.collection

import org.junit.Test
import scala.collection.immutable.List
import org.junit.Assert

//List comes only in the immutable flavor
//Map与Set有可变和不可变实现，但List只有不变实现
class ListTest {
  val feeds = List("blog.toolshed.com", "pragdave.pragprog.com",
    "dimsumthinking.com/blog")

  def findMax(value : List[Int]) = {
    value.foldLeft(Integer.MIN_VALUE)(Math.max)
  }
  
  //Int * 可变参数
  def max(values : Int *) = values.foldLeft(Integer.MIN_VALUE)(Math.max)
  
  @Test
  def maxTest(){
    val value = List(2,6,5,9,1)
    val max1 =  findMax(value)
    Assert.assertEquals(max1, 9);
    
    val doubleValue = value.map(_ * 2)
    println(value)
    println(doubleValue)
    
    val max2 = max(value : _*)
    Assert.assertEquals(max2, 9);
  }
  

  @Test
  def iteratorTest{
    val list1 : java.util.List[Int] = new java.util.ArrayList[Int]
    var total = 0
    
    (0 until list1.size).foreach(i => total += list1.get(i))
    
//    for(i <- 0 until list1.size){
//      total += list1.get(i)
//    }
    printf("total is %d" , total)
  }
  
  @Test
  def addListTest{
    //java list
    val javalist1 : java.util.List[Int] = new java.util.ArrayList[Int]
    val javalist2 = new java.util.ArrayList[Int]
    javalist1 add 3
    javalist1.add(5)
    println(javalist1)
    
    //scala list
    val list = List("1","2","3")
    Assert.assertEquals(list.head, list(0))
    //在list前即第一个位置添加“4”
    var newlist = "4" :: list
    println(list)
    println(newlist)
    
    val list1 = List("6","7","8")
    //在list前添加list1,也可理解为在list1后面添加list，
    //但“:::”是调用后面list的方法，因为访问头元素比最后一个元素快
    newlist = list1 ::: list
    println(newlist)
  }
  
  @Test
  def filterTest{

    println("Feeds with blog: " + feeds.filter(_ contains "blog").mkString(", "))
    println("All feeds have com: " + feeds.forall(_ contains "com"))
    println("All feeds have dave: " + feeds.forall(_ contains "dave"))
    println("Any feed has dave: " + feeds.exists(_ contains "dave"))
    println("Any feed has bill: " + feeds.exists(_ contains "bill"))

    println("Feed url lengths: " + feeds.map(_.length).mkString(", "))

    val total = feeds.foldLeft(0) { (total, feed) => total + feed.length}
    println("Total length of feed urls: " + total)
    //foldLeft=/: , foldRight=\:
    val total2 = (0 /: feeds) { (total, feed) => total + feed.length}
    println("Total length of feed urls: " + total2)

    val total3 = (0 /: feeds) {
      _ + _.length
    }
    println("Total length of feed urls: " + total3)
  }

  @Test
  def mapTest: Unit = {
    val upper = feeds.map(_.toUpperCase)
    println(upper)

    val (a,b) = (List(1,2,3), List(10,20,30))
    println(a.map(i => i * i))
    println(a flatMap (i => b map (j => i*j)))
    println(a map (i => b map (j => i*j)))

    println(List("abc","def") flatMap (_.toList))
    println(List("abc","def") map (_.toList))


  }

  @Test
  def foldTest: Unit ={
    val a = List(4,1,8,2,6)
    val sum1 = a.fold(0){
      (x,y) =>
        println("x=" + x + ",y=" + y)
        x + y
    }
    println(sum1)

    val sum2 = a.foldLeft(0){
      (x,y) =>
        println("x=" + x + ",y=" + y)
        x + y
    }
    println(sum2)
    val sum = a.foldRight(0){
      (x,y) =>
        println("x=" + x + ",y=" + y)
        x + y
    }
    println(sum)
  }

  @Test
  def sortAndTakeTest: Unit ={
    val a = List(4,1,8,2,6)
    //asc
    println(a.sortBy(x => x))
    //desc
    println(a.sortBy(-_))

    //take top 3
    println(a.sortBy(-_).take(3))

    val words = "The quick brown fox jumped over the lazy dog".split(' ')
    // this works because scala.Ordering will implicitly provide an Ordering[Tuple2[Int, Char]]
    println(words.sortBy(x => (x.length, x.head)).mkString(" "))
  }
}