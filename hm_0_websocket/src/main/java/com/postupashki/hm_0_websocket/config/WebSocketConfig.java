package com.postupashki.hm_0_websocket.config;

import com.postupashki.hm_0_websocket.handler.ConnectionWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Конфигурационный класс для настройки WebSocket в приложении.
 * <p>
 * Этот класс реализует интерфейс {@link WebSocketConfigurer} и настраивает обработчики WebSocket-запросов.
 * <p>
 * Метод {@code registerWebSocketHandlers} регистрирует {@link ConnectionWebSocketHandler} на эндпоинте "/ws".
 * <p>
 * Дополнительно разрешены все домены для CORS посредством вызова {@code setAllowedOrigins("*")}.
 *
 * @see WebSocketConfigurer
 * @see ConnectionWebSocketHandler
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(new ConnectionWebSocketHandler(), "/ws")
                .setAllowedOrigins("*");
    }
}
