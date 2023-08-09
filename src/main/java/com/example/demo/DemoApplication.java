package com.example.demo;

import io.vertx.rxjava.core.Vertx;
import io.vertxbeans.rxjava.VertxBeans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(VertxBeans.class)
public class DemoApplication {



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}


}
