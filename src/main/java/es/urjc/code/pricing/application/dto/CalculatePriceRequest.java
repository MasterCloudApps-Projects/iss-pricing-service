package es.urjc.code.pricing.application.dto;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.swagger.v3.oas.annotations.media.Schema;

public class CalculatePriceRequest {
    
	@Schema(description = "Product code.", example = "CAR", required = true)
	private String productCode;
	@Schema(description = "Policy start date.", example = "2017-04-16", required = true)
    private LocalDate policyFrom;
	@Schema(description = "Policy end date.", example = "2018-04-15", required = true)
    private LocalDate policyTo;
	@Schema(description = "List of selected covers.", example = "[\"C1\"]", required = true)
    private List<String> selectedCovers;
	@Schema(description = "List of questions and answers.", example = "[{\"questionCode\":\"NUM_OF_CLAIM\",\"answer\":1}]", required = true)
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
