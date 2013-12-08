package model.strategia;

import model.Model;
import model.SterujBohaterem;

public class UstawPrzesuniecieWLewo implements Strategia {

	private double przesuniecie;
	
	public UstawPrzesuniecieWLewo(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie(Model model) {
		SterujBohaterem.ustawPrzesuniecieWLewo(model.getStatekBohatera(),przesuniecie);
	}

}
