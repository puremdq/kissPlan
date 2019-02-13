package com.aojiaoo.modules.kissPlan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String input(String message) throws Exception {
        return message;
    }

    @SendTo("/topic/greetings")
    @RequestMapping("sbb")
    public String output() throws Exception {
        return "sbb";
    }


    @GetMapping("/sbb")
    public void roomMessage() {
        this.template.convertAndSend("/topic/greetings", "sdfasdf");
    }

}
