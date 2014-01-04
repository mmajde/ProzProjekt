package model;

import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

public class Pocisk extends ObiektGry {
	
	private Wspolrzedne wspolrzedne;
	private Przesuniecie przesuniecie;
	
	public Pocisk(Wspolrzedne wspolrzedne, double kierunek) {
		this.wspolrzedne = wspolrzedne;
		this.przesuniecie = new Przesuniecie(kierunek, 0, 0, 0);
	}
	
	public Wspolrzedne getWspolrzedne() {
		return wspolrzedne;
	}
	
	public void setWspolrzedne(Wspolrzedne wspolrzedne) {
		this.wspolrzedne = wspolrzedne;
	}
	
	public Przesuniecie getPrzesuniecie() {
		return przesuniecie;
	}
	
	public void setPrzesuniecie(Przesuniecie przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
}
