
package toko;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductWebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions =
            ConcurrentHashMap.newKeySet();

    private static final String EVENT_PESANAN =
            "{\"id\":\"pesanan-1\","
            + "\"pesan\":\"Pesanan #1: diproses\"}";

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        sessions.add(session);
        session.sendMessage(new TextMessage(EVENT_PESANAN));
    }

    @Override
    protected void handleTextMessage(
            WebSocketSession session, TextMessage message)
            throws Exception {
        session.sendMessage(new TextMessage(
                "{\"id\":\"balasan-" + System.currentTimeMillis()
                + "\",\"pesan\":\"Server menerima pesan\"}"
        ));
    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }
}
