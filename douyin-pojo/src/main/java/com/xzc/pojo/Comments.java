package com.xzc.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
/***
*   评论表
*/
@ApiModel(value = "评论pojo",description = "评论的实体类")
public class Comments implements Serializable {
        //
        @ApiModelProperty(value = "id",name = "id")
            private String id;
        //
//        @ApiModelProperty(value = "创建时间",name = "")
            private String fatherCommentId;
        //
//        @ApiModelProperty(value = "创建时间",name = "")
            private String toUserId;
        //视频id
        @ApiModelProperty(value = "视频id",name = "videoId")
            private String videoId;
        //留言者，评论的用户id
        @ApiModelProperty(value = "留言者id",name = "fromUserId")
            private String fromUserId;
        //评论内容
        @ApiModelProperty(value = "评论内容",name = "comment")
            private String comment;
        //
    @ApiModelProperty(value = "创建时间",name = "createTime")
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
