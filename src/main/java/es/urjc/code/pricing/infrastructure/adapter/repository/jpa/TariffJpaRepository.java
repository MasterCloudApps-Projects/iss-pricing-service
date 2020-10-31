package es.urjc.code.pricing.infrastructure.adapter.repository.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.urjc.code.pricing.application.domain.Tariff;

@Repository
public interface TariffJpaRepository extends JpaRepository<Tariff, UUID>{
    
	@Query("SELECT t FROM Tariff t LEFT JOIN FETCH t.discountMarkupRules where t.code=:code")
	public Optional<Tariff> findByCode(@Param("code") String code);
    
	@Query("SELECT t FROM Tariff t LEFT JOIN FETCH t.discountMarkupRules where t.code=:code")
	public Tariff getByCode(@Param("code") String code);
}
