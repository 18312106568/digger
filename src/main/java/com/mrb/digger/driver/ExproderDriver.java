/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.driver;


import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author MRB
 */
public class ExproderDriver {
    
    private static volatile FirefoxDriver instance;
    
    private ExproderDriver(){
        
    }
    
    public static FirefoxDriver getInstance(){
        if(instance==null){
            synchronized(FirefoxDriver.class){
                instance = new FirefoxDriver();
            }
        }
        return instance;
    }
    
}
