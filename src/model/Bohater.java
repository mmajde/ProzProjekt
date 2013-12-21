package model;

import java.awt.Dimension;

import statek.StatekBohatera;
import uzytkowe.Wspolrzedne;

public class Bohater {
		
	// to wyrzucic do StatekBohatera
	private StatekBohatera statekBohatera;
	private Wspolrzedne wspolrzedneBohatera;
	
	public Bohater(Dimension rozmiar) {
		stworzBohatera(new Wspolrzedne(270d, 530d));
		wspolrzedneBohatera = new Wspolrzedne(rozmiar.getWidth()/2, rozmiar.getHeight()/2);
	}

	public StatekBohatera getStatekBohatera() {
		return statekBohatera;
	}

	private void stworzBohatera(Wspolrzedne wspolrzedne) {
		statekBohatera = new StatekBohatera(wspolrzedne);
	}

	public int getGranice() {
		return 0;
	}
	
}
