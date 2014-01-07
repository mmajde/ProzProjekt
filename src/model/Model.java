package model;

import java.awt.Dimension;

import uzytkowe.Makieta;
import uzytkowe.kolejkablokujaca.KolejkaBlokujaca;
import wyjatki.NullBlockingQueueException;
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
		strateg = new Strateg(silnikBohatera);
	}

	/**
	 * Uruchamia główny silnik gry i zarządza zdarzeniami gry
	 * @throws NullBlockingQueueException jeśli kolejka blokująca nie została wcześniej utworzona
	 */
	public void dzialanieModelu() throws NullBlockingQueueException {
		silnikGry.dzialaj();
		while(czyIstniejeZdarzenieWKolejce()) {
			ZdarzenieGry nastepneZdarzenieGry = KolejkaBlokujaca.wezNastepneZdarzenieGry();
			strateg.dzialaj(nastepneZdarzenieGry);
		}
	}

	private boolean czyIstniejeZdarzenieWKolejce() throws NullBlockingQueueException {
		boolean czyIstniejeZdarzenie = false;
		czyIstniejeZdarzenie = KolejkaBlokujaca.czyPusta();
		return !czyIstniejeZdarzenie;
	}
	
	public Makieta getMakieta() {
		return makieta;
	}

	public boolean sprawdzCzyKoniecGry() {
		return silnikGry.czyKoniec();
	}

}
