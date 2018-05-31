/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.controller;


import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 *
 * @author MRB
 */
@RestController
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    HttpServletRequest request;
    
    static Set<String> values = new HashSet<>();
    
    @RequestMapping(value = "/v1", method = {RequestMethod.GET,RequestMethod.GET}, name = "")
    public String testV1(){
        String value = request.getParameter("key");
        if(values.contains(value)){
            System.out.println("重复处理的request"+value);
        }else{
            values.add(value);
            System.out.println("接收数据:"+value);
        }
        return "v1";
    }
    
}
