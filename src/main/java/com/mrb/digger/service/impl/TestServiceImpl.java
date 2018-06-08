/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.service.impl;



import com.mrb.digger.entity.TestEntity;
import com.mrb.digger.repository.TestEntityRepository;
import com.mrb.digger.service.TestService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MRB
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestEntityRepository testEntityRepository;
    
        
    @Transactional
    @Override
    public void testTestEntity(int num) {
        TestEntity testEntity =testEntityRepository.findById(1);
        System.out.println("orign:"+testEntity);
        testEntity.setScore(testEntity.getScore()+num);
        testEntity = testEntityRepository.save(testEntity);
        System.out.println("bew:"+testEntity);
    }

    
    
}
