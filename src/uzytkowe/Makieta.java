package uzytkowe;

import java.awt.Dimension;
import java.util.List;


/**
 *  Przechowuje rozmiar planszy na której wyświetlane są elementy oraz wszystkie współrzędne obiektów które mają być wyświetlone
 */
public class Makieta {

	private Dimension rozmiar;
	private List<Wspolrzedne> wspolrzedneStatkowWroga;
	private Wspolrzedne wspolrzedneStatkuBohatera;
	private List<Wspolrzedne> wspolrzednePociskow;
	
	public Makieta(Dimension rozmiar) {
		this.rozmiar = rozmiar;
	}
	
	public List<Wspolrzedne> getWspolrzednePociskow() {
		return wspolrzednePociskow;
	}

	public void setWspolrzednePociskow(List<Wspolrzedne> wspolrzednePociskow) {
		this.wspolrzednePociskow = wspolrzednePociskow;
	}

	public Wspolrzedne getWspolrzedneStatkuBohatera() {
		return wspolrzedneStatkuBohatera;
	}

	public void setWspolrzedneStatkuBohatera(Wspolrzedne wspolrzedneStatkuBohatera) {
		this.wspolrzedneStatkuBohatera = wspolrzedneStatkuBohatera;
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
