package es.urjc.code.pricing.infrastructure.adapter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.urjc.code.pricing.application.domain.Tariff;
import es.urjc.code.pricing.application.port.outgoing.LoadTariffPort;
import es.urjc.code.pricing.infrastructure.adapter.repository.jpa.TariffJpaRepository;

@Component
public class TariffRepositoryAdapter implements LoadTariffPort {

	private final TariffJpaRepository tariffJpaRepository;
	
	@Autowired
	public TariffRepositoryAdapter(TariffJpaRepository tariffJpaRepository) {
		this.tariffJpaRepository = tariffJpaRepository;
	}
	
	@Override
	public Tariff handle(String code) {
		return tariffJpaRepository.getByCode(code);
	}

}
