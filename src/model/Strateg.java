package model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import model.strategia.GenerujPocisk;
import model.strategia.Strategia;
import model.strategia.UstawPrzesuniecieBohatera;
import model.strategia.UstawPrzesuniecieBohateraWDol;
import model.strategia.UstawPrzesuniecieBohateraWGore;
import model.strategia.UstawPrzesuniecieBohateraWLewo;
import model.strategia.UstawPrzesuniecieBohateraWPrawo;
import zdarzenia.ZdarzenieGry;
import zdarzenia.ZdarzeniePrzycisku;


public class Strateg {

	private final double RUCH_BOHATERA = 1d;
	private final double ZATRZYMANIE_BOHATERA = 0d;
	
	private Map<ZdarzenieGry, Strategia> mapaStrategii;
	
	public Strateg(SilnikBohatera silnikBohatera) {
		inicjalizujPola();
		organizujStrategie(silnikBohatera);
	}

	public void inicjalizujPola() {
		// czy tutaj new RuntimeException()?
		mapaStrategii = new HashMap<ZdarzenieGry, Strategia>(0);
	}

	/**
	 * Tworzy wszystkie strategie w grze do obsługi zdarzeń
	 * @param silnikBohatera - wymagany aby możliwe było wykonywanie przesunięć i generowanie pocisków
	 */
	public void organizujStrategie(SilnikBohatera silnikBohatera) {
		UstawPrzesuniecieBohatera.PrzypiszPrzesuniecie(silnikBohatera.getPrzesuniecieBohatera());

		try {
			dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_UP, true), new UstawPrzesuniecieBohateraWGore(-RUCH_BOHATERA));
			dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_DOWN, true), new UstawPrzesuniecieBohateraWDol(RUCH_BOHATERA));
			dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_LEFT, true), new UstawPrzesuniecieBohateraWLewo(-RUCH_BOHATERA));
			dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_RIGHT, true), new UstawPrzesuniecieBohateraWPrawo(RUCH_BOHATERA));
	
			dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_UP, false), new UstawPrzesuniecieBohateraWGore(ZATRZYMANIE_BOHATERA));
			dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_DOWN, false), new UstawPrzesuniecieBohateraWDol(ZATRZYMANIE_BOHATERA));
			dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_LEFT, false), new UstawPrzesuniecieBohateraWLewo(ZATRZYMANIE_BOHATERA));
			dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_RIGHT, false), new UstawPrzesuniecieBohateraWPrawo(ZATRZYMANIE_BOHATERA));
			
			dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_SPACE, false), new GenerujPocisk(silnikBohatera));
		} catch (NullPointerException e) {
			throw new RuntimeException();
		}
	}
	
	private void dodajStrategie(ZdarzenieGry zdarzenieGry, Strategia strategia) throws NullPointerException{
		try {
			mapaStrategii.put(zdarzenieGry, strategia);
		} catch (UnsupportedOperationException | ClassCastException | IllegalArgumentException e) {
			throw new RuntimeException();
		}
	}
	
	public void dzialaj(ZdarzenieGry zdarzenieGry) {
		if(zdarzenieGry == null) {
			return;
		}
		Strategia strategia = pobierzStrategie(zdarzenieGry);
		if(strategia != null) {
			strategia.dzialanie();
		}	
	}

	private Strategia pobierzStrategie(ZdarzenieGry zdarzenieGry) {
		Strategia strategia;
		try {
			strategia = wezStrategie(zdarzenieGry);
		} catch (NullPointerException e) {
			throw new RuntimeException();
		}
		return strategia;
	}

	private Strategia wezStrategie(ZdarzenieGry zdarzenieGry) throws NullPointerException {
		Strategia strategia; 
		try {
			strategia = mapaStrategii.get(zdarzenieGry);
		} catch (ClassCastException e) {
			throw new RuntimeException();
		}
		return strategia;
	}
	
}
