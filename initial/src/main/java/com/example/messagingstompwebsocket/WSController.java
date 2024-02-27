package com.example.messagingstompwebsocket;

import com.example.messagingstompwebsocket.dto.Message;
import com.example.messagingstompwebsocket.service.WSService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RestController
public class WSController {
    WSService wsService;
    @Autowired
    public WSController(WSService wsService) {
        this.wsService = wsService;
    }

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Message message) {
        wsService.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-message/{id}")
    public void sendPrivateMessage(@PathVariable String id, @RequestBody Message message) {
        wsService.notifyUser(id, message.getMessageContent());
    }
}
