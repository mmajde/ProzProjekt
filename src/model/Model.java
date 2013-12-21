package model;

import java.awt.event.KeyEvent;
import java.util.concurrent.BlockingQueue;

import uzytkowe.StanPrzycisku;

public class Model {

	public Wrog wrog;
	public Bohater bohater;
	public Strateg2 strateg;
	
	public Model() {
		wrog = new Wrog();
		bohater = new Bohater();
		strateg = new Strateg2(bohater);
	}
	
	
	/**
	 * Metoda zarzadzająca elementami znajdującymi się na mapie w każdym momencie
	 */
	public void zarzadzajElementami() {
		wrog.ustawStatki();

	}


	public void zarzadzajZdarzeniami(BlockingQueue<StanPrzycisku> kolejkaBlokujaca) {

		for(StanPrzycisku stanPrzycisku : kolejkaBlokujaca) {
			kolejkaBlokujaca.poll();
			// pobierać te liczby z modelu
//			int klucz = stanPrzycisku.getZdarzenieKlawisza().getKeyCode() + (stanPrzycisku.isWcisniety() == true ? 0 : 200);
			strateg.dzialaj(stanPrzycisku);
		}
		
		SterowanieBohaterem.przesunBohatera(bohater.getStatekBohatera());
	}

}
