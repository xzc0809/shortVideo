package com.xzc.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   视频搜索的记录表
*/
public class SearchRecords implements Serializable {
        //
            private String id;
        //搜索的内容
            private String content;
        //get set 方法
            public void setId (String  id){
                this.id=id;
            }
            public  String getId(){
                return this.id;
            }
            public void setContent (String  content){
                this.content=content;
            }
            public  String getContent(){
                return this.content;
            }
}
