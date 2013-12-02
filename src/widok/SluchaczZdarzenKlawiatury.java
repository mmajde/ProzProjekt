package widok;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import uzytkowe.StanPrzycisku;

public class SluchaczZdarzenKlawiatury extends KeyAdapter  {
    
	private final int rozmiarKolejki = 3;
	private BlockingQueue<StanPrzycisku> kolejkaBlokujaca;
	
	public SluchaczZdarzenKlawiatury() {
		kolejkaBlokujaca = new ArrayBlockingQueue<StanPrzycisku>(rozmiarKolejki);
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		kolejkaBlokujaca.offer(new StanPrzycisku(keyEvent, true));
	}

	public void keyReleased(KeyEvent keyEvent) {
		kolejkaBlokujaca.offer(new StanPrzycisku(keyEvent, false));
	}
	
	public BlockingQueue<StanPrzycisku> getKolejkaBlokujaca() {
		return kolejkaBlokujaca;
	}
}