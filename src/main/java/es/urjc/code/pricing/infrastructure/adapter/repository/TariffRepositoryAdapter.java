package es.urjc.code.pricing.infrastructure.adapter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.urjc.code.pricing.application.domain.Tariff;
import es.urjc.code.pricing.application.port.outgoing.LoadTariffPort;
import es.urjc.code.pricing.infrastructure.adapter.mapper.TarrifMapper;
import es.urjc.code.pricing.infrastructure.adapter.repository.entity.TariffEntity;
import es.urjc.code.pricing.infrastructure.adapter.repository.jpa.TariffJpaRepository;

@Component
public class TariffRepositoryAdapter implements LoadTariffPort {

	private final TariffJpaRepository tariffJpaRepository;
	private final TarrifMapper tarrifMapper;
	
	@Autowired
	public TariffRepositoryAdapter(TariffJpaRepository tariffJpaRepository,TarrifMapper tarrifMapper) {
		this.tariffJpaRepository = tariffJpaRepository;
		this.tarrifMapper = tarrifMapper;
	}
	
	@Override
	public Tariff handle(String code) {
		TariffEntity entity = tariffJpaRepository.getByCode(code);
		return tarrifMapper.toDomain(entity);
	}

}
