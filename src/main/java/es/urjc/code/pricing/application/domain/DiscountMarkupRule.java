package es.urjc.code.pricing.application.domain;


import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.mvel2.MVEL;

@Entity
@DiscriminatorColumn(name = "type")
@Table(name = "discount_markup_rule", schema = "pricing")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class DiscountMarkupRule {

    @Id
    @GeneratedValue
    protected UUID id;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    protected Tariff tariff;

    @Column(name = "apply_if_formula")
    protected String applyIfFormula;

    @Column(name = "param_value")
    protected BigDecimal paramValue;

	public UUID getId() {
		return id;
	}

	public Tariff getTariff() {
		return tariff;
	}

	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
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
}
