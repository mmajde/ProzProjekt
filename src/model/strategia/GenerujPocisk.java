package model.strategia;

import uzytkowe.Wspolrzedne;

public class GenerujPocisk implements Strategia {

	private SilnikBohatera silnikBohatera;
	
	public GenerujPocisk(SilnikBohatera silnikBohatera) {
		this.silnikBohatera = silnikBohatera;
	}

	@Override
	public void dzialanie() {
		// magic numbers
		double x = silnikBohatera.getWspolrzedneBohatera().getX() + 20;
		double y = silnikBohatera.getWspolrzedneBohatera().getY();
		silnikBohatera.dodajPocisk(new Wspolrzedne(x, y), 1.5);
	}

}
