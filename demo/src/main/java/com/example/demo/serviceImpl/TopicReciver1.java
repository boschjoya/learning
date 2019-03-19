package com.example.demo.serviceImpl;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.message")
public class TopicReciver1 {
	@RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessageReceiver  : " +msg);
    }

}
