package com.jin.blog.controller;

import com.jin.blog.dto.ChatDto.ChatRequest;
import com.jin.blog.dto.ChatDto.ChatResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

  @MessageMapping("/receive")
  @SendTo("/chat/public")
  public ChatResponse SocketHandler(ChatRequest req) {
    ChatResponse res = new ChatResponse(req.getUsername(), req.getMessage());
    return res;
  }
}
