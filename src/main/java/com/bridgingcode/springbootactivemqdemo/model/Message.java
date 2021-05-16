package com.bridgingcode.springbootactivemqdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
@AllArgsConstructor
public class Message implements Serializable {
    @Id
    private Long id;
    @Column(name = "queue_name")
    private String queueName;
    @Column(name = "message")
    private String message;
    @CreatedDate
    @Column(name = "create_date")
    private Date createDate;

    public Message() {
    }
}
