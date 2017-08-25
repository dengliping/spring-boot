package com.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication //相当于 @Configuration、@EnableAutoConfiguration和  @ComponentScan
@MapperScan("com.spring.dao") //dao扫描
@EnableScheduling //启用定时任务的配置
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
//