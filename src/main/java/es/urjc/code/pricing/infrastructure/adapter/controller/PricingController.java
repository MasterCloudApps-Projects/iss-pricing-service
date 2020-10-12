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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "pricing", description = "the Pricing API")
public class PricingController {

	private final CalculatePriceUseCase calculatePriceUseCase;
	
	@Autowired
	public PricingController(CalculatePriceUseCase calculatePriceUseCase) {
		this.calculatePriceUseCase = calculatePriceUseCase;
	}
	
    @Operation(summary = "Calculate price", description = "", tags = { "pricing" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Price calculated",
                content = @Content(schema = @Schema(implementation = CalculatePriceResponse.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input")})
	@PostMapping("/api/v1/calculate")
	public ResponseEntity<CalculatePriceResponse> create(@Parameter(description="Calculate price request. Cannot null or empty.", 
            required=true, schema=@Schema(implementation = CalculatePriceRequest.class))@RequestBody CalculatePriceRequest command) {
		CalculatePriceResponse response = calculatePriceUseCase.handle(command);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
