package org.bsm.rabbitMQ;

import org.apache.htrace.shaded.fasterxml.jackson.databind.JsonNode;
import org.apache.htrace.shaded.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.IOException;

/**
 * @author GZC-
 * @create 2021-06-19 20:10
 */
public class RecvHandler implements MessageListener {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public void onMessage(Message msg) {
    try {
      JsonNode jsonData = MAPPER.readTree(msg.getBody());
      System.out.println(
          "我是可爱的小猪,我的id是" + jsonData.get("id").asText() + ",我的名字是" + jsonData.get("name").asText());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
