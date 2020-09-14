package es.urjc.code.pricing.application.domain;


import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.mvel2.MVEL;

public class BasePremiumCalculationRule {

    private String coverCode;

    private String applyIfFormula;

    private String basePriceFormula;
    
    
    public BasePremiumCalculationRule() {
		
	}

	public BasePremiumCalculationRule(String coverCode, String applyIfFormula, String basePriceFormula) {
        this.coverCode = coverCode;
        this.applyIfFormula = applyIfFormula;
        this.basePriceFormula = basePriceFormula;
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

    public boolean applies(Calculation calculation) {
        return applyIfFormula == null || applyIfFormula.isEmpty()
                ? true
                : MVEL.eval(applyIfFormula, calculation.toMap(), Boolean.class);
    }

    public BigDecimal calculateBasePrice(Calculation calculation) {
        return MVEL.eval(basePriceFormula, calculation.toMap(), BigDecimal.class);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BasePremiumCalculationRule)) return false;

        BasePremiumCalculationRule that = (BasePremiumCalculationRule) o;

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

        private final BasePremiumCalculationRule object;

        public Builder() {
            object = new BasePremiumCalculationRule();
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

        public BasePremiumCalculationRule build() {
            return object;
        }

    }    
}
