/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrb.digger.repository;

import com.mrb.digger.entity.TestEntity;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

/**
 * @author MRB
 * @createTime 2017-12-4
 */
public interface TestEntityRepository extends JpaRepository<TestEntity, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)  
    public TestEntity findById(Integer id);
}
