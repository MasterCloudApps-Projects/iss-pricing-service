package es.urjc.code.pricing.infrastructure.adapter.repository.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.urjc.code.pricing.infrastructure.adapter.repository.entity.TariffEntity;

@Repository
public interface TariffJpaRepository extends JpaRepository<TariffEntity, UUID>{
    
	public Optional<TariffEntity> findByCode(String code);
    
	public TariffEntity getByCode(String code);
}
