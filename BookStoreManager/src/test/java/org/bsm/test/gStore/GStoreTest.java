package org.bsm.test.gStore;

import org.bsm.util.HttpUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GZC-
 * @create 2021-07-04 9:00
 */
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

    String sparql1 = getTask1Sparql();
    paramsMap.put(
        "sparql", sparql1);
    String sparql2 = getTask2Sparql();
    paramsMap.put("sparql", sparql2);
    String sparql3 = getTask3Sparql();
    paramsMap.put("sparql",sparql3);
    //
    System.out.println(HttpUtils.sendPost(url, paramsMap));
  }

  @NotNull
  private String getTask3Sparql() {
    String companyName1 = "吉林敖东药业集团股份有限公司";
    String companyName2 = "广发证券股份有限公司";
    String sparql3 = "SELECT distinct  ?a ?re1 ?b \n" +
            "WHERE{ \n" +
            "?a ?re1 ?b.\n" +
            "?b ?re1 ?a.\n" +
            "FILTER ( ?a = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/"+companyName1+"> && ?b = <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/"+companyName2+"> ) \n" +
            "}";
    return sparql3;
  }

  @NotNull
  private String getTask1Sparql() {
    String sparql = "SELECT distinct  ?a ?re1 ?b ?re3 ?c\n"
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
    return sparql;
  }

  @NotNull
  private String getTask2Sparql() {
    int n = 3;
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
    String companyName = "招商银行股份有限公司";
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
