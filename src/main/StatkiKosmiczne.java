package main;

import kontroler.Kontroler;

public class StatkiKosmiczne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Gdzies okreslic wyjatki, jezeli programista wywola nie po kolei metody
		Kontroler kontroler = new Kontroler();
		kontroler.stworzPoleBitwy();
//		kontroler.organizujTimer();
		kontroler.run();
		
		
		// zastanowic sie czy nie odpalic kontrolera i on niech organizuje sam prace
	}

}
