package es.urjc.code.pricing.init;

import java.math.BigDecimal;

import es.urjc.code.pricing.application.domain.PercentMarkupRule;
import es.urjc.code.pricing.application.domain.Tariff;


public class DemoTariffsFactory {
	
	private DemoTariffsFactory() {
		
	}
	
    static Tariff travel() {
        Tariff t = new Tariff("TRI");

        t.addBasePriceRule("C1", null, "(NUM_OF_ADULTS) * (DESTINATION == 'EUR' ? 26.00B : 34.00B)");
        t.addBasePriceRule("C2", null, "(NUM_OF_ADULTS + NUM_OF_CHILDREN) * 26.00B");
        t.addBasePriceRule("C3", null, "(NUM_OF_ADULTS + NUM_OF_CHILDREN) * 10.00B");

        t.addPercentMarkup(new PercentMarkupRule("DESTINATION == 'WORLD'", new BigDecimal("1.50")));

        return t;
    }

    static Tariff house() {
        Tariff t = new Tariff("HSI");

        t.addBasePriceRule("C1", "TYP == 'APT'", "AREA * 1.25B");
        t.addBasePriceRule("C1", "TYP == 'HOUSE'", "AREA * 1.50B");

        t.addBasePriceRule("C2", "TYP == 'APT'", "AREA * 0.25B");
        t.addBasePriceRule("C2", "TYP == 'HOUSE'", "AREA * 0.45B");

        t.addBasePriceRule("C3", null, "30B");
        t.addBasePriceRule("C4", null, "50B");

        t.addPercentMarkup(new PercentMarkupRule("FLOOD == 'YES'", new BigDecimal("1.50")));
        t.addPercentMarkup(new PercentMarkupRule("NUM_OF_CLAIM > 1 ", new BigDecimal("1.25")));

        return t;
    }

    static Tariff farm() {
        Tariff t = new Tariff("FAI");

        t.addBasePriceRule("C1", null, "10B");
        t.addBasePriceRule("C2", null, "20B");
        t.addBasePriceRule("C3", null, "30B");
        t.addBasePriceRule("C4", null, "40B");

        t.addPercentMarkup(new PercentMarkupRule("FLOOD == 'YES'", new BigDecimal("1.50")));
        t.addPercentMarkup(new PercentMarkupRule("NUM_OF_CLAIM > 2", new BigDecimal("2.00")));

        return t;
    }

    static Tariff car() {
        Tariff t = new Tariff("CAR");

        t.addBasePriceRule("C1", null, "100B");
        t.addPercentMarkup(new PercentMarkupRule("NUM_OF_CLAIM > 2", new BigDecimal("50.00")));

        return t;
    }
}
