package com.chris.learn;

import java.nio.charset.StandardCharsets;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */

public class Send {

  private static final Logger log = LoggerFactory.getLogger(Send.class);

  private static final String QUEUE_NAME = "hello";

  public static void main(String[] args) {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.1.2");
    try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel();) {
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      String message = "Hello World!";
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
      System.out.println(" [x] Sent '" + message + "'");
    } catch (Exception e) {
      log.info("[exception] {}", e.getMessage());
      e.printStackTrace();
    }
  }
}
