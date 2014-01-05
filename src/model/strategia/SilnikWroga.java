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
import uzytkowe.Wymiary;

public class SilnikWroga {
// 600 x 600 cala plansza. x od 0 do 600. y od 0 do -600y ustawic to na stale w calym projekcie
	
	/**	Zmienna służąca do utrzymywania stałej liczby wrogów w grze */
	private final int MAX_LICZBA_STATKOW = 50;
	private final double DLUGOSC_RUCHU = 0.5;
	
	private int aktualnaLiczbaStatkow;
	private Random generatorWspolrzednych;
	// to wrzucic do jakiejs klasy
	private Map<StatekWroga, Wspolrzedne> statkiOrazIchWspolrzedne;
	private Makieta makieta;
	private Wymiary wymiaryStatku;
	
	public SilnikWroga(Makieta makieta) {
		this.makieta = makieta;
		aktualnaLiczbaStatkow = 0;
		generatorWspolrzednych = new Random();
		statkiOrazIchWspolrzedne = new ConcurrentHashMap<StatekWroga, Wspolrzedne>(0);
		// magic numbers
		wymiaryStatku = new Wymiary(25.0, 25.0);
		
		stworzStatki();
	}
	
	public void dzialaj() {
		stworzStatki();
		przesunStatki();
		usunStatkiZaMapa();
		ustawStatkiNaMakiecie();
	}
	
	public void usunStatek(StatekWroga statekWroga) {
		try {
			// pomyslec czy nie przekazywac wyzej wyjatku
			statkiOrazIchWspolrzedne.remove(statekWroga);
			aktualnaLiczbaStatkow--;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	private void usunStatkiZaMapa() {
		for(Map.Entry<StatekWroga, Wspolrzedne> wspolrzedneStatku : statkiOrazIchWspolrzedne.entrySet()) {
			// magic number
			if(czyStatekZaMapa(wspolrzedneStatku.getValue())) {
				try {
					// wrzucic tamta mape do jakies klasy (abstrakcja) 
					// i zwracac statek jako klucz i wspolrzedne jako wartosc
					usunStatek(wspolrzedneStatku.getKey());
				} catch (UnsupportedOperationException | IllegalStateException | NullPointerException e) {
					throw new RuntimeException();
				}
			}
		}
	}
	
	private boolean czyStatekZaMapa(Wspolrzedne wspolrzedneStatku) {
		// magic number
		return wspolrzedneStatku.getY() > 600;
	}
	
	// zrobic wyjatek jak ktos nie stowrzyl wrogow a porusza nimi
	private void stworzStatki() {
		while(aktualnaLiczbaStatkow < MAX_LICZBA_STATKOW) {	
			dodajStatek(generujWspolrzedne());
		}
	}

	private Wspolrzedne generujWspolrzedne() {
		// zmienic te magic numbers
		double x = (generatorWspolrzednych.nextInt(550)) + 20 ;
		double y = ((generatorWspolrzednych.nextInt(750)) + 20);
		x = x < 0 ? -x : x; 
		y = y < 0 ? y : -y;
		return new Wspolrzedne(x, y);
	}

	private void dodajStatek(Wspolrzedne wspolrzedneStatku) {
		statkiOrazIchWspolrzedne.put(new StatekWroga(wymiaryStatku), wspolrzedneStatku);	
		aktualnaLiczbaStatkow++;
	}
	
	private void przesunStatki() {
		for(Map.Entry<StatekWroga, Wspolrzedne> wspolrzedneStatku : statkiOrazIchWspolrzedne.entrySet()) {
			wspolrzedneStatku.setValue(RuchObiektem.przesunObiekt(wspolrzedneStatku.getValue(), new Przesuniecie(0, DLUGOSC_RUCHU,0, 0)));
		}
	}

	private void ustawStatkiNaMakiecie() {
		makieta.setWspolrzedneStatkowWroga(new ArrayList<Wspolrzedne>(statkiOrazIchWspolrzedne.values()));
	}
	
	public List<Wspolrzedne> getWspolrzedneStatkow() {
		return new ArrayList<Wspolrzedne>(statkiOrazIchWspolrzedne.values());
	}
	
	public Map<StatekWroga, Wspolrzedne> getStatkiOrazIchWspolrzedne() {
		return statkiOrazIchWspolrzedne;
	}
	
	public Wymiary getWymiaryStatku() {
		return wymiaryStatku;
	}
}
