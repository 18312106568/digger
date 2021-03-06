/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrb.digger.model.emun.BasicType;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.util.StringUtils;

/**
 *
 * @author MRB
 */
public class ConverUtil {

    private static final String SET = "set";

    private static final String GET = "get";
    
    private static final String SNULL = "null";
    
    private final Map<Class,Map<String, String>> cacheMap = new HashMap();

    /**
     * Map转换为目标对象
     *
     * @param <T>
     * @param paramMap
     * @param clazz
     * @return
     */
    @Deprecated
    public static <T> T converToTarget(Map<String, String> paramMap, Class<T> clazz) {
        try {
            T instance = clazz.newInstance();
            Field fields[] = clazz.getFields();
            for (Field field : fields) {
                Object o = null;
                Method method = clazz.getDeclaredMethod(toSetName(field.getName()), field.getType());
                if (StringUtils.isEmpty(paramMap.get(field.getName()))) {
                    o = null;
                } else if (field.getType().equals(Integer.class)) {
                    o = Integer.parseInt(paramMap.get(field.getName()));
                } else if (field.getType().equals(Date.class)) {
                    o = new Date(Integer.parseInt(paramMap.get(field.getName())));
                } else if (field.getType().equals(Float.class)) {
                    o = Float.parseFloat(paramMap.get(field.getName()));
                } else if (field.getType().equals(Double.class)) {
                    o = Double.parseDouble(paramMap.get(field.getName()));
                } else if (field.getType().equals(Long.class)) {
                    o = Long.parseLong(paramMap.get(field.getName()));
                } else if (field.getType().equals(Short.class)) {
                    o = Short.parseShort(paramMap.get(field.getName()));
                } else if (field.getType().equals(Boolean.class)) {
                    o = Boolean.parseBoolean(paramMap.get(field.getName()));
                } else if (Enum.class.equals(field.getType().getSuperclass())) {
                    o = Enum.valueOf((Class) field.getType(), paramMap.get(field.getName()));
                } else {
                    o = null;
                }
                method.invoke(instance, o);
            }
            return instance;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        }
        return null;
    }

