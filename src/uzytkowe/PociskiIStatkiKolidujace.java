package uzytkowe;

import java.util.List;

import model.Pocisk;

import statek.StatekWroga;

/**
 * Przechowuje 2 listy: pocisków i statków wroga które biorą udział w zderzeniach
 */
public class PociskiIStatkiKolidujace {

	private List<StatekWroga> statkiWrogaKolidujace;
	private List<Pocisk> pociskiKolidujace;
	
	public PociskiIStatkiKolidujace(List<StatekWroga> statkiWrogaDoUsuniecia, List<Pocisk> pociskiDoUsuniecia) {
		this.statkiWrogaKolidujace = statkiWrogaDoUsuniecia;
		this.pociskiKolidujace = pociskiDoUsuniecia;
	}

	public List<StatekWroga> getStatkiWrogaKolidujace() {
		return statkiWrogaKolidujace;
	}

	public void setStatkiWrogaKolidujace(List<StatekWroga> statkiWrogaKolidujace) {
		this.statkiWrogaKolidujace = statkiWrogaKolidujace;
	}

	public List<Pocisk> getPociskiKolidujace() {
		return pociskiKolidujace;
	}

	public void setPociskiKolidujace(List<Pocisk> pociskiKolidujace) {
		this.pociskiKolidujace = pociskiKolidujace;
	}
	
	
}
