package com.bridgingcode.springbootactivemqdemo.repository;

import com.bridgingcode.springbootactivemqdemo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m from Message m where " +
            "(cast(:startDate as date) is null or m.createDate >= :startDate) and " +
            "(cast(:finishDate as date) is null or m.createDate <= :finishDate) and " +
            "(:message is null or lower(m.message) like lower(concat('%',:message,'%'))) and " +
            "(:queueName is null or lower(m.queueName) like lower(concat('%',:queueName,'%'))) and " +
            "(:messageId is null or m.id=:messageId)"
    )
    List<Message> filter(
            Date startDate,
            Date finishDate,
            Long messageId,
            String message,
            String queueName
    );
}
