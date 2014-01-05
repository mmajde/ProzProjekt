package model;

import java.awt.Dimension;

import model.strategia.SilnikBohatera;
import model.strategia.SilnikGry;
import model.strategia.SilnikWroga;
import uzytkowe.Makieta;
import uzytkowe.kolejkablokujaca.KolejkaBlokujaca;
import zdarzenia.ZdarzenieGry;

public class Model {

	private SilnikGry silnikGry;
	public Strateg strateg;
	public Makieta makieta;
	
	public Model(Dimension rozmiar) {
		makieta = new Makieta(rozmiar);
		SilnikWroga silnikWroga = new SilnikWroga(makieta);
		SilnikBohatera silnikBohatera = new SilnikBohatera(makieta);
		silnikGry = new SilnikGry(silnikWroga, silnikBohatera);
		strateg = new Strateg(silnikWroga, silnikBohatera);
	}

	public void zarzadzajZdarzeniami() {
		silnikGry.dzialaj();
		while(!KolejkaBlokujaca.czyPusta()) {
			ZdarzenieGry nastepneZdarzenieGry = KolejkaBlokujaca.wezNastepneZdarzenieGry();
			strateg.dzialaj(nastepneZdarzenieGry);
		}
	}
	
	public Makieta getMakieta() {
		return makieta;
	}

}
