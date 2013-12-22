package model.strategia;


public class UstawPrzesuniecieBohateraWPrawo extends UstawPrzesuniecieBohatera {

	public UstawPrzesuniecieBohateraWPrawo(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie() {
		PrzesuniecieBohatera.setPrawo(przesuniecie);
	}
	
}
