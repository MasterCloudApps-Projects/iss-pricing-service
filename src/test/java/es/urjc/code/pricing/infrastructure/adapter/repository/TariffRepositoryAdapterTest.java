package es.urjc.code.pricing.infrastructure.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import es.urjc.code.pricing.application.domain.Tariff;
import es.urjc.code.pricing.infrastructure.adapter.mapper.TarrifMapper;
import es.urjc.code.pricing.infrastructure.adapter.repository.entity.TariffEntity;
import es.urjc.code.pricing.infrastructure.adapter.repository.jpa.TariffJpaRepository;

class TariffRepositoryAdapterTest {
    
	private static final String CODE_CAR = "CAR";
	private TariffJpaRepository tariffJpaRepository;
	private TarrifMapper tarrifMapper;
	private TariffRepositoryAdapter sut;
	
	@BeforeEach
	public void setUp() {
		this.tariffJpaRepository = Mockito.mock(TariffJpaRepository.class);
		this.tarrifMapper = Mockito.mock(TarrifMapper.class);
		this.sut = new TariffRepositoryAdapter(tariffJpaRepository, tarrifMapper);
	}
	
	@Test
	void shouldBeReturnTariffDomain() {
		// given
		TariffEntity entity = getTariffEntity();
		when(tariffJpaRepository.getByCode(CODE_CAR)).thenReturn(entity);
		when(tarrifMapper.toDomain(entity)).thenReturn(getTariff());
	    // when
		Tariff response = sut.handle(CODE_CAR);
		// then
		verify(tariffJpaRepository).getByCode(CODE_CAR);
		verify(tarrifMapper).toDomain(entity);
		assertEquals(CODE_CAR, response.getCode());
	}

	private TariffEntity getTariffEntity() {
		return new TariffEntity.Builder().withCode(CODE_CAR).build();
	}
	
	private Tariff getTariff() {
		return new Tariff.Builder().withCode(CODE_CAR).build();
	}
}
