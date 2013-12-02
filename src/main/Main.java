package main;

import kontroler.Kontroler;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Gdzies okreslic wyjatki, jezeli programista wywola nie po kolei metody
		Kontroler kontroler = new Kontroler();
		kontroler.stworzPoleBitwy();
		kontroler.organizujTimer();
		
		
		// zastanowic sie czy nie odpalic kontrolera i on niech organizuje sam prace
	}

}
