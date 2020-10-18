package pro.sisit.utils.webhookproxy.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import lombok.SneakyThrows;
import org.springframework.data.util.ReflectionUtils;

public class ObjectUtil {

    @SneakyThrows
    public static Object getFieldValue(Object obj, String fieldPath) {
        if (obj == null) {
            return null;
        }

        Object value = obj;
        String[] properties = fieldPath.split("\\.");
        for (String property : properties) {
            String methodName = "get" + Character.toUpperCase(property.charAt(0)) + property.substring(1);
            Method getter = ReflectionUtils.getMethod(value.getClass(), methodName).orElse(null);
            if (getter != null) {
                value = getter.invoke(value);
            } else {
                Field field = ReflectionUtils.findField(value.getClass(), f -> f.getName().equals(property));
                value = field.get(value);
            }
        }

        return value;
    }

    public static String getFieldName(Field field) {
        return field.getName();
    }

    public static String getFieldValue(Object object, Field field) {
        try {
            return String.valueOf(field.get(object));
        } catch (IllegalAccessException e) {
            return "";
        }
    }
}
