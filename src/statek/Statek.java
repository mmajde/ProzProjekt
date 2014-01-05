package statek;

import model.ObiektGry;
import uzytkowe.Wymiary;

public class Statek extends ObiektGry {
	
	private Wymiary wymiary;
	
	public Statek(Wymiary wymiary) {
		this.wymiary = wymiary;
	}

	public Wymiary getWymiary() {
		return wymiary;
	}
	
}
