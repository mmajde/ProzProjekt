package model.strategia;

import model.SterowanieBohaterem;
import statek.StatekBohatera;

public class UstawPrzesuniecieWGore implements Strategia {
	
	private double przesuniecie;
	
	public UstawPrzesuniecieWGore(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie(StatekBohatera statekBohatera) {
		SterowanieBohaterem.ustawPrzesuniecieWGore(statekBohatera, przesuniecie);
	}

}
