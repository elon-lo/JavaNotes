package com.yu.socket.controller;

import com.yu.socket.config.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author elonlo
 * @date 2023/6/24 19:06
 */
@Controller
@RequestMapping("/ws")
public class WebSocketController {

    /**
     * 访问websocket连接页面
     *
     * @return {@link String}
     */
    @GetMapping("/main")
    public String main() {
        return "main";
    }

    /**
     * 服务器主动推送消息
     *
     * @param cid     推送消息的指定客户端id
     * @param message 推送消息
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @ResponseBody
    @GetMapping("/socket/push")
    public Map<String, Object> pushToWeb(@RequestParam(value = "cid", required = false) String cid, String message) {
        Map<String, Object> result = new HashMap<>();
        // 客户端id为空则推送到所有客户端,不为空则推送至指定客户端
        if (StringUtils.isEmpty(cid)) {
            WebSocketServer.sendInfo(message, null);
        } else {
            WebSocketServer.sendInfo(message, cid);
        }
        result.put("code", cid);
        result.put("msg", message);

        return result;
    }
}
