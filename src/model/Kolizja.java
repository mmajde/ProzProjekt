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
	
	/** Zwraca Statek Wroga z którym zderzył się Statek Bohatera.
	 * 	Zwraca null w przypadku gdy nie ma kolizji.
	 */
	public static StatekWroga pobierzKolizjeWrogaZBohaterem(Map<StatekWroga, Wspolrzedne> mapaStatkow, Wymiary wymiaryStatkuWroga, 
			Wspolrzedne wspolrzedneBohatera, Wymiary wymiaryBohatera) {
		Rectangle obszarBohatera = StworzObszarZajmowanyPrzezBohatera(wspolrzedneBohatera, wymiaryBohatera);
		for(Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne : mapaStatkow.entrySet()) {
			Rectangle obszarWroga = StworzObszarZajmowanyPrzezWroga(statekIWspolrzedne.getValue(), wymiaryStatkuWroga);
			if(czyBohaterUderzaWWroga(obszarBohatera, obszarWroga)) {
				return pobierzStatekWroga(statekIWspolrzedne);
			}
		}
		return null;
	}
	
	/**
	 * Sprawdza czy jest kolizja któregoś z wrogich statków z pociskiem
	 * @param mapaStatkow wszystkie statki wraz ze wspolrzednymi
	 * @param wymiaryStatkuWroga wymiary statku wroga
	 * @param pociski lista przechowujaca wszystkie pociski
	 * @return Obiekt klasy PociskiIStatkiDoUsuniecia przekazujący dwie listy z elementami do usuniecia
	 */
	public static PociskiIStatkiKolidujace pobierzKolizjePociskowZWrogami(Map<StatekWroga, Wspolrzedne> mapaStatkow, Wymiary wymiaryStatkuWroga, 
			List<Pocisk> pociski) {
		// czy tutaj też throws new RuntimeException()?
		List<StatekWroga> statkiWrogaDoUsuniecia = new ArrayList<StatekWroga>(0);
		List<Pocisk> pociskiDoUsuniecia = new ArrayList<Pocisk>(0);
		for(Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne : mapaStatkow.entrySet()) {
			Rectangle zajmowanyObszarWroga = StworzObszarZajmowanyPrzezWroga(statekIWspolrzedne.getValue(), wymiaryStatkuWroga);
			for(Pocisk pocisk : pociski) {
				if(czyPociskUderzaWStatek(zajmowanyObszarWroga, pocisk)) {
					StatekWroga statekWrogaDoUsuniecia = pobierzStatekWroga(statekIWspolrzedne);
					dodajDoListPociskiIStatkiDoUsuniecia(statkiWrogaDoUsuniecia, pociskiDoUsuniecia, pocisk, statekWrogaDoUsuniecia);
				}
			}
		}
		return new PociskiIStatkiKolidujace(statkiWrogaDoUsuniecia, pociskiDoUsuniecia);
	}

	private static void dodajDoListPociskiIStatkiDoUsuniecia(List<StatekWroga> statkiWrogaDoUsuniecia, List<Pocisk> pociskiDoUsuniecia, 
			Pocisk pocisk, StatekWroga statekWrogaDoUsuniecia) {
		try {
			statkiWrogaDoUsuniecia.add(statekWrogaDoUsuniecia);
			pociskiDoUsuniecia.add(pocisk);
		} catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
			throw new RuntimeException();
		}
	}

	private static StatekWroga pobierzStatekWroga(Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne) {
		try {
			return statekIWspolrzedne.getKey();
		} catch (IllegalStateException e) {
			throw new RuntimeException();
		}
	}

	private static boolean czyBohaterUderzaWWroga(Rectangle obszarBohatera, Rectangle obszarWroga) {
		return obszarWroga.intersects(obszarBohatera);
	}
	
	private static boolean czyPociskUderzaWStatek(Rectangle zajmowanyObszarWroga, Pocisk pocisk) {
		return zajmowanyObszarWroga.contains(pocisk.getWspolrzedne().getX(), pocisk.getWspolrzedne().getY());
	}
	
	private static Rectangle StworzObszarZajmowanyPrzezBohatera(Wspolrzedne wspolrzedneObiektu, Wymiary wymiaryObiektu) {
		Rectangle obszar = new Rectangle((int)wspolrzedneObiektu.getX()+15, (int)wspolrzedneObiektu.getY()+5, (int)wymiaryObiektu.getSzerokosc(), (int)wymiaryObiektu.getWysokosc());
		return obszar; 
	}
	
	private static Rectangle StworzObszarZajmowanyPrzezWroga(Wspolrzedne wspolrzedneObiektu, Wymiary wymiaryObiektu) {
		Rectangle obszar = new Rectangle((int)wspolrzedneObiektu.getX()+4, (int)wspolrzedneObiektu.getY(), (int)wymiaryObiektu.getSzerokosc(), (int)wymiaryObiektu.getWysokosc());
		return obszar; 
	}
}
