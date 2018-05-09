/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;

import org.junit.Test;
import java.io.File;
import java.util.Base64;
import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;

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
}
