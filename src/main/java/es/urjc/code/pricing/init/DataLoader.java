package es.urjc.code.pricing.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import es.urjc.code.pricing.infrastructure.adapter.repository.jpa.TariffJpaRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final TariffJpaRepository tariffJpaRepository;

	@Autowired
	public DataLoader(TariffJpaRepository jpaTariffRepository) {
		this.tariffJpaRepository = jpaTariffRepository;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!tariffJpaRepository.findByCode("HSI").isPresent()) {
        	tariffJpaRepository.save(DemoTariffsFactory.house());
        }
        
        if (!tariffJpaRepository.findByCode("TRI").isPresent()) {
        	tariffJpaRepository.save(DemoTariffsFactory.travel());
        }

        if (!tariffJpaRepository.findByCode("FAI").isPresent()) {
        	tariffJpaRepository.save(DemoTariffsFactory.farm());
        }

        if (!tariffJpaRepository.findByCode("CAR").isPresent()) {
        	tariffJpaRepository.save(DemoTariffsFactory.car());
        }
    }

}
