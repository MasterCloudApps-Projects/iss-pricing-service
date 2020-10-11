package es.urjc.code.pricing.application.domain;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import es.urjc.code.pricing.application.domain.Calculation;
import es.urjc.code.pricing.application.domain.Tariff;

class TariffTest {
	
    @Test
    void canCalculateTravelPolicyPrice() {
        Map<String, Object> subject = new HashMap<>();
        subject.put("NUM_OF_ADULTS", new BigDecimal("1"));
        subject.put("NUM_OF_CHILDREN", new BigDecimal("1"));
        subject.put("DESTINATION", "EUR");

        Calculation calculation = new Calculation(
                "TRI",
                LocalDate.of(2017, 4, 16),
                LocalDate.of(2017, 4, 20),
                Arrays.asList("C1", "C2", "C3"),
                subject);

        Tariff tariff = TariffsFactory.travel();

        calculation = tariff.calculatePrice(calculation);

        assertEquals("Total premium should be 78", new BigDecimal("98.00"), calculation.getTotalPremium());
        assertEquals("C1 premium should be 26", new BigDecimal("26.00"), calculation.getCovers().get("C1").getPrice());
        assertEquals("C2 should be 52", new BigDecimal("52.00"), calculation.getCovers().get("C2").getPrice());
        assertEquals("C3 should be 20", new BigDecimal("20.00"), calculation.getCovers().get("C3").getPrice());
    }

    @Test
    void canCalculateHousePolicyPrice() {
        Map<String, Object> subject = new HashMap<>();
        subject.put("TYP", "APT");
        subject.put("AREA", new BigDecimal("95"));
        subject.put("NUM_OF_CLAIM", 1);
        subject.put("FLOOD", "NO");

        Calculation calculation = new Calculation(
                "HSI",
                LocalDate.of(2017, 4, 16),
                LocalDate.of(2018, 4, 15),
                Arrays.asList("C1", "C2", "C3"),
                subject);

        Tariff tariff = TariffsFactory.house();

        calculation = tariff.calculatePrice(calculation);

        assertEquals("Total premium should be 172.50", new BigDecimal("172.50"), calculation.getTotalPremium());
        assertEquals("C1 premium should be 118.75", new BigDecimal("118.75"), calculation.getCovers().get("C1").getPrice());
        assertEquals("C2 should be 23.75", new BigDecimal("23.75"), calculation.getCovers().get("C2").getPrice());
        assertEquals("C3 should be 30", new BigDecimal("30"), calculation.getCovers().get("C3").getPrice());
    }

    @Test
    void canCalculateCarPolicyPrice() {
        Map<String, Object> subject = new HashMap<>();
        subject.put("NUM_OF_CLAIM", 1);

        Calculation calculation = new Calculation(
                "CAR",
                LocalDate.of(2017, 4, 16),
                LocalDate.of(2018, 4, 15),
                Collections.singletonList("C1"),
                subject);

        Tariff tariff = TariffsFactory.car();

        calculation = tariff.calculatePrice(calculation);

        assertEquals("Total premium should be 100", new BigDecimal("100"), calculation.getTotalPremium());
        assertEquals("C1 premium should be 100", new BigDecimal("100"), calculation.getCovers().get("C1").getPrice());
    }
}
