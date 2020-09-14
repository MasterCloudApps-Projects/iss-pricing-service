package es.urjc.code.pricing.infrastructure.adapter.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.urjc.code.pricing.application.domain.Tariff;
import es.urjc.code.pricing.infrastructure.adapter.repository.entity.PercentMarkupRuleEntity;
import es.urjc.code.pricing.infrastructure.adapter.repository.entity.TariffEntity;

class TarrifMapperTest {

	private static final String CODE_CAR = "CAR";
	private TarrifMapper sut;
	
	@BeforeEach
	public void setUp() {
		this.sut = new TarrifMapper();
	}
	
	@Test
	void shouldBeReturnTariff() {
		// given
		TariffEntity entity = getTariffEntity();
		// when
		Tariff domain = sut.toDomain(entity);
		// then
		assertEquals(entity.getCode(),domain.getCode());
		assertEquals(entity.getBasePriceCalculationRules().get(0).getCoverCode(),domain.getBasePriceCalculationRules().get(0).getCoverCode()); 
		assertEquals(entity.getBasePriceCalculationRules().get(0).getBasePriceFormula(),domain.getBasePriceCalculationRules().get(0).getBasePriceFormula()); 
		assertEquals(entity.getBasePriceCalculationRules().get(0).getApplyIfFormula(),domain.getBasePriceCalculationRules().get(0).getApplyIfFormula());
		assertEquals(entity.getDiscountMarkupRules().get(0).getApplyIfFormula(),domain.getDiscountMarkupRules().get(0).getApplyIfFormula());
		assertEquals(entity.getDiscountMarkupRules().get(0).getParamValue(),domain.getDiscountMarkupRules().get(0).getParamValue());
	}
	
	private TariffEntity getTariffEntity() {
		TariffEntity entity = new TariffEntity(CODE_CAR);
		entity.addBasePriceRule("C1", null, "100B");
		entity.addPercentMarkup(new PercentMarkupRuleEntity("NUM_OF_CLAIM > 2", new BigDecimal("50.00")));
		return entity;
	}
}
