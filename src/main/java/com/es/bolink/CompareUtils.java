package com.es.bolink;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CompareUtils {

    /**
     * @param oldObject 对象1
     * @param newObject 对象2
     * @return 属性差异比较结果map
     */
    @SuppressWarnings("rawtypes")
    public static StringBuilder compareFields(Object oldObject, Object newObject, Map<String, String> noteMap, StringBuilder builder) {
        int start = builder.length();
        try {
            if (noteMap.isEmpty()) return builder.replace(start-1, start, "");
            if (Objects.nonNull(oldObject) && Objects.nonNull(newObject) && oldObject.getClass() == newObject.getClass()) {
                Class clazz = oldObject.getClass();
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {
                    String name = pd.getName();
                    Method readMethod = pd.getReadMethod();
                    Object oldValue = readMethod.invoke(oldObject);
                    Object newValue = readMethod.invoke(newObject);
                    if (oldValue instanceof List) {
                        continue;
                    }
                    if (newValue instanceof List) {
                        continue;
                    }
                    if (oldValue == null && newValue == null) {
                        continue;
                    } else if (oldValue == null) {
                        if (noteMap.get(name) != null) {
                            oldValue = "空";
                            builder.append(noteMap.get(name)).append(":").append(oldValue).append("->").append(newValue).append(";");
                        }
                        continue;
                    }
                    if (!oldValue.equals(newValue)) {
                        newValue = newValue == null ? "空": newValue;
                        if (noteMap.get(name) != null) {
                            builder.append(noteMap.get(name)).append(":").append(oldValue).append("->").append(newValue).append(";");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (builder.length() == start) return builder.replace(builder.length()-1, builder.length(), "");
        return builder.replace(builder.length() - 1, builder.length(),")");
    }

    public static StringBuilder compareMap(Map<String, Object> oldMap, Map<String, Object> newMap, Map<String, String> noteMap, StringBuilder builder) {
        if (newMap.isEmpty()) return builder.replace(builder.length() - 1, builder.length(),")");
        int start = builder.length();
        try {
            newMap.keySet().forEach(key -> {
                Object oldValue = oldMap.get(key);
                Object newValue = newMap.get(key);
                if (!(oldValue instanceof List) && !(newValue instanceof List)) {
                    if (oldValue== null) {
                        oldValue = "空";
                        builder.append(noteMap.get(key)).append(":").append(oldValue).append("->").append(newValue).append(";");
                    } else if (!oldValue.equals(newValue)) {
                        if (noteMap.get(key) != null) {
                            builder.append(noteMap.get(key)).append(":").append(oldValue).append("->").append(newValue).append(";");
                        }
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (builder.length() == start) return builder.replace(builder.length()-1, builder.length(), "");
        return builder.replace(builder.length() - 1, builder.length(),")");
    }

    @SuppressWarnings("rawtypes")
    public static Map<String, Object> pojoToMap(Object pojo) {
        Map<String, Object> map = new HashMap<>();
        try {
            Class clazz = pojo.getClass();
            PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                String name = pd.getName();
                Method readMethod = pd.getReadMethod();
                Object value = readMethod.invoke(pojo);
                if (value instanceof List) {
                    continue;
                }
                if (value != null) {
                    map.put(name, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
