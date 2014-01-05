package kontroler;

import model.Model;
import uzytkowe.Makieta;
import widok.Widok;

public class Kontroler {

	private Widok widok;
	private Model model;
	
	private final long czasCzekania;
	private long czasPrzedObslugaGry;
	private long czasPoObsludzeGry;

	public Kontroler() {
		widok = new Widok();
		model = new Model(widok.getRozmiar());
		
		czasCzekania = 5;
		czasPrzedObslugaGry = 0;
		czasPoObsludzeGry = 0;
		
	}

	public void uruchom() {		
		while(true) {			
			czekaj();
			ustawCzasPrzedPracaGry();	
			pracaGry();			
			ustawCzasPoPracyGry();
		}
	}

	public void pracaGry() {
		model.zarzadzajZdarzeniami();
		Makieta makieta = model.getMakieta();
		widok.getPoleBitwy().rysujPoleBitwy(makieta);
	}

	public void ustawCzasPoPracyGry() {
		czasPoObsludzeGry = System.currentTimeMillis();
	}

	public void ustawCzasPrzedPracaGry() {
		czasPrzedObslugaGry = System.currentTimeMillis();
	}

	private void czekaj() {
		long roznica = czasPoObsludzeGry - czasPrzedObslugaGry;
		if(roznica < 0) {
			throw new RuntimeException();
		}
		
		if(roznica < czasCzekania) {
			uspijWatek(roznica);
		}
	}

	public void uspijWatek(long roznica) {
		try {
			Thread.sleep(czasCzekania - roznica);
		} catch (InterruptedException e) {
			System.out.println("Wątek przerwany podczas uśpienia." + e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException();
		}
	}
     
}
