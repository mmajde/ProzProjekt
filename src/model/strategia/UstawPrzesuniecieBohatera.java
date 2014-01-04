package model.strategia;

import uzytkowe.Przesuniecie;

/**
 * Klasa bazowa dla strategii sterujących bohaterem.
 * Przed wykorzystaniem każdej klasy pochodnej musi być wywołana metoda PrzypiszPrzesuniecie
 */
public abstract class UstawPrzesuniecieBohatera implements Strategia{

	protected static Przesuniecie PrzesuniecieBohatera;
	protected double przesuniecie;
	
	/** Metoda wywoływana aby wszystkie klasy dziedziczące operowały na tym samym przesunięciu
	 */
	public static void PrzypiszPrzesuniecie(Przesuniecie przesuniecieBohatera) {
		PrzesuniecieBohatera = przesuniecieBohatera;
	}
	
	public static Przesuniecie getPrzesuniecieBohatera() {
		return PrzesuniecieBohatera;
	}

	public static void setPrzesuniecieBohatera(Przesuniecie przesuniecieBohatera) {
		PrzesuniecieBohatera = przesuniecieBohatera;
	}
	
}
