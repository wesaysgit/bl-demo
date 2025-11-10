package com.es.log;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class LogUtils {



    Logger logger = Logger.getLogger(LogUtils.class);



    public Long getPlateId(Long pid, Map<String, Object> user) {
        Long plateId = 0L;
        switch (pid.intValue()) {
            case 2:
                plateId = (Long) user.get("union_id");
                break;
            case 3:
                plateId = (Long) user.get("server_id");
                break;
            case 4:
                plateId = (Long) user.get("park_id");
                break;
            case 5:
                plateId = (Long) user.get("operator_id");
                break;
        }
        return plateId;
    }

    public String compareMap(
            Map<String, Object> oldMap,
            Map<String, Object> newMap,
            Map<String, String> noteMap,
            StringBuilder builder,
            Map<String, Map<Integer, String>> mappingMap
    ) {
        try {
            int length = builder.length();
            newMap.keySet().forEach(key -> {
                String note = noteMap.get(key);
                Object oldValue = oldMap.get(key) == null ? "空" : oldMap.get(key);
                Object newValue = newMap.get(key) == null ? "空": newMap.get(key);
                if (note != null) {
                    if (!(oldValue instanceof List) && !(newValue instanceof List)) {
                        if (!Objects.equals(oldValue, newValue)) {
                            if (oldValue instanceof Integer && newValue instanceof Integer && mappingMap != null && mappingMap.get(key) != null) {
                                Map<Integer, String> typeMap = mappingMap.get(key);
                                oldValue = typeMap.get(oldValue)==null?oldValue:typeMap.get(oldValue);
                                newValue = typeMap.get(newValue)==null?newValue:typeMap.get(newValue);
                            }
                            builder.append(note).append("由").append(oldValue).append("修改为").append(newValue).append(",");
                        }
                    }

                }
            });
            if (length != builder.length()) {
                return builder.substring(0, builder.length()-1);
            }
        } catch (Exception e) {
            logger.error("-拼接日志描述错误", e);
        }
        return builder.toString();
    }

    /**
     * @param oldObject 对象1
     * @param newObject 对象2
     */
    @SuppressWarnings("rawtypes")
    public StringBuilder comparePojo(Object oldObject, Object newObject, Map<String, String> noteMap, StringBuilder builder) {
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
            if (builder.length() == start) return builder.replace(builder.length()-1, builder.length(), "");
            return builder.replace(builder.length() - 1, builder.length(),")");
        } catch (Exception e) {
            logger.error("--拼接日志描述错误", e);
        }
        return builder.replace(start-1, start, "");
    }

    @SuppressWarnings("rawtypes")
    public Map<String, Object> pojoToMap(Object pojo) {
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
