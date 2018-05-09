/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.utils;

import com.mrb.digger.constant.WebConstant;
import com.mrb.digger.driver.ExproderDriver;
import com.mrb.digger.model.PtuiCheckVK;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author MRB
 */
public class LoginUtil {

    static final String LOGIN_PATTERN = "[0-9]{9,10}\\-[a-zA-Z0-9\\+\\/\\=]*";
    
    static final Integer MAX_LEN = 2048;
    
    /**
     * 生成login_sig
     *
     * @return
     */
    public static String getLoginSig() {
        return new String(Base64.getEncoder().encode((UUID.randomUUID().toString() + new Date().getTime())
                .substring(1).getBytes())).replaceAll("\\/", "-").replaceAll("\\+", "*").replaceAll("\\=", "_");
    }

    public static PtuiCheckVK getCheckVK(String str) {
        PtuiCheckVK vk = new PtuiCheckVK();
        String[] arr = str.substring(str.indexOf("(") + 1, str.length() - 1).split("\\,");
        if (arr.length > 4) {
            vk.setType(arr[0]);
            vk.setVerifyCode(arr[1]);
            vk.setLookCode(arr[2]);
            vk.setPVerifySession(arr[3]);
        }
        return vk;
    }

    /**
     * 密码加密
     *
     * @param vk
     * @return
     */
    public static String getEntryptPassword(PtuiCheckVK vk) {
        try {
            //输入要访问的网页地址
            WebDriver driver = ExproderDriver.getInstance();
            driver.get(WebConstant.ENCODE_URL + "?vk=" + URLEncoder.encode(new Gson().toJson(vk), "UTF-8"));
            final String encryption = driver.findElement(By.tagName("body")).getText();
            return encryption;
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }
    
    /**
     * 文件导入QQ账号信息
     * @param file
     * @return 
     */
    public static Map<String,String> getQQLoginMap(File file){
        Map<String,String> loginMap = new HashMap();
        try{
            FileReader reader = new FileReader(file);
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[MAX_LEN];
            while (reader.read(buf)!=-1) {
                sb.append(new String(buf));
                buf = new char[MAX_LEN];
            }
            String uins = sb.toString().trim();
            String[] uinArr = uins.split("\r\n");
            for(String uin : uinArr){
                if(Pattern.matches(LOGIN_PATTERN, uin)){
                    String[] uinEntiry = uin.split("\\-");
                    loginMap.put(uinEntiry[0], uinEntiry[1]);
                }
            }
        }catch(IOException e){
        }
        return loginMap;
    }
}
