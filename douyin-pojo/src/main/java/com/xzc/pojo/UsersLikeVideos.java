package com.xzc.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   用户喜欢的/赞过的视频
*/
public class UsersLikeVideos implements Serializable {
        //
            private String id;
        //用户
            private String userId;
        //视频
            private String videoId;
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
            public void setVideoId (String  videoId){
                this.videoId=videoId;
            }
            public  String getVideoId(){
                return this.videoId;
            }
}
