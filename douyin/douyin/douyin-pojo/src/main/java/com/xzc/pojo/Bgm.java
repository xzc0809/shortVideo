package com.xzc.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   
*/
public class Bgm implements Serializable {
        //
            private String id;
        //
            private String author;
        //
            private String name;
        //播放地址
            private String path;
        //get set 方法
            public void setId (String  id){
                this.id=id;
            }
            public  String getId(){
                return this.id;
            }
            public void setAuthor (String  author){
                this.author=author;
            }
            public  String getAuthor(){
                return this.author;
            }
            public void setName (String  name){
                this.name=name;
            }
            public  String getName(){
                return this.name;
            }
            public void setPath (String  path){
                this.path=path;
            }
            public  String getPath(){
                return this.path;
            }
}
