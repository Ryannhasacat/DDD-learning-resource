/*
 * Created by 2020-05-03 20:35:13 
 */
package com.demo2.contract;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo2.customer.CustomerApplication;
import com.mars.support.web.OrmController;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import java.util.HashMap;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
/**
 * @author fangang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=CustomerApplication.class)
public class ContractBase {
	@Autowired
	private OrmController controller;
	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(controller);
	}

	@Test
	public void test(){

		HashMap<String, Object> params = new HashMap<>();

		given()
				.params(params)
				.when()
				.post("/orm/customer").
		then().
				statusCode(200).
				body("content", equalTo("Hello, Johan!"));

	}
}
