package com.regain.product.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebsocketController {
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public String sendMessage(@Payload String message) {
        System.out.println(message);
        return message; // Echo the message back
    }

    @ExceptionHandler(Exception.class)
    @SendTo("/topic/errors")
    public String handleException(Exception ex) {
        return "Error: " + ex.getMessage();
    }

}
