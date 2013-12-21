package model;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import statek.StatekWroga;
import uzytkowe.Wspolrzedne;

public class Wrog {
// 600 x 600 cala plansza. x od 0 do 600. y od 0 do -600y ustawic to na stale w calym projekcie
	private final int MAX_LICZBA_WROGOW = 200;
	private final double DLUGOSC_RUCHU = 1;
	
	private List<StatekWroga> statkiWroga;
	private int aktualnaLiczbaWrogow;
	
	private Random generatorWspolrzednych;
	
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
			
			statekWroga.setDol(DLUGOSC_RUCHU);
			
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
			statekWroga.setWspolrzedne(RuchBohaterem.przesun(statekWroga.getWspolrzedne(), statekWroga.getPrzesuniecie()));
		}
	}
	
	public void czyscMape() {
		for(StatekWroga statekWroga : statkiWroga) {
			if(statekWroga.getY() > 600) {
				try{
					statkiWroga.remove(statekWroga);
					aktualnaLiczbaWrogow--;
				} catch (UnsupportedOperationException | IllegalStateException e) {
					throw new RuntimeException();
				}
			}
		}
	}
}
