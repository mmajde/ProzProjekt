package model;

import statek.StatekWroga;
import uzytkowe.PociskiIStatkiKolidujace;

public class SilnikGry {

	private SilnikWroga silnikWroga;
	private SilnikBohatera silnikBohatera;
	private boolean koniecGry;
	
	public SilnikGry(SilnikWroga silnikWroga, SilnikBohatera silnikBohatera) {
		this.silnikWroga = silnikWroga;
		this.silnikBohatera = silnikBohatera;
		koniecGry = false;
	}

	public void dzialaj() {
		uruchomSilniki();
		sprawdzIUsunKolizje();
	}

	private void uruchomSilniki() {
		silnikWroga.dzialaj();
		silnikBohatera.dzialaj();
	}

	private void sprawdzIUsunKolizje() {
		usunKolizjeBohateraZWrogiem();
		usunKolizjePociskuZWrogiem();
	}

	private void usunKolizjePociskuZWrogiem() {
		PociskiIStatkiKolidujace pociskiIStatkiKolidujace = Kolizja.pobierzKolizjePociskowZWrogami(
				silnikWroga.getStatkiOrazIchWspolrzedne(), silnikWroga.getWymiaryStatku(), silnikBohatera.getPociski());
		for(StatekWroga statekWrogaDoUsuniecia : pociskiIStatkiKolidujace.getStatkiWrogaKolidujace()) {
			silnikWroga.usunStatek(statekWrogaDoUsuniecia);
		}
		for(Pocisk pociskDoUsuniecia : pociskiIStatkiKolidujace.getPociskiKolidujace()) {
			silnikBohatera.usunPocisk(pociskDoUsuniecia);
		}
	}

	private void usunKolizjeBohateraZWrogiem() {
		StatekWroga kolidujacyStatekWroga = Kolizja.pobierzKolizjeWrogaZBohaterem(silnikWroga.getStatkiOrazIchWspolrzedne(), silnikWroga.getWymiaryStatku(), 
				silnikBohatera.getWspolrzedneBohatera(), silnikBohatera.getWymiaryBohatera());
		if(kolidujacyStatekWroga != null) {
			silnikWroga.usunStatek(kolidujacyStatekWroga);
			koniecGry = true;
		}
	}
	
	public boolean czyKoniec() {
		return koniecGry;
	}

}
