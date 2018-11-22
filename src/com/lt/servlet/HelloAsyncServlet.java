package com.lt.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/async",asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.开启异步处理 asyncSupported = true

        //2.开启异步模式
        System.out.println("主线程 ..... start" + Thread.currentThread());
        AsyncContext asyncContext = req.startAsync();

        //3.业务逻辑进行异步处理；开始异步处理
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("子线程。。。。。。。开始" + Thread.currentThread());
                    sayHello();

                    //获取异步上下文
                    AsyncContext asyncContext1 = req.getAsyncContext();
                    ServletResponse response = asyncContext1.getResponse();

                    response.getWriter().write("hello async");
                    System.out.println("子线程。。。。。。。end" + Thread.currentThread());

                    //此方法应该在子线程完成结束之后才可以调用！！！
                    asyncContext.complete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                }
            }
        });

        System.out.println("主线程 ..... end" + Thread.currentThread());

    }

    public void sayHello() throws InterruptedException {
        System.out.println(Thread.currentThread() + "ing..........");
        Thread.sleep(3000);
    }
}
