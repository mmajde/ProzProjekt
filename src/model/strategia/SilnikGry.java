package model.strategia;


public class SilnikGry implements Strategia {

	private SilnikWroga silnikWroga;
	private SilnikBohatera silnikBohatera;
	
	public SilnikGry(SilnikWroga silnikWroga, SilnikBohatera silnikBohatera) {
		this.silnikWroga = silnikWroga;
		this.silnikBohatera = silnikBohatera;
	}

	@Override
	public void dzialanie() {
		silnikWroga.dzialaj();
		silnikBohatera.dzialaj();
	}

}
