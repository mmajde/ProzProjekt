package model.strategia;


public class UstawPrzesuniecieBohateraWGore extends UstawPrzesuniecieBohatera {
	
	public UstawPrzesuniecieBohateraWGore(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}
	
	@Override
	public void dzialanie() {
		PrzesuniecieBohatera.setGora(przesuniecie);
	}
}
