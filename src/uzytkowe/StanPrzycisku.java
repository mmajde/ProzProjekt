package uzytkowe;

import java.awt.event.KeyEvent;

/* Rejestruje zdarzenie klawisza i odpowiadającą mu akcję (wciśnięcie, puszczenie) */
public class StanPrzycisku {

	/* Określa zdarzenie wcisniecia lub puszczenia klawisza */
	private KeyEvent zdarzenieKlawisza;
	/* Określa czy zdarzenie klawisza to przyciśnięcie czy puszczenie */
	private boolean czyWcisniety;
	
	public StanPrzycisku(KeyEvent zdarzenieKlawisza, boolean czyWcisniety) {
		this.zdarzenieKlawisza = zdarzenieKlawisza;
		this.czyWcisniety = czyWcisniety;
	}

	public KeyEvent getZdarzenieKlawisza() {
		return zdarzenieKlawisza;
	}

	public void setZdarzenieKlawisza(KeyEvent zdarzenieKlawisza) {
		this.zdarzenieKlawisza = zdarzenieKlawisza;
	}

	public boolean isWcisniety() {
		return czyWcisniety;
	}

	public void setCzyWcisniety(boolean czyWcisniety) {
		this.czyWcisniety = czyWcisniety;
	}
	
	
	
	
}
