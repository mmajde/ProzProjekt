package model.strategia;

import uzytkowe.Przesuniecie;

public abstract class UstawPrzesuniecieBohatera implements Strategia{

	protected static Przesuniecie PrzesuniecieBohatera;
	protected double przesuniecie;

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
