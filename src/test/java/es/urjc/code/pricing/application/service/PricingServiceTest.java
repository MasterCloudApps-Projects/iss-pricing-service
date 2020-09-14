package es.urjc.code.pricing.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import es.urjc.code.pricing.application.dto.CalculatePriceRequest;
import es.urjc.code.pricing.application.port.outgoing.LoadTariffPort;

class PricingServiceTest {

	private LoadTariffPort loadTariffPort;
	private PricingService sut;
	
	@BeforeEach
	public void setUp() {
		this.loadTariffPort = Mockito.mock(LoadTariffPort.class);
		this.sut = new PricingService(loadTariffPort);
	}
	
	
	private CalculatePriceRequest getCalculatePriceRequest() {
		return new CalculatePriceRequest.Builder()
				                        .build();
	}
}
