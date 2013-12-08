package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import model.Strateg;
import uzytkowe.StanPrzycisku;
import widok.Widok;
import widok.SluchaczZdarzenKlawiatury;

/* Klasa nasluchująca timera i wywołująca metody z modelu dla zdarzeń z widoku */
public class KontrolerZdarzenBohatera implements ActionListener {

	private final int rozmiarKolejki = 3;
	private BlockingQueue<StanPrzycisku> kolejkaBlokujaca;
	
	private Widok widok;
	private Strateg strateg;
	
	public KontrolerZdarzenBohatera(Widok widok, Strateg strateg) {
		this.widok = widok;
		this.strateg = strateg;
		kolejkaBlokujaca = przypiszKolejkeBlokujaca();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(StanPrzycisku stanPrzycisku : kolejkaBlokujaca) {
			kolejkaBlokujaca.poll();
			// pobierać te liczby z modelu
			int klucz = stanPrzycisku.getZdarzenieKlawisza().getKeyCode() + (stanPrzycisku.isWcisniety() == true ? 0 : 200);
			strateg.dzialaj(klucz);
		}
	}

	private BlockingQueue<StanPrzycisku> przypiszKolejkeBlokujaca() {
		// zastanowić się czy tu nie będzie wyjątków
		SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury = widok.getSluchaczaZdarzenKlawiatury();
		if(sluchaczZdarzenKlawiatury != null) {
			return sluchaczZdarzenKlawiatury.getKolejkaBlokujaca();
		} else {
			return new ArrayBlockingQueue<StanPrzycisku>(0);
		}
	}
}
