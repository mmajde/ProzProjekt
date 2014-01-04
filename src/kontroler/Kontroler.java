package kontroler;

import java.util.concurrent.BlockingQueue;


import model.Model;
import uzytkowe.Makieta;
import uzytkowe.kolejkablokujaca.KolejkaBlokujaca;
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
		
		czasCzekania = 5;
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
		KolejkaBlokujaca.stworzKolejke();
		
		while(true) {
			
			czekaj();
			przed = System.currentTimeMillis();

			KolejnyMomentZdarzenie kolejnyMoment = new KolejnyMomentZdarzenie(this);
			try {
//				for(int i =0; i<100; i++) {
					KolejkaBlokujaca.wstawZdarzenieGry(kolejnyMoment);
//				}
			} catch (InterruptedException e) {
				// dodac wszystkie inne wyjatki
//				e.printStackTrace();
				System.out.println("Nie stworzono kolejki");
			}
			
			model.zarzadzajZdarzeniami();
			
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
