/*
 * created by 2019年7月9日 上午11:46:33
 */
package com.demo2.customer;

import com.alibaba.cloud.nacos.ConditionalOnNacosDiscoveryEnabled;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author fangang
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.demo2","com.mars"})
@ImportResource(locations={"classpath*:applicationContext-*.xml"})
@MapperScan("com.mars.support.dao")
@EnableDiscoveryClient
public class CustomerApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
		System.out.println(".....................................");
		System.out.println("...The Customer Application started..");
		System.out.println(".....................................");
	}

}
