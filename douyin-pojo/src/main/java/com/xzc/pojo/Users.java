package com.xzc.pojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
/***
*   
*/
@ApiModel(value = "user对象",description = "user对象")
public class Users implements Serializable {
        //
        @ApiModelProperty(hidden = true)
            private String id;
        //用户名
        @ApiModelProperty(value = "用户名",name = "username",required = true)
            private String username;
        //密码

        @ApiModelProperty(value = "密码",name = "password",required = true)
            private String password;
        //我的头像，如果没有默认给一张
        @ApiModelProperty(hidden = true)
            private String faceImage;
        //昵称
        @ApiModelProperty(value = "昵称",name = "nickname")
            private String nickname;
        //我的粉丝数量
        @ApiModelProperty(hidden = true)
            private Integer fansCounts;
        //我关注的人总数
        @ApiModelProperty(hidden = true)
            private Integer followCounts;
        //我接受到的赞美/收藏 的数量
        @ApiModelProperty(hidden = true)
            private Integer receiveLikeCounts;
        //get set 方法
            public void setId (String  id){
                this.id=id;
            }
            public  String getId(){
                return this.id;
            }
            public void setUsername (String  username){
                this.username=username;
            }
            public  String getUsername(){
                return this.username;
            }
            public void setPassword (String  password){
                this.password=password;
            }
            public  String getPassword(){
                return this.password;
            }
            public void setFaceImage (String  faceImage){
                this.faceImage=faceImage;
            }
            public  String getFaceImage(){
                return this.faceImage;
            }
            public void setNickname (String  nickname){
                this.nickname=nickname;
            }
            public  String getNickname(){
                return this.nickname;
            }
            public void setFansCounts (Integer  fansCounts){
                this.fansCounts=fansCounts;
            }
            public  Integer getFansCounts(){
                return this.fansCounts;
            }
            public void setFollowCounts (Integer  followCounts){
                this.followCounts=followCounts;
            }
            public  Integer getFollowCounts(){
                return this.followCounts;
            }
            public void setReceiveLikeCounts (Integer  receiveLikeCounts){
                this.receiveLikeCounts=receiveLikeCounts;
            }
            public  Integer getReceiveLikeCounts(){
                return this.receiveLikeCounts;
            }
}
