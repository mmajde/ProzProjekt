package model;

import java.awt.Dimension;

import model.strategia.SilnikBohatera;
import model.strategia.SilnikWroga;
import uzytkowe.Makieta;
import uzytkowe.kolejkablokujaca.KolejkaBlokujaca;
import zdarzenia.ZdarzenieGry;

public class Model {

	public SilnikWroga silnikWroga;
	public SilnikBohatera silnikBohatera;
	public Strateg strateg;
	public Makieta makieta;
	
	public Model(Dimension rozmiar) {
		makieta = new Makieta(rozmiar);
		// tu nie powinno byc przekazywanie makiety, 
		// makieta jest jedna wiec moze miec statyczna metoda getMakieta ale oczywiscie wczesniej na przyklad tutaj trzeba
		// zainicjalizowac makiete
		silnikWroga = new SilnikWroga(makieta);
		silnikBohatera = new SilnikBohatera(makieta);
		strateg = new Strateg(silnikWroga, silnikBohatera);
	}

	public void zarzadzajZdarzeniami() {
		silnikWroga.dzialaj();
		silnikBohatera.dzialaj();
		ZdarzenieGry zdarzenieGry;
		while((zdarzenieGry = KolejkaBlokujaca.wezNastepneZdarzenieGry()) != null) {
//		for(ZdarzenieGry zdarzenieGry : kolejkaBlokujaca) {
			strateg.dzialaj(zdarzenieGry);
			// zrobic funkcje pod tytulem usun glowe
//			kolejkaBlokujaca.poll();
		}
	}
	
	public Makieta getMakieta() {
		return makieta;
	}

}
