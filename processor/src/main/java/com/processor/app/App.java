package com.processor.app;

import com.cart.app.config.EnableJobCart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableJobCart
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class,args);
    }
}
