package org.bsm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON工具类，主要使用jackson实现
 *
 * @author Corvey
 * @version 1.0.0
 */
public class DataUtils {

    private static final String CHARSET_NAME = "UTF-8";
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        // 设置忽略未知的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 从URL地址中获取json（字符串形式）
     *
     * @param url 目标地址
     * @return json（字符串形式）
     * @throws IOException
     */
    public static String getJsonStringFromURL(String url) throws IOException {
        URL _url = new URL(url);
        InputStream in = _url.openStream();
        String ret = inputStream2String(in);
        in.close();
        return ret;
    }

    /**
     * 从URL地址中获取HTML（字符串形式）
     *
     * @param url 目标地址
     * @return HTML（字符串形式）
     * @throws IOException
     */
    public static String getHtmlStringFromURL(String url) throws IOException {
        URL _url = new URL(url);
        InputStream in = _url.openStream();
        String ret = inputStream2String(in);
        in.close();
        return ret;
    }

    /**
     * 从json（字符串形式）中获取某一属性
     *
     * @param jsonString json（字符串形式）
     * @param fieldName  属性名
     * @return json中该属性的值（字符串形式）
     * @throws IOException
     */
    public static String getField(String jsonString, String fieldName)
            throws IOException {
        JsonNode node = mapper.readTree(jsonString);
        return node.get(fieldName).toString();
    }

    /**
     * 将json（字符串形式）转换为java中的实例
     *
     * @param jsonString json（字符串形式）
     * @param valueType  转换目标的类
     * @return valueType的实例
     * @throws IOException
     */
    public static <T> T json2Object(String jsonString, Class<T> valueType)
            throws IOException {
        return mapper.readValue(jsonString, valueType);
    }

    /**
     * 将JS中的数组（字符串形式）转换为java中的列表
     *
     * @param jsArrayString   JS的数组（字符串形式）
     * @param collectionClass 列表的实现类
     * @param elementClass    列表元素所属类
     * @return collectionClass的实例
     * @throws IOException
     */
    public static <T> T json2List(String jsArrayString, Class<?> collectionClass, Class<?> elementClass)
            throws IOException {
        JavaType jt = getCollectionType(collectionClass, elementClass);
        return mapper.readValue(jsArrayString, jt);
    }

    /**
     * 将Java中的实例转换为json（字符串形式）
     *
     * @param object 实例
     * @return json（字符串形式）
     */
    public static String object2Json(Object object) {
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    /**
     * 由列表实现的类和列表元素的类，构造jackson中的JavaType
     *
     * @param collectionClass 列表实现类
     * @param elementClass    列表元素类
     * @return jackson中的JavaType
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?> elementClass) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
    }

    /**
     * 获取inputStream的内容并返回
     *
     * @param in inputStream
     * @return inputStream的内容
     * @throws IOException
     */
    private static String inputStream2String(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, CHARSET_NAME));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }
}
