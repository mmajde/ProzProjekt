package kontroler;

import java.util.logging.Logger;

import model.Model;
import uzytkowe.Makieta;
import widok.Widok;
import wyjatki.NullBlockingQueueException;

public class Kontroler {

	private final Logger LOG = Logger.getLogger("log");
	
	private final long CZAS_CZEKANIA = 5;
	private long czasPrzedObslugaGry;
	private long czasPoObsludzeGry;
	
	private Widok widok;
	private Model model;
	
	private boolean koniecGry;
	

	public Kontroler() {
		widok = new Widok();
		model = new Model(widok.getRozmiar());
		
		czasPrzedObslugaGry = 0;
		czasPoObsludzeGry = 0;
		
		koniecGry = false;	
	}

	public void uruchom() {		
		while(true) {			
			czekaj();
			ustawCzasPrzedPracaGry();	
			pracaGry();			
			ustawCzasPoPracyGry();
			if(koniecGry) {
				return;
			}
		}
	}

	public void pracaGry() {
		try {
			model.dzialanieModelu();
		} catch (NullBlockingQueueException e) {
			LOG.info("Kolejka blokująca nie zostałą utworzona.");
		}
		koniecGry = model.sprawdzCzyKoniecGry();
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
		
		if(roznica < CZAS_CZEKANIA) {
			uspijWatek(roznica);
		}
	}

	public void uspijWatek(long roznica) {
		try {
			Thread.sleep(CZAS_CZEKANIA - roznica);
		} catch (InterruptedException e) {
			LOG.info("Wątek przerwany podczas uśpienia.");
		} catch (IllegalArgumentException e) {
			throw new RuntimeException();
		}
	}
     
}
