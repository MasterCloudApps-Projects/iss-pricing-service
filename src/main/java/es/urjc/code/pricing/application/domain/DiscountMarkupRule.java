package es.urjc.code.pricing.application.domain;


import java.math.BigDecimal;
import java.util.UUID;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.mvel2.MVEL;

public abstract class DiscountMarkupRule {

    protected UUID id;

    protected Tariff tariff;

    protected String applyIfFormula;

    protected BigDecimal paramValue;

	public UUID getId() {
		return id;
	}

	public Tariff getTariff() {
		return tariff;
	}

	public String getApplyIfFormula() {
		return applyIfFormula;
	}

	public BigDecimal getParamValue() {
		return paramValue;
	}

	public boolean applies(Calculation calculation) {
        return applyIfFormula == null || applyIfFormula.isEmpty()
                ? true
                : MVEL.eval(applyIfFormula, calculation.toMap(), Boolean.class);
    }

    public abstract Calculation apply(Calculation calculation);
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof DiscountMarkupRule)) return false;

        DiscountMarkupRule that = (DiscountMarkupRule) o;

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
}
