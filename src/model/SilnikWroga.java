package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


import statek.StatekWroga;
import uzytkowe.Makieta;
import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;
import uzytkowe.Wymiary;

/**
 * Kontroluje ustawienie statkow wroga na mapie
 */
public class SilnikWroga {
	
	private final Logger LOG = Logger.getLogger("log");
	
	/**	Zmienna służąca do utrzymywania stałej liczby wrogów w grze */
	private final int MAX_LICZBA_STATKOW = 50;
	private final double DLUGOSC_RUCHU = 0.5;
	private final double SZEROKOSC_STATKU = 30.0;
	private final double WYSOKOSC_STATKU = 20.0;
	
	private int aktualnaLiczbaStatkow;
	private Random generatorWspolrzednych;
	private Map<StatekWroga, Wspolrzedne> statkiOrazIchWspolrzedne;
	private Wymiary wymiaryStatku;
	private Makieta makieta;
	private double szerokoscMapy;
	private double wysokoscMapy;
	
	/**
	 * Tworzy statki na mapie
	 * @param makieta - potrzebna przy pobieraniu wspolrzednych statkow
	 */
	public SilnikWroga(Makieta makieta) {
		inicjalizujWartosci(makieta);	
		stworzStatki();
	}

	private void inicjalizujWartosci(Makieta makieta) {
		this.makieta = makieta;
		aktualnaLiczbaStatkow = 0;
		generatorWspolrzednych = new Random();
		//czy tutaj kontrola throw new RuntimeException()?
		statkiOrazIchWspolrzedne = new ConcurrentHashMap<StatekWroga, Wspolrzedne>(0);
		//czy tutaj kontrola throw new RuntimeException()?
		wymiaryStatku = new Wymiary(SZEROKOSC_STATKU, WYSOKOSC_STATKU);
		szerokoscMapy = makieta.getRozmiar().getWidth();
		wysokoscMapy = makieta.getRozmiar().getHeight();
	}
	
	/**
	 * Wowyluje metody kontrolujace ustawieniem statkow na mapie i na makiecie
	 */
	public void dzialaj() {
		stworzStatki();
		przesunStatki();
		usunStatkiZaMapa();
		ustawStatkiNaMakiecie();
	}
	
	public void usunStatek(StatekWroga statekWroga) {
		try {
			statkiOrazIchWspolrzedne.remove(statekWroga);
			aktualnaLiczbaStatkow--;
		} catch(NullPointerException e) {
			LOG.info("StatekWroga nie może być nullem. " + e.getMessage());
		} catch(ClassCastException | UnsupportedOperationException e) {
			throw new RuntimeException();
		}
	}
	
	private void usunStatkiZaMapa() {
		for(Map.Entry<StatekWroga, Wspolrzedne> wspolrzedneStatku : statkiOrazIchWspolrzedne.entrySet()) {
			//czy tutaj kontrola throw new RuntimeException()?
			if(czyStatekZaMapa(wspolrzedneStatku.getValue())) {
				try {
					usunStatek(wspolrzedneStatku.getKey());
				} catch (UnsupportedOperationException | IllegalStateException | NullPointerException e) {
					throw new RuntimeException();
				}
			}
		}
	}
	
	private boolean czyStatekZaMapa(Wspolrzedne wspolrzedneStatku) {
		return wspolrzedneStatku.getY() > makieta.getRozmiar().getHeight();
	}
	
	private void stworzStatki() {
		while(aktualnaLiczbaStatkow < MAX_LICZBA_STATKOW) {	
			dodajStatek(generujWspolrzedne());
		}
	}

	private Wspolrzedne generujWspolrzedne() {
		double x = generatorWspolrzednych.nextDouble() * szerokoscMapy;
		double y = generatorWspolrzednych.nextDouble() * wysokoscMapy + wysokoscMapy/2;
		x = x < 0 ? -x : x; 
		y = y < 0 ? y : -y;
		return new Wspolrzedne(x, y);
	}

	private void dodajStatek(Wspolrzedne wspolrzedneStatku) {
		try {
			statkiOrazIchWspolrzedne.put(new StatekWroga(wymiaryStatku), wspolrzedneStatku);
			aktualnaLiczbaStatkow++;
		} catch(NullPointerException e) {
			LOG.info("Pocisk nie może być nullem. " + e.getMessage());
		} catch(ClassCastException | IllegalArgumentException | UnsupportedOperationException e) {
			throw new RuntimeException();
		}
	}
	
	private void przesunStatki() {
		Przesuniecie przesuniecie = new Przesuniecie(0, DLUGOSC_RUCHU, 0, 0);
		for(Map.Entry<StatekWroga, Wspolrzedne> wspolrzedneStatku : statkiOrazIchWspolrzedne.entrySet()) {
			//czy tutaj kontrola throw new RuntimeException()?
			Wspolrzedne biezaceWspolrzedne = wspolrzedneStatku.getValue();
			Wspolrzedne noweWspolrzedne = RuchObiektem.przesunObiekt(biezaceWspolrzedne, przesuniecie);
			ustawWspolrzedneStatku(wspolrzedneStatku, noweWspolrzedne);
		}
	}

	private void ustawWspolrzedneStatku(Map.Entry<StatekWroga, Wspolrzedne> wspolrzedneStatku, Wspolrzedne noweWspolrzedne) {
		try {
			wspolrzedneStatku.setValue(noweWspolrzedne);
		} catch (NullPointerException e) {
			LOG.info("Wspolrzedne statku nie mogą być nullem. " + e.getMessage());
		} catch (UnsupportedOperationException | ClassCastException | IllegalArgumentException | IllegalStateException e) {
			throw new RuntimeException();
		}
	}

	private void ustawStatkiNaMakiecie() {
		try {
			List<Wspolrzedne> wspolrzedneStatkow = new ArrayList<Wspolrzedne>(statkiOrazIchWspolrzedne.values());
			makieta.setWspolrzedneStatkowWroga(wspolrzedneStatkow);
		} catch (NullPointerException e) {
			throw new NullPointerException();
		}
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
