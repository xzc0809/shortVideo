package com.xzc.pojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
/***
*   举报用户表
*/
@ApiModel(description = "举报用户实体类")
public class UsersReport implements Serializable {

        //
            @ApiModelProperty(name = "id",value = "id",required = true)
            private String id;
        //被举报用户id
        @ApiModelProperty(name = "dealUserId",value = "被举报用户id",required = true)
            private String dealUserId;
        //
        @ApiModelProperty(name = "dealVideoId",value = "被举报视频id",required = true)
            private String dealVideoId;
        //类型标题，让用户选择，详情见 枚举
        @ApiModelProperty(name = "title",value = "标题类型",required = true)
            private String title;
        //内容
        @ApiModelProperty(name = "content",value = "内容",required = true)
            private String content;
        //举报人的id
        @ApiModelProperty(name = "userid",value = "举报人id",required = true)
            private String userid;
        //举报时间
        @JsonIgnore
        @ApiModelProperty(name = "createDate",value = "举报时间",required = false,hidden = true)
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
