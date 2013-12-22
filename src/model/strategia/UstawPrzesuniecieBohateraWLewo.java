package model.strategia;


public class UstawPrzesuniecieBohateraWLewo extends UstawPrzesuniecieBohatera implements Strategia {
	
	public UstawPrzesuniecieBohateraWLewo(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie() {
		PrzesuniecieBohatera.setLewo(przesuniecie);
	}

}
