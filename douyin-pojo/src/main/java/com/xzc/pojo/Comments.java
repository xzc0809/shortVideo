package com.xzc.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   课程评论表
*/
public class Comments implements Serializable {
        //
            private String id;
        //
            private String fatherCommentId;
        //
            private String toUserId;
        //视频id
            private String videoId;
        //留言者，评论的用户id
            private String fromUserId;
        //评论内容
            private String comment;
        //
            private Date createTime;
        //get set 方法
            public void setId (String  id){
                this.id=id;
            }
            public  String getId(){
                return this.id;
            }
            public void setFatherCommentId (String  fatherCommentId){
                this.fatherCommentId=fatherCommentId;
            }
            public  String getFatherCommentId(){
                return this.fatherCommentId;
            }
            public void setToUserId (String  toUserId){
                this.toUserId=toUserId;
            }
            public  String getToUserId(){
                return this.toUserId;
            }
            public void setVideoId (String  videoId){
                this.videoId=videoId;
            }
            public  String getVideoId(){
                return this.videoId;
            }
            public void setFromUserId (String  fromUserId){
                this.fromUserId=fromUserId;
            }
            public  String getFromUserId(){
                return this.fromUserId;
            }
            public void setComment (String  comment){
                this.comment=comment;
            }
            public  String getComment(){
                return this.comment;
            }
            public void setCreateTime (Date  createTime){
                this.createTime=createTime;
            }
            public  Date getCreateTime(){
                return this.createTime;
            }
}
