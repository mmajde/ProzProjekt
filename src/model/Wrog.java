package model;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import statek.StatekWroga;
import uzytkowe.Wspolrzedne;

public class Wrog {
// 600 x 600 cala plansza. x od 0 do 600. y od 0 do -600y ustawic to na stale w calym projekcie
	private List<StatekWroga> statkiWroga;
	private Random generatorWspolrzednych; 
	private final int MAX_LICZBA_WROGOW = 200;
	private int aktualnaLiczbaWrogow;
	
	public Wrog() {
		generatorWspolrzednych = new Random();
		statkiWroga = new CopyOnWriteArrayList<StatekWroga>();
		aktualnaLiczbaWrogow = 0;
	}

	// zrobic wyjatek jak ktos nie stowrzyl wrogow a porusza nimi
	public void stworzWrogow() {
		for(; aktualnaLiczbaWrogow < MAX_LICZBA_WROGOW; aktualnaLiczbaWrogow++) {
			double x = (generatorWspolrzednych.nextInt(550)) + 20 ;
			double y = ((generatorWspolrzednych.nextInt(750)) + 20);
			x = x < 0 ? -x : x; 
			y = y < 0 ? y : -y;
			StatekWroga statekWroga = new StatekWroga(new Wspolrzedne(x, y));
			
			// poprawic magic number
			statekWroga.setDol(1d);
			
			statkiWroga.add(statekWroga);
		}
	}
	
	public void ustawStatki() {
		czyscMape();
		if(aktualnaLiczbaWrogow < getMaxLiczbaWrogow()) {
			stworzWrogow();
		}
		przesunStatkiWroga();	
	}
	
	public int getAktualnaLiczbaWrogow() {
		return aktualnaLiczbaWrogow;
	}
	
	public int getMaxLiczbaWrogow() {
		return MAX_LICZBA_WROGOW;
	}

	public List<StatekWroga> getStatkiWroga() {
		return statkiWroga;
	}

	public void przesunStatkiWroga() {
		for(StatekWroga statekWroga : statkiWroga) {
			statekWroga.setWspolrzedne(Ruch.przesun(statekWroga.getWspolrzedne(), statekWroga.getPrzesuniecie()));
		}
	}
	
	public void czyscMape() {
		for(StatekWroga statekWroga : statkiWroga) {
			if(statekWroga.getY() > 600) {
				try{
					statkiWroga.remove(statekWroga);
					aktualnaLiczbaWrogow--;
				} catch (UnsupportedOperationException e) {
					throw new RuntimeException();
				} catch (IllegalStateException e) {
					throw new RuntimeException();
				}
			}
		}
	}
}
