package com.example.messagingstompwebsocket.controller;

import com.example.messagingstompwebsocket.dto.Message;
import com.example.messagingstompwebsocket.dto.ResponseMessage;
import com.example.messagingstompwebsocket.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {
    private final NotificationService notificationService;

    @Autowired
    public MessageController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(Message message) throws InterruptedException{
        Thread.sleep(1000);
        notificationService.sendGlobalNotification();
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    /**
     * https://www.youtube.com/watch?v=LdQY-OUM2mk&t=141s&ab_channel=LiliumCode
     */
    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(Message message,
                                             final Principal principal) throws InterruptedException{
        Thread.sleep(1000);
        notificationService.sendPrivateNotification(principal.getName());
        return new ResponseMessage(HtmlUtils.htmlEscape("Sending private message to user: "+principal.getName()+ " "+message.getMessageContent()));
    }
}
