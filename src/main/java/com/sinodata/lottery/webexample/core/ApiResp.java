package com.sinodata.lottery.webexample.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/* 响应信息主体
 *
 * @param <T>
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "响应信息主体")
public class ApiResp<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Integer SUCCESS = 0;
    private static Integer FAIL = 1;

    @Getter
    @Setter
    @ApiModelProperty(value = "返回标记：成功标记=0，失败标记=1")
    private int code;

    @Getter
    @Setter
    @ApiModelProperty(value = "返回信息")
    private String msg;


    @Getter
    @Setter
    @ApiModelProperty(value = "数据")
    private T data;

    public static <T> ApiResp<T> ok() {
        return restResult(null, SUCCESS, null);
    }

    public static <T> ApiResp<T> ok(T data) {
        return restResult(data, SUCCESS, null);
    }

    public static <T> ApiResp<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> ApiResp<T> failed() {
        return restResult(null, FAIL, null);
    }

    public static <T> ApiResp<T> failed(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> ApiResp<T> failed(T data) {
        return restResult(data, FAIL, null);
    }

    public static <T> ApiResp<T> failed(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> ApiResp<T> restResult(T data, int code, String msg) {
        ApiResp<T> apiResult = new ApiResp<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
