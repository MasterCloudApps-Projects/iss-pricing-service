package es.urjc.code.pricing.application.dto;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CalculatePriceRequest {
	private String productCode;
    private LocalDate policyFrom;
    private LocalDate policyTo;
    private List<String> selectedCovers;
    private List<QuestionAnswer> answers;

    public String getProductCode() {
        return productCode;
    }

    public LocalDate getPolicyFrom() {
        return policyFrom;
    }

    public LocalDate getPolicyTo() {
        return policyTo;
    }

    public List<String> getSelectedCovers() {
        return selectedCovers;
    }

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CalculatePriceRequest)) return false;

        CalculatePriceRequest that = (CalculatePriceRequest) o;

        return new EqualsBuilder()
                .append(productCode, that.productCode)
                .append(policyFrom, that.policyFrom)
                .append(policyTo, that.policyTo)
                .append(selectedCovers, that.selectedCovers)
                //.append(answers, that.answers)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(productCode)
                .append(policyFrom)
                .append(policyTo)
                .append(selectedCovers)
                //.append(answers)
                .toHashCode();
    }
    
    public static final class Builder {

        private final CalculatePriceRequest object;

        public Builder() {
            object = new CalculatePriceRequest();
        }

        public Builder withProductCode(String value) {
            object.productCode = value;
            return this;
        }

        public Builder withPolicyFrom(LocalDate value) {
            object.policyFrom = value;
            return this;
        }

        public Builder withPolicyTo(LocalDate value) {
            object.policyTo = value;
            return this;
        }

        public Builder withSelectedCovers(List<String> value) {
            object.selectedCovers = value;
            return this;
        }

        public Builder withAnswers(List<QuestionAnswer> value) {
            object.answers = value;
            return this;
        }

        public CalculatePriceRequest build() {
            return object;
        }

    }    
}
