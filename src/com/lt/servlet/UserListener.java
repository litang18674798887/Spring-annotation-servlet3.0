package com.lt.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UserListener implements ServletContextListener {


    /**
     * 监听ServletContext销毁
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("UserListener ..... contextDestroyed");
    }

    /**
     * 监听ServletContext启动初始化
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("UserListener ..... contextInitialized");
    }
}
