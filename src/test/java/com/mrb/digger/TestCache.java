/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;

import org.springframework.beans.factory.annotation.Autowired;
import com.whalin.MemCached.MemCachedClient;  
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.springframework.data.redis.core.StringRedisTemplate;
/**
 *
 * @author MRB
 */
public class TestCache extends DiggerApplicationTests {
    @Autowired  
    MemCachedClient memCachedClient;  
  
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    
    @Test  
    public void contextLoads() { 
        long start = System.currentTimeMillis();
       
        for(int i=0;i<10000;i++){ //100w w27.987 w31.337 r26.194 remote 1w w23.5 r25.4
          //memCachedClient.set(i+"", UUID.randomUUID().toString());  
//            System.out.println(String.valueOf(flag));  
            memCachedClient.get(i+"");  
        }
        long end = System.currentTimeMillis();
        System.out.println("总共消耗时间："+(end -start));
    } 
    
    

    @Test
    public void testRedis() {
       //stringRedisTemplate.opsForValue().set("abc", "测试");
       long start = System.currentTimeMillis();
       
        for(int i=0;i<10000;i++){ //100w w34.773 r32575
            stringRedisTemplate.opsForValue().set(i+"", UUID.randomUUID().toString());
          //stringRedisTemplate.opsForValue().get(i+"");
         
        }
        long end = System.currentTimeMillis();
        System.out.println("总共消耗时间："+(end -start));
    }
}
