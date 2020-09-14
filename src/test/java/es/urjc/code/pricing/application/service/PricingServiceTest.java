package es.urjc.code.pricing.application.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import es.urjc.code.pricing.application.domain.Tariff;
import es.urjc.code.pricing.application.dto.CalculatePriceRequest;
import es.urjc.code.pricing.application.dto.CalculatePriceResponse;
import es.urjc.code.pricing.application.dto.QuestionAnswer;
import es.urjc.code.pricing.application.port.outgoing.LoadTariffPort;
import es.urjc.code.pricing.domain.TariffsFactory;

class PricingServiceTest {

	private static final String CODE_CAR = "CAR";
	
	private LoadTariffPort loadTariffPort;
	private PricingService sut;
	
	@BeforeEach
	public void setUp() {
		this.loadTariffPort = Mockito.mock(LoadTariffPort.class);
		this.sut = new PricingService(loadTariffPort);
	}
	
	@Test
	void shouldBeCalculatePrice() {
		// given
		final CalculatePriceRequest calculatePriceRequest = getCalculatePriceRequest();
		final Tariff tariff = TariffsFactory.car();
		when(loadTariffPort.handle(CODE_CAR)).thenReturn(tariff); 	
		// when
		final CalculatePriceResponse response = sut.handle(calculatePriceRequest);
		// then
		verify(loadTariffPort).handle(CODE_CAR);
		assertNotNull(response.getTotalPrice());
		assertNotNull(response.getCoversPrices());
	}
	
	private CalculatePriceRequest getCalculatePriceRequest() {
		ArrayList<QuestionAnswer> questionAnswers = new ArrayList();
		questionAnswers.add(new QuestionAnswer("NUM_OF_CLAIM",1));
		return new CalculatePriceRequest.Builder()
				                        .withProductCode(CODE_CAR)
				                        .withPolicyFrom(LocalDate.of(2017, 4, 16))
				                        .withPolicyTo(LocalDate.of(2018, 4, 15))
				                        .withSelectedCovers(Collections.singletonList("C1"))
				                        .withAnswers(questionAnswers)
				                        .build();
	}
}
