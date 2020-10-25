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

import es.urjc.code.pricing.base.AbstractContainerBaseTest;
import es.urjc.code.pricing.infrastructure.adapter.repository.entity.PercentMarkupRuleEntity;
import es.urjc.code.pricing.infrastructure.adapter.repository.entity.TariffEntity;
import es.urjc.code.pricing.infrastructure.adapter.repository.jpa.TariffJpaRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = { AbstractContainerBaseTest.PropertiesInitializer.class })
class TariffJpaRepositoryIT extends AbstractContainerBaseTest {

	private static final String CODE_NOT_EXIST = "NOT_EXIST";
	private static final String CODE_CAR = "CAR";

	@Autowired
	private TariffJpaRepository jpaTariffRepository;
	
	private TariffEntity entity;

	@BeforeEach
	public void setUp() {
		entity = new TariffEntity(CODE_CAR);
		entity.addBasePriceRule("C1", null, "100B");
		entity.addPercentMarkup(new PercentMarkupRuleEntity("NUM_OF_CLAIM > 2", new BigDecimal("50.00")));
		jpaTariffRepository.save(entity);
	}
	
	@Test
	void testWhenFindByCodeThenReturnTariffEntity() {
		var t = jpaTariffRepository.findByCode(CODE_CAR);
		assertTrue(t.isPresent());
	}
	
	@Test
	void testWhenFindByCodeThenNotReturnTariffEntity() {
		var t = jpaTariffRepository.findByCode(CODE_NOT_EXIST);
		assertTrue(!t.isPresent());
	}
	
	@Test
	void testWhenGetByCodeThenReturnTariffEntity() {
		var t = jpaTariffRepository.getByCode(CODE_CAR);
		assertEquals(CODE_CAR,t.getCode());
	}

}
