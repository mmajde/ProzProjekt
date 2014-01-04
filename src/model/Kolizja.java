package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import statek.StatekWroga;
import uzytkowe.PociskiIStatkiKolidujace;
import uzytkowe.Wspolrzedne;
import uzytkowe.Wymiary;

/**
 * Obsługa kolizji w grze
 */
public class Kolizja {

	
	/** Metoda zwraca Statek Wroga z którym zderzył się Statek Bohatera.
	 * 	Zwraca null w przypadku gdy nie ma kolizji.
	 */
	public static StatekWroga pobierzKolizjeWrogaZBohaterem(Map<StatekWroga, Wspolrzedne> mapaStatkow, Wymiary wymiaryStatkuWroga, 
			Wspolrzedne wspolrzedneBohatera, Wymiary wymiaryBohatera) {
		Rectangle obszarBohatera = StworzObszarZajmowany(wspolrzedneBohatera, wymiaryBohatera);
		for(Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne : mapaStatkow.entrySet()) {
			Rectangle obszarWroga = StworzObszarZajmowany(statekIWspolrzedne.getValue(), wymiaryStatkuWroga);
			if(czyBohaterUderzaWWroga(obszarBohatera, obszarWroga)) {
				return statekIWspolrzedne.getKey();
			}
		}
		return null;
	}


	public static boolean czyBohaterUderzaWWroga(Rectangle obszarBohatera, Rectangle obszarWroga) {
		return obszarWroga.intersects(obszarBohatera);
	}
	
	
	/** Sprawdza czy jest kolizja któregoś z wrogich statków z pociskiem
	 * @return Obiekt klasy PociskiIStatkiDoUsuniecia przekazujący dwie listy z elementami do usuniecia
	 */
	public static PociskiIStatkiKolidujace pobierzKolizjePociskowZWrogami(Map<StatekWroga, Wspolrzedne> mapaStatkow, Wymiary wymiaryStatkuWroga, 
			List<Pocisk> pociski) {
		List<StatekWroga> statkiWrogaDoUsuniecia = new ArrayList<StatekWroga>(0);
		List<Pocisk> pociskiDoUsuniecia = new ArrayList<Pocisk>(0);
		for(Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne : mapaStatkow.entrySet()) {
			Rectangle zajmowanyObszarWroga = StworzObszarZajmowany(statekIWspolrzedne.getValue(), wymiaryStatkuWroga);
			for(Pocisk pocisk : pociski) {
				if(czyPociskUderzaWStatek(zajmowanyObszarWroga, pocisk)) {
					statkiWrogaDoUsuniecia.add(statekIWspolrzedne.getKey());
					pociskiDoUsuniecia.add(pocisk);
				}
			}
		}
		return new PociskiIStatkiKolidujace(statkiWrogaDoUsuniecia, pociskiDoUsuniecia);
	}


	private static boolean czyPociskUderzaWStatek(Rectangle zajmowanyObszarWroga, Pocisk pocisk) {
		return zajmowanyObszarWroga.contains(pocisk.getWspolrzedne().getX(), pocisk.getWspolrzedne().getY());
	}
	
	private static Rectangle StworzObszarZajmowany(Wspolrzedne wspolrzedneObiektu, Wymiary wymiaryObiektu) {
		// magic number
		Rectangle obszar = new Rectangle((int)wspolrzedneObiektu.getX()+10, (int)wspolrzedneObiektu.getY()+10, (int)wymiaryObiektu.getSzerokosc(), (int)wymiaryObiektu.getWysokosc());
		return obszar; 
	}
}
