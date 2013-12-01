package model;

import statek.StatekBohatera;
import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

// zrobic z tego zwykla klase z metodami i wstawic do modelu a kontorler niech sobie tworzy obiekt
/* Klasa sterująca bohaterem */
public class SterujBohaterem {

	// zastanowic się nad wyjatkami jezeli ktos poda dodatnia gore
	public static void ustawPrzesuniecieWGore(StatekBohatera statekBohatera, double gora) {
		Przesuniecie przesuniecieBohatera = statekBohatera.getPrzesuniecie();
		przesuniecieBohatera.setGora(gora);
	}
	
	public static void ustawPrzesuniecieWDol(StatekBohatera statekBohatera, double dol) {
		Przesuniecie przesuniecieBohatera = statekBohatera.getPrzesuniecie();
		przesuniecieBohatera.setDol(dol);
	}
	
	public static void ustawPrzesuniecieWPrawo(StatekBohatera statekBohatera, double prawo) {
		Przesuniecie przesuniecieBohatera = statekBohatera.getPrzesuniecie();
		przesuniecieBohatera.setPrawo(prawo);
	}
	
	public static void ustawPrzesuniecieWLewo(StatekBohatera statekBohatera, double lewo) {
		Przesuniecie przesuniecieBohatera = statekBohatera.getPrzesuniecie();
		przesuniecieBohatera.setLewo(lewo);
	}

	/* Metoda przesuwająca bohatera w zależności od jego wartości przesunięć */ 
	public static void przesunBohatera(StatekBohatera statekBohatera) {
		Wspolrzedne wspolrzedneBohatera = statekBohatera.getWspolrzedne();
		Przesuniecie przesuniecieBohatera = statekBohatera.getPrzesuniecie();
		
		Wspolrzedne noweWspolrzedne = Ruch.przesun(wspolrzedneBohatera, przesuniecieBohatera);
		statekBohatera.setWspolrzedne(noweWspolrzedne);
	}
	
}
