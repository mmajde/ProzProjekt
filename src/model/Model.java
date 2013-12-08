package model;

import java.awt.event.KeyEvent;
import java.util.List;

import model.strategia.*;

import statek.StatekBohatera;
import statek.StatekWroga;
import uzytkowe.Wspolrzedne;

public class Model {

	private StatekBohatera statekBohatera;
	private List<StatekWroga> statkiWroga;
	private Strateg strateg;
	
	public Model() {
		stworzBohatera(new Wspolrzedne(270d, 530d));
		organizujStrategie();
	}
	
	public StatekBohatera getStatekBohatera() {
		return statekBohatera;
	}
	
	public Strateg getStrateg() {
		return strateg;
	}
	
	/* wyrzucic zarzadzanie strategia w inne miejsce */
	public void organizujStrategie() {
		strateg = new Strateg(this);
		strateg.organizujStrategie();
	}

	private void stworzBohatera(Wspolrzedne wspolrzedne) {
		statekBohatera = new StatekBohatera(wspolrzedne);
	}
	
}
