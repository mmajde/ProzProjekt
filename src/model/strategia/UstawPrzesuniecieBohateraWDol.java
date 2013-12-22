package model.strategia;

public class UstawPrzesuniecieBohateraWDol extends UstawPrzesuniecieBohatera {
	
	public UstawPrzesuniecieBohateraWDol(double przesuniecie) {
		this.przesuniecie = przesuniecie;
	}

	@Override
	public void dzialanie() {
		PrzesuniecieBohatera.setDol(przesuniecie);
	}
}
