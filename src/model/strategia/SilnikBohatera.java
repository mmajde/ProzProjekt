package model.strategia;

import model.RuchObiektem;
import statek.StatekBohatera;
import uzytkowe.Makieta;
import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

public class SilnikBohatera {
		
	private StatekBohatera statekBohatera;
	private Wspolrzedne wspolrzedneBohatera;
	private Przesuniecie przesuniecieBohatera;
	private Makieta makieta;
	
	public SilnikBohatera(Makieta makieta) {
		statekBohatera = new StatekBohatera();
		this.makieta = makieta;
		wspolrzedneBohatera = new Wspolrzedne(makieta.getRozmiar().getWidth()/2, makieta.getRozmiar().getHeight()-60);
		przesuniecieBohatera = new Przesuniecie();
		
	}

	public Wspolrzedne getWspolrzedneBohatera() {
		return wspolrzedneBohatera;
	}
	
	public Przesuniecie getPrzesuniecieBohatera() {
		return przesuniecieBohatera;
	}

	public int getGranice() {
		return 0;
	}

	public void dzialaj() {
		wspolrzedneBohatera = RuchObiektem.przesun(wspolrzedneBohatera, przesuniecieBohatera);
		makieta.setWspolrzedneStatkuBohatera(wspolrzedneBohatera);
	}
	
}
