package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.Timer;

import model.Model;
import model.SterujBohaterem;
import uzytkowe.Wspolrzedne;
import widok.Widok;

public class Kontroler implements ActionListener{

	private Model model;
	private Widok widok;
	
	private Timer timer;
	
	public BlockingQueue<KeyEvent> blockingQueue;
	public BlockingQueue<KeyEvent> blockingQueue2;

	
	public Kontroler() {
		widok = new Widok();
		model = new Model();
		
		// wyrzucic to stad
		blockingQueue = new ArrayBlockingQueue<KeyEvent>(2);
	}

	/* Metoda tworząca pole bitwy */
	public void stworzPoleBitwy() {
		widok.stworzPoleBitwy();
	}
	
	/* Metoda uruchamiajca timer */
	public void organizujTimer() {
		timer = new Timer(5, this);
		timer.start();
		// jakies wyjatki jak te wartosci sa nullami
		timer.addActionListener(new Lacznik(widok, model.getStrateg()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		SterujBohaterem.przesunBohatera(model.wezStatekBohatera());
		try {
			// ty wyjatek jezeli nie stowrzylismy wczesniej bohatera
			widok.rysujPoleBitwy(model.wezStatekBohatera());
			
			// potem zrobic wlasna klase wyjatkow
		} catch (Exception e1) {
			System.out.println("Blad. Pole bitwy jest nullem.");
		}
		
//		for(KeyEvent keyEvent : blockingQueue) {
//			blockingQueue.poll();
//			model.dzialaj(keyEvent.getKeyCode() + 0);
//		}
	}
     
}
