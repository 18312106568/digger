/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;

/**
 *
 * @author MRB
 */
@Slf4j
public class TestRequest {

    static BlockingQueue blockingQueue = new ArrayBlockingQueue<>(1);
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 100, 0, TimeUnit.NANOSECONDS, blockingQueue);
    
    @Test
    public void testThreadPoolExecutor() {
        
        Map<Integer, String> map = new Hashtable<>();
        try {
            int LOOP = 1000;
            int index = 1;
            while (index<LOOP) {
                OkHttpClient client = new OkHttpClient();
                final int key =  index;
                Runnable runable = new Runnable() {
                    @Override
                    public void run() {
                        MediaType mediaType = MediaType.parse("application/json");
                        RequestBody body = RequestBody.create(mediaType, "{\n\t\"COOP_CODE\":\"GMY000\",\n\t\"ORG_CODE\":\"556230444\",\n\t\"TOKEN\":\"424117075d785e1a981c829658b72762\",\n\t\"PRODUCT_ID\":\"12972F86-62FA-4503-B6CB-E6F7D75BA4A5\",\n\t\"REQ_SERNO\":\"201805221011\",\n\t\"SIGNATURE\":\"123\"\n}");
                        Request request = new Request.Builder()
                                .url("http://43.254.44.215:8091/coop/product/list")
                                .post(body)
                                .addHeader("content-type", "application/json")
                                .addHeader("cache-control", "no-cache")
                                .addHeader("postman-token", "9038099d-cd4d-0d90-4c48-fda80d672a61")
                                .build();
                        try {
                            Response response = client.newCall(request).execute();
                            System.out.println(response.body().string() + key);
                        } catch (IOException ex) {
                            System.out.println(ex.toString());
                        }
                    }
                };
               
                if(executor.getActiveCount()<executor.getMaximumPoolSize()||executor.prestartCoreThread()){
                    executor.submit(runable);
                    index++;
                }else{
                    System.out.println("当前线程数"+executor.getActiveCount());
                    Thread.sleep(1000);
                }
            }
            long now = System.currentTimeMillis();
            final long end = now + 1000 * 3;
            while ((now = System.currentTimeMillis()) <= end) {
                System.out.println(now + "-" + end + ":" + executor.getActiveCount());
                Thread.sleep(500);
            }
        } catch (Exception e) {
            log.error("线程池溢出：{}", e.toString());
            //System.out.println(Thread.activeCount());
        }
    }

    public static void main(String args[]) {
        int LOOP = 50;
        for (int i = 0; i < LOOP; i++) {
            OkHttpClient client = new OkHttpClient();
            final int value = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                    RequestBody body = RequestBody.create(mediaType, "key=test-thread&value=" + value);
                    //System.out.println("存储"+value);
                    Request request = new Request.Builder()
                            .url("http://43.254.44.215:8090/session/save")
                            .post(body)
                            .addHeader("content-type", "application/x-www-form-urlencoded")
                            .addHeader("cache-control", "no-cache")
                            .addHeader("postman-token", "9c6af940-7aad-7d2c-b05a-4bf57330b84b")
                            .build();
                    try {
                        Thread.sleep(50);
                        Response response = client.newCall(request).execute();
                        System.out.println(response.body().string() + ":" + value);
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(TestRequest.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }).start();
        }
        for (int i = 0; i < LOOP; i++) {
            OkHttpClient client = new OkHttpClient();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                    RequestBody body = RequestBody.create(mediaType, "key=test-thread");
                    Request request = new Request.Builder()
                            .url("http://43.254.44.215:8090/session/get")
                            .post(body)
                            .addHeader("content-type", "application/x-www-form-urlencoded")
                            .addHeader("cache-control", "no-cache")
                            .addHeader("postman-token", "a5c30c0a-12a3-07c9-82d2-6e891769be6c")
                            .build();
                    try {
                        Response response = client.newCall(request).execute();
                        System.out.println("获取" + response.body().string());
                    } catch (IOException ex) {
                        Logger.getLogger(TestRequest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
        }

    }
    
    @Test
    public void testGsonThread() throws InterruptedException{
        final ObjectMapper mapper = new ObjectMapper();
        final Gson gson = new Gson();
        final Set<String> jsonSet = new HashSet();
        for(int i=0;i<100;i++){           
            JsonModel jsonModel = new JsonModel();
            jsonModel.setId(i);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        log.error("Thread interrupt error");
                    }
                   String jsonStr ="";
                    try {
                        jsonStr = mapper.writeValueAsString(jsonModel); //gson.toJson(jsonModel);
                    } catch (JsonProcessingException ex) {
                        Logger.getLogger(TestRequest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   if(jsonSet.contains(jsonStr)){
                       System.out.println(jsonStr);
                   }
                   jsonSet.add(jsonStr);
                   //System.out.println(gson.toJson(jsonModel));
                }
            }).start();
        }
        Thread.sleep(1500);
        System.out.println(jsonSet.size());
    }
}
