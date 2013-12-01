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
		
		blockingQueue = new ArrayBlockingQueue<KeyEvent>(2);
	}

	/* Metoda tworząca pole bitwy */
	public void stworzPoleBitwy() {
		model.stworzBohatera(new Wspolrzedne(270, 530));
		widok.stworzPoleBitwy();
		widok.dodajSluchaczaPrzyciskow(new TAdapter());
	}
	
	/* Metoda uruchamiajca timer */
	public void uruchomTimer() {
		timer = new Timer(5, this);
		timer.start();
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
		
		for(KeyEvent keyEvent : blockingQueue) {
			blockingQueue.poll();
			model.dzialaj(keyEvent.getKeyCode() + 0);
		}
	}
    
	 //wyrzucic do oddzielnej klasy w pakiecie widok!!
 private class TAdapter extends KeyAdapter {
	        
     public void keyPressed(KeyEvent keyEvent) {
//    	 System.out.println("Przycisk wcisniety.");
    	 
    	blockingQueue.offer(keyEvent);
   	  
//	   	 if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
//	   		 SterujBohaterem.ustawPrzesuniecieWGore(model.wezStatekBohatera(), -1d);
//	   	 }
	   	 
	   	 
//	   	 if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
//	   		SterujBohaterem.ustawPrzesuniecieWDol(model.wezStatekBohatera(), 1d);
//	   	 }
//	   	 
//	   	 if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
//	   		SterujBohaterem.ustawPrzesuniecieWLewo(model.wezStatekBohatera(), -1d);
//	   	 }
//	   	 
//	   	 if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
//	   		SterujBohaterem.ustawPrzesuniecieWPrawo(model.wezStatekBohatera(), 1d);
//	   	 }
     }
     
     public void keyReleased(KeyEvent keyEvent) {
//    	 System.out.println("Przycisk puszczony.");

    	 model.dzialaj(keyEvent.getKeyCode() + 200);
//			try {
//				blockingQueue.put(keyEvent);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	   	 if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
//	   		SterujBohaterem.ustawPrzesuniecieWGore(model.wezStatekBohatera(), 0d);
//	   	 }
//	   	 
//	   	 if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
//	   		SterujBohaterem.ustawPrzesuniecieWDol(model.wezStatekBohatera(), 0d);
//	   	 }
//	   	 
//	   	 if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
//	   		SterujBohaterem.ustawPrzesuniecieWLewo(model.wezStatekBohatera(), 0d);
//	   	 }
//	   	 
//	   	 if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
//	   		SterujBohaterem.ustawPrzesuniecieWPrawo(model.wezStatekBohatera(), 0d);
//	   	 }
     }
 }
 
}
