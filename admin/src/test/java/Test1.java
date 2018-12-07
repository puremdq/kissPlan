import com.aojiaoo.core.mybatis.annotations.TableId;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.utils.DbInfoUtil;
import com.aojiaoo.utils.PropertiesUtil;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

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
        try {

            JsonFactory jfactory = new JsonFactory();

            /*** write to file ***/

            OutputStream os = System.out;
//            os.write("[{name='pure',age=10},{name='mdq',age=10}]".getBytes());
            JsonGenerator jGenerator = jfactory.createJsonGenerator(os, JsonEncoding.UTF8);
            jGenerator.writeStartObject(); // {

            jGenerator.writeStringField("name", "mkyong"); // "name" : "mkyong"
            jGenerator.writeNumberField("age", 29); // "age" : 29

            jGenerator.writeFieldName("messages"); // "messages" :
            jGenerator.writeStartArray(); // [

            jGenerator.writeString("msg 1"); // "msg 1"
            jGenerator.writeString("msg 2"); // "msg 2"
            jGenerator.writeString("msg 3"); // "msg 3"

            jGenerator.writeEndArray(); // ]

            jGenerator.writeEndObject(); // }

            jGenerator.close();

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    @Test
    public void test3c() throws Exception {
        Properties properties = PropertiesUtil.getProperties("classpath:extra/generatorCode.properties");
        Field[] fields = ClassUtils.getClass(properties.getProperty("generatorCode.baseEntity")).getDeclaredFields();

        for (Field field : fields) {
            System.out.println(Modifier.toString(field.getModifiers()));
        }
    }


}
