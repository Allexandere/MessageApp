package com.bridgingcode.springbootactivemqdemo.service;

import com.bridgingcode.springbootactivemqdemo.dto.MessageFindRequest;
import com.bridgingcode.springbootactivemqdemo.dto.MessageSaveRequest;
import com.bridgingcode.springbootactivemqdemo.model.Message;
import com.bridgingcode.springbootactivemqdemo.repository.MessageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    private final List<String> QUEUES = Arrays.asList("queue1", "queue2", "queue3");

    public boolean QueueExists(String queue) {
        return QUEUES.contains(queue);
    }

    public void SaveMessage(MessageSaveRequest messageSaveRequest) {
        Message message = new Message(messageSaveRequest.getMessageId(),
                messageSaveRequest.getQueueName(),
                messageSaveRequest.getMessage(),
                new Date());
        messageRepository.save(message);
    }

    public List<Message> MessageFilter(MessageFindRequest messageFindRequest){
        return messageRepository.filter(
                messageFindRequest.getStartDate(),
                messageFindRequest.getFinishDate(),
                messageFindRequest.getMessageId(),
                messageFindRequest.getMessage(),
                messageFindRequest.getQueueName()
        );
    }
}
