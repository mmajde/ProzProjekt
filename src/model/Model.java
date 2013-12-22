package model;

import java.awt.Dimension;
import java.util.concurrent.BlockingQueue;

import model.strategia.SilnikBohatera;
import model.strategia.SilnikWroga;

import uzytkowe.Makieta;
import zdarzenia.ZdarzenieGry;

public class Model {

	public SilnikWroga silnikWroga;
	public SilnikBohatera silnikBohatera;
	public Strateg strateg;
	public Makieta makieta;
	
	public Model(Dimension rozmiar) {
		makieta = new Makieta(rozmiar);
		silnikWroga = new SilnikWroga(makieta);
		silnikBohatera = new SilnikBohatera(makieta);
		strateg = new Strateg(silnikWroga, silnikBohatera);
	}

	public void zarzadzajZdarzeniami(BlockingQueue<ZdarzenieGry> kolejkaBlokujaca) {

		for(ZdarzenieGry zdarzenieGry : kolejkaBlokujaca) {
			kolejkaBlokujaca.poll();
			strateg.dzialaj(zdarzenieGry);
		}
	}
	
	public Makieta getMakieta() {
		return makieta;
	}

}
