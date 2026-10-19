package toko;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class OrderHandler
    extends TextWebSocketHandler {
  public void afterConnectionEstablished(
      WebSocketSession s) throws Exception {
    s.sendMessage(new TextMessage(
        "Pesanan #1: diproses"));
  }
  protected void handleTextMessage(
      WebSocketSession s, TextMessage m) {
    System.out.println(m.getPayload());
  }
}