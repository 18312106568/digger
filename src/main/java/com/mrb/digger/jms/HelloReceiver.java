/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.jms;

import com.mrb.digger.entity.TestEntity;
import com.mrb.digger.service.TestService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MRB
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @Autowired
    TestService testService;
    
    @RabbitHandler
    public void process(String hello) {
        System.out.println("receiver :"+hello);
        testService.testTestEntity(Integer.parseInt(hello));
    }

}
