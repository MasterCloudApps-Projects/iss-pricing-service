package es.urjc.code.pricing.infrastructure.adapter.controller;

import static es.urjc.code.pricing.infrastructure.adapter.controller.BaseE2ETestCase.Resources.V1_CALCULATE_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;

import com.google.gson.JsonObject;

import es.urjc.code.pricing.base.TestDataProvider;
import io.restassured.response.ValidatableResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PricingControllerE2ETestCase extends BaseE2ETestCase {

	private static final String CODE_CAR = "CAR";

	@Test
	void shouldCalculatePrice() throws Exception {
        //given
        JsonObject calculatePriceRequestJson = TestDataProvider.getCalculatePriceRequestJson();

        //when
        ValidatableResponse response = given()
                .contentType("application/json")
                .body(calculatePriceRequestJson.toString())

                .when()
                .post(V1_CALCULATE_ENDPOINT.build())
                .prettyPeek()
                .then();

        //then
        response.statusCode(HttpStatus.CREATED.value())
                .body("totalPrice", notNullValue())
                .body("coversPrices", notNullValue());
	}
	
}
