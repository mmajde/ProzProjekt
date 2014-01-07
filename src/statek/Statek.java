package statek;

import uzytkowe.Wymiary;

/**
 * Nadrzędna klasa dla wszystkich statków grze
 */
public class Statek {
	
	private Wymiary wymiary;
	
	public Statek(Wymiary wymiary) {
		this.wymiary = wymiary;
	}

	public Wymiary getWymiary() {
		return wymiary;
	}
	
}
