package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.modules.kissPlan.entity.Message;
import com.aojiaoo.modules.kissPlan.service.MessageService;
import com.aojiaoo.utils.UserUtil;
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
    public ServerResponse messageList(Date maxSendTime) {
        return  ServerResponse.createBySuccess(messageService.messageList(UserUtil.getCurrentUserId(), maxSendTime));
    }


}
