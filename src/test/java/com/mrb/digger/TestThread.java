/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;

/**
 *
 * @author MRB
 */
public class TestThread {

    final ThreadLocal<Map<Integer, String>> local = new ThreadLocal<Map<Integer, String>>() {
        @Override
        protected Map<Integer, String> initialValue() {
            return new HashMap<Integer, String>();
        }
    };

    @Test
    public void testGetThreadLocal1() {
        System.out.println(local.get().get(1));
    }

    @Test
    public void testThreadLocal() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(10);     
        long startTime= System.currentTimeMillis();//开始时间  
        for(int i=0;i<50;i++){
            final int num =i;
            new Thread(new Runnable(){
                @Override
                public void run() {
                    local.get().put(num, "test"+num);
                    System.out.println(local.get().get(num));
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("执行总时间:"+(System.currentTimeMillis()-startTime)); 
    }

    @Test
    public void testGetThreadLocal() {
        System.out.println(local.get().get(1));
    }
}
