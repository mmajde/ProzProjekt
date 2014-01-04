package model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import zdarzenia.KolejnyMomentZdarzenie;
import zdarzenia.ZdarzenieGry;
import zdarzenia.ZdarzeniePrzycisku;

import model.strategia.GenerujPocisk;
import model.strategia.SilnikBohatera;
import model.strategia.SilnikGry;
import model.strategia.SilnikWroga;
import model.strategia.Strategia;
import model.strategia.UstawPrzesuniecieBohatera;
import model.strategia.UstawPrzesuniecieBohateraWDol;
import model.strategia.UstawPrzesuniecieBohateraWGore;
import model.strategia.UstawPrzesuniecieBohateraWLewo;
import model.strategia.UstawPrzesuniecieBohateraWPrawo;


public class Strateg {

	private Map<ZdarzenieGry, Strategia> mapaStrategii;
	
	public Strateg(SilnikWroga silnikWroga, SilnikBohatera silnikBohatera) {
		inicjalizujPola();
		organizujStrategie(silnikWroga, silnikBohatera);
	}

	public void inicjalizujPola() {
		mapaStrategii = new HashMap<ZdarzenieGry, Strategia>(0);
	}

	public void organizujStrategie(SilnikWroga silnikWroga, SilnikBohatera silnikBohatera) {
		// organizuj Strategia w throws a tam wyzej powinno sie oddzielic organizuj strategie do funkcji
		// w ktorej bedzie sprawdzenie czy nie wystepuje blad
		dodajStrategie(new KolejnyMomentZdarzenie(this), new SilnikGry(silnikWroga, silnikBohatera));
		
		// zrobic wyjatek jak nie ma tego przypisania
		// side effect? - mozna podzielic to na 3 czesci i w czesci z przyciskami wywolywac najpierw
		// metode PrzypisPrzesuniecie
		UstawPrzesuniecieBohatera.PrzypiszPrzesuniecie(silnikBohatera.getPrzesuniecieBohatera());
		// magic numbers
		dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_UP, true), new UstawPrzesuniecieBohateraWGore(-1d));
		dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_DOWN, true), new UstawPrzesuniecieBohateraWDol(1d));
		dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_LEFT, true), new UstawPrzesuniecieBohateraWLewo(-1d));
		dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_RIGHT, true), new UstawPrzesuniecieBohateraWPrawo(1d));

		dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_UP, false), new UstawPrzesuniecieBohateraWGore(0d));
		dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_DOWN, false), new UstawPrzesuniecieBohateraWDol(0d));
		dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_LEFT, false), new UstawPrzesuniecieBohateraWLewo(0d));
		dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_RIGHT, false), new UstawPrzesuniecieBohateraWPrawo(0d));
		
		dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_SPACE, false), new GenerujPocisk(silnikBohatera));

	}
	
	private void dodajStrategie(ZdarzenieGry zdarzenieGry, Strategia strategia) {
		// try catch a raczej throws
		mapaStrategii.put(zdarzenieGry, strategia);
	}
	
	public void dzialaj(ZdarzenieGry zdarzenieGry) {
		Strategia strategia = wezStrategie(zdarzenieGry);
		if(strategia != null) {
			strategia.dzialanie();
		}	
	}

	private Strategia wezStrategie(ZdarzenieGry zdarzenieGry) {
		// tutaj try catch
		Strategia strategia = mapaStrategii.get(zdarzenieGry);
		return strategia;
	}
	
}
