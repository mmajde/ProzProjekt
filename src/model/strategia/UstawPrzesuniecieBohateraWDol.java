package model.strategia;

public class UstawPrzesuniecieBohateraWDol extends UstawPrzesuniecieBohatera {
	
	private final double przesuniecieWDol = -1d;
	
//	public UstawPrzesuniecieBohateraWDol(double przesuniecie) {
//		this.przesuniecie = przesuniecie;
//	}

	@Override
	public void dzialanie() {
		PrzesuniecieBohatera.setDol(przesuniecie);
	}
}
