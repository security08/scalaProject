package com.test.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by kinwu on 2015/1/30.
 * ./bin/spark-submit \
  --class <main-class>
  --master <master-url> \
  --deploy-mode <deploy-mode> \
  --conf <key>=<value> \
  ... # other options
  <application-jar> \
  [application-arguments]
 */
object WordCountApp {

  def main(args: Array[String]) {
    println("Start submit application to spark")
    if(args.length != 1){
      println("usage is UdidWordCountApp oryx generation <generationID>")
      return
    }
    println("Main arguments are: " + args(0))
    val idMappingPath = "/input/oryx/" + args(0) + "/idMapping/*"
    println("The path is " + idMappingPath)
    val conf = new SparkConf().setAppName("UdidWordCountApp")
    val sc = new SparkContext(conf)
    val file = sc.textFile(idMappingPath)
    val linesWithUdid = file.filter(line => line.contains("udid_"))
    println("There are " + linesWithUdid.count + " lines contain udid in " + idMappingPath)
  }
}
