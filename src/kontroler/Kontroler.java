package kontroler;

import java.util.concurrent.BlockingQueue;

import model.Model;
import uzytkowe.StanPrzycisku;
import widok.SluchaczZdarzenKlawiatury;
import widok.Widok;

public class Kontroler {

	private Widok widok;
	private Model model;
	
	private final long czasCzekania = 3;
	private long przed;
	private long po;
	
	private BlockingQueue<StanPrzycisku> kolejkaBlokujaca;
	private SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury;

	
	public Kontroler() {
		widok = new Widok();
		model = new Model();
		
		przed = 0;
		po = 0;
		
		sluchaczZdarzenKlawiatury = widok.getSluchaczZdarzenKlawiatury();
	}

	// zastanwaic sie czy to w ogole powinno byc watkiem?
	public void uruchom() {
		widok.getPoleBitwy().ustawStatekBohatera(model.bohater.getStatekBohatera());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(true) {
			
			czekaj();
			
			przed = System.currentTimeMillis();
			
			kolejkaBlokujaca = sluchaczZdarzenKlawiatury.getKolejkaBlokujaca();

			

			// tp sie wykonuje w kazdym obiegu petli ale powinno byc w modelu!!! anie tutaj
			model.zarzadzajElementami();
			model.zarzadzajZdarzeniami(kolejkaBlokujaca);
			
			
			// model zwraca makiete!! i robie od razu rysujPoleBitwy!!!
			widok.getPoleBitwy().ustawWrogieStatki(model.wrog.getStatkiWroga());
			widok.getPoleBitwy().ustawStatekBohatera(model.bohater.getStatekBohatera());
			// to zawsze na koncu ale przekazywac gotowe DTO, makiete!
			widok.getPoleBitwy().rysujPoleBitwy();
			
			
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
