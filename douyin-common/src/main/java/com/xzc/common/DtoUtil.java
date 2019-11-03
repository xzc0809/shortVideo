package com.xzc.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于返回Dto的工具类
 * Created by XX on 17-5-8.
 */
public class DtoUtil {

    public static String success = "true";

    public static String fail = "false";

    public static String errorCode = "0000";

    /***
     * 统一返回成功的DTO
     */
    public static Dto returnSuccess() {
        Dto dto = new Dto();
        dto.setSuccess(success);
        return dto;
    }

    /***
     * 统一返回成功的DTO 带数据
     */
    public static Dto returnSuccess(String message, Object data) {
        Dto dto = new Dto();
        dto.setSuccess(success);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        dto.setData(data);
        return dto;
    }

    /**
     * @param message
     * @param data
     * @param total(总条数)
     * @param pageSize(每页行数)
     * @param pageCount(总页)
     * @return
     */
    public static Dto returnSuccess(String message, Object data, Integer total, Integer pageSize, Integer pageCount) {
        Dto dto = new Dto();
        dto.setSuccess(success);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        dto.setData(data);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("pageSize", pageSize);
        map.put("pageCount", pageCount);
        dto.setPage(map);
        return dto;
    }

    /***
     * 统一返回成功的DTO 不带数据
     */
    public static Dto returnSuccess(String message) {
        Dto dto = new Dto();
        dto.setSuccess(success);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        return dto;
    }

    /***
     * 统一返回成功的DTO 带数据 没有消息
     */
    public static Dto returnDataSuccess(Object data) {
        Dto dto = new Dto();
        dto.setSuccess(success);
        dto.setErrorCode(errorCode);
        dto.setData(data);
        return dto;
    }

    public static Dto returnFail(String message, String errorCode) {
        Dto dto = new Dto();
        dto.setSuccess(fail);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        return dto;
    }
}
