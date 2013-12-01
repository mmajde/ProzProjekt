package model;

import java.awt.event.KeyEvent;
import java.util.List;

import model.strategia.*;

import statek.StatekBohatera;
import statek.StatekWroga;
import uzytkowe.Wspolrzedne;

public class Model {

	private StatekBohatera statekBohatera;
	private List<StatekWroga> statkiWroga;
	
	private Kontekst kontekst;
	
	public Model() {
		statekBohatera = new StatekBohatera(new Wspolrzedne(0d, 0d));
		organizujStrategie();
	}
	
	public void stworzBohatera(Wspolrzedne wspolrzedne) {
		statekBohatera = new StatekBohatera(wspolrzedne);
	}
	
	public StatekBohatera wezStatekBohatera() {
		return statekBohatera;
	}
	
	/*wyrzucic zarzadzanie strategia w inne miejsce */
	public void organizujStrategie() {
		kontekst = new Kontekst();
		kontekst.organizujStrategie();
	}
	
	public void dzialaj(int klucz) {
		Strategia strategia = kontekst.pobierzStrategie(klucz);
		if(strategia != null) {
			strategia.dzialanie(this);
			return;
		}
		
		// zastanowic sie czy rzucac wyjatek jezeli nie ma strategii
		System.out.println("Brak danej strategii");
	}


}
