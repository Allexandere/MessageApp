package com.bridgingcode.springbootactivemqdemo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageSaveRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String queueName;
    private String message;
    private Long messageId;
}
