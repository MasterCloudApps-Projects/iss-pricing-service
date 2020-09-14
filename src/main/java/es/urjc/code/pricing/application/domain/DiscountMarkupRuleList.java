package es.urjc.code.pricing.application.domain;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DiscountMarkupRuleList {

    private Tariff tariff;
    private List<DiscountMarkupRule> discountMarkupRules;

    public DiscountMarkupRuleList(Tariff tariff, List<DiscountMarkupRule> discountMarkupRules) {
        this.tariff = tariff;
        this.discountMarkupRules = discountMarkupRules;
    }

    public void addPercentMarkup(String applyIfFormula, BigDecimal markup){
        discountMarkupRules.add(new PercentMarkupRule(tariff, applyIfFormula, markup));
    }

    public void apply(Calculation calculation) {
        discountMarkupRules
                .stream()
                .filter(r -> r.applies(calculation))
                .forEach(r -> r.apply(calculation));
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof DiscountMarkupRuleList)) return false;

        DiscountMarkupRuleList that = (DiscountMarkupRuleList) o;

        return new EqualsBuilder()
                .append(tariff, that.tariff)
                .append(discountMarkupRules, that.discountMarkupRules)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(tariff)
                .append(discountMarkupRules)
                .toHashCode();
    } 
}
