package org.bsm.test.gStore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bsm.util.HttpUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author GZC-
 * @create 2021-07-04 9:00
 */
class TypeValue {
  String type;

  String value;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}

public class GStoreTest {
  @Test
  public void gStoreTest() {
    // 从redis 中获取AccessKeyID，AccessSecret
    ApplicationContext ac =
        new ClassPathXmlApplicationContext(
            new String[] {"classpath:spring.xml", "classpath:spring-hibernate.xml"});
    RedisTemplate<String, String> redisTemplate =
        (RedisTemplate<String, String>) ac.getBean("redisTemplate");
    String AccessKeyID = redisTemplate.opsForValue().get("gStoreAccessKeyID");
    String AccessSecret = redisTemplate.opsForValue().get("gStoreAccessSecret");
    String url = "http://cloud.gstore.cn/api";
    Map<String, String> paramsMap = new HashMap<>();
    paramsMap.put("accesskeyid", AccessKeyID);
    paramsMap.put("access_secret", AccessSecret);
    paramsMap.put("action", "queryDB");
    paramsMap.put("dbName", "jinrong");
    // TODO
    String companyName1 = "中国东方航空股份有限公司";
    String companyName2 = "上海浦东发展银行股份有限公司";
    task3(url, paramsMap, companyName1, companyName2);
  }

  private void task3(
      String url, Map<String, String> paramsMap, String companyName1, String companyName2) {
    boolean task3Result = false;
    String sparql1 =
        "ASK {"
            + "?x <http://localhost:2020/vocab/resource/holder_copy_holder_name>+ ?y \n"
            + "FILTER(?x = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/"
            + companyName1
            + ">)\n"
            + "FILTER(?y = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/"
            + companyName2
            + ">)"
            + "}";
    paramsMap.put("sparql", sparql1);
    boolean ask1 = false;
    boolean ask2 = false;
    getAskResult(url, paramsMap);
    String temp = companyName1;
    companyName1 = companyName2;
    companyName2 = temp;
    String sparql2 =
        "ASK {"
            + "?x <http://localhost:2020/vocab/resource/holder_copy_holder_name>+ ?y \n"
            + "FILTER(?x = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/"
            + companyName1
            + ">)\n"
            + "FILTER(?y = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/"
            + companyName2
            + ">)"
            + "}";
    paramsMap.put("sparql", sparql2);
    getAskResult(url, paramsMap);
    task3Result = ask1 && ask2;
  }

  private void getAskResult(String url, Map<String, String> paramsMap) {
    boolean ask1;
    JSONObject jsonObject = JSON.parseObject(HttpUtils.sendPost(url, paramsMap));
    String data = jsonObject.getString("data");
    JSONObject results = JSON.parseObject(data);
    String resultsString = results.getString("results");
    JSONObject bindingsString = JSON.parseObject(resultsString);
    JSONArray bindings = bindingsString.getJSONArray("bindings");
    for (Object binding : bindings) {
      JSONObject json = (JSONObject) binding;
      int j = 0;
      String askResult = json.getString("askResult");
      TypeValue ask = JSON.parseObject(askResult, TypeValue.class);
      ask1 = Boolean.parseBoolean(ask.getValue());
    }
  }

  private void task1(String url, Map<String, String> paramsMap) {
    String sparql1 = getTask1Sparql();
    paramsMap.put("sparql", sparql1);
    HttpUtils.sendPost(url, paramsMap);
  }

  private void tesk2(String url, Map<String, String> paramsMap) {
    Set<String> resultSet = new HashSet<>();
    String sparql2 = getTask2Sparql();
    paramsMap.put("sparql", sparql2);
    JSONObject jsonObject = JSON.parseObject(HttpUtils.sendPost(url, paramsMap));
    String data = jsonObject.getString("data");
    JSONObject results = JSON.parseObject(data);
    String resultsString = results.getString("results");
    JSONObject bindingsString = JSON.parseObject(resultsString);
    JSONArray bindings = bindingsString.getJSONArray("bindings");
    for (Object binding : bindings) {
      JSONObject json = (JSONObject) binding;
      int j = 0;
      while (json.getString("com" + j) != null) {
        String companyName = json.getString("com" + j++);
        TypeValue company = JSON.parseObject(companyName, TypeValue.class);
        resultSet.add(company.value);
      }
    }
    for (String company : resultSet) {
      System.out.println(company);
    }
  }

  @NotNull
  private String getTask3Sparql() {
    // TODO
    String companyName1 = "吉林敖东药业集团股份有限公司";
    String companyName2 = "广发证券股份有限公司";
    String sparql3 =
        "SELECT distinct  ?a ?re1 ?b \n"
            + "WHERE{ \n"
            + "?a ?re1 ?b.\n"
            + "?b ?re1 ?a.\n"
            + "FILTER ( ?a = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/"
            + companyName1
            + "> && ?b = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/"
            + companyName2
            + "> ) \n"
            + "}";
    return sparql3;
  }

  @NotNull
  private String getTask1Sparql() {
    return "SELECT distinct  ?a ?re1 ?b ?re3 ?c\n"
        + "WHERE{ {\n"
        + "?a ?re1 ?b.\n"
        + "?b ?re3 ?c.\n"
        + "FILTER ( ?a = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/招商银行股份有限公司> && ?c = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/招商局轮船股份有限公司> ) \n"
        + "}\n"
        + "UNION\n"
        + "{\n"
        + "?a ?re1 ?b.\n"
        + "?b ?re3 ?c.\n"
        + "FILTER ( ?c = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/招商银行股份有限公司> && ?a = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/招商局轮船股份有限公司> ) \n"
        + "}\n"
        + "}";
  }

  @NotNull
  private String getTask2Sparql() {
    String companyName = "融科物业投资有限公司";
    int n = 4;
    StringBuilder resultSb = new StringBuilder();
    StringBuilder whereSb = new StringBuilder();
    for (int i = 0; i <= n; i++) {
      if (i == n) {
        resultSb.append("?com").append(i);
      } else {
        resultSb.append("?com").append(i).append(" ").append("?re").append(i).append(" ");
        whereSb
            .append("?com")
            .append(i)
            .append(" ")
            .append("?re")
            .append(i)
            .append(" ")
            .append("?com")
            .append(i + 1)
            .append(".\n");
      }
    }
    String sparql =
        "SELECT distinct  "
            + resultSb.toString()
            + "\n"
            + "WHERE{ \n"
            + whereSb.toString()
            + "FILTER ( ?com0 = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/"
            + companyName
            + "> ) \n"
            + "}";
    System.out.println(sparql);
    return sparql;
  }
}
