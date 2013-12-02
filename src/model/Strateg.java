package model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import model.strategia.Strategia;
import model.strategia.UstawPrzesuniecieWDol;
import model.strategia.UstawPrzesuniecieWGore;
import model.strategia.UstawPrzesuniecieWLewo;
import model.strategia.UstawPrzesuniecieWPrawo;

/* Klasa przechowująca strategie */
public class Strateg {
	
	private Map<Integer, Strategia> mapaStrategii;
	private Model model;
	
	public Strateg(Model model) {
		this.model = model;
		mapaStrategii = new HashMap<Integer, Strategia>(0);
	}
	
	public Strategia pobierzStrategie(Integer klucz) {
		return mapaStrategii.get(klucz);
	}
	
	/* Metoda definiująca wszystkie reakcje na dane kliknięcie przycisku */
	public void organizujStrategie() {
		dodajStrategie(KeyEvent.VK_UP, new UstawPrzesuniecieWGore(-1d));
		dodajStrategie(KeyEvent.VK_DOWN, new UstawPrzesuniecieWDol(1d));
		dodajStrategie(KeyEvent.VK_RIGHT, new UstawPrzesuniecieWPrawo(1d));
		dodajStrategie(KeyEvent.VK_LEFT, new UstawPrzesuniecieWLewo(-1d));
		
		// zmienic z 200 na zmienna globalna w jakiejs klasie na przyklad
		dodajStrategie(KeyEvent.VK_UP + 200 , new UstawPrzesuniecieWGore(0d));
		dodajStrategie(KeyEvent.VK_DOWN + 200, new UstawPrzesuniecieWDol(0d));
		dodajStrategie(KeyEvent.VK_RIGHT + 200, new UstawPrzesuniecieWPrawo(0d));
		dodajStrategie(KeyEvent.VK_LEFT + 200, new UstawPrzesuniecieWLewo(0d));
	}
	
	private void dodajStrategie(Integer klucz, Strategia strategia) {
		mapaStrategii.put(klucz, strategia);
	}
	
	public void dzialaj(int klucz) {
		Strategia strategia = pobierzStrategie(klucz);
		if(strategia != null) {
			strategia.dzialanie(model);
			return;
		}
		
		// zastanowic sie czy rzucac wyjatek jezeli nie ma strategii
		System.out.println("Brak danej strategii");
	}
	
}