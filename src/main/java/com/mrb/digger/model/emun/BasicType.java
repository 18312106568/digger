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
    S(2,Short.class),
    I(3,Integer.class),
    L(4,Long.class),
    F(5,Float.class),
    D(6,Double.class),
    V(7,Void.class),
    DATE(8,Date.class),
    String(9,String.class),
    List(10,java.util.List.class),
    Set(11,java.util.Set.class),
    Map(12,java.util.Map.class)
    ;

    public void setType(Class type) {
        this.type = type;
    }

    public void setKey(int key) {
        this.key = key;
    }
    
    private int key ;
    private Class type;
    private BasicType(int key,Class type){
        this.key = key;
        this.type = type;
        
    }
    
    public static boolean isBasicType(Class clazz){
        BasicType[] basics = BasicType.values();
        for(BasicType basic : basics){
            if(basic.type.equals(clazz)){
                return true;
            }
        }        
        return false;
    }
    
}
