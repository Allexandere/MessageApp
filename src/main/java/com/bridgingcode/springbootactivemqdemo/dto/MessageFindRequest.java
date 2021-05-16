package com.bridgingcode.springbootactivemqdemo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MessageFindRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String queueName = "";
    private String message = "";
    private Long messageId;
    private Date startDate;
    private Date finishDate;
}
