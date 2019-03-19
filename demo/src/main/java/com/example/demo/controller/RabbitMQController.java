package com.example.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.User;
import com.example.demo.entity.FFResponseModel;
import com.example.demo.serviceImpl.HelloSender1;
import com.example.demo.serviceImpl.HelloSender2;
import com.example.demo.serviceImpl.UserSender;
import com.example.demo.serviceImpl.callBackServiceImpl.CallBackSender;
import com.example.demo.serviceImpl.fanoutServiceImpl.MessageSender1;

import io.swagger.annotations.ApiParam;

@RestController(value = "RabbitMQ测试用的接口")
@RequestMapping(value = "/rabbitqqqq")
public class RabbitMQController {
	private static Logger logger = Logger.getLogger(RabbitMQController.class);
	
	@Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender2 helloSender2;
    @Autowired
    private UserSender userSender;
    @Autowired
    private CallBackSender callbackSender;
    @Autowired
    private MessageSender1 messageSender;
    
    
    @RequestMapping(value = "/oneDirect",name = "一个队列的简单模式",method = RequestMethod.POST)
    public FFResponseModel<String> hello(@RequestParam(value = "any words", defaultValue = "World") String name) {
        helloSender1.send(name);
        return new FFResponseModel("200","ok","给消息加上" + name);
    }
    
    @RequestMapping(value = "/sendeUser", name = "试验发送一个对象消息", method = RequestMethod.POST)
    public FFResponseModel<String> helloUser(@RequestBody  @ApiParam(name="传入对象",value="传入json格式",required=true) User user) {
        userSender.sender(user);
        return new FFResponseModel("200","ok","传入对象" + user.toString());
    }
    
    @RequestMapping(value = "/Topic", name = "以Topic模式发送消息", method = RequestMethod.POST)
    public FFResponseModel<String> topicUser(@RequestParam(value = "any words", defaultValue = "World1") String word1,
    		@RequestParam(value = "any words", defaultValue = "World2") String word2) {
    	helloSender2.send(word1,word2);
        return new FFResponseModel("200","ok","Topic模式" + word1 + word2);
    }
    
    @RequestMapping(value = "/fanoutModel", name = "以fanout模式发送消息", method = RequestMethod.POST)
    public FFResponseModel<String> fanoutSender(@RequestParam(value = "any words", defaultValue = "World")String worldString){
    	return new FFResponseModel("200","ok","fanout模式" + worldString);
    }
    
    @RequestMapping(value = "/callbackModel", name = "带消息返回ACK模式的消息发送", method = RequestMethod.POST)
    public FFResponseModel<String> addCallBackModel(@RequestParam(value = "any words", defaultValue = "World")String worldString){
    	callbackSender.send(worldString);
    	return new FFResponseModel("200","ok","带消息返回ACK模式的消息发送" + worldString);
    }
    
}
