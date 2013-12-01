package model.strategia;

import model.Model;
import model.SterujBohaterem;

public class UstawPrzesuniecieWDol implements Strategia {

	private double przesuniecie;
	
	public UstawPrzesuniecieWDol(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie(Model model) {
		SterujBohaterem.ustawPrzesuniecieWDol(model.wezStatekBohatera(), przesuniecie);
	}

}
