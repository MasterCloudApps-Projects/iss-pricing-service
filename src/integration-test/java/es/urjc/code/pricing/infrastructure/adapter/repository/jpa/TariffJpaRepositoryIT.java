package es.urjc.code.pricing.infrastructure.adapter.repository.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import es.urjc.code.pricing.application.domain.PercentMarkupRule;
import es.urjc.code.pricing.application.domain.Tariff;
import es.urjc.code.pricing.base.AbstractContainerBaseTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = { AbstractContainerBaseTest.PropertiesInitializer.class })
class TariffJpaRepositoryIT extends AbstractContainerBaseTest {

	private static final String CODE_NOT_EXIST = "NOT_EXIST";
	private static final String CODE_CAR = "CAR";

	@Autowired
	private TariffJpaRepository jpaTariffRepository;
	
	private Tariff entity;

	@BeforeEach
	public void setUp() {
		entity = new Tariff(CODE_CAR);
		entity.addBasePriceRule("C1", null, "100B");
		entity.addPercentMarkup(new PercentMarkupRule("NUM_OF_CLAIM > 2", new BigDecimal("50.00")));
		jpaTariffRepository.save(entity);
	}
	
	@Test
	void testWhenFindByCodeThenReturnTariff() {
		var t = jpaTariffRepository.findByCode(CODE_CAR);
		assertTrue(t.isPresent());
	}
	
	@Test
	void testWhenFindByCodeThenNotReturnTariff() {
		var t = jpaTariffRepository.findByCode(CODE_NOT_EXIST);
		assertTrue(!t.isPresent());
	}
	
	@Test
	void testWhenGetByCodeThenReturnTariff() {
		var t = jpaTariffRepository.getByCode(CODE_CAR);
		assertEquals(CODE_CAR,t.getCode());
	}

}
