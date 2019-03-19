package com.example.demo.serviceImpl.fanoutServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender1 {
	@Autowired
    private AmqpTemplate rabbitTemplate;
	
	private static Logger logger = Logger.getLogger(MessageSender1.class);
 
    public void send(String s) {
        String msgString="fanoutSender :hello i am hzb" + s;
        logger.info(msgString);
        this.rabbitTemplate.convertAndSend("fanoutExchange","abcd.ee", msgString);
    }

}
