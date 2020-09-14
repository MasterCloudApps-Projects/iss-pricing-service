package es.urjc.code.pricing.application.port.incoming;

import es.urjc.code.pricing.application.dto.CalculatePriceRequest;
import es.urjc.code.pricing.application.dto.CalculatePriceResponse;

public interface CalculatePriceUseCase {

	CalculatePriceResponse handle(CalculatePriceRequest calculatePriceRequest);
}
