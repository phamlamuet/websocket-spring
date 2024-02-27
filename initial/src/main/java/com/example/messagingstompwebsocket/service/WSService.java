package com.example.messagingstompwebsocket.service;

import com.example.messagingstompwebsocket.dto.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSService {

    private SimpMessagingTemplate messagingTemplate;
    private NotificationService notificationService;

    public WSService(SimpMessagingTemplate messagingTemplate, NotificationService notificationService) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }


    public void notifyFrontend(String message) {
        ResponseMessage response = new ResponseMessage(message);
        notificationService.sendGlobalNotification();

        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(String id, String message) {
        ResponseMessage response = new ResponseMessage(message);
        notificationService.sendPrivateNotification(id);
        messagingTemplate.convertAndSendToUser(id,"/topic/private-messages", response);
    }





}
