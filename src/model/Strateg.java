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
		mapaStrategii = new HashMap<ZdarzenieGry, Strategia>(0);
		organizujStrategie(silnikWroga, silnikBohatera);
	}

	public void organizujStrategie(SilnikWroga silnikWroga, SilnikBohatera silnikBohatera) {
		
		dodajStrategie(new KolejnyMomentZdarzenie(this), new SilnikGry(silnikWroga, silnikBohatera));
		
		// zrobic wyjatek jak nie ma tego przypisania
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
		mapaStrategii.put(zdarzenieGry, strategia);
	}
	
	public void dzialaj(ZdarzenieGry zdarzenieGry) {
		// tutaj try catch
		Strategia strategia = mapaStrategii.get(zdarzenieGry);
		if(strategia == null) {
			System.out.println("Brak danej strategii");
			return;
		}
		strategia.dzialanie();
	}
	
}
