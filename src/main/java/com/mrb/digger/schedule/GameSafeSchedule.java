/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author MRB
 */
@Slf4j
@Component
public class GameSafeSchedule {
    @Scheduled(cron = "* * 0/1 * * ? ")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void tpCapture() {
        log.info("===initialDelay: 第{}次执行方法",1);
    }
}
