package model;

import java.awt.Dimension;

import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

public class Makieta {

	private Dimension rozmiar;
	private Bohater bohater;
	private Wspolrzedne wspolrzedneBohatera;
	private Przesuniecie przesuniecieBohatera;
	private Wrog wrog;
	private Strateg strateg;
	
	public Makieta(Dimension rozmiar) {
		this.rozmiar = rozmiar;
		wrog = new Wrog();
	}
	
	
}
