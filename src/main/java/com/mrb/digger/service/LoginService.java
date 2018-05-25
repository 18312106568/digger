/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.service;

import com.mrb.digger.entity.QQLogin;
import com.mrb.digger.model.PtuiCheckVK;
import com.mrb.digger.model.QQLoginModel;
import java.util.Map;

/**
 *
 * @author MRB
 */
public interface LoginService {
    
    PtuiCheckVK isSafeLogin(String qq,String loginSig);
    
    QQLoginModel tryLogin(String qq,String loginSig ,PtuiCheckVK vK);
    
    void save(String uin,String password);
    
    void batchSave(Map<String,String> map);
    
    QQLoginModel findQQLogin(String qqLogin);
}
