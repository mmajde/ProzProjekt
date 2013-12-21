package model.strategia;

import model.SterowanieBohaterem;
import statek.StatekBohatera;

public class UstawPrzesuniecieWPrawo implements Strategia {

	private double przesuniecie;
	
	public UstawPrzesuniecieWPrawo(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie(StatekBohatera statekBohatera) {
		SterowanieBohaterem.ustawPrzesuniecieWPrawo(statekBohatera, przesuniecie);
	}

}
