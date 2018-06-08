
package com.mrb.digger.entity;


import com.mrb.digger.entity.base.EntityId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author MRB
 */

@Entity(name = "TB_TEST")
@Data
public class TestEntity  {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @Column(name = "SCORE")
    private Integer score;
    
}
