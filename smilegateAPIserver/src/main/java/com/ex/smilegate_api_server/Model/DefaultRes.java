package com.ex.smilegate_api_server.Model;

import com.ex.smilegate_api_server.Util.ResponseMessage;
import com.ex.smilegate_api_server.Util.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * Created by ds on 2018-10-19.
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultRes<T> {

    //Response StatusCode
    private int statusCode;

    //Response Message
    private String responseMessage;

    //Response TestData
    private T responseData;

    public DefaultRes(final HttpStatus statusCode, final String responseMessage) {
        this.statusCode = statusCode.value();
        this.responseMessage = responseMessage;
        this.responseData = null;
    }

    public static<T> DefaultRes<T> res(final int statusCode, final String responseMessage) {
        return res(statusCode, responseMessage, null);
    }

    public static<T> DefaultRes<T> res(final int statusCode, final String responseMessage, final T t) {
        return DefaultRes.<T>builder()
                .responseData(t)
                .statusCode(statusCode)
                .responseMessage(responseMessage)
                .build();
    }

    public static final DefaultRes FAIL_DEFAULT_RES
            = DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);

}