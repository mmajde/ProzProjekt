package model.strategia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import model.RuchObiektem;

import statek.StatekWroga;
import uzytkowe.Makieta;
import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

public class SilnikWroga {
// 600 x 600 cala plansza. x od 0 do 600. y od 0 do -600y ustawic to na stale w calym projekcie
	
	/**	Zmienna służąca do utrzymywania stałej liczby wrogów w grze */
	private final int MAX_LICZBA_STATKOW = 20;
	private final double DLUGOSC_RUCHU = 0.5;
	
	private int aktualnaLiczbaStatkow;
	private Random generatorWspolrzednych;
	private Map<StatekWroga, Wspolrzedne> mapaStatkow;
	private Makieta makieta;
	
	public SilnikWroga(Makieta makieta) {
		this.makieta = makieta;
		aktualnaLiczbaStatkow = 0;
		generatorWspolrzednych = new Random();
		mapaStatkow = new ConcurrentHashMap<StatekWroga, Wspolrzedne>(0);
		
		stworzStatki();
	}


	public void dzialaj() {
		usunStatki();
		if(aktualnaLiczbaStatkow < MAX_LICZBA_STATKOW) {
			stworzStatki();
		}
		przesunStatki();	
	}
	
	public void usunStatki() {
		for(Map.Entry<StatekWroga, Wspolrzedne> wspolrzedneStatku : mapaStatkow.entrySet()) {
			if(wspolrzedneStatku.getValue().getY() > 600) {
				try {
				mapaStatkow.remove(wspolrzedneStatku.getKey());
				aktualnaLiczbaStatkow--;
				} catch (UnsupportedOperationException | IllegalStateException | NullPointerException e) {
					throw new RuntimeException();
				}
			}
		}
	}
	
	// zrobic wyjatek jak ktos nie stowrzyl wrogow a porusza nimi
	public void stworzStatki() {
		while(aktualnaLiczbaStatkow < MAX_LICZBA_STATKOW) {
			
			// zmienic na double (nextDouble)
			double x = (generatorWspolrzednych.nextInt(550)) + 20 ;
			double y = ((generatorWspolrzednych.nextInt(750)) + 20);
			x = x < 0 ? -x : x; 
			y = y < 0 ? y : -y;
			mapaStatkow.put(new StatekWroga(), new Wspolrzedne(x, y));
			
			aktualnaLiczbaStatkow++;
		}
	}
	
	public void przesunStatki() {
		for(Map.Entry<StatekWroga, Wspolrzedne> wspolrzedneStatku : mapaStatkow.entrySet()) {
			wspolrzedneStatku.setValue(RuchObiektem.przesun(wspolrzedneStatku.getValue(), new Przesuniecie(0, DLUGOSC_RUCHU,0, 0)));
		}
		makieta.setWspolrzedneStatkowWroga(new ArrayList<Wspolrzedne>(mapaStatkow.values()));
	}
	
	public List<Wspolrzedne> getWspolrzedneStatkow() {
		return new ArrayList<Wspolrzedne>(mapaStatkow.values());
	}
	
}
