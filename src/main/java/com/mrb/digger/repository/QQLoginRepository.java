package com.mrb.digger.repository;

import com.mrb.digger.entity.QQLogin;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


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
   
   /**
    * 通过账号批量获取登陆QQ
    * @param uins
    * @return 
    */
   @Query(value = "SELECT q FROM QQLogin q WHERE q.uin IN :UINS")
    List<QQLogin> listAllByUins(@Param("UINS")Collection<String> uins);
}
