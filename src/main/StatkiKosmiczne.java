package main;

import kontroler.Kontroler;

public class StatkiKosmiczne {
// javadoc: po co jest ta metoda
// sciagnac javadoca!
// illegalargumentexception zrobić własny
// nie obslugiwac bledow ktore wynikaja ze zlego uzycia metod i klas przez developerow
// tab to spacje
// @created w javadoc
// invoke later w widoku w konstruktorze
	public static void main(String[] args) {
		final Kontroler kontroler = new Kontroler();
		kontroler.uruchom();

		System.exit(0);
	}

}
