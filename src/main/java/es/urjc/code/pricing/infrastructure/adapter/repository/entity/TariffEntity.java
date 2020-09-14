package es.urjc.code.pricing.infrastructure.adapter.repository.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "tariff", schema = "pricing")
public class TariffEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "code")
    private String code;

    @ElementCollection
    @CollectionTable(name = "base_price_rules", joinColumns = @JoinColumn(name = "tariff_id"), schema="pricing")
    private List<BasePremiumCalculationRuleEntity> basePriceCalculationRules = new ArrayList<>();

    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DiscountMarkupRuleEntity> discountMarkupRules= new ArrayList<>();
    
    public TariffEntity() {

	}
    
	public TariffEntity( String code) {
        this.code = code;
        this.basePriceCalculationRules = new ArrayList<>();
        this.discountMarkupRules = new ArrayList<>();
    }

    public UUID getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public List<BasePremiumCalculationRuleEntity> getBasePriceCalculationRules() {
		return basePriceCalculationRules;
	}

	public List<DiscountMarkupRuleEntity> getDiscountMarkupRules() {
		return discountMarkupRules;
	}

    public void addBasePriceRule(String coverCode, String applyIfFormula, String basePriceFormula) {
    	BasePremiumCalculationRuleEntity rule = new BasePremiumCalculationRuleEntity(coverCode, applyIfFormula, basePriceFormula);
        basePriceCalculationRules.add(rule);
    }

    public void addPercentMarkup(DiscountMarkupRuleEntity discountMarkupRuleEntity){
        discountMarkupRules.add(discountMarkupRuleEntity);
        discountMarkupRuleEntity.setTariff(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TariffEntity)) return false;

        TariffEntity that = (TariffEntity) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(code, that.code)
                .append(basePriceCalculationRules, that.basePriceCalculationRules)
                .append(discountMarkupRules, that.discountMarkupRules)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(code)
                .append(basePriceCalculationRules)
                .append(discountMarkupRules)
                .toHashCode();
    }
    
    public static final class Builder {

        private final TariffEntity object;

        public Builder() {
            object = new TariffEntity();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }

        public Builder withCode(String value) {
            object.code = value;
            return this;
        }

        public Builder withBasePriceCalculationRules(List<BasePremiumCalculationRuleEntity> value) {
            object.basePriceCalculationRules = value;
            return this;
        }

        public Builder withDiscountMarkupRules(List<DiscountMarkupRuleEntity> value) {
            object.discountMarkupRules = value;
            return this;
        }

        public TariffEntity build() {
            return object;
        }

    }
}
