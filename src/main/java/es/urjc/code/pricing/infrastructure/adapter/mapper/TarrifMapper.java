package es.urjc.code.pricing.infrastructure.adapter.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import es.urjc.code.pricing.application.domain.BasePremiumCalculationRule;
import es.urjc.code.pricing.application.domain.DiscountMarkupRule;
import es.urjc.code.pricing.application.domain.PercentMarkupRule;
import es.urjc.code.pricing.application.domain.Tariff;
import es.urjc.code.pricing.infrastructure.adapter.repository.entity.BasePremiumCalculationRuleEntity;
import es.urjc.code.pricing.infrastructure.adapter.repository.entity.DiscountMarkupRuleEntity;
import es.urjc.code.pricing.infrastructure.adapter.repository.entity.TariffEntity;

@Component
public class TarrifMapper {

	public Tariff toDomain(TariffEntity entity) {
		return new Tariff.Builder()
				         .withId(entity.getId())
				         .withCode(entity.getCode())
				         .withBasePriceCalculationRules(entity.getBasePriceCalculationRules().stream().map(this::toBasePremiumCalculationRule).collect(Collectors.toList()))
				         .withDiscountMarkupRules(entity.getDiscountMarkupRules().stream().map(this::toPercentMarkupRule).collect(Collectors.toList()))
				         .build();
	}
	
	private BasePremiumCalculationRule toBasePremiumCalculationRule(BasePremiumCalculationRuleEntity entity) {
	  return new BasePremiumCalculationRule.Builder()			                          
			                               .withCoverCode(entity.getCoverCode())
			                               .withApplyIfFormula(entity.getApplyIfFormula())
			                               .withBasePriceFormula(entity.getBasePriceFormula())
			                               .build();	
	}

	private DiscountMarkupRule toPercentMarkupRule(DiscountMarkupRuleEntity entity) {
		return new PercentMarkupRule.Builder()
				                    .withId(entity.getId())
				                    .withApplyIfFormula(entity.getApplyIfFormula())
				                    .withParamValue(entity.getParamValue())
				                    .build();
	}
}
