package com.postupashki.hm_0_websocket.client;

import com.postupashki.hm_0_websocket.handler.ConnectionWebSocketHandler;
import jakarta.websocket.ClientEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

/**
 * Клиент WebSocket для подключения к серверу и проверки получения сообщения "OK".
 * <p>
 * Этот класс является клиентом WebSocket, который инициирует handshake с сервером WebSocket на указанном URL.
 * При успешном соединении в лог выводится информация о начале handshake.
 * <p>
 * В случае возникновения исключений во время подключения, они логируются как ошибки.
 *
 * @see StandardWebSocketClient
 */
@ClientEndpoint
public class SimpleWebSocketClient {
    private static final Logger logger = LoggerFactory.getLogger(SimpleWebSocketClient.class);

    public static void main(String[] args) {
        try {
            StandardWebSocketClient client = new StandardWebSocketClient();
            client.doHandshake(new ConnectionWebSocketHandler(), "ws://localhost:8080/ws");
            logger.info("WebSocket handshake initiated.");
        } catch (Exception e) {
            logger.error("Error connecting to the WebSocket server", e);
        }
    }
}
