package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Bohater;
import model.Wrog;
import widok.Widok;

public class Kontroler implements ActionListener{

	private Bohater bohater;
	private Widok widok;
	private Wrog wrog;
	
	private Timer timer;
	
	//public BlockingQueue<KeyEvent> blockingQueue;

	
	public Kontroler() {
		widok = new Widok();
		bohater = new Bohater();
		wrog = new Wrog();
		
		// wyrzucic to stad
		//blockingQueue = new ArrayBlockingQueue<KeyEvent>(2);
	}

	/* Metoda tworząca pole bitwy */
	public void stworzPoleBitwy() {
		widok.stworzPoleBitwy();
	}
	
	/* Metoda uruchamiajca timer */
	public synchronized void organizujTimer() {
		timer = new Timer(5, this);
		// jakies wyjatki jak te wartosci sa nullami
//		try {
//			this.wait(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		timer.addActionListener(new KontrolerBohatera(widok, bohater));
		timer.addActionListener(new KontrolerWroga(widok, wrog));
		timer.addActionListener(new KontrolerZderzen(bohater, wrog));
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		widok.getPoleBitwy().rysujPoleBitwy();
	}
     
}
