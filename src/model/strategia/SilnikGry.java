package model.strategia;

import model.Kolizja;
import model.Pocisk;
import statek.StatekWroga;
import uzytkowe.PociskiIStatkiKolidujace;


public class SilnikGry implements Strategia {

	private SilnikWroga silnikWroga;
	private SilnikBohatera silnikBohatera;
	
	public SilnikGry(SilnikWroga silnikWroga, SilnikBohatera silnikBohatera) {
		this.silnikWroga = silnikWroga;
		this.silnikBohatera = silnikBohatera;
	}

	@Override
	public void dzialanie() {
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
		// tu zastanowic sie nad jakims DTO do przekazywania argumentow
		PociskiIStatkiKolidujace pociskiIStatkiKolidujace = Kolizja.pobierzKolizjePociskowZWrogami(
				silnikWroga.getStatkiOrazIchWspolrzedne(), silnikWroga.getWymiaryStatku(), silnikBohatera.getPociski());
		for(StatekWroga statekWrogaDoUsuniecia : pociskiIStatkiKolidujace.getStatkiWrogaKolidujace()) {
			// try catch
			silnikWroga.usunStatek(statekWrogaDoUsuniecia);
		}
		for(Pocisk pociskDoUsuniecia : pociskiIStatkiKolidujace.getPociskiKolidujace()) {
			// try catch
			// zastanowic sie czy w Silniku nie zrobic funkcji do usuwania danego obiektu z danej listy
			silnikBohatera.usunPocisk(pociskDoUsuniecia);
		}
	}

	private void usunKolizjeBohateraZWrogiem() {
		StatekWroga kolidujacyStatekWroga = Kolizja.pobierzKolizjeWrogaZBohaterem(silnikWroga.getStatkiOrazIchWspolrzedne(), silnikWroga.getWymiaryStatku(), 
				silnikBohatera.getWspolrzedneBohatera(), silnikBohatera.getWymiaryStatku());
		if(kolidujacyStatekWroga != null){
			silnikWroga.usunStatek(kolidujacyStatekWroga);
		}
	}

}
