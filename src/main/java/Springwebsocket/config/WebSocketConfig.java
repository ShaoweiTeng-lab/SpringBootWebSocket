package Springwebsocket.config;

import Springwebsocket.filter.MyWebSocketHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    @Autowired
    private  MyWebSocketHandler webSocketHandler;

    /**
     * 自定義消息處理器
     */



    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/websocket")// 添加消息处理器
                .setAllowedOrigins("*")//設置跨域
                .addInterceptors(new MyWebSocketHandshakeInterceptor());
    }


    /**
     * 其他配置，如session空閒失效时间，消息緩衝區大小
     *
     * @return
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxSessionIdleTimeout(10 * 60 * 1000L);
        return container;
    }


}
