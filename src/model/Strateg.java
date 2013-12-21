package model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import uzytkowe.StanPrzycisku;

import model.strategia.Strategia;
import model.strategia.UstawPrzesuniecieWDol;
import model.strategia.UstawPrzesuniecieWGore;
import model.strategia.UstawPrzesuniecieWLewo;
import model.strategia.UstawPrzesuniecieWPrawo;

public class Strateg {

	private Map<ObiektGry, Strategia> mapaStrategii;
	private Bohater bohater;
	
	public Strateg(Bohater bohater) {
		this.bohater = bohater;
		mapaStrategii = new HashMap<ObiektGry, Strategia>(0);
		organizujStrategie();
	}
	
	public void organizujStrategie() {
		dodajStrategie(new StanPrzycisku(KeyEvent.VK_UP, true), new UstawPrzesuniecieWGore(-1d));
		dodajStrategie(new StanPrzycisku(KeyEvent.VK_DOWN, true), new UstawPrzesuniecieWDol(1d));
		dodajStrategie(new StanPrzycisku(KeyEvent.VK_LEFT, true), new UstawPrzesuniecieWLewo(-1d));
		dodajStrategie(new StanPrzycisku(KeyEvent.VK_RIGHT, true), new UstawPrzesuniecieWPrawo(1d));

		dodajStrategie(new StanPrzycisku(KeyEvent.VK_UP, false), new UstawPrzesuniecieWGore(0d));
		dodajStrategie(new StanPrzycisku(KeyEvent.VK_DOWN, false), new UstawPrzesuniecieWDol(0d));
		dodajStrategie(new StanPrzycisku(KeyEvent.VK_LEFT, false), new UstawPrzesuniecieWLewo(0d));
		dodajStrategie(new StanPrzycisku(KeyEvent.VK_RIGHT, false), new UstawPrzesuniecieWPrawo(0d));

	}
	
	private void dodajStrategie(ObiektGry obiektGry, Strategia strategia) {
		mapaStrategii.put(obiektGry, strategia);
	}
	
	public void dzialaj(ObiektGry obiektGry) {
		Strategia strategia = mapaStrategii.get(obiektGry);
		if(strategia == null) {
			System.out.println("Brak danej strategii");
			return;
		}
		strategia.dzialanie(bohater.getStatekBohatera());
	}
	
}
