package com.test.collection

import org.junit.Test
//Set have mutable and immutable implementations
//Map与Set有可变和不可变实现，但List只有不变实现
class SetTest {

  @Test
  def test1{
    val set1 = Set("red" , "blue" , "green")
    var set2 = set1
    println("set1=set2:" + set1)
    
    //修改set2，添加元素
    set2 += "black"
    //set1未改变
    println("set1:" + set1)
    println("set2:" + set2)
  }
  
  @Test
  def filterTest{
    val feeds1 = Set("blog.toolshed.com", "pragdave.pragprog.com",
	"pragmactic-osxer.blogspot.com", "vita-contemplativa.blogspot.com")
	val feeds2 = Set("blog.toolshed.com", "martinfowler.com/bliki")
	
	val blogSpotFeeds = feeds1 filter ( _ contains "blogspot" )
    println("blogspot feeds: " + blogSpotFeeds.mkString(", "))
    
    val mergedSet = feeds1 ++ feeds2
    println("mergedSet: " + mergedSet.mkString(", "))
    
    val mergedSet2 = feeds1 | feeds2
    println("mergedSet2: " + mergedSet2.mkString(", "))
    
    val commonSet = feeds1 & feeds2
    println("commonSet: " + commonSet.mkString(", "))
    
    val urls = feeds1 map ("http://" + _)
    println(urls.toArray)
    urls foreach {url => println(url)}
  }
}