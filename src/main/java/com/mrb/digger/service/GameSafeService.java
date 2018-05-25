/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.service;

import com.mrb.digger.vo.CrackVo;

/**
 *
 * @author MRB
 */
public interface GameSafeService {
    CrackVo loanGameSafe(String uin);
}
