/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;

import org.junit.Test;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 *
 * @author MRB
 */
public class TestQRCode {
    @Test
    public void testQRCode(){
//        String text = "https://www.baidu.com/"; // 二维码内容
//        int width = 300; // 二维码图片宽度
//        int height = 300; // 二维码图片高度
//        String format = "gif";// 二维码的图片格式
//
//        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");	// 内容所使用字符集编码
//        try {
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
//                // 生成二维码
//            File outputFile = new File("d:" + File.separator + "new.gif");
//            MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		
    }
    
    @Test
    public void testStringFormat(){
        UUID uuid = UUID.randomUUID(); 
        
        System.out.println(
           new String(Base64 .getEncoder().encode((uuid.toString()+new Date().getTime())
                .substring(1).getBytes())).replaceAll("\\/", "-").replaceAll("\\+", "*").replaceAll("\\=", "_"));
        System.out.println(String.format("试算成功条数：%d", 10));
    }
    
    @Test
    public void testEquals(){
        Integer integer = new Integer(2018);
        Integer integer2 = new Integer(2018);
        int i = 2018;
        System.out.println(i==integer);
        System.out.println(integer==i);
        System.out.println(integer2==i);
        System.out.println(integer2==integer);
    }
    
    @Test
    public void testDecode(){
        System.out.println(new String(Base64.getEncoder().encode("123abc123abc".getBytes())));
    }
    
    @Test
    public void testChangeJson(){
       String DATE_PATTERN =".*T.*";
       String date1 = "20180201";
       System.out.println(Pattern.matches(DATE_PATTERN, date1));
       
    }
    
    @Test
    public void testInteger(){
        Integer i1 = 1234;
        Integer i2 = 1234;
        System.out.println(i1==i2);
        
        
        String s1 = "1234";
        String s2 = "1234";
        System.out.println(s1==s2);
    
    }
    
    
}