    /**
     * 分割字符串成Map
     *
     * @param data
     * @param seporator
     * @return
     */
    public static Map converToMap(String data, String seporator) {
        Map<String, String> result = new HashMap();
        String[] dataArr = data.split(seporator);
        for (int i = 0; i < dataArr.length; i++) {
            String[] entry = dataArr[i].split("\\=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

    /**
     * Map拼接成字符串
     *
     * @param dataMap
     * @param seporator
     * @return
     */
    public static String converToStr(Map<String, String> dataMap, String seporator) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(seporator);
        }
        return sb.toString();
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        return new StringBuilder(str.substring(0, 1).toUpperCase()).append(str.substring(1)).toString();
    }

    /**
     * 获取set方法名
     *
     * @param fieldName
     * @return
     */
    private static String toSetName(String fieldName) {
        return new StringBuilder(SET).append(upperCase(fieldName)).toString();
    }

    /**
     * 获取get方法名
     *
     * @param fieldName
     * @return
     */
    private static String toGetName(String fieldName) {
        return GET + upperCase(fieldName);
    }

    /**
     * 峰驼式转匈牙利命名
     *
     * @param cameString
     * @return
     */
    public String changeCameToHung(String cameString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cameString.length(); i++) {
            int c = cameString.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sb.append("_").append((char) (c + 32));
                continue;
            }
            sb.append((char) c);
        }
        return sb.toString();
    }

    /**
     * json转为map
     *
     * @param crackJson
     * @return
     * @throws java.io.IOException
     */
    public static Map<String, String> converJsonToMap(String crackJson) throws IOException {
        char[] crackArr = crackJson.toCharArray();
        Map<String, String> map = new HashMap();
        StringBuilder sb = new StringBuilder();
        crackArr[crackJson.length() - 1] = ',';
        int signNum = 0;
        boolean flag = true;
        int ptr = 1;
        int index =0;
        String key = "";
        String value = "";
        for (int i = 1; i < crackArr.length; i++) {
            if (flag) {
                if (crackArr[i] == '=') {
                    flag = false;
                    key = new String(crackArr,ptr,index).trim();
                    ptr += ++index;
                    index =0;
                } else {
                    index++;
                    continue;
                }
            } else {
                if (crackArr[i] == ',' && signNum == 0) {
                    flag = true;
                    value = new String(crackArr,ptr,index).trim();
                    value = SNULL.equals(value)?null:value;
                    ptr += ++index;
                    index =0;
                } else if (crackArr[i] == '{') {
                    index++;
                    signNum++;
                    continue;
                } else if (crackArr[i] == '}') {
                    index++;
                    signNum--;
                    continue;
                } else {
                    index++;
                    continue;
                }
                map.put(key, value);
            }
        }
        if (signNum != 0 || !flag) {
            throw new IOException("error format json");
        }
        map.put(key, value);
        return map;
    }
    
    /**
     * 转换Json为指定类型
     * @param <T>
     * @param clazz
     * @param json
     * @return 
     */
    public static <T>T converJsonToClass(Class<T> clazz,String json){
        T instance = null;
        try {
           instance = converMapToClass(clazz, converJsonToMap(json));
        } catch (IOException e) {
        }
        return instance;
    }

    /**
     * 转换json字符为json数组
     *
     * @param json
     * @return
     */
    private static List<String> converJsonToList(String json) {
        char[] crackArr = json.toCharArray();
        List<String> jsonList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String value;
        crackArr[json.length() - 1] = ',';
        int signNum = 0;
        for (int i = 1; i < crackArr.length; i++) {
            if (crackArr[i] == ',' && signNum == 0) {
                value = "null".equals(sb.toString().trim()) ? null : sb.toString().trim();
                sb = new StringBuilder();
                jsonList.add(value);
            } else if (crackArr[i] == '{') {
                sb.append(crackArr[i]);
                signNum++;
            } else if (crackArr[i] == '}') {
                sb.append(crackArr[i]);
                signNum--;
            } else {
                sb.append(crackArr[i]);
            }
        }
        return jsonList;
    }

    /**
     * 转换json为对象集合
     *
     * @param <T>
     * @param clazz
     * @param jsons
     * @return
     * @throws IOException
     */
    private static <T> Collection<T> converJsonToCollection(Class<T> clazz, String jsons) throws IOException {
        Collection<T> collection = new ConcurrentSkipListSet<>();
        List<String> jsonList = converJsonToList(jsons);
        for (String json : jsonList) {
            collection.add(converMapToClass(clazz, converJsonToMap(json)));
        }
        return collection;
    }

    /**
     * map转化为clazz<T>
     *
     * @param <T>
     * @param clazz
     * @param map
     * @return
     */
    private static <T> T converMapToClass(Class<T> clazz, Map<String, String> map) {
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConverUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        Field[] fieldArr = clazz.getDeclaredFields();
        for (Field field : fieldArr) {
            try {
                JsonProperty ann = field.getAnnotation(JsonProperty.class);
                if (ann == null) {
                    continue;
                }
                String value = map.get(ann.value());
                int type = BasicType.getBasicType(field.getType());
                Method method = clazz.getDeclaredMethod(toSetName(field.getName()), field.getType());
                if (type > 0 && type <= 10) {
                    switch (type) {
                        case 1:
                            method.invoke(instance, Boolean.parseBoolean(value));
                            break;
                        case 2:
                            method.invoke(instance, Short.parseShort(value));
                            break;
                        case 3:
                            method.invoke(instance, Integer.parseInt(value));
                            break;
                        case 4:
                            method.invoke(instance, Long.parseLong(value));
                            break;
                        case 5:
                            method.invoke(instance, Float.parseFloat(value));
                            break;
                        case 6:
                            method.invoke(instance, Double.parseDouble(value));
                            break;
                        case 7:
                            method.invoke(instance, (Object) null);
                            break;
                        case 8:
                            method.invoke(instance, Byte.parseByte(value));
                            break;
                        case 9:
                            method.invoke(instance, new Date(Long.parseLong(value)));
                            break;
                        case 10:
                            method.invoke(instance, value);
                            break;
                    }
                } else if (type >= 11 && type <= 12) {
                    method.invoke(instance, converJsonToCollection(field.getType(), value));
                } else if (type == 13) {
                    method.invoke(instance, converJsonToMap(value));
                } else {
                    method.invoke(instance, converMapToClass(field.getType(), converJsonToMap(value)));
                }
            } catch (IllegalAccessException | NoSuchMethodException
                    | SecurityException | IllegalArgumentException | InvocationTargetException | IOException ex) {
                Logger.getLogger(ConverUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return instance;
    }

}
