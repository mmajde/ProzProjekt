package model.strategia;

import model.Model;

public interface Strategia {

	/* Strategie będą wywoływane na rzecz konkretnego modelu */
	public void dzialanie(Model model);
}
