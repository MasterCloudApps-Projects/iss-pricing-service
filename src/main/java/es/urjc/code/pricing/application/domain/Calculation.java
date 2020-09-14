package es.urjc.code.pricing.application.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Calculation {

    private String productCode;
    private LocalDate policyFrom;
    private LocalDate policyTo;
    private BigDecimal totalPremium;
    private Map<String, Cover> covers = new HashMap<>();
    private Map<String, Object> subject = new HashMap<>();

    public String getProductCode() {
		return productCode;
	}

	public LocalDate getPolicyFrom() {
		return policyFrom;
	}

	public LocalDate getPolicyTo() {
		return policyTo;
	}

	public BigDecimal getTotalPremium() {
		return totalPremium;
	}

	public Map<String, Cover> getCovers() {
		return covers;
	}

	public Map<String, Object> getSubject() {
		return subject;
	}

	public Calculation(String productCode,
                       LocalDate policyFrom,
                       LocalDate policyTo,
                       Iterable<String> selectedCovers,
                       Map<String, Object> subject) {
        this.productCode = productCode;
        this.policyFrom = policyFrom;
        this.policyTo = policyTo;
        this.totalPremium = BigDecimal.ZERO;
        selectedCovers.forEach(this::zeroPrice);
        this.subject = subject;
    }

	public Map<String, Object> toMap() {
        Map<String, Object> context = new HashMap<>();

        context.put("policyFrom", policyFrom);
        context.put("policyTo", policyTo);
        for (Cover cover : covers.values()) {
            context.put(cover.getCode(), cover);
        }
        context.putAll(subject);

        return context;
    }


	public void updateTotal() {
        totalPremium = covers
                .values()
                .stream()
                .filter(c -> c.getPrice() != null)
                .map(Cover::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void zeroPrice(String cover) {
        covers.put(cover, new Cover.Builder().withCode(cover).withPrice(BigDecimal.ZERO).build());
    }
}
