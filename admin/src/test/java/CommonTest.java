import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.core.mybatis.annotations.TableId;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.utils.DbInfoUtil;
import com.aojiaoo.utils.PropertiesUtil;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.apache.commons.codec.binary.Hex.decodeHex;

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
        System.out.println(GlobalProperties.get("uploadPath"));
        System.out.println(GlobalProperties.get("uploadPath"));
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
        System.out.println(DigestUtils.md5Hex("亲爱的你都不知道我有多爱你‘撒打发斯蒂芬闪电法师，，，是的发生的"));
    }


}
