import com.aojiaoo.utils.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

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

        Map map = JsonUtil.readValue("{'from':0,'size':36,'query':{'bool':{'filter':[{'bool':{'should':[{'term': {'enabled.keyword': '1'}},{'bool': {'must_not': {'exists': {'field': 'enabled'}}}}],'minimum_s\n" +
                "hould_match': 1}},{'range':{'publishDate':{'gte':1561538746157,'lt':1561625146157}}},{'terms':{'mediaType.keyword':['new','wc','app','epa']}},{'bool':{'must_not':[@disab\n" +
                "leMediasStr@]}} ,{'bool':{'must_not': [{'terms':{'location3.keyword':['宣城市']}},{'terms':{'news_city.keyword':['宣城市']}}]}}],'must':[{'query_string':{'fields':['titl\n" +
                "e'],'query':'( \\\"宣城\\\" OR \\\"新宣中\\\" OR \\\"宣州\\\" OR \\\"爰陵\\\" OR \\\"宛陵\\\" OR \\\"宁国县\\\" OR \\\"郎溪县\\\" OR \\\"广德县\\\" OR \\\"泾县\\\" OR \\\"绩溪县\\\" OR \\\"旌德县\\\" OR \\\"徽杭\\\" )\n" +
                "NOT( \\\"招聘\\\"  \\\"公告\\\"  \\\"物流\\\"  \\\"天气\\\"  \\\"预报\\\"  \\\"笔试\\\"  \\\"查询\\\"  \\\"统考\\\"  \\\"新房\\\"  \\\"联系电话\\\"  \\\"福利\\\"  \\\"采购\\\"  \\\"求职\\\" )','type':'cross_fields','analy\n" +
                "zer':'ik_max_word'}},{'bool':{'should':[{'terms':{'mediaName.keyword':['搜狐新闻'],'boost':190}},{'terms':{'mediaName.keyword':['中国经济网'],'boost':180}},{'terms':{'me\n" +
                "diaName.keyword':['中国文明网'],'boost':192}},{'terms':{'mediaName.keyword':['中青在线'],'boost':170}},{'terms':{'mediaName.keyword':['光明网'],'boost':182}},{'terms':{'\n" +
                "mediaName.keyword':['新浪网'],'boost':160}},{'terms':{'mediaName.keyword':['新华社'],'boost':194}},{'terms':{'mediaName.keyword':['中工网'],'boost':172}},{'terms':{'medi\n" +
                "aName.keyword':['央视网'],'boost':184}},{'terms':{'mediaName.keyword':['腾讯网'],'boost':162}},{'terms':{'mediaName.keyword':['人民日报'],'boost':196}},{'terms':{'mediaN\n" +
                "ame.keyword':['中国网'],'boost':174}},{'terms':{'mediaName.keyword':['环球网'],'boost':186}},{'terms':{'mediaName.keyword':['搜狐'],'boost':164}},{'terms':{'mediaName.ke\n" +
                "yword':['新华网'],'boost':198}},{'terms':{'mediaName.keyword':['中国女网'],'boost':176}},{'terms':{'mediaName.keyword':['经济日报'],'boost':188}},{'terms':{'mediaName.ke\n" +
                "yword':['凤凰网'],'boost':166}},{'terms':{'mediaName.keyword':['人民网'],'boost':200}},{'terms':{'mediaName.keyword':['中国农业新闻网'],'boost':178}},{'terms':{'mediaNam\n" +
                "e.keyword':['中国日报中文网'],'boost':168}},{'terms':{'mediaName.keyword':['网易'],'boost':158}},{'exists':{'field':'id'}}],'minimum_should_match':1}}] }},'_source': ['i\n" +
                "d','mediaType','source','url','title','content','publishDate','mediaName','publishChannel','author','pictureUrl','bloggerId','description','summary','nlpSummary','homepa\n" +
                "geUrl','headPicUrl','wbFollowersNum','wbFansNum','wbDocumentNum','nickName','nlpCoefficientKeywords','nlpSentmentialScore','isContainsImg','isForward','udClassify','forw\n" +
                "ardNum','commentNum','thumbsUpNum','similarNum','similarArrs','clickNum','replyNum','readNum','nlpPlace','nlpProperNoun','news_province','udClassifies','revision','pageN\n" +
                "ames'],'highlight':{'fields':{'title':{'fragment_size':100,'number_of_fragments':3},'content':{'fragment_size':70,'number_of_fragments':3}}},'sort':[{'_score': 'desc'},{\n" +
                "'id.keyword':'desc'}],'collapse':{'field':'title.keyword','inner_hits':{'size':0,'name':'maxRecord'}},'aggs':{'distinctCount':{'cardinality':{'field':'title.keyword','pr\n" +
                "ecision_threshold':39999 }}}}", Map.class);
        System.out.println(JsonUtil.toJson(map));
    }

    @Test
    public void test3a() throws Exception {

        String shell = "ipconfig";//需要执行的命令
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
//
        // System.getProperties().getProperty("os.name");
        try {
            Process p = Runtime.getRuntime().exec(shell, null);//调用控制台执行shell
            for (int n; (n = p.getInputStream().read(b)) != -1; ) {
//                out.append(new String(b, 0, n, Charset.forName("GBK")));
                out.append(new String(b, 0, n, Charset.forName("GBK")));
            }
            System.out.println(out.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

