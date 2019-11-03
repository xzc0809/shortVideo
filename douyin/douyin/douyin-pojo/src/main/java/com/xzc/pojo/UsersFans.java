package com.xzc.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   用户粉丝关联关系表
*/
public class UsersFans implements Serializable {
        //
            private String id;
        //用户
            private String userId;
        //粉丝
            private String fanId;
        //get set 方法
            public void setId (String  id){
                this.id=id;
            }
            public  String getId(){
                return this.id;
            }
            public void setUserId (String  userId){
                this.userId=userId;
            }
            public  String getUserId(){
                return this.userId;
            }
            public void setFanId (String  fanId){
                this.fanId=fanId;
            }
            public  String getFanId(){
                return this.fanId;
            }
}
