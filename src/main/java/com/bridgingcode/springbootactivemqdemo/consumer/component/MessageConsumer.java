package com.bridgingcode.springbootactivemqdemo.consumer.component;

import com.bridgingcode.springbootactivemqdemo.dto.MessageSaveRequest;
import com.bridgingcode.springbootactivemqdemo.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class MessageConsumer {
    @Autowired
    private MessageService messageService;

    @JmsListener(destination = "queue1")
    public void queue1Listener(MessageSaveRequest messageSaveRequest) {
        messageService.SaveMessage(messageSaveRequest);
        log.info("[MessageConsumer] Message arrived and saved {}", messageSaveRequest);
    }
    @JmsListener(destination = "queue2")
    public void queue2Listener(MessageSaveRequest messageSaveRequest) {
        messageService.SaveMessage(messageSaveRequest);
        log.info("[MessageConsumer] Message arrived and saved {}", messageSaveRequest);
    }
    @JmsListener(destination = "queue3")
    public void queue3Listener(MessageSaveRequest messageSaveRequest) {
        messageService.SaveMessage(messageSaveRequest);
        log.info("[MessageConsumer] Message arrived and saved {}", messageSaveRequest);
    }
}
