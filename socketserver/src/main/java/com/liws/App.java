package com.liws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = null;
        if (args.length == 0) {
            applicationContext = new ClassPathXmlApplicationContext("server.xml");
        } else {
            applicationContext = new ClassPathXmlApplicationContext(args);
        }
    }
}
