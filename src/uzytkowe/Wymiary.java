package uzytkowe;

/**
 * Przechowuje wymiary danego obiektu w grze (szerokość oraz wysokość)
 */
public class Wymiary {
	
	private double szerokosc;
	private double wysokosc;
	
	/**
	 * Tworzy obiekt przechowujący wymiary danego obiektu w grze
	 * @param szerokosc - szerokosc danego obiektu
	 * @param wysokosc - wysokosc danego obiektu
	 * @throws IllegalArgumentException szerokosc lub wysokosc jest ujemna
	 */
	public Wymiary(double szerokosc, double wysokosc) throws IllegalArgumentException{
		if(szerokosc < 0 || wysokosc < 0) {
			throw new IllegalArgumentException();
		}
		this.szerokosc = szerokosc;
		this.wysokosc = wysokosc;
	}

	public double getSzerokosc() {
		return szerokosc;
	}

	public void setSzerokosc(double szerokosc) {
		this.szerokosc = szerokosc;
	}

	public double getWysokosc() {
		return wysokosc;
	}

	public void setWysokosc(double wysokosc) {
		this.wysokosc = wysokosc;
	}
	
}
