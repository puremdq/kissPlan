package com.aojiaoo.core.base;

import com.aojiaoo.core.common.ResponseCode;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.utils.DateUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

public class BaseController {


    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
        });
    }

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
            return ServerResponse.createByError();
        }
    }

}
