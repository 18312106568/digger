/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;

import com.mrb.digger.model.TestSerializeModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author MRB
 */
public class TestSerialize {
    
   
    @Test
    public void testSerialize() throws ClassNotFoundException, FileNotFoundException, IOException{
//        TestSerializeModel model = new TestSerializeModel();
//        model.setAge(10);
//        model.setName("123");
//        File file = ResourceUtils.getFile("classpath:serialize.txt");
//        FileOutputStream fos = new FileOutputStream(file);  
//        ObjectOutputStream oos = new ObjectOutputStream(fos);  
//        System.out.println(model);
//        oos.writeObject(model);  
//        oos.flush();  
//        oos.close();  
//        fos.close();  
        File file = ResourceUtils.getFile("classpath:serialize.txt");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        TestSerializeModel model = (TestSerializeModel) ois.readObject();
        System.out.println(model);
    }
}
