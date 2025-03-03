package com.example.SpringBoot3.services;

import com.example.SpringBoot3.dto.MessageDTO;
import com.example.SpringBoot3.entities.MessageEntity;
import com.example.SpringBoot3.repositories.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GreetingService {

    String message;
    GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
        message = "Hello World!";
    }


    public String getGreetings(){
        return this.message;
    }

    public MessageDTO saveMessage(MessageDTO message){

        MessageEntity me = new MessageEntity(message.getMessage());

        greetingRepository.save(me);

        MessageDTO dto = new MessageDTO(me.getMessage());

        dto.setId(me.getId());

        return dto;
    }

    public MessageDTO findById(Long id){

        MessageEntity m1 = greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("No messages found with given id"));

        MessageDTO m2 = new MessageDTO(m1.getMessage());
        m2.setId(m1.getId());

        return m2;

    }

    public List<MessageDTO> listAll(){

        List<MessageDTO> list = greetingRepository.findAll().stream().map(entity -> {
                MessageDTO m = new MessageDTO(entity.getMessage());
                m.setId(entity.getId());
                return m;
        }).collect(Collectors.toList());

        return list;
    }

}
