package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Chat;
import com.Jobxpress.Jobxpress.Repository.ChatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    @GetMapping("/{id}")
    public Chat getById(@PathVariable Integer id) {
        return chatRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Chat create(@RequestBody Chat chat) {
        return chatRepository.save(chat);
    }

    @PutMapping("/{id}")
    public Chat update(@PathVariable Integer id, @RequestBody Chat chat) {
        chat.setIdChat(id);
        return chatRepository.save(chat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        chatRepository.deleteById(id);
    }
}
