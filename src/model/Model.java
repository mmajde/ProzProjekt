package model;

import java.awt.Dimension;
import java.util.concurrent.BlockingQueue;

import uzytkowe.StanPrzycisku;

public class Model {

	public Wrog wrog;
	public Bohater bohater;
	public Strateg strateg;
	
	public Model(Dimension rozmiar) {
		wrog = new Wrog();
		bohater = new Bohater(rozmiar);
		strateg = new Strateg(bohater);
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
