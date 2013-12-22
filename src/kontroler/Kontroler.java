package kontroler;

import java.util.concurrent.BlockingQueue;

import model.Model;
import uzytkowe.Makieta;
import widok.SluchaczZdarzenKlawiatury;
import widok.Widok;
import zdarzenia.KolejnyMomentZdarzenie;
import zdarzenia.ZdarzenieGry;

public class Kontroler {

	private Widok widok;
	private Model model;
	
	private final long czasCzekania;
	private long przed;
	private long po;
	
	private BlockingQueue<ZdarzenieGry> kolejkaBlokujaca;
	private SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury;

	
	public Kontroler() {
		widok = new Widok();
		model = new Model(widok.getRozmiar());
		
		czasCzekania = 3;
		przed = 0;
		po = 0;
		
		sluchaczZdarzenKlawiatury = widok.getSluchaczZdarzenKlawiatury();
	}

	// zastanwaic sie czy to w ogole powinno byc watkiem?
	public void uruchom() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		while(true) {
			
			czekaj();
			przed = System.currentTimeMillis();
			
			kolejkaBlokujaca = sluchaczZdarzenKlawiatury.getKolejkaBlokujaca();

			KolejnyMomentZdarzenie kolejnyMoment = new KolejnyMomentZdarzenie(this);
			try {
				kolejkaBlokujaca.put(kolejnyMoment);
			} catch (InterruptedException e) {
				// dodac wszystkie inne wyjatki
				e.printStackTrace();
			}
			
			model.zarzadzajZdarzeniami(kolejkaBlokujaca);
			
			Makieta makieta = model.getMakieta();
			widok.getPoleBitwy().rysujPoleBitwy(makieta);
			
			po = System.currentTimeMillis();
		}
	}

	private void czekaj() {
		long roznica = po - przed;
		if(roznica < 0) {
			roznica = czasCzekania;
		}
		
		if(roznica < czasCzekania) {
			try {
				Thread.sleep(czasCzekania - roznica);
//					this.wait(czasCzekania - roznica);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
     
}
