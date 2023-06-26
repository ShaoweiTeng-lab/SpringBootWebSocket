package Springwebsocket.filter;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

public class MyWebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // 執行授權驗證邏輯，例如檢查令牌或使用者憑證
        if (isAuthorized(request)) {
            // 授權驗證通過，允許WebSocket握手繼續進行
            System.out.println("認證授權");
            return super.beforeHandshake(request, response, wsHandler, attributes);
        } else {
            // 授權驗證失敗，中止握手過程
            return false;
        }
    }
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // 連結建立後执行的邏輯
    }
    private boolean isAuthorized(ServerHttpRequest request) {
        // 實現您的授權驗證邏輯，例如檢查請求中的令牌或使用者憑證
        // 返回true表示
        return  true;
    }
}

