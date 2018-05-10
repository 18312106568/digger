/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.model.emun;

import java.util.Date;

/**
 *
 * @author MRB
 */
public enum BasicType {
    
    B(1,Boolean.class),
    b(1,boolean.class),
    S(2,Short.class),
    s(2,short.class),
    I(3,Integer.class),
    i(3,int.class),
    L(4,Long.class),
    l(4,long.class),
    F(5,Float.class),
    f(5,float.class),
    D(6,Double.class),
    d(6,double.class),
    V(7,Void.class),
    BYTE(8,Byte.class),
    by(8,byte.class),
    DATE(9,Date.class),
    STRING(10,String.class),
    LIST(11,java.util.List.class),
    SET(12,java.util.Set.class),
    MAP(13,java.util.Map.class)
    ;

    public void setType(Class type) {
        this.type = type;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public Class getType() {
        return type;
    }
    
    private int key ;
    private Class type;
    private BasicType(int key,Class type){
        this.key = key;
        this.type = type;
        
    }
    
    public static int getBasicType(Class clazz){
        BasicType[] basics = BasicType.values();
        for(BasicType basic : basics){
            if(basic.type.equals(clazz)){
                return basic.getKey();
            }
        }        
        return 0;
    }
    
}
