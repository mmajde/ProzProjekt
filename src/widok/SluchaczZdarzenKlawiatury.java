package widok;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import zdarzenia.ZdarzenieGry;
import zdarzenia.ZdarzeniePrzycisku;

public class SluchaczZdarzenKlawiatury extends KeyAdapter  {
    
	private final int rozmiarKolejki = 5;
	private BlockingQueue<ZdarzenieGry> kolejkaBlokujaca;
	
	public SluchaczZdarzenKlawiatury() {
		kolejkaBlokujaca = new ArrayBlockingQueue<ZdarzenieGry>(rozmiarKolejki);
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		kolejkaBlokujaca.offer(new ZdarzeniePrzycisku(keyEvent.getSource(), keyEvent.getKeyCode(), true));
	}

	public void keyReleased(KeyEvent keyEvent) {
		try {
			/* tutaj put żeby nie było blokowania systemu */
			kolejkaBlokujaca.put(new ZdarzeniePrzycisku(keyEvent.getSource(), keyEvent.getKeyCode(), false));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public BlockingQueue<ZdarzenieGry> getKolejkaBlokujaca() {
		return kolejkaBlokujaca;
	}
}