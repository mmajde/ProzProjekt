package statek;

import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

public class Statek {

	protected Wspolrzedne wspolrzedne;
	protected Przesuniecie przesuniecie;
	
	public Statek(Wspolrzedne wspolrzedne) {
		this.wspolrzedne = wspolrzedne;
		this.przesuniecie = new Przesuniecie();
	}
	
	public double getX() {
		return wspolrzedne.getX();
	}

	public void setX(double x) {
		wspolrzedne.setX(x);
	}
	
	public double getY() {
		return wspolrzedne.getY();
	}

	public void setY(double y) {
		wspolrzedne.setY(y);
	}

	public double getDol() {
		return przesuniecie.getDol();
	}
	
	public void setDol(double dol) {
		przesuniecie.setDol(dol);
	}

	public double getGora() {
		return przesuniecie.getGora();
	}
	
	public void setGora(double gora) {
		przesuniecie.setGora(gora);
	}
	
	public double getPrawo() {
		return przesuniecie.getPrawo();
	}
	
	public void setPrawo(double prawo) {
		przesuniecie.setPrawo(prawo);
	}
	
	public double getLewo() {
		return przesuniecie.getLewo();
	}
	
	public void setLewo(double lewo) {
		przesuniecie.setLewo(lewo);
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
