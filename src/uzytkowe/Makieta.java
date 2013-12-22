package uzytkowe;

import java.awt.Dimension;
import java.util.List;


public class Makieta {

	private Dimension rozmiar;
	private List<Wspolrzedne> wspolrzedneStatkowWroga;
	private Wspolrzedne wspolrzedneStatkuBohatera;
	
	//tymczasowe gettery potem zwracam cala makiete
	public Wspolrzedne getWspolrzedneStatkuBohatera() {
		return wspolrzedneStatkuBohatera;
	}

	public void setWspolrzedneStatkuBohatera(Wspolrzedne wspolrzedneStatkuBohatera) {
		this.wspolrzedneStatkuBohatera = wspolrzedneStatkuBohatera;
	}

	public Makieta(Dimension rozmiar) {
		this.rozmiar = rozmiar;
	}
	
	public List<Wspolrzedne> getWspolrzedneStatkowWroga() {
		return wspolrzedneStatkowWroga;
	}

	public void setWspolrzedneStatkowWroga(List<Wspolrzedne> wspolrzedneStatkowWroga) {
		this.wspolrzedneStatkowWroga = wspolrzedneStatkowWroga;
	}
	
	public Dimension getRozmiar() {
		return rozmiar;
	}
	
	
}
