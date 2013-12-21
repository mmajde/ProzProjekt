package model;

import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

/* Klasa posiadająca metody statyczne poruszające obiektami w grze */
public class RuchBohaterem {
	
	/**
	 * @param wspolrzedne
	 * @param przesuniecie
	 * @return
	 */
	
	// ZASTANOWIC SIE CZY NIE PODAWAC TUTAJ OBIEKTY IMPLEMENTUJACEGO WSPOLRZEDNE!!
	// i na nim operowac ustawiając mu nowe wspolrzedne
	public static Wspolrzedne przesun(Wspolrzedne wspolrzedne, Przesuniecie przesuniecie) {
		
		double nowyX = wspolrzedne.getX() + przesuniecie.getPrawo() + przesuniecie.getLewo();
		double nowyY = wspolrzedne.getY() + przesuniecie.getGora() + przesuniecie.getDol();
			
		return new Wspolrzedne(nowyX, nowyY);
		
	}
}
