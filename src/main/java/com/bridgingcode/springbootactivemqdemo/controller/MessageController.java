package com.bridgingcode.springbootactivemqdemo.controller;

import com.bridgingcode.springbootactivemqdemo.dto.MessageFindRequest;
import com.bridgingcode.springbootactivemqdemo.dto.MessageSaveRequest;
import com.bridgingcode.springbootactivemqdemo.model.Message;
import com.bridgingcode.springbootactivemqdemo.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
public class MessageController {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private MessageService messageService;


    @PostMapping("/message")
    public ResponseEntity<?> postMessage(@RequestBody MessageSaveRequest messageSaveRequest) {
        if (messageSaveRequest.getMessageId() == null || messageSaveRequest.getQueueName() == null)
            return new ResponseEntity<>(" Id or Queue name can't be null", HttpStatus.BAD_REQUEST);

        if (!messageService.QueueExists(messageSaveRequest.getQueueName()))
            return new ResponseEntity<>(messageSaveRequest.getQueueName() + " queue doesn't exist", HttpStatus.BAD_REQUEST);

        try {
            jmsTemplate.convertAndSend(messageSaveRequest.getQueueName(), messageSaveRequest);

            return new ResponseEntity<>("Sent", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<?> postMessages(@RequestBody List<MessageSaveRequest> messageSaveRequests) {
        try {
            Optional<MessageSaveRequest> errorRequest = messageSaveRequests.parallelStream()
                    .filter(request -> request.getQueueName() == null
                            || request.getMessageId() == null
                            || !messageService.QueueExists(request.getQueueName()))
                    .findFirst();

            if (errorRequest.isPresent())
                return new ResponseEntity<>(errorRequest.get().toString() + " is incorrect", HttpStatus.BAD_REQUEST);

            messageSaveRequests.forEach(request -> jmsTemplate.convertAndSend(request.getQueueName(), request));

            return new ResponseEntity<>("Successfully sent messages", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/messages")
    public List<Message> getMessage(@RequestBody MessageFindRequest messageFindRequest) {
        log.info("Get request {}", messageFindRequest);
        return messageService.MessageFilter(messageFindRequest);
    }
}
