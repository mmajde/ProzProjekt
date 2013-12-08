package model.strategia;

import kontroler.SterowanieBohaterem;
import statek.StatekBohatera;

public class UstawPrzesuniecieWLewo implements Strategia {

	private double przesuniecie;
	
	public UstawPrzesuniecieWLewo(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie(StatekBohatera statekBohatera) {
		SterowanieBohaterem.ustawPrzesuniecieWLewo(statekBohatera, przesuniecie);
	}

}
