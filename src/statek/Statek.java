package statek;

import model.ObiektGry;
import uzytkowe.Wymiary;

public class Statek extends ObiektGry {
	
	private Wymiary wymiary;
	protected int iloscZyc;
	
	public Statek(Wymiary wymiary) {
		this.wymiary = wymiary;
	}

	public Wymiary getWymiary() {
		return wymiary;
	}
	
	public int getIloscZyc() {
		return iloscZyc;
	}
	
	public void strataZycia() {
		iloscZyc--;
	}
	
}
