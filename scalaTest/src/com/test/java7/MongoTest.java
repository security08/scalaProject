package com.test.java7;

import com.mongodb.*;
import org.apache.cassandra.utils.OutputHandler;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

/**
 * Created by kinwu on 2015/3/3.
 */
public class MongoTest {
    private MongoClient mongo = null;
    private DB userDB = null;
    private DBCollection userTable = null;
    private DB uidDB = null;
    private DBCollection uidTable = null;
    private String userDBName = "user_tag_stat" ;
    private String userTableName = "user_tag_stat" ;
    private String uidDBName = "uid_tag_stat" ;
    private String uidTableName = "uid_tag_stat" ;

    @Before
    public void init(){
        try {
            mongo = new MongoClient("192.168.0.235",27017);
            //获取temp DB；如果默认没有创建，mongodb会自动创建
            userDB = mongo.getDB(userDBName);
            //获取users DBCollection；如果默认没有创建，mongodb会自动创建
            userTable = userDB.getCollection(userTableName);
            uidDB = mongo.getDB(uidDBName);
            //获取users DBCollection；如果默认没有创建，mongodb会自动创建
            uidTable = userDB.getCollection(uidTableName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @After
    public void clean(){
        if(mongo != null){
            mongo.close();
        }
    }

    @Test
    public void addUser(){
        DBObject user = new BasicDBObject();
        user.put("_udid", "D4DF8FAE-7046-448E-903D-B120E39E67B4");
        user.put("last_update", System.currentTimeMillis());
        JSONArray ja = new JSONArray();
        JSONObject jo = new JSONObject();
        jo.put("score",20);
        jo.put("tag","杨幂");
        ja.add(jo);

        jo = new JSONObject();
        jo.put("score",15);
        jo.put("tag","明星");
        ja.add(jo);

        jo = new JSONObject();
        jo.put("score",10);
        jo.put("tag","素颜");
        ja.add(jo);

        user.put("tag_list",ja);

        userTable.save(user);
    }

    @Test
    public void modifyUser(){
        DBCursor cursor = userTable.find(new BasicDBObject("_udid", "D4DF8FAE-7046-448E-903D-B120E39E67B4"));
        if(cursor.hasNext()){
            DBObject userObj = cursor.next();
            BasicDBList tagList = (BasicDBList)userObj.get("tag_list");
            for(Object obj : tagList){
                BasicDBObject dbo = (BasicDBObject)obj;
                System.out.println(dbo.toString());
            }
        }
    }

    @Test
    public void deleteUser(){
        userTable.remove(new BasicDBObject("_id",new ObjectId("54f7f4bfeea55d5997726c09")));
    }
}
