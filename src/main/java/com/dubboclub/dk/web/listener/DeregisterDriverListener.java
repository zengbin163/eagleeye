package com.dubboclub.dk.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Created by tzwang on 2017/1/8.
 * 解除jdbc驱动注册
 */
public class DeregisterDriverListener implements ServletContextListener,ServletRequestListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        deregisterDriver();
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        //deregisterDriver();
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }

    private void deregisterDriver(){
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
