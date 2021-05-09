package org.bsm.util;

import java.lang.reflect.Field;

import org.bsm.pageModel.Book;
import org.springframework.util.StringUtils;


/**
 * API工具类
 *
 * @version 1.0.0
 */
public class APIUtils {


    /**
     * 根据搜索条件构造豆瓣API地址
     * <p>注意：关键词与标签两者必填其一
     *
     * @param q     关键词
     * @param tag   标签
     * @param start 起始位置，默认为0
     * @param count 要求搜索结果条数，默认为 DEFAULT_COUNT
     * @return API地址
     */
    public static String createFindBooksURL(String isbn) {
        final String url = "http://49.234.70.238:9001/book/worm/isbn?";
        StringBuilder sb = new StringBuilder(url);
        if (!StringUtils.isEmpty(isbn)) {
            sb.append("isbn=");
            sb.append(isbn);
        }
        return sb.toString();
    }
}
