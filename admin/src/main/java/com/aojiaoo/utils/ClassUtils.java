package com.aojiaoo.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ClassUtils extends org.apache.commons.lang3.ClassUtils {

    public static List<String> getStringFields(String fullClassName, boolean isExceptPrivate) throws ClassNotFoundException {
        Field[] fields = org.apache.commons.lang3.ClassUtils.getClass(fullClassName).getDeclaredFields();
        List<String> stringFields = new ArrayList<>();
        for (Field field : fields) {
            if (!isExceptPrivate || !Modifier.isPrivate(field.getModifiers())) {
                stringFields.add(field.getName());
            }
        }
        return stringFields;
    }

    public static List<String> getStringFields(String fullClassName) throws ClassNotFoundException {
        return getStringFields(fullClassName, false);
    }

    public static Field[] getFields(String fullClassName) throws ClassNotFoundException {
        return getClass(fullClassName).getFields();
    }

    public static Map<String, String> getParameterStringMap(Object[] parameters) {
        if (parameters == null) {
            return null;
        }

        Map<String, String> parametersMap = new LinkedHashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            parametersMap.put("arg" + i, parameters[i].toString());
        }
        return parametersMap;
    }
}
