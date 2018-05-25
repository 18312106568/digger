/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger.service.impl;

import com.google.gson.Gson;
import com.mrb.digger.constant.QQConstant;
import com.mrb.digger.entity.QQLogin;
import com.mrb.digger.model.PtuiCheckVK;
import com.mrb.digger.model.QQLoginModel;
import com.mrb.digger.repository.QQLoginRepository;
import com.mrb.digger.service.GameSafeService;
import com.mrb.digger.service.LoginService;
import com.mrb.digger.utils.ConverUtil;
import com.mrb.digger.utils.LoginUtil;
import com.mrb.digger.vo.BaseResult;
import com.mrb.digger.vo.CrackVo;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MRB
 */
@Slf4j
@Service
public class GameSafeServiceImpl implements GameSafeService {

    @Autowired
    Gson gson;

    @Autowired
    OkHttpClient client;

    @Autowired
    LoginService loginService;

    @Override
    public CrackVo loanGameSafe(String uin) {
        try {
            QQLoginModel qqLogin = loginService.findQQLogin(uin);
            if (qqLogin != null && qqLogin.getExpireTime().before(new Date())) {
                String loginSig = LoginUtil.getLoginSig();
                PtuiCheckVK vk = loginService.isSafeLogin(uin, loginSig);
                qqLogin = loginService.tryLogin(uin, loginSig, vk);
            }
            if (qqLogin == null) {
                return null;
            }
            Request request = new Request.Builder()
                    .url(String.format(QQConstant.TP_GAME_SAFE_URL, uin))
                    .get()
                    .addHeader(QQConstant.HD_HOST_KEY, QQConstant.HD_HOST_SAFE_VALUE)
                    .addHeader(QQConstant.HD_CONNECTION_KEY, QQConstant.HD_CONNECTION_VALUE)
                    .addHeader(QQConstant.HD_UPGRADE_KEY, QQConstant.HD_UPGRADE_VALUE)
                    .addHeader(QQConstant.HD_AGENT_KEY, QQConstant.HD_AGENT_VALUE)
                    .addHeader(QQConstant.HD_ACCEPT_KEY, QQConstant.HD_ACCEPT_VALUE)
                    .addHeader(QQConstant.HD_REFER_KEY, QQConstant.HD_REFER_VALUE)
                    .addHeader(QQConstant.HD_ENCODE_KEY, QQConstant.HD_ENCODE_VALUE)
                    .addHeader(QQConstant.HD_LANG_KEY, QQConstant.HD_LAN_VALUE)
                    .addHeader(QQConstant.HD_COOKIE_KEY, qqLogin.getCookies())
                    .build();
            Response response = client.newCall(request).execute();
            BaseResult baseResult = gson.fromJson(response.body().string(), BaseResult.class);
            CrackVo vo = ConverUtil.converJsonToClass(CrackVo.class, baseResult.getData().get(0).toString());
            System.out.println(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
