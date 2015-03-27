package com.test.spark.streaming

import org.apache.spark.{SparkConf, SparkContext}
import play.api.libs.json.{JsArray, JsObject, JsString, Json}

import scala.collection.mutable.HashMap

/**
 * Created by kinwu on 2015/3/1.
 */
object TagStreaming {
  val split = "#==#"

  def main(args: Array[String]) {
    if (args.length < 1) {
      System.err.println("Usage: TagStreaming <file>")
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("Tag-stat")

    val filePath = args(0)

    //    val ssc = new StreamingContext(conf, Seconds(2))
    //    val input = ssc.textFileStream(filePath)

    val sc = new SparkContext(conf)

    val input = sc.textFile(filePath, 1)

    val parsed = input.map(Json.parse)

    val eachUidJs = parsed.flatMap {
      case ja: JsArray =>ja.value
    }

    val tagMapper = eachUidJs.flatMap{
      case uidJs: JsObject =>
      val uid = uidJs.\("uid").asInstanceOf[JsString].value
      val udid = uidJs.\("_udid").asInstanceOf[JsString].value
      //动作类型， 1-分享 2-收藏 3-评论 4-阅读 5-赞
      val action_type = uidJs.\("action_type").asInstanceOf[JsString].value
      val tags = uidJs \ "tags"

      val map = HashMap.empty[String,HashMap[String,Double]]
        tags match {
          case eachTag:JsArray =>
            var i = 0
            var ratio = 0.0
            // 系数:
            // 收藏  5
            // 分享  3
            // 评论  1
            // 其他  0.5
            action_type match{
              case "1" => ratio = 3
              case "2" => ratio = 5
              case "3" => ratio = 1
              case _ => ratio = 0.5
            }
            val tagMap = HashMap.empty[String,Double]

            for(tag <- eachTag.value){
              tag match{
                case tagStr:JsString =>
                  var baseScore = 0
                  i match{
                    case 0 => baseScore = 8
                    case 1 => baseScore = 6
                    case _ => baseScore = 2
                  }
                  val score = baseScore * ratio
                  tagMap(tagStr.value) = score
//                  map("udid_tag_rank_" + udid + split + tagStr.value) = score
//                  if(!uid.equals("0")){
//                    map("uid_tag_rank_" + uid + split + tagStr.value) = score
//                  }
              }
              i+=1
            }
            map("udid_tag_rank_" + udid) = tagMap
            if(!uid.equals("0")){
              map("uid_tag_rank_" + uid) = tagMap
            }
        }
        map
    }

    val tabReducer = tagMapper.reduce{(a,b) =>
      val value1 = a._2
      val value2 = b._2
      for(x <- value2){
        if(value1.contains(x._1)){
          value1(x._1) = value1(x._1) + x._2
        }else{
          value1(x._1) = x._2
        }
      }
      a
    }



//    ssc.start()
//    ssc.awaitTermination()

  }
}
