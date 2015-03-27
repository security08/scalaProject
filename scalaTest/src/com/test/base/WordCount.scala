package com.test.base

import org.apache.spark.{SparkContext}
import SparkContext._

object WordCount {

   def main(args: Array[String]) {
	   if(args.length != 3){
	     println("usage is Udid_Count <master> <input> <output>")
	     return
	   }
	   
	   val sc = new SparkContext(args(0) , "WordCount" ,
	       System.getenv("SPARK_HOME") , Seq(System.getenv("SPARK_JAR")))
	   val textFile = sc.textFile(args(1))
	   val result = textFile.flatMap(line => line.split("\\s+"))
        .map(word => (word, 1)).reduceByKey(_ + _)
	   result.saveAsTextFile(args(2))

   }
}