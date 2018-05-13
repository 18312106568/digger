/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;


import com.mrb.digger.controller.EncryptController;
import com.mrb.digger.entity.QQLogin;
import com.mrb.digger.model.PtuiCheckVK;
import com.mrb.digger.vo.CrackVo;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;  
import java.util.List;
  
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
import javax.crypto.spec.SecretKeySpec;  
   
import org.apache.commons.lang3.StringUtils;  
import org.apache.tomcat.util.codec.binary.Base64;

import org.junit.Test;

/**
 *
 * @author MRB
 */
public class TestDecode {
    
     /** 
     * 密钥 
     */  
    private static final String KEY = "efushui201709011";  
      
    /** 
     * 算法 
     */  
    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";  
    
    
    
//    @Test
//    public void testCrypto(){
//        try {
//            String pwd = "WPrJ/L6szlO7Z4r/EQs2/Q=="; 
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
//            byte[] raw = pwd.getBytes("utf-8"); 
//            SecretKey secretKey = new SecretKeySpec(raw, "AES"); 
//            System.out.println("密钥的长度为：" +(secretKey.getEncoded().length)); 
//
//            byte[] encrypt = Base64.getDecoder().decode("efushui201709011");
//            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(raw));//使用解密模式初始化 密钥 
//            byte[] decrypt = cipher.doFinal(encrypt); 
//            System.out.println("解密后：" + new String(decrypt));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
  
    @Test
    public  void testCrypto()  {  
//        try {
//             String mobile = SecurityUtils.getSalt("18312106568");
//            System.out.println(mobile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Double d = Double.parseDouble("8.64E7");
        Class clazz =  List.class;
        
        System.out.println(d.longValue());
    }  
      
    /** 
     * aes解密 
     * @param encrypt   内容 
     * @return 
     * @throws Exception 
     */  
    public static String aesDecrypt(String encrypt) throws Exception {  
        return aesDecrypt(encrypt, KEY);  
    }  
      
    /** 
     * aes加密 
     * @param content 
     * @return 
     * @throws Exception 
     */  
    public static String aesEncrypt(String content) throws Exception {  
        return aesEncrypt(content, KEY);  
    }  
  
    /** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */  
    public static String binary(byte[] bytes, int radix){  
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
    }  
  
    /** 
     * base 64 encode 
     * @param bytes 待编码的byte[] 
     * @return 编码后的base 64 code 
     */  
    public static String base64Encode(byte[] bytes){  
        return new String(bytes);
    }  
  
    /** 
     * base 64 decode 
     * @param base64Code 待解码的base 64 code 
     * @return 解码后的byte[] 
     * @throws Exception 
     */  
    public static byte[] base64Decode(String base64Code) throws Exception{  
        return StringUtils.isEmpty(base64Code) ? null : Base64.decodeBase64(base64Code.getBytes());  
    }  
  
      
    /** 
     * AES加密 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的byte[] 
     * @throws Exception 
     */  
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128);  
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));  
  
        return cipher.doFinal(content.getBytes("utf-8"));  
    }  
  
  
    /** 
     * AES加密为base 64 code 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的base 64 code 
     * @throws Exception 
     */  
    public static String aesEncrypt(String content, String encryptKey) throws Exception {  
        return base64Encode(aesEncryptToBytes(content, encryptKey));  
    }  
  
    /** 
     * AES解密 
     * @param encryptBytes 待解密的byte[] 
     * @param decryptKey 解密密钥 
     * @return 解密后的String 
     * @throws Exception 
     */  
     public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
            KeyGenerator kgen = KeyGenerator.getInstance("AES");  
            kgen.init(128);  
  
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);  
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));  
            byte[] decryptBytes = cipher.doFinal(encryptBytes);  
  
            return new String(decryptBytes);  
        }  
  
  
    /** 
     * 将base 64 code AES解密 
     * @param encryptStr 待解密的base 64 code 
     * @param decryptKey 解密密钥 
     * @return 解密后的string 
     * @throws Exception 
     */  
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {  
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);  
    } 
    
    @Test
    public void testBase64(){
        //ptui_checkVC('0','!SZH','\x00\x00\x00\x00\x7d\x86\x50\xf9','ac12e55243e33b212ebea78975358bbe894dac93315a5ad36e015377441f7ad2973f2288f72e8a51256de7ceda319a8c0f236c3506123314','2')
        System.out.println(new String(Base64.encodeBase64(intToByteArray(0x000000007d8650f9))));
        System.out.println(new String(Base64.decodeBase64("fYZQ+Q==".getBytes())));
        System.out.println(new String(Base64.encodeBase64("\\x00\\x00\\x00\\x00\\x7d\\x86\\x50\\xf9".getBytes())));
         System.out.println(new String(Base64.encodeBase64("123abc123abc".getBytes())));
        System.out.println(new String(Base64.decodeBase64("AAAAAH2GUPk=".getBytes())));
        //AAAAAH0/UKi0  
         System.out.println(new String(Base64.decodeBase64("AAAAAH0/UKi0=".getBytes())));
    }
    
    public static byte[] intToByteArray(int a) {
        return new byte[]{
            (byte) ((a >> 24) & 0xFF),
            (byte) ((a >> 16) & 0xFF),
            (byte) ((a >> 8) & 0xFF),
            (byte) (a & 0xFF)
        };
    }
    
    @Test
    public void testRegex(){
        System.out.println("1[2]3,".replaceAll("[12\\[\\],]", ""));
    }
    
    @Test
    public void testAnnotation(){
        Class clazz = QQLogin.class;
        //Annotation[] annotations = clazz.getAnnotations();
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        System.out.println(annotations.length);
        for(Annotation annotation : annotations){
            System.out.println("注解名"+annotation.toString());
            System.out.println(annotation.annotationType());
        }
        Method[] methods = clazz.getMethods();
        for(Method method : methods) {
            System.out.println(method.getName());
        }
    }
    
    @Test
    public void testFieldType(){
        Class clazz = CrackVo.class;
        for(Field field : clazz.getDeclaredFields()){
            System.out.println(field.getType());
        }
    
    }
    
    
    
}
