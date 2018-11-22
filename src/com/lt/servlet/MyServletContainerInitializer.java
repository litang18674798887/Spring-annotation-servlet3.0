package com.lt.servlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * 容器启动的时候会将@HandlesTypes指定的这个类型下面的子类（实现类，子接口）传递过来
 *
 * 传入感兴趣的类型
 */
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {

    /**
     * 应用启动的时候，会运行onStartup方法：
     *  ServletContext servletContext ：代表当前web 应用的ServletContext:一个Web引用一个context
     *  Set<Class<?>> set  感兴趣的类型的所有子类型
     *
     *  1.使用ServletContext注册Web组件（Servlet Filter Listener）
     *  2.使用编码的方式，在项目启动的时候给ServletContext里面添加组件
     *      必须在项目启动的时候来添加
     *      1.ServletContainerInitializer 得到ServletContext
     *      2.ServletContextListener 得到的ServletContext
     *
     *
     * @param set
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {

        System.out.println("感兴趣的类型：");
        for (Class<?> aClass : set) {
            System.out.println(aClass);
        }

        //注册组件 ServletRegistration
        ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet", new UserServlet());
        //配置Servlet的映射信息
        userServlet.addMapping("/user");

        //注册Listener
        servletContext.addListener(UserListener.class);

        //注册Filter FilterRegistration
        FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter", UserFilter.class);
        //配置Filter的映射信息
        userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");


    }
}
