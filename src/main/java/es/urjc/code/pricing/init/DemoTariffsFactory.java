package es.urjc.code.pricing.init;

import java.math.BigDecimal;

import es.urjc.code.pricing.infrastructure.adapter.repository.entity.PercentMarkupRuleEntity;
import es.urjc.code.pricing.infrastructure.adapter.repository.entity.TariffEntity;


public class DemoTariffsFactory {
	
	private DemoTariffsFactory() {
		
	}
	
    static TariffEntity travel() {
        TariffEntity t = new TariffEntity("TRI");

        t.addBasePriceRule("C1", null, "(NUM_OF_ADULTS) * (DESTINATION == 'EUR' ? 26.00B : 34.00B)");
        t.addBasePriceRule("C2", null, "(NUM_OF_ADULTS + NUM_OF_CHILDREN) * 26.00B");
        t.addBasePriceRule("C3", null, "(NUM_OF_ADULTS + NUM_OF_CHILDREN) * 10.00B");

        t.addPercentMarkup(new PercentMarkupRuleEntity("DESTINATION == 'WORLD'", new BigDecimal("1.50")));

        return t;
    }

    static TariffEntity house() {
        TariffEntity t = new TariffEntity("HSI");

        t.addBasePriceRule("C1", "TYP == 'APT'", "AREA * 1.25B");
        t.addBasePriceRule("C1", "TYP == 'HOUSE'", "AREA * 1.50B");

        t.addBasePriceRule("C2", "TYP == 'APT'", "AREA * 0.25B");
        t.addBasePriceRule("C2", "TYP == 'HOUSE'", "AREA * 0.45B");

        t.addBasePriceRule("C3", null, "30B");
        t.addBasePriceRule("C4", null, "50B");

        t.addPercentMarkup(new PercentMarkupRuleEntity("FLOOD == 'YES'", new BigDecimal("1.50")));
        t.addPercentMarkup(new PercentMarkupRuleEntity("NUM_OF_CLAIM > 1 ", new BigDecimal("1.25")));

        return t;
    }

    static TariffEntity farm() {
        TariffEntity t = new TariffEntity("FAI");

        t.addBasePriceRule("C1", null, "10B");
        t.addBasePriceRule("C2", null, "20B");
        t.addBasePriceRule("C3", null, "30B");
        t.addBasePriceRule("C4", null, "40B");

        t.addPercentMarkup(new PercentMarkupRuleEntity("FLOOD == 'YES'", new BigDecimal("1.50")));
        t.addPercentMarkup(new PercentMarkupRuleEntity("NUM_OF_CLAIM > 2", new BigDecimal("2.00")));

        return t;
    }

    static TariffEntity car() {
        TariffEntity t = new TariffEntity("CAR");

        t.addBasePriceRule("C1", null, "100B");
        t.addPercentMarkup(new PercentMarkupRuleEntity("NUM_OF_CLAIM > 2", new BigDecimal("50.00")));

        return t;
    }
}
