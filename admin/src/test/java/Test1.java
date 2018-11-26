import com.aojiaoo.core.mybatis.annotations.TableId;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.utils.DbInfoUtil;
import com.aojiaoo.utils.FileUtils;
import com.aojiaoo.utils.PropertiesUtil;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

public class Test1 {

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
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid + "  \n" + uuid.toString().length());

        uuid = UUID.randomUUID();
        System.out.println(uuid + "  \n" + uuid.toString().length());

        uuid = UUID.randomUUID();
        System.out.println(uuid + "  \n" + uuid.toString().length());

        uuid = UUID.randomUUID();
        System.out.println(uuid + "  \n" + uuid.toString().length());

        uuid = UUID.randomUUID();
        System.out.println(uuid + "  \n" + uuid.toString().length());

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
    public void test3b() {
        FileUtils.getFilePathByClasspathOrSelf("");
        URL url = ClassLoader.getSystemResource("ehcache.xml");
//        URL url =   EhcacheShiroManager.class.getClass().getResource("ehcache.xml");
        System.out.println(url);

    }


    @Test
    public void test3c() throws Exception {
        Properties properties = PropertiesUtil.getProperties("classpath:extra/generatorCode.properties");
        Field[] fields = ClassUtils.getClass(properties.getProperty("generatorCode.baseEntity")).getDeclaredFields();

        for(Field field:fields){
            System.out.println(  Modifier.toString(field.getModifiers()));
        }
    }


}
