package com.grepiu.www.process.common.api.controller;

import com.grepiu.www.process.common.api.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * Web Socket 컨트롤러
 *
 */
@Controller
public class WebSocketController {

    /**
     *
     * Web소켓
     * Ref : https://spring.io/guides/gs/messaging-stomp-websocket/
     *
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Object greeting(Message message) throws Exception {
        Thread.sleep(500);
        return message;
    }

}
