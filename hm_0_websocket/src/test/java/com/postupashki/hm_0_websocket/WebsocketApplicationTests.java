package com.postupashki.hm_0_websocket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс для интеграционного теста с использованием {@link SpringBootTest}.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebsocketApplicationTests {

    public static final String URI = "ws://localhost:";
    public static final String HANDLER = "/ws";
    public static final String EXPECTED_STATUS = "OK";
    /**
     * Номер порта.
     * Автоматические инжектируем порт с помощью {@link LocalServerPort}, на котором запущен веб-сервер.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * Тест проверяет основной функционал WebSocket-сервера и клиента, обеспечивая, что соединение устанавливается
     * правильно, и сервер возвращает ожидаемое сообщение равное "OK".
     *
     * @throws Exception любая ошибка на стороне сервера
     */
    @Test
    public void testWebSocketConnection() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        // WebSocket-клиент, который реализован на основе стандартного API WebSocket в Java (JSR 356)
        StandardWebSocketClient client = new StandardWebSocketClient();
        // подмножество HTTP-заголовков, специфичных для WebSocket-протокола
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

        // ConnectionWebSocketHandler является потомком AbstractWebSocketHandler
        client.doHandshake(new AbstractWebSocketHandler() {
            @Override
            protected void handleTextMessage(org.springframework.web.socket.WebSocketSession session,
                                             org.springframework.web.socket.TextMessage message) {
                assertEquals(EXPECTED_STATUS, message.getPayload());
                latch.countDown();
            }
        }, URI + serverPort + HANDLER, headers);

        latch.await(1, TimeUnit.SECONDS);
    }

}
