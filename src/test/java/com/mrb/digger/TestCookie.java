/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.digger;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.mrb.digger.constant.QQConstant;
import com.mrb.digger.utils.ConverUtil;
import com.mrb.digger.vo.BaseResult;
import com.mrb.digger.vo.CrackVo;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
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
              .url(String.format(QQConstant.TP_GAME_SAFE_URL, "3602158526"))
              .get()
              .addHeader(QQConstant.HD_HOST_KEY, QQConstant.HD_HOST_SAFE_VALUE)
                .addHeader(QQConstant.HD_CONNECTION_KEY, QQConstant.HD_CONNECTION_VALUE)
                .addHeader(QQConstant.HD_UPGRADE_KEY,QQConstant.HD_UPGRADE_VALUE)
                .addHeader(QQConstant.HD_AGENT_KEY,QQConstant.HD_AGENT_VALUE)
                .addHeader(QQConstant.HD_ACCEPT_KEY,QQConstant.HD_ACCEPT_VALUE)
                .addHeader(QQConstant.HD_REFER_KEY, QQConstant.HD_REFER_VALUE)
                .addHeader(QQConstant.HD_ENCODE_KEY, QQConstant.HD_ENCODE_VALUE)
                .addHeader(QQConstant.HD_LANG_KEY, QQConstant.HD_LAN_VALUE)
                .addHeader(QQConstant.HD_COOKIE_KEY," eas_sid=E1x5j250c048U224M8L1g259P1; pac_uid=0_59954353aa931;pt2gguin=o3602158526; ts_uid=8285669824; superuin=o3602158526; ptnick_3602158526=e5b08fe7a096e5a4b4; pt_recent_uins=4028c4dfe900004480c6a6572d893eb2e0b7774da8d4e60e22513e68cf8eb2beb1fe5e84b3a484bf3cc66c1a5be97644a465f6b08d9cd467;  ied_rf=www.baidu.com/link; ETK=; skey=@EhpQQuyps; pgv_info=pgvReferrer; _ga=GA1.2.199907167.1524067202; ptdrvs=7CA8FZikVBxbLZ6zYbkhkMB4kL6e81fTpRTzgJlCVg4_; RK=eU+qjsz/aP; ptisp=ctc;HttpOnly=; pgv_pvid=3025145152; ptvfsession=a1e4e51606239d7a65d28f2ea468aa6358f3d390645414cfa2f3776a95bd0faa2b681d7dd894d1f3c25d3b409b840f316699b1bb2d3ce014;Path=/;o_cookie=3602158526; tvfe_boss_uuid=dc72a0023dfc59dd; uin=o3602158526;pgv_pvi=1345968128; ptcz=;confirmuin=0; supertoken=3411413177; ts_refer=aq.qq.com/cn2/unionverify/pc/pc_uv_show;ptui_loginuin=3602158526; superkey=gnVOyINMATRCKw*PSOKJt7*i-8XrUfKdHrr8aICBG*k_;Expires=Thu 01 Jan 1970 00:00:00 GMT;Domain=ptlogin2.qq.com;" )
              .build();
            Response response = client.newCall(request).execute();
           
           BaseResult baseResult = gson.fromJson(response.body().string(), BaseResult.class);
           System.out.println("返回结果："+ baseResult);
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
        String crackJson2 = "{\"appeal_state\":1.0,\"zone\":\"全区\",\"extend\":\"{rank\\u003d1.0}\",\"reduce_state\":1.0,\"reason\":\"1.0\",\"appeal_time\":1.523024851E9,\"start_stmp\":1.523024851E9,\"game_name\":\"地下城与勇士\",\"free_state\":1.0,\"duration\":1.0,\"game_id\":5.0,\"type\":\"封号\",\"reduced\":0.0,\"reduce_percent\":0.0}";
        long start = System.currentTimeMillis();
        CrackVo vo =null;
         for (int i =0;i<1000000;i++) {
             vo = ConverUtil.converJsonToClass(CrackVo.class, crackJson);
          
              //vo = gson.fromJson(crackJson2, CrackVo.class);
             // mapper.readValue(crackJson2, CrackVo.class);
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
        String crackJson2 = "{\"appeal_state\":1.0,\"zone\":\"全区\",\"extend\":\"{rank\\u003d1.0}\",\"reduce_state\":1.0,\"reason\":\"1.0\",\"appeal_time\":1.523024851E9,\"start_stmp\":1.523024851E9,\"game_name\":\"地下城与勇士\",\"free_state\":1.0,\"duration\":1.0,\"game_id\":5.0,\"type\":\"封号\",\"reduced\":0.0,\"reduce_percent\":0.0}";
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
    
    @Test
    public void testJParser()  {
    	ObjectMapper mapper = new ObjectMapper();
        String crackJson2 = "{\"appeal_state\":1.0,\"zone\":\"全区\",\"extend\":\"{rank\\u003d1.0}\",\"reduce_state\":1.0,\"reason\":\"1.0\",\"appeal_time\":1.523024851E9,\"start_stmp\":1.523024851E9,\"game_name\":\"地下城与勇士\",\"free_state\":1.0,\"duration\":1.0,\"game_id\":5.0,\"type\":\"封号\",\"reduced\":0.0,\"reduce_percent\":0.0}";
        JsonFactory jsonFactory = new MappingJsonFactory(mapper);
    	DeserializationConfig cfg = mapper.getDeserializationConfig();
       
        try {
        	//获取json解析对象
            JsonParser jsonParser = jsonFactory.createParser(crackJson2);
            //获取序列化上下文
            DeserializationContext ctxt = new DefaultDeserializationContext
            		.Impl(BeanDeserializerFactory.instance).createInstance(cfg, jsonParser, null);//.createDeserializationContext(jsonParser, cfg)
            //获取序列化者
            BeanDeserializerBase deser = (BeanDeserializerBase) ctxt.findRootValueDeserializer(TypeFactory.defaultInstance().constructType(CrackVo.class));
            //ctxt.prope
           // Iterator<SettableBeanProperty> properties = deser.properties();
           // SettableBeanProperty property ;
//            while( properties.hasNext()) {
//            	
//            	property = properties.next();
//            	System.out.println(property.getName());
//            }
            
            Object bean = CrackVo.class.newInstance();
            	
                String propName = jsonParser.getCurrentName();
                do {
                	jsonParser.nextToken();
                    // TODO: 06-Jan-2015, tatu: try streamlining call sequences here as well
//                	while(propName==null) {
//                		jsonParser.nextToken();
//                		propName = jsonParser.getCurrentName();
//                	}
//                    SettableBeanProperty prop = deser.findProperty(propName);
//                    if (prop != null) {
//                        if (!prop.visibleInView(activeView)) {
//                            p.skipChildren();
//                            continue;
//                        }
//                        try {
//                            prop.deserializeAndSet(jsonParser, ctxt, bean);
//                        } catch (Exception e) {
//                           // wrapAndThrow(e, bean, propName, ctxt);
//                        }
//                        continue;
//                    }
                	System.out.print(propName);
                   // handleUnknownVanilla(jsonParser, ctxt, bean, propName);
                } while ((propName = jsonParser.nextFieldName()) != null);
           
            System.out.println(bean);
        }catch (IOException ex) {
            Logger.getLogger(TestCookie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    
    @Test
    public void testStaticMethod(){
        try {
            Class<Integer> clz = Integer.class;
            Constructor<Integer> constructor = clz.getConstructor(int.class);
             System.out.println(constructor.getDeclaringClass().getName());
            Type type = clz.getGenericSuperclass();
            System.out.println(type);
            
            Integer instance = constructor.newInstance(123);
            Method method = clz.getMethod("parseInt", String.class);
            System.out.println( method.invoke(null, "13254"));
            System.out.println(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
}
