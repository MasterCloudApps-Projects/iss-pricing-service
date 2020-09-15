package es.urjc.code.pricing.application.dto;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.swagger.v3.oas.annotations.media.Schema;


public class CalculatePriceResponse {

	@Schema(description = "Total price.", example = "100", required = true)
	private BigDecimal totalPrice;
	@Schema(description = "Map Detail covers prices.", example = "{\"C1\": 100}", required = true)
    private Map<String, BigDecimal> coversPrices;

    public CalculatePriceResponse() {

	}

	public CalculatePriceResponse(BigDecimal totalPrice, Map<String, BigDecimal> coversPrices) {
		super();
		this.totalPrice = totalPrice;
		this.coversPrices = coversPrices;
	}

	public static CalculatePriceResponse empty() {
        return new CalculatePriceResponse();
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Map<String, BigDecimal> getCoversPrices() {
        return coversPrices;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CalculatePriceResponse)) return false;

        CalculatePriceResponse that = (CalculatePriceResponse) o;

        return new EqualsBuilder()
                .append(totalPrice, that.totalPrice)
                .append(coversPrices, that.coversPrices)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(totalPrice)
                .append(coversPrices)
                .toHashCode();
    } 
    
	public static final class Builder {

        private final CalculatePriceResponse object;

        public Builder() {
            object = new CalculatePriceResponse();
        }

        public Builder withTotalPrice(BigDecimal value) {
            object.totalPrice = value;
            return this;
        }

        public Builder withCoversPrices(Map<String, BigDecimal> value) {
            object.coversPrices = value;
            return this;
        }

        public CalculatePriceResponse build() {
            return object;
        }

    }

}
