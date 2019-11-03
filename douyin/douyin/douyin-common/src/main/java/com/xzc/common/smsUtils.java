package com.xzc.common;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
/**
 * @Author xiaozhichao
 * @Description 短信接口工具
 * @Date 15:28 2019/8/21
 * @Param * @param null
 * @return 
 **/
public class smsUtils {
    public static Map<String,Object> sendSms(String mobilePhone,int code,String TemplateCode) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");//密钥从阿里云控制台获取
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FpWMvPyQKfAFHuwpLw9", "YL4Zv1jX28Ud5wwlysr3Wvu2p8bzSF");//密钥从阿里云控制台获取
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FcWzWwJ1WeMcHXuBhTd", "XIT9F8avNCpfgz9nCQ5xnrCSDFIUvV");//密钥从阿里云控制台获取

        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //随机生成四位验证码

        String code1= "{\"code\":" + code + "}";
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobilePhone);//手机号码从前端获取
        request.putQueryParameter("SignName", "优优二手车");//签名 可修改
        request.putQueryParameter("TemplateCode", TemplateCode);//模板id 可修改
        request.putQueryParameter("TemplateParam", code1);

        Map<String,Object> data=new HashMap<>();
        try {
            CommonResponse response = client.getCommonResponse(request);
            data.put("responseData",response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static int getCode(){
        int code =  (int) ((Math.random() * 9 + 1) * 1000);
        return code;
    }


    /**
     * java生成随机数字10位数
     *
     * @param "length[生成随机数的长度]"
     * @return
     *     
     */
    public static String getRandomNickname(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }
}