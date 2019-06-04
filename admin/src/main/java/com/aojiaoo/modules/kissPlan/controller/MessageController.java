package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.ResponseCode;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.modules.kissPlan.entity.Message;
import com.aojiaoo.modules.kissPlan.service.MessageService;
import com.aojiaoo.utils.IdUtil;
import com.aojiaoo.utils.UserUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 用户发送消息
     **/
    @MessageMapping("/msg")
    public void sendMsg(Message message, Principal principal) {
        messageService.sendMessage(simpMessagingTemplate, message, principal);
    }

    @ResponseBody
    @GetMapping("/getUnreadNum")
    public ServerResponse getUnreadNum() {
        return ServerResponse.createBySuccess(messageService.getUnreadNum(UserUtil.getCurrentUserId()));
    }


    @ResponseBody
    @GetMapping("/messageList")
    public ServerResponse messageList(Integer partnerId, Date maxSendTime) {
        if (IdUtil.isInValidId(partnerId) || partnerId.equals(UserUtil.getCurrentUserId())) {
            //非法参数
            return ServerResponse.createByResponseCode(ResponseCode.ILLEGAL_ARGUMENT);
        }

        return ServerResponse.createBySuccess(messageService.messageList(partnerId, UserUtil.getCurrentUserId(), maxSendTime));
    }


    /**
     * 得到当前用户的消息列表预览
     *
     * @return ServerResponse
     */
    @ResponseBody
    @GetMapping("/preview")
    public ServerResponse messagePreview() {
        return ServerResponse.createBySuccess(messageService.messagePreview(UserUtil.getCurrentUserId()));
    }


}
