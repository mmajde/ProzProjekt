package zdarzenia;

// ta klasa wystrzeliwuje zdarzenie pod tytulem KolejnyMoment (jest to takie zdarzenie
// jak KeyEvent), a klasa model przechwytuje to zdarzenie i przekazuje do strategii
public class KolejnyMomentZdarzenie extends ZdarzenieGry {
	
	// wczytywac te liczby z jakiejs klasy zeby sie nie powtarzaly, dla testu sprawdzic co bezie jak zrobie dwie takei same
	private final static int kodKlasy = 555;
	
	public KolejnyMomentZdarzenie(Object source) {
		super(source);
	}

	@Override
	public boolean equals(Object obiekt) {
		return true;
	}

	@Override
	public int hashCode() {
		return kodKlasy;
	}
}
