package com.xzc.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   举报用户表
*/
public class UsersReport implements Serializable {
        //
            private String id;
        //被举报用户id
            private String dealUserId;
        //
            private String dealVideoId;
        //类型标题，让用户选择，详情见 枚举
            private String title;
        //内容
            private String content;
        //举报人的id
            private String userid;
        //举报时间
            private Date createDate;
        //get set 方法
            public void setId (String  id){
                this.id=id;
            }
            public  String getId(){
                return this.id;
            }
            public void setDealUserId (String  dealUserId){
                this.dealUserId=dealUserId;
            }
            public  String getDealUserId(){
                return this.dealUserId;
            }
            public void setDealVideoId (String  dealVideoId){
                this.dealVideoId=dealVideoId;
            }
            public  String getDealVideoId(){
                return this.dealVideoId;
            }
            public void setTitle (String  title){
                this.title=title;
            }
            public  String getTitle(){
                return this.title;
            }
            public void setContent (String  content){
                this.content=content;
            }
            public  String getContent(){
                return this.content;
            }
            public void setUserid (String  userid){
                this.userid=userid;
            }
            public  String getUserid(){
                return this.userid;
            }
            public void setCreateDate (Date  createDate){
                this.createDate=createDate;
            }
            public  Date getCreateDate(){
                return this.createDate;
            }
}
