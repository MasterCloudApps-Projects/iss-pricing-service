package es.urjc.code.pricing.infrastructure.adapter.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

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

import es.urjc.code.pricing.application.dto.CalculatePriceRequest;
import es.urjc.code.pricing.application.dto.QuestionAnswer;
import es.urjc.code.pricing.base.TestDataProvider;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PricingControllerIT {

	private static final String CODE_CAR = "CAR";

	@Container
	public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer().withPassword("inmemory")
			.withUsername("inmemory");

	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
	}

	@LocalServerPort
	private int port;

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

	private CalculatePriceRequest getCalculatePriceRequest() {
		ArrayList<QuestionAnswer> questionAnswers = new ArrayList();
		questionAnswers.add(new QuestionAnswer("NUM_OF_CLAIM", 1));
		return new CalculatePriceRequest.Builder().withProductCode(CODE_CAR).withPolicyFrom(LocalDate.of(2017, 4, 16))
				.withPolicyTo(LocalDate.of(2018, 4, 15)).withSelectedCovers(Collections.singletonList("C1"))
				.withAnswers(questionAnswers).build();
	}
}
