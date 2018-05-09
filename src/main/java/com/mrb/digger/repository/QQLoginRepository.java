package com.mrb.digger.repository;

import com.mrb.digger.entity.QQLogin;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author MRB
 */
public interface QQLoginRepository extends JpaRepository<QQLogin, String> {

    /**
     * 通过账号获取登陆QQ
     * 
     * @param uin
     * @return 
     */
   QQLogin findQQLoginByUin(String uin);
}
