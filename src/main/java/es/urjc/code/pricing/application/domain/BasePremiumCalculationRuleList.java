package es.urjc.code.pricing.application.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BasePremiumCalculationRuleList {

    private List<BasePremiumCalculationRule> basePriceCalculationRules;

    public BasePremiumCalculationRuleList(List<BasePremiumCalculationRule> basePriceCalculationRules) {
        this.basePriceCalculationRules = basePriceCalculationRules;
    }

    public void addBasePriceRule(String coverCode, String applyIfFormula, String basePriceFormula) {
        BasePremiumCalculationRule rule = new BasePremiumCalculationRule(coverCode, applyIfFormula, basePriceFormula);
        basePriceCalculationRules.add(rule);
    }


    public BigDecimal calculateBasePriceFor(Cover cover, Calculation calculation) {
        return getRulesFor(cover.getCode())
                .stream()
                .filter(r -> r.applies(calculation))
                .map(r -> r.calculateBasePrice(calculation))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BasePremiumCalculationRuleList)) return false;

        BasePremiumCalculationRuleList that = (BasePremiumCalculationRuleList) o;

        return new EqualsBuilder()
                .append(basePriceCalculationRules, that.basePriceCalculationRules)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(basePriceCalculationRules)
                .toHashCode();
    }     

    private List<BasePremiumCalculationRule> getRulesFor(String coverCode) {
        return basePriceCalculationRules
                .stream()
                .filter(r -> r.getCoverCode().equals(coverCode))
                .collect(Collectors.toList());
    }
    
    
}
