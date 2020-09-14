package es.urjc.code.pricing.application.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Tariff {

    private UUID id;

    private String code;

    private List<BasePremiumCalculationRule> basePriceCalculationRules;

    private List<DiscountMarkupRule> discountMarkupRules;
    
    public Tariff() {

	}
    
	public Tariff( String code) {
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

	public List<BasePremiumCalculationRule> getBasePriceCalculationRules() {
		return basePriceCalculationRules;
	}

	public List<DiscountMarkupRule> getDiscountMarkupRules() {
		return discountMarkupRules;
	}

	public BasePremiumCalculationRuleList rules() {
        return new BasePremiumCalculationRuleList(basePriceCalculationRules);
    }

    public DiscountMarkupRuleList discountMarkupRules() {
        return new DiscountMarkupRuleList(this, discountMarkupRules);
    }

    public Calculation calculatePrice(Calculation calculation) {
        calcBasePrices(calculation);
        applyDiscounts(calculation);
        buildResponse(calculation);

        return calculation;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Tariff)) return false;

        Tariff that = (Tariff) o;

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

        private final Tariff object;

        public Builder() {
            object = new Tariff();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }

        public Builder withCode(String value) {
            object.code = value;
            return this;
        }

        public Builder withBasePriceCalculationRules(List<BasePremiumCalculationRule> value) {
            object.basePriceCalculationRules = value;
            return this;
        }

        public Builder withDiscountMarkupRules(List<DiscountMarkupRule> value) {
            object.discountMarkupRules = value;
            return this;
        }

        public Tariff build() {
            return object;
        }

    }    
    
    private void calcBasePrices(Calculation calculation) {
        for (Cover c : calculation.getCovers().values()) {
            c.setPrice(rules().calculateBasePriceFor(c, calculation));
        }
    }

    private void applyDiscounts(Calculation calculation) {
        discountMarkupRules().apply(calculation);
    }

    private void buildResponse(Calculation calculation) {
        calculation.updateTotal();
    }
 
}
