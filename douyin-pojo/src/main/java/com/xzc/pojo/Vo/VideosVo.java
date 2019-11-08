package com.xzc.pojo.Vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/***
 *   视频信息表
 */
@ApiModel(value = "VideosVo", description = "视频vo类")
public class VideosVo implements Serializable {
    //
    private String id;
    //发布者id
    private String userId;
    //用户使用音频的信息
    private String audioId;
    //视频描述
    private String videoDesc;
    //视频存放的路径
    private String videoPath;
    //视频秒数
    private Float videoSeconds;
    //视频宽度
    private Integer videoWidth;
    //视频高度
    private Integer videoHeight;
    //视频封面图
    private String coverPath;
    //喜欢/赞美的数量
    private Long likeCounts;
    //视频状态：
//1、发布成功
//2、禁止播放，管理员操作
    private Integer status;
    //创建时间
    private Date createTime;

    private String faceImage;
    private String nickname;

    //get set 方法
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    public String getAudioId() {
        return this.audioId;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoDesc() {
        return this.videoDesc;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoPath() {
        return this.videoPath;
    }

    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    public Float getVideoSeconds() {
        return this.videoSeconds;
    }

    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    public Integer getVideoWidth() {
        return this.videoWidth;
    }

    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    public Integer getVideoHeight() {
        return this.videoHeight;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getCoverPath() {
        return this.coverPath;
    }

    public void setLikeCounts(Long likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Long getLikeCounts() {
        return this.likeCounts;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
