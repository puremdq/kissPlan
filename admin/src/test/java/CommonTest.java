import com.aojiaoo.utils.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

        List<Map<String, String>> cityList = JsonUtil.readValue(FileUtils.readFileToString(new File("C:\\Users\\puremdq\\Desktop\\quyu\\3.area.js"), Charset.forName("UTF-8"))
                , List.class);


        Map<String, Map<String, Map<String, String>>> parentChildMap = new HashMap<>();

        for (Map<String, String> city : cityList) {
            if (parentChildMap.containsKey(city.get("parentCode"))) {
                Map<String, Map<String, String>> currentChildMap = parentChildMap.get(city.get("parentCode"));
                currentChildMap.put(city.get("name"), city);
            } else {
                Map<String, Map<String, String>> currentChildMap = new HashMap<>();
                currentChildMap.put(city.get("name"), city);
                parentChildMap.put(city.get("parentCode"), currentChildMap);
            }

        }

        File cityFiles = new File("C:\\Users\\puremdq\\Desktop\\city");
        for (File file : cityFiles.listFiles()) {
            String parentCode = file.getName();
            parentCode = parentCode.substring(0, parentCode.lastIndexOf("."));
            String json = IOUtils.toString(new BOMInputStream(new FileInputStream(file)), StandardCharsets.UTF_8);
            Map<String, Object> res = JsonUtil.readValue(json, Map.class);
            List<Map> features = (List) res.get("features");

            for (Map Feature : features) {
                Map properties = (Map) Feature.get("properties");
                if (!properties.containsKey("cp")) {
                    if (parentChildMap.get(parentCode) != null && properties.get("name") != null && parentChildMap.get(parentCode).get(properties.get("name")) != null) {
                        Map<String, String> currentMap = parentChildMap.get(parentCode).get(properties.get("name"));
                        if (currentMap != null) {
                            List<Double> cplist = new ArrayList<>();
                            if (StringUtils.isNotBlank(currentMap.get("longitude")) && StringUtils.isNotBlank(currentMap.get("latitude"))) {
                                cplist.add(Double.valueOf(currentMap.get("longitude")));
                                cplist.add(Double.valueOf(currentMap.get("latitude")));
                                properties.put("cp", cplist);
                            }
                        }
                    }

                }
            }
            FileUtils.write(new File("C:\\Users\\puremdq\\Desktop/cityNew/" + file.getName()), JsonUtil.toJson(res), StandardCharsets.UTF_8);
        }


    }

    @Test
    public void test3() throws Exception {

        List<String> a=new ArrayList<>();
        List<String> b=new ArrayList<>();
        a.add("a");
        a.add("b");
        b.add("c");
        System.out.println(CollectionUtils.retainAll(a, b));
    }
}

