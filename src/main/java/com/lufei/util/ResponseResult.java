package com.lufei.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * 统一响应格式
 *
 * @author kun.li
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult {
    /**
     * 状态码
     */
    private Integer stateCode;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private Object data;


    public ResponseResult() {
    }

    public ResponseResult(Integer stateCode, String msg, Object data) {
        this.stateCode = stateCode;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult success(Object data) {
        return success("处理成功", data);
    }

    public static ResponseResult success(String msg, Object data) {
        return success(200, msg, data);
    }

    public static ResponseResult success(Integer stateCode, String msg, Object data) {
        return new ResponseResult(stateCode, msg, data);
    }

    public static ResponseResult error() {
        return error("处理失败");
    }

    public static ResponseResult error(String msg) {
        return error(500, msg);
    }

    public static ResponseResult error(Integer stateCode, String msg) {
        return new ResponseResult(stateCode, msg, null);
    }

}
