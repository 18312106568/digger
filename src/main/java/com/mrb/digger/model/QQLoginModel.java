/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author MRB
 */
@Data
public class QQLoginModel implements Serializable{
    
    private String id;
    
    private String uin ;
    
    private String password;
    
    private String cookies ;
    
    private Date expireTime;
    
    private Date createDate;
    
    private Date updateDate;
    
}
