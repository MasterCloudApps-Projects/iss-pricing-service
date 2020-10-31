package es.urjc.code.pricing.application.domain;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@DiscriminatorValue("perc_markup")
public class PercentMarkupRule extends DiscountMarkupRule {

	public PercentMarkupRule() {
		
	}
	
    public PercentMarkupRule(String applyIfFormula, BigDecimal paramValue) {
        this.applyIfFormula = applyIfFormula;
        this.paramValue = paramValue;
    }
	
    public PercentMarkupRule(Tariff tariff, String applyIfFormula, BigDecimal paramValue) {
        this.tariff = tariff;
        this.applyIfFormula = applyIfFormula;
        this.paramValue = paramValue;
    }

    @Override
    public Calculation apply(Calculation calculation) {
        for (Cover cover : calculation.getCovers().values()) {
            cover.setPrice(cover.getPrice()
                    .multiply(paramValue)
                    .setScale(2, RoundingMode.HALF_UP)
            );
        }
        return calculation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PercentMarkupRule)) return false;

        PercentMarkupRule that = (PercentMarkupRule) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(applyIfFormula, that.applyIfFormula)
                .append(paramValue, that.paramValue)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(applyIfFormula)
                .append(paramValue)
                .toHashCode();
    }
    
    public static final class Builder {

        private final PercentMarkupRule object;

        public Builder() {
            object = new PercentMarkupRule();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }

        public Builder withTariff(Tariff value) {
            object.tariff = value;
            return this;
        }

        public Builder withApplyIfFormula(String value) {
            object.applyIfFormula = value;
            return this;
        }

        public Builder withParamValue(BigDecimal value) {
            object.paramValue = value;
            return this;
        }

        public PercentMarkupRule build() {
            return object;
        }

    }

}
