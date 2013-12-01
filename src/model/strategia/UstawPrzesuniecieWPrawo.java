package model.strategia;

import model.Model;
import model.SterujBohaterem;

public class UstawPrzesuniecieWPrawo implements Strategia {

	private double przesuniecie;
	
	public UstawPrzesuniecieWPrawo(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie(Model model) {
		SterujBohaterem.ustawPrzesuniecieWPrawo(model.wezStatekBohatera(), przesuniecie);
	}

}
