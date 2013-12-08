package model.strategia;

import statek.StatekBohatera;

public interface Strategia {

	/* Strategie będą wywoływane na rzecz konkretnego modelu */
	public void dzialanie(StatekBohatera statekBohatera);
}
