package org.lushen.mrh.http.server;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * springboot 启动类
 * 
 * @author hlm
 */
@SpringBootApplication
@ComponentScan(basePackageClasses=HttpServerRunner.class)
public class HttpServerRunner {

	public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(HttpServerRunner.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
		new CountDownLatch(1).await();
	}

}
