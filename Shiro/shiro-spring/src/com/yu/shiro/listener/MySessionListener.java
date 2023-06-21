package com.yu.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * 自定义会话监听器
 *
 * @author elonlo
 * @date 2023/6/20 9:23
 */
public class MySessionListener implements SessionListener {

    // 会话创建
    @Override
    public void onStart(Session session) {
        System.out.println("会话创建: " + session.getId());
    }

    // 退出会话
    @Override
    public void onStop(Session session) {
        System.out.println("会话退出: " + session.getId());
    }

    // 会话过期
    @Override
    public void onExpiration(Session session) {
        System.out.println("会话过期: " + session.getId());
    }
}
