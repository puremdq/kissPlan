package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.modules.kissPlan.entity.Message;
import com.aojiaoo.modules.kissPlan.service.MessageService;

import com.aojiaoo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/msg")
    public void sendMsg(Message message) {
        if (!UserUtil.isAuthenticated()) {
            return;
        }
        messageService.save(message);
    }



    @RequestMapping("sbb")
    @SendTo("/topic/greetings")
    public String output() throws Exception {
        return "sbb";
    }


    @GetMapping("/sbb")
    public void roomMessage() {
        this.simpMessagingTemplate.convertAndSend("/topic/greetings", "sdfasdf");
    }
}
