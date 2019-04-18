import com.aojiaoo.core.mybatis.annotations.TableId;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.utils.DbInfoUtil;
import com.aojiaoo.utils.JsonUtil;
import com.aojiaoo.utils.PropertiesUtil;
import com.aojiaoo.utils.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonTest {

    @Test
    public void testGenerator() throws Exception {
        String tableName = "sys_user";
        Map<String, List<Map<String, String>>> tableInfo = DbInfoUtil.getTableInfo(PropertiesUtil.getProperties("classpath:jdbc.properties"), tableName);
        List<Map<String, String>> columnList = tableInfo.get(tableName);
        System.out.println(columnList);
//        GeneratorUtil.generatorMapping(tableName, columnList, properties);
//        GeneratorUtil.generatorMapper(tableName, columnList, properties);
//        GeneratorUtil.generatorService(tableName, properties);
//        GeneratorUtil.generatorEntity(tableName, columnList,properties);
//        GeneratorUtil.generatorMapping(tableName, columnList,properties);
    }

    @Test
    public void test2() throws Exception {

        Field field = User.class.getDeclaredField("id");
        TableId myFieldAnnotation = field.getAnnotation(TableId.class);
        System.out.println(myFieldAnnotation.type());
        System.out.println(myFieldAnnotation.value());
    }

    @Test
    public void test3() {
        String a = "　　　　会议听取了院扫黑办关于当前全市法院扫黑除恶专项斗争工作开展情况和纪检组、监察室关于相关线索的调查情况，执行庭就涉恶案件的财产刑执行情况进行了汇报，会议讨论并通过了《宣城市中级人民法院扫黑除恶专项斗争线索移送具体办法》\n";
        System.out.println(StringUtils.strip(a));
    }


    @Test
    public void test3c() {
        String a = "sdfsdf";
        System.out.println(Arrays.asList(a).size());
    }

    @Test
    public void test3a() {
        Field a[] = User.class.getDeclaredFields();
        for (int i = 0; i < a.length; i++) {
            if (null != a[i].getAnnotation(TableId.class)) {
                TableId dd = (a[i].getAnnotation(TableId.class));
            }
        }

    }

    @Test
    public void test3b() throws Exception {

//        String pythonPath="E:/development/Python/Python37-32/python.exe";
        String pythonPath="/srv/Python-3.6.4/bin/python3";
//        String searchenginePath = "C:\\Users\\puremdq\\Desktop\\searchengine";//需要执行的命令
        String searchenginePath = "/srv/workSpace/searchengine";//需要执行的命令

        String shell = "E:/development/Python/Python37-32/python.exe search.py weibo 网络";//需要执行的命令
        BufferedReader br = null;

        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];


        try {
            Process p = Runtime.getRuntime().exec(shell, null, new File(searchenginePath));//调用控制台执行shell
            for (int n; (n = p.getInputStream().read(b)) != -1; ) {
                out.append(new String(b, 0, n, Charset.forName("GBK")));
            }
//            br = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));//获取执行后出现的错误；getInputStream是获取执行后的结果
//            String line = null;
//            StringBuilder sb = new StringBuilder();
//            while ((line = br.readLine()) != null) {
//                sb.append(line + "\n");
//                System.out.println(sb);
//            }
//            System.out.println(out.toString());
            List list = JsonUtil.readValue(out.toString(), List.class);
            System.out.println(JsonUtil.toJson(list));//打印执行后的结果
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testExec() throws Exception {
        Map<String, String> baseRes = new HashMap<>();
        String shell = "E:/development/Python/Python37-32/python.exe search.py weibo 我们";//需要执行的命令
        String path = "C:/Users/puremdq/Desktop/searchengine";//需要执行的命令
        try {
            Process p0 = Runtime.getRuntime().exec(shell, null, new File(path));
            //读取标准输出流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p0.getInputStream(), Charset.forName("GBK")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            //读取标准错误流
            BufferedReader brError = new BufferedReader(new InputStreamReader(p0.getErrorStream(),  Charset.forName("UTF-8")));
            String errline = null;
            while ((errline = brError.readLine()) != null) {
                System.out.println(errline);
            }
            //waitFor()判断Process进程是否终止，通过返回值判断是否正常终止。0代表正常终止
            int c = p0.waitFor();
            if (c != 0) {
//                baseRes.put("desc", "软件升级失败：执行zxvf.sh异常终止");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        System.out.println(baseRes);

    }


}
