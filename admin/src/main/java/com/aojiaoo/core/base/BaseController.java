package com.aojiaoo.core.base;

import com.aojiaoo.core.common.ResponseCode;
import com.aojiaoo.core.common.ServerResponse;

public class BaseController {


    /**
     * @param object 要返回的参数
     * @return 如果参数为空则返回404 否则则用ServerResponse包装这个返回参数
     */
    protected ServerResponse createServerResponseNotFoundOrSuccess(Object object) {
        if (object == null) {
            return ServerResponse.createByResponseCode(ResponseCode.NOT_FOUND);
        }
        return ServerResponse.createBySuccess(object);
    }


    protected ServerResponse createServerResponse(boolean tf) {
        if (tf) {
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByErrorMessage("操作失败了,请稍后再试");

        }
    }
}
