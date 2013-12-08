package model.strategia;

import model.Model;
import model.SterujBohaterem;

public class UstawPrzesuniecieWGore implements Strategia {
	
	private double przesuniecie;
	
	public UstawPrzesuniecieWGore(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie(Model model) {
		SterujBohaterem.ustawPrzesuniecieWGore(model.getStatekBohatera(), przesuniecie);
	}

}
