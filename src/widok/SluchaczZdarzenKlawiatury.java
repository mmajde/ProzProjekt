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
		kolejkaBlokujaca.offer(new StanPrzycisku(keyEvent, true));
	}

	public void keyReleased(KeyEvent keyEvent) {
		try {
			kolejkaBlokujaca.put(new StanPrzycisku(keyEvent, false));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public BlockingQueue<StanPrzycisku> getKolejkaBlokujaca() {
		return kolejkaBlokujaca;
	}
}