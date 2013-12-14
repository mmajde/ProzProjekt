package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.swing.Timer;

import model.Bohater;
import model.Wrog;
import statek.StatekWroga;
import uzytkowe.StanPrzycisku;
import widok.SluchaczZdarzenKlawiatury;
import widok.Widok;

public class Kontroler implements ActionListener, Runnable{

	private Bohater bohater;
	private Widok widok;
	private Wrog wrog;
	
	private Timer timer;
	
	private final long czasCzekania = 10;
	private long przed;
	private long po;
	//public BlockingQueue<KeyEvent> blockingQueue;

	
	public Kontroler() {
		widok = new Widok();
		bohater = new Bohater();
		wrog = new Wrog();
		
		przed = 0;
		po = 0;
		// wyrzucic to stad
		//blockingQueue = new ArrayBlockingQueue<KeyEvent>(2);
	}

	/* Metoda tworząca pole bitwy */
	public void stworzPoleBitwy() {
		widok.stworzPoleBitwy();
	}
	
	/* Metoda uruchamiajca timer */
	public synchronized void organizujTimer() {
		timer = new Timer(10, this);
		// jakies wyjatki jak te wartosci sa nullami
//		try {
//			this.wait(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		timer.addActionListener(new KontrolerBohatera(widok, bohater));
//		timer.addActionListener(new KontrolerWroga(widok, wrog));
//		timer.addActionListener(new KontrolerZderzen(bohater, wrog));
//		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("button pressed");
//		widok.getPoleBitwy().rysujPoleBitwy();
	
	}

	@Override
	public synchronized void run() {
		while(true) {
			
			long roznica = po - przed;
			if(roznica < 0) {
				roznica = czasCzekania;
			}
			
			if(po - przed < czasCzekania) {
				try {
					this.wait(czasCzekania - (po - przed));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			przed = System.currentTimeMillis();
			
			
			SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury = widok.getSluchaczaZdarzenKlawiatury();
			BlockingQueue<StanPrzycisku> kolejkaBlokujaca = sluchaczZdarzenKlawiatury.getKolejkaBlokujaca();
			

			// tp sie wykonuje w kazdym obiegu petli
			wrog.ustawStatki();
			
			// to wrzucic do DTO a do DTO przekazywac wroga, bohatera, itd.
			widok.getPoleBitwy().ustawWrogieStatki(wrog.getStatkiWroga());
			
			// to zawsze na koncu ale przekazywac gotowe DTO
			widok.getPoleBitwy().rysujPoleBitwy();
			
			
			po = System.currentTimeMillis();
		}
	}
     
}
