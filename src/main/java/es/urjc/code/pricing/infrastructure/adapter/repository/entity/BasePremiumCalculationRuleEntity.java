package es.urjc.code.pricing.infrastructure.adapter.repository.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class BasePremiumCalculationRuleEntity {

    @Column(name = "cover_code")
    private String coverCode;

    @Column(name = "apply_if_formula")
    private String applyIfFormula;

    @Column(name = "price_formula")
    private String basePriceFormula;
    
    
    public BasePremiumCalculationRuleEntity() {
		
	}

	public String getCoverCode() {
		return coverCode;
	}

	public String getApplyIfFormula() {
		return applyIfFormula;
	}

	public String getBasePriceFormula() {
		return basePriceFormula;
	}

	public BasePremiumCalculationRuleEntity(String coverCode, String applyIfFormula, String basePriceFormula) {
        this.coverCode = coverCode;
        this.applyIfFormula = applyIfFormula;
        this.basePriceFormula = basePriceFormula;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BasePremiumCalculationRuleEntity)) return false;

        BasePremiumCalculationRuleEntity that = (BasePremiumCalculationRuleEntity) o;

        return new EqualsBuilder()
                .append(coverCode, that.coverCode)
                .append(applyIfFormula, that.applyIfFormula)
                .append(basePriceFormula, basePriceFormula)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(coverCode)
                .append(applyIfFormula)
                .append(basePriceFormula)
                .toHashCode();
    } 
    
    public static final class Builder {

        private final BasePremiumCalculationRuleEntity object;

        public Builder() {
            object = new BasePremiumCalculationRuleEntity();
        }

        public Builder withCoverCode(String value) {
            object.coverCode = value;
            return this;
        }

        public Builder withApplyIfFormula(String value) {
            object.applyIfFormula = value;
            return this;
        }

        public Builder withBasePriceFormula(String value) {
            object.basePriceFormula = value;
            return this;
        }

        public BasePremiumCalculationRuleEntity build() {
            return object;
        }

    }    
}
