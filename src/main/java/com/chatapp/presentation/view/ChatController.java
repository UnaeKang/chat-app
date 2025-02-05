package com.chatapp.presentation.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/view/chat")
    public String chatRoom(@RequestParam String room, @RequestParam String user, Model model) {
        model.addAttribute("userName", user);
        model.addAttribute("roomName", room);
        return "chatRoom";
    }

}
