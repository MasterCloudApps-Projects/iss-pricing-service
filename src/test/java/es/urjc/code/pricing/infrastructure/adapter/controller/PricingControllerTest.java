package es.urjc.code.pricing.infrastructure.adapter.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import es.urjc.code.pricing.application.dto.CalculatePriceRequest;
import es.urjc.code.pricing.application.dto.CalculatePriceResponse;
import es.urjc.code.pricing.application.port.incoming.CalculatePriceUseCase;
import es.urjc.code.pricing.infrastructure.adapter.controller.PricingController;

class PricingControllerTest {

	private CalculatePriceUseCase calculatePrice;
	
	private PricingController sut;
	
	@BeforeEach
	public void setUp() {
		this.calculatePrice = Mockito.mock(CalculatePriceUseCase.class);
		this.sut = new PricingController(calculatePrice);
	}
	
	@Test
	void shouldBeCalculatePrice() {
		// given
		final CalculatePriceRequest command = getCalculatePriceCommand();
		final CalculatePriceResponse result = getCalculatePriceResult(); 
		when(calculatePrice.handle(command)).thenReturn(result);
		// when
		ResponseEntity<CalculatePriceResponse> response = sut.create(command);
		// then
		verify(calculatePrice).handle(command);
		assertEquals(response.getBody(), result);
	}
	
	private CalculatePriceRequest getCalculatePriceCommand() {
		return new CalculatePriceRequest.Builder().build();
	}
	
	private CalculatePriceResponse getCalculatePriceResult() {
		return new CalculatePriceResponse.Builder().build();
	}
}
