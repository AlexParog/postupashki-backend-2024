package com.postupashki.hm_0_websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Обработчик WebSocket-соединений, который отвечает за установку и закрытие соединения.
 * <p>
 * Этот класс расширяет {@link TextWebSocketHandler} и переопределяет метод {@code afterConnectionEstablished}
 * для обработки событий установления соединения. При установлении соединения отправляется сообщение "OK",
 * после чего соединение сразу закрывается.
 * <p>
 * Логирование используется для отслеживания событий установления и закрытия соединения.
 *
 * @see TextWebSocketHandler
 */
@Service
public class ConnectionWebSocketHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("The connection is established with: {}", session.getRemoteAddress());
        session.sendMessage(new TextMessage("OK"));
        session.close();
        logger.info("The connection is closed with: {}", session.getRemoteAddress());
    }
}
