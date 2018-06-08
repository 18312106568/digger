/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.model;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author MRB
 */
@Data
public class TestSerializeModel implements Serializable{
    
    private String name;
    
    private Integer age;
}
