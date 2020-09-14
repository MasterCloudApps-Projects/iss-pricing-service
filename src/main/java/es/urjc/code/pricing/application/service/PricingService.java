package es.urjc.code.pricing.application.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.urjc.code.pricing.application.domain.Calculation;
import es.urjc.code.pricing.application.domain.Tariff;
import es.urjc.code.pricing.application.dto.CalculatePriceRequest;
import es.urjc.code.pricing.application.dto.CalculatePriceResponse;
import es.urjc.code.pricing.application.dto.QuestionAnswer;
import es.urjc.code.pricing.application.port.incoming.CalculatePriceUseCase;
import es.urjc.code.pricing.application.port.outgoing.LoadTariffPort;

@Service
@Transactional
public class PricingService implements CalculatePriceUseCase {

	private final LoadTariffPort loadTariffPort;
	
	@Autowired
	public PricingService(LoadTariffPort loadTariffPort) {
		this.loadTariffPort = loadTariffPort;
	}
	
	@Override
	public CalculatePriceResponse handle(CalculatePriceRequest calculatePriceRequest) {
		Tariff tariff = loadTariffPort.handle(calculatePriceRequest.getProductCode());
        Calculation calculation = tariff.calculatePrice(toCalculation(calculatePriceRequest));
        return resultFromCalculation(calculation);
	}

    private Calculation toCalculation(CalculatePriceRequest calculatePriceRequest) {
        return new Calculation(
                calculatePriceRequest.getProductCode(),
                calculatePriceRequest.getPolicyFrom(),
                calculatePriceRequest.getPolicyTo(),
                calculatePriceRequest.getSelectedCovers(),
                calculatePriceRequest.getAnswers().stream()
                        .collect(Collectors.toMap(QuestionAnswer::getQuestionCode, QuestionAnswer::getAnswer))
        );
    }

    private CalculatePriceResponse resultFromCalculation(Calculation calculation) {
        return new CalculatePriceResponse(
                calculation.getTotalPremium(),
                calculation.getCovers().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getPrice()))
        );
    }
}
