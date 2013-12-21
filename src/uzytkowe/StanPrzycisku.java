package uzytkowe;

import java.awt.event.KeyEvent;

import model.ObiektGry;

/* Rejestruje zdarzenie klawisza i odpowiadającą mu akcję (wciśnięcie, puszczenie) */
public class StanPrzycisku extends ObiektGry {

	/* Określa zdarzenie wcisniecia lub puszczenia klawisza */
	private int kodPrzycisku;
	/* Określa czy zdarzenie klawisza to przyciśnięcie czy puszczenie */
	private boolean wcisniety;
	
	/**
	 * @param zdarzenieKlawisza
	 * @param czyWcisniety
	 */
	public StanPrzycisku(int kodPrzycisku, boolean czyWcisniety) {
		this.kodPrzycisku = kodPrzycisku;
		this.wcisniety = czyWcisniety;
	}

	public int hashCode() {
		return kodPrzycisku;
	}
	
	public boolean equals(Object obiekt) {
        if (obiekt == null)
            return false;
        if (obiekt == this)
            return true;
        if (!(obiekt instanceof StanPrzycisku))
            return false;
        
        StanPrzycisku stanPrzycisku = (StanPrzycisku)obiekt;
        if((this.kodPrzycisku == stanPrzycisku.getKodPrzycisku() && this.isWcisniety() == stanPrzycisku.isWcisniety())) {
        	return true;
        }
        
        return false;
    }
	   
	public int getKodPrzycisku() {
	return kodPrzycisku;
}
	
//	public KeyEvent getZdarzenieKlawisza() {
//		return zdarzenieKlawisza;
//	}
//
//	public void setZdarzenieKlawisza(KeyEvent zdarzenieKlawisza) {
//		this.zdarzenieKlawisza = zdarzenieKlawisza;
//	}

	public boolean isWcisniety() {
		return wcisniety;
	}

	public void setCzyWcisniety(boolean czyWcisniety) {
		this.wcisniety = czyWcisniety;
	}
	
	
	
	
}
