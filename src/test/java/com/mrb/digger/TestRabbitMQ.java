/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;

import com.mrb.digger.jms.HelloSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MRB
 */
public class TestRabbitMQ extends DiggerApplicationTests {
    
    @Autowired
    private HelloSender helloSender;

    @Test
    public void hello() throws Exception {
        for(int i=0;i<100;i++){
            final int num =i;
            new Thread(new Runnable(){
                @Override
                public void run() {
                    helloSender.send(num+"");
                }
            }).start();
        }
        Thread.sleep(1000);
    }
    
}
