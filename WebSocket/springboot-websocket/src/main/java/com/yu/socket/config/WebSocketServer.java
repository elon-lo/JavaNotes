package com.yu.socket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author elonlo
 * @date 2023/6/24 19:04
 */
@Component
@Slf4j
@ServerEndpoint("/api/websocket/{sid}")
public class WebSocketServer {

    /**
     * 记录当前在线连接数
     */
    private static final AtomicInteger onlineCount = new AtomicInteger();

    /**
     * concurrent包的线程安全Set,具体存什么根据业务情况自己定义
     */
    private static final CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收sid,客户端与服务器需一致
     */
    private String sid = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        // 连接关闭,从set中添加当前连接对象
        webSocketSet.add(this);
        this.sid = sid;
        // 连接建立成功,在线数加1
        addOnlineCount();
        try {
            sendMessage("websocket connect success......");
            log.info("有新客户端开始监听:" + sid + ",当前在线人数为:" + getOnlineCount());
        } catch (IOException e) {
            log.error("websocket连接建立失败: []", e);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        // 连接关闭,从set中移除当前连接对象
        webSocketSet.remove(this);
        // 连接关闭,在线数减1
        subOnlineCount();
        log.info("释放的sid为：" + sid);
        // 连接关闭后需要处理的业务
        log.info("有一客户端连接关闭！当前在线人数为" + getOnlineCount());

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @ Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自客户端" + sid + "的信息:" + message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @ Param session
     * @ Param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("websocket未知异常: []", error);
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) {
        for (WebSocketServer item : webSocketSet) {
            try {
                // 这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null || "".equals(sid)) {
                    log.info("推送消息到所有客户端,推送内容:" + message);
                    item.sendMessage(message);
                } else if (sid.equals(item.sid)) {
                    log.info("推送消息到客户端" + sid + ",推送内容:" + message);
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                log.error("服务器推送消息到客户端失败: []", e);
                e.printStackTrace();
            }
        }
    }

    public static int getOnlineCount() {
        return onlineCount.get();
    }

    public static void addOnlineCount() {
        WebSocketServer.onlineCount.getAndIncrement();
    }

    public static void subOnlineCount() {
        WebSocketServer.onlineCount.getAndDecrement();
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}
