/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 *
 * @author MRB
 */
public class TestScanClass {
    
    final String projectPath = System.getProperty("user.dir");
    
   
    @Test
    public void testScan() throws ClassNotFoundException{
        String regS = "([a-zA-Z_]{1,})\\.class";
        Pattern pt = Pattern.compile(regS);
        //当前报名
        String packName = this.getClass().getPackage().getName();
        //当前类路径
        URL classUrl = this.getClass().getResource("");
        File file = new File(classUrl.getPath());
        List<Class> classList = new ArrayList();
        if(file.isDirectory()){
            File[] files=file.listFiles();
            for(File childFile : files){
                if(Pattern.matches(regS, childFile.getName())){
                    classList.add(Class.forName(packName+'.'+childFile.getName().replaceAll(regS, "$1")));
                }
            }
        }
        for(Class clazz : classList){
            System.out.println(clazz);
        }
    }
}
