package es.urjc.code.pricing.infrastructure.adapter.repository.entity;



import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@DiscriminatorValue("perc_markup")
public class PercentMarkupRuleEntity extends DiscountMarkupRuleEntity {

	public PercentMarkupRuleEntity() {
		
	}
	
    public PercentMarkupRuleEntity(String applyIfFormula, BigDecimal paramValue) {
        this.applyIfFormula = applyIfFormula;
        this.paramValue = paramValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PercentMarkupRuleEntity)) return false;

        PercentMarkupRuleEntity that = (PercentMarkupRuleEntity) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(tariff, that.tariff)
                .append(applyIfFormula, that.applyIfFormula)
                .append(paramValue, that.paramValue)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(tariff)
                .append(applyIfFormula)
                .append(paramValue)
                .toHashCode();
    }
    
    public static final class Builder {

        private final PercentMarkupRuleEntity object;

        public Builder() {
            object = new PercentMarkupRuleEntity();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }

        public Builder withTariff(TariffEntity value) {
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

        public PercentMarkupRuleEntity build() {
            return object;
        }

    }

}
