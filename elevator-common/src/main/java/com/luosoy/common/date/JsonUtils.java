package com.luosoy.common.date;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;


public final class JsonUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * The mapper.
     */
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 不可实例化.
     */
    private JsonUtils() {
    }

    /**
    * @Title: getTypeFactory
    * @Description:
    * @param
    * @return TypeFactory    返回类型
    */
    public static TypeFactory getTypeFactory() {
        return mapper.getTypeFactory();
    }
    /**
     * 将对象转换为JSON字符串.
     *
     * @param value
     *            对象
     * @return JSOn字符串
     */
    public static String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            LOGGER.warn("Generate JSON String Error: " + value, e);
        }
        return null;
    }

    /**
     * 将JSON字符串转换为对象.
     *
     * @param <T>
     *            the generic type
     * @param json
     *            JSON字符串
     * @param valueType
     *            对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            LOGGER.warn("Parse JSON String Error: " + json, e);
        }
        return null;
    }

    /**
     * To object with exception.
     *
     * @param <T>
     *            the generic type
     * @param json
     *            the json
     * @param valueType
     *            the value type
     * @return the t
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static <T> T toObjectWithException(String json, Class<T> valueType) throws IOException {
        return mapper.readValue(json, valueType);
    }

    /**
     * 将JSON字符串转换为对象.
     *
     * @param json
     *            JSON字符串
     * @param typeReference
     *            对象类型
     * @return 对象
     */
    public static Object toObject(String json, TypeReference<?> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            LOGGER.warn("Parse JSON String Error: " + json, e);
        }
        return null;
    }

    /**
     * 将JSON字符串转换为对象.
     *
     * @param json
     *            JSON字符串
     * @param javaType
     *            对象类型
     * @return 对象
     */
    public static Object toObject(String json, JavaType javaType) {
        try {
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            LOGGER.warn("Parse JSON String Error: " + json, e);
        }
        return null;
    }

    /**
     * 将对象转换为JSON流.
     *
     * @param writer
     *            writer
     * @param value
     *            对象
     */
    public static void writeValue(Writer writer, Object value) {
        try {
            mapper.writeValue(writer, value);
        } catch (IOException e) {
            LOGGER.warn("Generate JSON String Error: " + value, e);
        }
    }

}
