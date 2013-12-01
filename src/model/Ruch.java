package model;

import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

/* Klasa posiadająca metody statyczne poruszające obiektami w grze */
public class Ruch {
	
	/* Metoda pobierająca wspolrzędne i przesunięcie.
	 * @returns nowe współrzędne 
	 */ 
	public static Wspolrzedne przesun(Wspolrzedne wspolrzedne, Przesuniecie przesuniecie) {
		
		double nowyX = wspolrzedne.getX() + przesuniecie.getPrawo() + przesuniecie.getLewo();
		double nowyY = wspolrzedne.getY() + przesuniecie.getGora() + przesuniecie.getDol();
			
		return new Wspolrzedne(nowyX, nowyY);
		
	}
}
