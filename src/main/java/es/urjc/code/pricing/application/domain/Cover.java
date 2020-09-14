package es.urjc.code.pricing.application.domain;


import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Cover {

    private String code;
    private BigDecimal price;
    
    public String getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setCode(String code) {
		this.code = code;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Cover)) return false;

        Cover that = (Cover) o;

        return new EqualsBuilder()
                .append(code, that.code)
                .append(price, that.price)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(code)
                .append(price)
                .toHashCode();
    } 
    
	public static final class Builder {

        private final Cover object;

        public Builder() {
            object = new Cover();
        }

        public Builder withCode(String value) {
            object.code = value;
            return this;
        }

        public Builder withPrice(BigDecimal value) {
            object.price = value;
            return this;
        }

        public Cover build() {
            return object;
        }

    }
}
