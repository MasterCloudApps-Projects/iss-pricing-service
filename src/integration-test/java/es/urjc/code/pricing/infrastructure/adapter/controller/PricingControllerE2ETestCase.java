package es.urjc.code.pricing.infrastructure.adapter.controller;

import static es.urjc.code.pricing.infrastructure.adapter.controller.BaseE2ETestCase.Resources.V1_CALCULATE_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.google.gson.JsonObject;

import es.urjc.code.pricing.base.TestDataProvider;
import io.restassured.response.ValidatableResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PricingControllerE2ETestCase extends BaseE2ETestCase {
	
	private static final String COVERS_PRICES_ATTRIBUT = "coversPrices";
	private static final String TOTAL_PRICE_ATTRIBUT = "totalPrice";
	private static final String CALCULATE_PRICE_CAR = "data/calculatePriceRequestCar.json";
	private static final String CALCULATE_PRICE_FARM = "data/calculatePriceRequestFarm.json";
	private static final String CALCULATE_PRICE_HOUSE = "data/calculatePriceRequestHouse.json";
	private static final String CALCULATE_PRICE_TRAVEL = "data/calculatePriceRequestTravel.json";

	@Test
	void shouldCalculatePriceCarPolicy() {
        //given
        JsonObject calculatePriceRequestJson = TestDataProvider.getCalculatePriceRequestJson(CALCULATE_PRICE_CAR);

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
                .body(TOTAL_PRICE_ATTRIBUT, notNullValue())
                .body(COVERS_PRICES_ATTRIBUT, notNullValue());
	}
	
	@Test
	void shouldCalculatePriceHousePolicy() {
        //given
        JsonObject calculatePriceRequestJson = TestDataProvider.getCalculatePriceRequestJson(CALCULATE_PRICE_HOUSE);

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
                .body(TOTAL_PRICE_ATTRIBUT, notNullValue())
                .body(COVERS_PRICES_ATTRIBUT, notNullValue());
	}
	
	@Test
	void shouldCalculatePriceTravelPolicy() {
        //given
        JsonObject calculatePriceRequestJson = TestDataProvider.getCalculatePriceRequestJson(CALCULATE_PRICE_TRAVEL);

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
                .body(TOTAL_PRICE_ATTRIBUT, notNullValue())
                .body(COVERS_PRICES_ATTRIBUT, notNullValue());
	}
	
	@Test
	void shouldCalculatePriceFarmPolicy() {
        //given
        JsonObject calculatePriceRequestJson = TestDataProvider.getCalculatePriceRequestJson(CALCULATE_PRICE_FARM);

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
                .body(TOTAL_PRICE_ATTRIBUT, notNullValue())
                .body(COVERS_PRICES_ATTRIBUT, notNullValue());
	}
}
