/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.mrb.digger.utils.ConverUtil;
import com.mrb.digger.vo.BaseResult;
import com.mrb.digger.vo.CrackVo;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.util.ResourceUtils;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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
              .addHeader("Cookie", "eas_sid=E1x5j250c048U224M8L1g259P1; pac_uid=0_59954353aa931;pt2gguin=o3602158526; ts_uid=8285669824; superuin=o3602158526; ptnick_3602158526=e5b08fe7a096e5a4b4; pt_recent_uins=2cac7802d37c33ed9bd43a82d7621efe7a1e4016bdfaf4d03b8b0b5a30dd28a28956ddb277b475487abf0d2792e9e3882a03b29a047b7171;  ied_rf=www.baidu.com/link; ETK=; skey=@fMu56XMEQ; pgv_info=pgvReferrer; _ga=GA1.2.199907167.1524067202; ptdrvs=pf2eciF-kVAFZ6NfZf-wOxb-Mi1r0fkKTrcYILblalA_; RK=eU+qjsz/aP; ptisp=ctc;HttpOnly=; pgv_pvid=3025145152; ptvfsession=1c56931e7ccda4bc08149bd4426438ae7d3744306246196e3183fd7e029c6a87def2d32f93e99665103938619548c25e2d09d860237b8419;Path=/;o_cookie=3602158526; tvfe_boss_uuid=dc72a0023dfc59dd; uin=o3602158526;pgv_pvi=1345968128; ptcz=;confirmuin=0; supertoken=2570945179; ts_refer=aq.qq.com/cn2/unionverify/pc/pc_uv_show;ptui_loginuin=3602158526; superkey=E*u*6FD4YAyTV2QFRccuxH9bxHcmdMQ*Jemi*y22C0Q_;Expires=Thu 01 Jan 1970 00:00:00 GMT;Domain=ptlogin2.qq.com;")
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
    
    @Test
    public void converJsonToMap(){
        //{appeal_time=null, appeal_state=1.0, zone=全区, extend={rank=1.0}, reduce_state=1.0, reason=游戏作弊, start_stmp=1.523024851E9, game_name=地下城与勇士, free_state=1.0, duration=8.64E7, game_id=5.0, type=封号, reduced=0.0, reduce_percent=0.0}
        String crackJson = "{appeal_time=null, appeal_state=1.0, zone=全区, extend={rank=1.0}, reduce_state=1.0, reason=游戏作弊, start_stmp=1.523024851E9, game_name=地下城与勇士, free_state=1.0, duration=8.64E7, game_id=5.0, type=封号, reduced=0.0, reduce_percent=0.0}";
        char[] crackArr = crackJson.toCharArray();
        crackArr[crackJson.length()-1] = ',';
        Map<String,String> map = new HashMap();
        StringBuilder sb = new StringBuilder();
        int signNum = 0;
        boolean flag = true;
        String key ="";
        String value ="";
        for(int i=1;i<crackArr.length;i++){
            if(flag){
                if(crackArr[i]=='='){
                    flag =false;
                    key =sb.toString().trim();
                    sb = new StringBuilder();
                }else{
                    sb.append(crackArr[i]);
                    continue;
                }
            }else{
                if(crackArr[i]==','&&signNum==0){
                    flag = true;
                    value = sb.toString().trim();
                    sb = new StringBuilder();
                }else if(crackArr[i]=='{'){
                    sb.append(crackArr[i]);
                    signNum++;
                    continue;
                }else if(crackArr[i]=='}'){
                    sb.append(crackArr[i]);
                    signNum--;
                    continue;
                }else{
                    sb.append(crackArr[i]);
                    continue;
                }
                map.put(key, value);
            }
        }
        System.out.println(signNum);
        System.out.println(flag);
        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+"===>"+entry.getValue());
        }
        
    }
    
    @Test
    public void testConverJsonToClass() throws IOException{
        Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        String crackJson = "{appeal_time=null, appeal_state=1.0, zone=全区, extend={rank=1.0}, reduce_state=1.0, reason=游戏作弊, start_stmp=1.523024851E9, game_name=地下城与勇士, free_state=1.0, duration=8.64E7, game_id=5.0, type=封号, reduced=0.0, reduce_percent=0.0}";
        String crackJson2 = "{\"appeal_state\":1.0,\"zone\":\"全区\",\"extend\":\"{rank\\u003d1.0}\",\"reduce_state\":1.0,\"reason\":\"1.0\",\"start_stmp\":1.523024851E9,\"game_name\":\"地下城与勇士\",\"free_state\":1.0,\"duration\":1.0,\"game_id\":5.0,\"type\":\"封号\",\"reduced\":0.0,\"reduce_percent\":0.0}";
        long start = System.currentTimeMillis();
        CrackVo vo =null;
         for (int i =0;i<1;i++) {
             vo = ConverUtil.converJsonToClass(CrackVo.class, crackJson);
              //vo = gson.fromJson(crackJson2, CrackVo.class);
              mapper.readValue(crackJson2, CrackVo.class);
        }
        long mid = System.currentTimeMillis();
        for (int i =0;i<1000000;i++) {
             //vo = ConverUtil.converJsonToClass(CrackVo.class, crackJson);
              vo = gson.fromJson(crackJson2, CrackVo.class);
        }
        long end = System.currentTimeMillis();
        System.out.println(mid - start);
        System.out.println(end - mid);
        System.out.println(vo);
    }
    
    @Test
    public void testNewString(){
        String json = "1234567890";
        char[] jsonArr =json.toCharArray();
        StringBuilder sb = new StringBuilder();
        long start = System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            for(int j=0;j<3;j++){
                sb.append(jsonArr[j]);
            }
            sb = new StringBuilder();
        }
        long mid = System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            new String(jsonArr,0,3);
        }
        long end = System.currentTimeMillis();
        System.out.println(mid-start);
        System.out.println(end-mid);
    }
    
    /**
     * 学习json底层实现原理
     */
    @Test
    public void testObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        String crackJson2 = "{\"appeal_state\":1.0,\"zone\":\"全区\",\"extend\":\"{rank\\u003d1.0}\",\"reduce_state\":1.0,\"reason\":\"1.0\",\"start_stmp\":1.523024851E9,\"game_name\":\"地下城与勇士\",\"free_state\":1.0,\"duration\":1.0,\"game_id\":5.0,\"type\":\"封号\",\"reduced\":0.0,\"reduce_percent\":0.0}";
        JsonFactory jsonFactory = new MappingJsonFactory(mapper);
        try {
            JsonParser jsonParser = jsonFactory.createParser(crackJson2);
            mapper.readValue(crackJson2, CrackVo.class);
            System.out.println(jsonParser.nextToken());
            
            IOContext ioContext =new IOContext(jsonFactory._getBufferRecycler(), crackJson2, false);
            System.out.println(ioContext);
        } catch (IOException ex) {
            Logger.getLogger(TestCookie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TypeFactory typeFactory = TypeFactory.defaultInstance();
        JavaType javaType = typeFactory.constructType(CrackVo.class);
       
        System.out.println(javaType);
        //JavaType javaType2 = typeFactory.constructType(CrackVo.class);
        
        
        
        
    }
    
    
}
