package widok;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import uzytkowe.StanPrzycisku;

public class SluchaczZdarzenKlawiatury extends KeyAdapter  {
    
	private final int rozmiarKolejki = 5;
	private BlockingQueue<StanPrzycisku> kolejkaBlokujaca;
	
	public SluchaczZdarzenKlawiatury() {
		kolejkaBlokujaca = new ArrayBlockingQueue<StanPrzycisku>(rozmiarKolejki);
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		kolejkaBlokujaca.offer(new StanPrzycisku(keyEvent.getKeyCode(), true));
	}

	public void keyReleased(KeyEvent keyEvent) {
		try {
			/* tutaj put żeby nie było blokowania systemu */
			kolejkaBlokujaca.put(new StanPrzycisku(keyEvent.getKeyCode(), false));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public BlockingQueue<StanPrzycisku> getKolejkaBlokujaca() {
		return kolejkaBlokujaca;
	}
}