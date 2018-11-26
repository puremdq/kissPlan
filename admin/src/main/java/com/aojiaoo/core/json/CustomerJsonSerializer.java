package com.aojiaoo.core.json;

import com.aojiaoo.core.json.annotation.JSON;
import com.aojiaoo.modules.sys.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

/**
 * depend on jackson
 *
 * @author Diamond
 */
public class CustomerJsonSerializer {


    private ObjectMapper mapper = new ObjectMapper();
    private JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();

    /**
     * @param clazz   target type
     * @param include include fields
     * @param filter  filter fields
     */
    public void filter(Class<?> clazz, String include, String filter) {
        if (clazz == null) return;
        if (StringUtils.isNotBlank(include)) {
            jacksonFilter.include(clazz, include.split(","));
        }
        if (StringUtils.isNotBlank(filter)) {
            jacksonFilter.filter(clazz, filter.split(","));
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    public String toJson(Object object) throws JsonProcessingException {
        mapper.setFilterProvider(jacksonFilter);
        return mapper.writeValueAsString(object);
    }

    public void filter(JSON json) {
        this.filter(json.type(), json.include(), json.filter());
    }

    public static void main(String args[]) {
        CustomerJsonSerializer cjs = new CustomerJsonSerializer();
        // 设置转换 Article 类时，只包含 id, name
        cjs.filter(User.class, "id,name", null);
        try {
            String include = cjs.toJson(new User());

            cjs = new CustomerJsonSerializer();
            // 设置转换 Article 类时，过滤掉 id, name
            cjs.filter(User.class, "id", null);

            String filter = cjs.toJson(new User(1));

            System.out.println("include: " + include);
            System.out.println("filter: " + filter);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}