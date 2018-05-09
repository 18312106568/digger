/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;


import com.google.gson.Gson;
import com.mrb.digger.utils.ConverUtil;
import com.mrb.digger.vo.BaseResult;
import com.mrb.digger.vo.CrackVo;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.util.ResourceUtils;
import java.util.Arrays;

/**
 *
 * @author MRB
 */
public class TestCookie {
    
    private static final int SIZE = 4096;  
    
    final OkHttpClient client = new OkHttpClient();
    
    final Gson gson = new Gson();
    
    @Test
    public void testStringFormat(){
    
        System.out.println(String.format("试算成功条数：%d", 10));
    }
    
    @Test
    public void testEquals(){
        Integer integer = new Integer(2018);
        Integer integer2 = new Integer(2018);
        int i = 2018;
        System.out.println(i==integer);
        System.out.println(integer==i);
        System.out.println(integer2==i);
        System.out.println(integer2==integer);
    }
    
    @Test
    public void testFile(){
        try {
             File file = ResourceUtils.getFile("classpath:area-code.txt");
             FileInputStream fis = new FileInputStream(file);
             StringBuilder codeBuilder = new StringBuilder();
             int len = 0; 
             byte[] buf =new byte[SIZE];
             while((len=fis.read(buf))!=-1){
                 System.out.println(new String(buf,0,len));  
                 codeBuilder.append(new String(buf,0,len) );
             }
             String codeAll = codeBuilder.toString();
             String[] codeArr = codeAll.split("\\|");
             Map<String,String> codeMap = new HashMap<>();
             for(String code :Arrays.asList(codeArr) ){
                 String[] codePC = code.split("-");
                 codeMap.put(codePC[0], codePC[1]);
             }
             for(Map.Entry<String,String> entity: codeMap.entrySet()){
                 System.out.println(entity.getKey()+"-"+entity.getValue());
             }
             fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCookie(){
        try {
            Request request = new Request.Builder()
              .url("http://gamesafe.qq.com/api/punish?need_appeal=1&_=3602158526")
              .get()
              .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
              .addHeader("Accept-Encoding", "gzip, deflate")
              .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
              .addHeader("Cache-Control", "max-age=0")
              .addHeader("Connection", "keep-alive")
              .addHeader("Cookie", "  eas_sid=E1x5j250c048U224M8L1g259P1; pac_uid=0_59954353aa931;pt2gguin=o3602158526; ts_uid=8285669824; superuin=o3602158526; ptnick_3602158526=e5b08fe7a096e5a4b4; pt_recent_uins=552d69bc3d903879bf340ab0517b5f90e215ac7985e4c626e89981f722958d0b0d21706fec1ccccad816e1014f2179ae90ea714adbd522ce;  ied_rf=www.baidu.com/link; ETK=; skey=@Rvryh7ehp; pgv_info=pgvReferrer; _ga=GA1.2.199907167.1524067202; ptdrvs=yQQAcsYJW1TOYWCwFivcZb98-hei7QbGquk0khOypiE_; RK=eU+qjsz/aP; ptisp=ctc;HttpOnly=; pgv_pvid=3025145152; ptvfsession=7f3ef078014769353d20031d8c5fcfda8816cd655c7ab16eeb9d868a011f68b296b4966eda6bc9f1ccb1c592d77da8b70b1698521f3c5f3d;Path=/;o_cookie=3602158526; tvfe_boss_uuid=dc72a0023dfc59dd; uin=o3602158526;pgv_pvi=1345968128; ptcz=;confirmuin=0; supertoken=3870176117; ts_refer=aq.qq.com/cn2/unionverify/pc/pc_uv_show;ptui_loginuin=3602158526; superkey=mYJ0wwuKSh*uQtGW8Saj8Txf1Bz3ESoxEzWuet4A0vM_;Expires=Thu 01 Jan 1970 00:00:00 GMT;Domain=ptlogin2.qq.com;")
              .addHeader("Host", "gamesafe.qq.com")
              .addHeader("If-None-Match", "025239ac14b4fa2b48a435e04d620767cea4af9c")
              .addHeader("Upgrade-Insecure-Requests", "1") 
              .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")      
              .build();
            Response response = client.newCall(request).execute();
           
           BaseResult baseResult = gson.fromJson(response.body().string(), BaseResult.class);
           System.out.println("返回结果："+ baseResult.getData());
           // System.out.println(  gson.fromJson(baseResult.getData().get(0).toString()  , CrackVo.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    @Test
    public void testConver(){
        String url = "https://ssl.ptlogin2.qq.com/check?regmaster=&pt_tea=2&pt_vcode=1&uin=2105954553&appid=21000109&js_ver=10270&js_type=1&login_sig=vuAsTWmmA70Az8-Ugk-plwLIf8tbve0xDoBEPHPg6EsYC4*Fq-ytN40tJvLgXi8L&u1=http%3A%2F%2Fgamesafe.qq.com%2F&r=0.8029317727916006&pt_uistyle=40&pt_jstoken=915971442";
        Map<String,String> paramMap = ConverUtil.converToMap(url.substring(url.indexOf("?")+1), "\\&");
        for(Map.Entry<String,String> entry :paramMap.entrySet()){
            if(entry.getKey().equals("login_sig")){
                System.out.println("login_sig的长度"+entry.getValue().length());
            }
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }
        System.out.println("5S22EjF3fvgzDQDn0a6qvHT8QxJl4*QHo*EqmvqDrtBWXFZVnrKVX*IRUMeNfgT1".length());
    }
    
    
    
    
    
}
