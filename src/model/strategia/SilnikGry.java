package model.strategia;

import model.Kolizja;
import statek.Pocisk;
import statek.StatekWroga;
import uzytkowe.PociskiIStatkiDoUsuniecia;


public class SilnikGry implements Strategia {

	private SilnikWroga silnikWroga;
	private SilnikBohatera silnikBohatera;
	
	public SilnikGry(SilnikWroga silnikWroga, SilnikBohatera silnikBohatera) {
		this.silnikWroga = silnikWroga;
		this.silnikBohatera = silnikBohatera;
	}

	@Override
	public void dzialanie() {
		silnikWroga.dzialaj();
		silnikBohatera.dzialaj();
		sprawdzIUsunKolizje();
	}

	private void sprawdzIUsunKolizje() {
		kolizjaBohateraZWrogiem();
		kolizjaPociskuZWrogiem();
	}

	private void kolizjaPociskuZWrogiem() {
		// tu zastanowic sie nad jakims DTO
		PociskiIStatkiDoUsuniecia pociskiIStatkiDoUsuniecia = Kolizja.sprawdzKolizjePociskuZWrogiem(
				silnikWroga.getStatkiOrazIchWspolrzedne(), silnikWroga.getWymiaryStatku(), silnikBohatera.getPociski());
		for(StatekWroga statekWrogaDoUsuniecia : pociskiIStatkiDoUsuniecia.getStatkiWrogaDoUsuniecia()) {
			// try catch
			silnikWroga.usunStatek(statekWrogaDoUsuniecia);
		}
		for(Pocisk pociskDoUsuniecia : pociskiIStatkiDoUsuniecia.getPociskiDoUsuniecia()) {
			// try catch
			silnikBohatera.usunPocisk(pociskDoUsuniecia);
		}
	}

	public void kolizjaBohateraZWrogiem() {
		StatekWroga kolidujacyStatekWroga = Kolizja.sprawdzKolizjeWrogaZBohaterem(silnikWroga.getStatkiOrazIchWspolrzedne(), silnikWroga.getWymiaryStatku(), 
				silnikBohatera.getWspolrzedneBohatera(), silnikBohatera.getWymiaryStatku());
		if(kolidujacyStatekWroga != null){
			silnikWroga.usunStatek(kolidujacyStatekWroga);
		}
	}

}
