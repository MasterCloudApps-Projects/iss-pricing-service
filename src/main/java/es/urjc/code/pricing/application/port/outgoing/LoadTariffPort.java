package es.urjc.code.pricing.application.port.outgoing;

import es.urjc.code.pricing.application.domain.Tariff;

public interface LoadTariffPort {
	public Tariff handle(String code);
}
