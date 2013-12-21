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

public class Strateg2 {

	private Map<ObiektGry, Strategia> mapaStrategii;
	private Bohater bohater;
	
	public Strateg2(Bohater bohater) {
		this.bohater = bohater;
		mapaStrategii = new HashMap<ObiektGry, Strategia>(0);
		organizujStrategie();
	}
	
	public void organizujStrategie() {
		dodajStrategie(new StanPrzycisku(KeyEvent.VK_UP, true), new UstawPrzesuniecieWGore(-1d));
//		dodajStrategie(new WcisnieciePrzyciskuWGore(), new UstawPrzesuniecieWGore(-1d));
//		dodajStrategie(new WcisnieciePrzyciskuWDol(), new UstawPrzesuniecieWDol(1d));
//		dodajStrategie(KeyEvent.VK_DOWN, new UstawPrzesuniecieWDol(1d));
//		dodajStrategie(KeyEvent.VK_RIGHT, new UstawPrzesuniecieWPrawo(1d));
//		dodajStrategie(KeyEvent.VK_LEFT, new UstawPrzesuniecieWLewo(-1d));
//		
//		// zmienic z 200 na zmienna globalna w jakiejs klasie na przyklad
		dodajStrategie(new StanPrzycisku(KeyEvent.VK_UP, false), new UstawPrzesuniecieWGore(0d));
//		dodajStrategie(new PuszczeniePrzyciskuWGore(), new UstawPrzesuniecieWGore(0d));
//		dodajStrategie(KeyEvent.VK_DOWN + 200, new UstawPrzesuniecieWDol(0d));
//		dodajStrategie(KeyEvent.VK_RIGHT + 200, new UstawPrzesuniecieWPrawo(0d));
//		dodajStrategie(KeyEvent.VK_LEFT + 200, new UstawPrzesuniecieWLewo(0d));

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
