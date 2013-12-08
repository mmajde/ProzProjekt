package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import model.Bohater;
import model.Strateg;
import uzytkowe.StanPrzycisku;
import widok.Widok;
import widok.SluchaczZdarzenKlawiatury;

/* Klasa nasluchująca timera i wywołująca metody z modelu dla zdarzeń z widoku */
public class KontrolerBohatera implements ActionListener {

	private BlockingQueue<StanPrzycisku> kolejkaBlokujaca;
	
	private Bohater bohater;
	private Widok widok;
	
	private Strateg strateg;
	
	public KontrolerBohatera(Widok widok, Bohater bohater) {
		this.widok = widok;
		this.bohater = bohater;
		kolejkaBlokujaca = przypiszKolejkeBlokujaca();
		this.strateg = new Strateg(bohater);
		strateg.organizujStrategie();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(StanPrzycisku stanPrzycisku : kolejkaBlokujaca) {
			kolejkaBlokujaca.poll();
			// pobierać te liczby z modelu
			int klucz = stanPrzycisku.getZdarzenieKlawisza().getKeyCode() + (stanPrzycisku.isWcisniety() == true ? 0 : 200);
			strateg.dzialaj(klucz);
		}
		
		SterowanieBohaterem.przesunBohatera(bohater.getStatekBohatera());
		
		try {
			// ty wyjatek jezeli nie stowrzylismy wczesniej bohatera
			// plus wyjatek jezeli nie ma pola bitwy
			widok.getPoleBitwy().ustawStatekBohatera(bohater.getStatekBohatera());
			// potem zrobic wlasna klase wyjatkow
		} catch (Exception e1) {
			System.out.println("Blad. Pole bitwy jest nullem.");
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
