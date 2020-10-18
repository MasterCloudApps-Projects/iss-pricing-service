package es.urjc.code.pricing.infrastructure.adapter.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.google.gson.JsonObject;

import es.urjc.code.pricing.base.TestDataProvider;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PricingControllerIT {

	private static final String CODE_CAR = "CAR";

	@Container
	public static PostgreSQLContainer postgresContainer = new PostgreSQLContainer("postgres:9.6.15").withDatabaseName("pricing")
			.withUsername("postgres").withPassword("password");

	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		registry.add("spring.datasource.password", postgresContainer::getPassword);
		registry.add("spring.datasource.username", postgresContainer::getUsername);
	}

	@LocalServerPort
	private int port;

	@BeforeAll
	static void setUpAll() {
		if (!postgresContainer.isRunning()) {
			 postgresContainer.start();
		}
	}
	
	@BeforeEach
	void setUpBeforeEach() {
		RestAssured.port = this.port;
	}

	@Test
	void shouldCalculatePrice() throws Exception {
        //given
        JsonObject calculatePriceRequestJson = TestDataProvider.getCalculatePriceRequestJson();

        //when
        ValidatableResponse response = given()
                .contentType("application/json")
                .body(calculatePriceRequestJson.toString())

                .when()
                .post("/api/v1/calculate")
                .prettyPeek()
                .then();

        //then
        response.statusCode(HttpStatus.CREATED.value())
                .body("totalPrice", notNullValue())
                .body("coversPrices", notNullValue());
	}
	
}
