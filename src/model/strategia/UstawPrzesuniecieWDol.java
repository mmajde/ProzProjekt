package model.strategia;

import model.SterowanieBohaterem;
import statek.StatekBohatera;

public class UstawPrzesuniecieWDol implements Strategia {

	private double przesuniecie;
	
	public UstawPrzesuniecieWDol(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie(StatekBohatera statekBohatera) {
		SterowanieBohaterem.ustawPrzesuniecieWDol(statekBohatera, przesuniecie);
	}

}
