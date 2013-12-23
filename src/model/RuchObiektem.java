package model;

import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

/** Klasa posiadająca metodę statyczną poruszającą obiektami w grze */
public class RuchObiektem {
	
	/**
	 * @param wspolrzedneObiektu - aktualne wspólrzednę obiektu
	 * @param przesuniecieObiektu - określa w jaką stronę i ole ma przesunąć się dany obiekt
	 * @return wspolrzedne przesuniętego obiektu 
	 */
	public static Wspolrzedne przesunObiekt(Wspolrzedne wspolrzedneObiektu, Przesuniecie przesuniecieObiektu) {
		
		double nowyX = wspolrzedneObiektu.getX() + przesuniecieObiektu.getPrawo() + przesuniecieObiektu.getLewo();
		double nowyY = wspolrzedneObiektu.getY() + przesuniecieObiektu.getGora() + przesuniecieObiektu.getDol();
			
		return new Wspolrzedne(nowyX, nowyY);
	}
}
