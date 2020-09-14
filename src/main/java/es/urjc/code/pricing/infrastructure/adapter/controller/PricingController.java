package es.urjc.code.pricing.infrastructure.adapter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.pricing.application.dto.CalculatePriceRequest;
import es.urjc.code.pricing.application.dto.CalculatePriceResponse;
import es.urjc.code.pricing.application.port.incoming.CalculatePriceUseCase;

@RestController
public class PricingController {

	private final CalculatePriceUseCase calculatePriceUseCase;
	
	@Autowired
	public PricingController(CalculatePriceUseCase calculatePriceUseCase) {
		this.calculatePriceUseCase = calculatePriceUseCase;
	}
	
	@PostMapping("/api/v1/calculate")
	public ResponseEntity<CalculatePriceResponse> create(@RequestBody CalculatePriceRequest command) {
		CalculatePriceResponse response = calculatePriceUseCase.handle(command);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
