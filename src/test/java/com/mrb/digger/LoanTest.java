package com.mrb.digger;

import com.mrb.digger.constant.QQConstant;
import com.mrb.digger.entity.QQLogin;
import com.mrb.digger.model.PtuiCheckVK;
import com.mrb.digger.model.QQLoginModel;
import com.mrb.digger.repository.QQLoginRepository;
import com.mrb.digger.service.GameSafeService;
import com.mrb.digger.service.LoginService;
import com.mrb.digger.utils.LoginUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class LoanTest extends DiggerApplicationTests {

    @Autowired
    QQLoginRepository loginRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() {
        try {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("v");
            stringRedisTemplate.opsForValue().set("abc", "测试");
           // stringRedisTemplate.opsForList().leftPushAll("qq", list); // 向redis存入List
            stringRedisTemplate.opsForList().range("qq", 0,6).forEach(value -> {
                System.out.println(value);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    @Autowired
    GameSafeService gameSafeService;
    
    @Test
    public void testRedisThread(){
        long start = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized(stringRedisTemplate){
                        System.out.println("存储数据："+num);
                        stringRedisTemplate.opsForValue().set("abc", "test"+num);
                    }
                }
            }).start();
        }
        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized(stringRedisTemplate){
                        System.out.println("获取数据："+stringRedisTemplate.opsForValue().get("abc"));
                    }
                }
            }).start();
        }
        while(Thread.activeCount()>5){
            //System.out.println("还在运行中的线程数"+Thread.activeCount());
        }
        long end = System.currentTimeMillis();
        System.out.println("redis 消耗时间："+(end-start));
    }
    
   

    @Test
    public void testQQLogin() {
        System.out.println(String.format(QQConstant.QQ_LOGIN_URL, "3602158526", "123abc123abc"));
        System.out.println(loginRepository.findQQLoginByUin("3602158526"));
    }

    @Test
    public void testLogin() throws InterruptedException {
        List<QQLoginModel> modelList = loginService.findAll();
        for(QQLoginModel model : modelList){
            String qq = model.getUin();
            String loginSig = LoginUtil.getLoginSig();
            PtuiCheckVK vk = loginService.isSafeLogin(qq, loginSig);
            System.out.println(vk);
            loginService.tryLogin(qq, loginSig, vk);
            Thread.sleep(1000);
        }
    }

    @Test
    public void testSaveQQLogin() {
        String qq = "3602158526";
        String password = "MTIzYWJjMTIzYWJj";
        loginService.save(qq, password);
    }

    @Test
    public void testLoanGameSafe(){
        gameSafeService.loanGameSafe("3602158526");
    }
    
    @Test
    public void testBatchSave(){
        String filePath = "D:\\tmp\\TP\\QQ.txt";
        File file = new File(filePath);
        loginService.batchSave(LoginUtil.getQQLoginMap(file));
    }

    @Test
    public void listQQLogin() {
        List<String> uinList = new ArrayList<>();
        uinList.add("3250537630");
        uinList.add("3602158526");
        List<QQLogin> loginList = loginRepository.listAllByUins(uinList);
        System.out.println(loginList.size());
    }

    @Test
    public void testFile() throws FileNotFoundException, IOException {
        Pattern pattern = Pattern.compile("[0-9]{9,10}-");
        final String PATTERN = "[0-9]{9,10}\\-[a-zA-Z0-9\\+\\/\\=]*";
        String filePath = "D:\\tmp\\TP\\QQ.txt";
        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[2048];
        int count = 0;
        int sum = 0;
        while ((sum = reader.read(buf)) != -1) {
            count += sum;
            sb.append(new String(buf));
            buf = new char[2048];
        }
        String uins = sb.toString().trim();
        String[] uinArr = uins.split("\r\n");
        for (String uin : uinArr) {
            //System.out.println(uin);
            if (pattern.matcher(uin).matches()) {
                System.out.println(uin);
            }
            if (Pattern.matches(PATTERN, uin)) {
                System.out.println(uin);
            }
        }
//        System.out.println(sb.toString());
//        System.out.println(sb.toString().replaceAll("\r\n","").length());
//        System.out.println(count);

    }

    /*
    @PersistenceContext
    EntityManager em;
    

    @Test
    public void nativeQueryLoan() {
        StringBuilder sql = new StringBuilder("SELECT " +
                "  a.ID as id," +
                "  a.CAP_REQUEST_NO as capRequestNo," +
                "  c.NAME as productName," +
                "  a.AMOUNT as amount," +
                "  (IFNULL(b.INSTALLMENTSERVERFEE, 0) + IFNULL(b.AMOUNTINTEREST, 0) -" +
                "   IFNULL(b.couponAomunt, 0)) AS couponAmount," +
                "  a.TIMERECORDED as timeRecord," +
                "  a.PAY_DATE as payDate," +
                "  a.REPAY_DATE as repayDate," +
                "  a.STATUS as loanStatus" +
                " FROM TB_LOAN a " +
                "  INNER JOIN TB_LOAN_REPAYMENT b ON a.ID = b.LOAN_ID " +
                "  INNER JOIN TB_LOANREQUEST_PRODUCT c ON a.PRODUCT_ID = c.ID where 1=1 ");
        Query q = em.createNativeQuery(sql.toString());
        List<Object[]> resultList = q.getResultList();
        resultList.forEach(e -> {
            System.out.println(Arrays.toString(e));
        });
    }

    @Test
    public void testBorrowHistory() {
        String sql = "SELECT\n" +
                "\ta.`TITLE` as loanTitle,\n" +
                "\ta.`USER_ID` as userId,\n" +
                "\ta.`AMOUNT` as amount,\n" +
                "\t ifnull(b.`REPAYAMOUNT`, 0) as repayAmount,\n" +
                "\td.`LOAN_RATE` as loanRate,\n" +
                "\td.`PRODUCTKEY` as loanProductType,\n" +
                "\td.`LOAN_DURATION_DAYS` as loanDurationDays,\n" +
                "\ta.`REPAY_DATE` as repayDate,\n" +
                "\t(select count(1) from TB_REPAYMENT_OVERDUE_RECORD f where f.REPAYMENT_ID = b.ID) as dueDays,\n" +
                "\ta.`STATUS` as status\n" +
                "FROM\n" +
                "\t`TB_LOAN` a\n" +
                "\tINNER JOIN `TB_LOAN_REPAYMENT` b on a.id =b.LOAN_ID\n" +
                "\tLEFT JOIN `TB_LOANREQUEST_PRODUCT` d ON a.`PRODUCT_ID` = d.id \n" +
                "WHERE\n" +
                "\ta.`USER_ID` IN (?1,?2)";
        Query q = em.createNativeQuery(sql).setParameter(1, "E5A9B920-78D3-4556-A068-FA9A87CF4426")
                .setParameter(2,"E5A9B920-78D3-4556-A068-FA9A87CF4426");
        List<Object[]> resultList = q.getResultList();
        resultList.forEach(e -> {
            System.out.println(Arrays.toString(e));
        });
    }
    
    @Test
    public void testQuoteClient(){
        try {
            QuoteClient quoteClient = new QuoteClient();
//            GetQuoteResponse response = quoteClient.getQuote("HISTORY_DECL_DOWNLOAD_ACCREDIT_DATA");
          //  System.err.println(response.getGetQuoteResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
     */
}
