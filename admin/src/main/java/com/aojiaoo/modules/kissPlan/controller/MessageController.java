package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.modules.kissPlan.entity.Message;
import com.aojiaoo.modules.kissPlan.service.MessageService;

import com.aojiaoo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;

    /*用户发送消息*/
    @MessageMapping("/msg")
//    @SendToUser("/receive/greetings")
    public void sendMsg(Message message, Principal principal) {
        message.setFrom(Integer.valueOf(principal.getName()));
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getTo()), "/receive/msg", message);
        message.setIsRead(GlobalProperties.TRUE);
//        messageService.save(message);
    }


    /**
     * 用户接受消息
     */
    @GetMapping("/receive")
    public void roomMessage() {
//        simpMessagingTemplate.convertAndSendToUser();
        Message message = new Message();
        message.setTo(UserUtil.getCurrentUserId());
        message.setIsRead(0);
        List<Message> messageList = messageService.findList(message);
        this.simpMessagingTemplate.convertAndSend("/", messageList);
    }


    @GetMapping("/test")
    public void test() {
        this.simpMessagingTemplate.convertAndSend("/receive", "hahaha");
    }

    @GetMapping("/test1")
    public void test1() {
        try {
            simpMessagingTemplate.convertAndSendToUser("1", "/receive", "hahaha");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
