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
		wstawDoKolejki(keyEvent, true);
	}

	public void keyReleased(KeyEvent keyEvent) {
		wstawDoKolejki(keyEvent, false);
	}

	public void wstawDoKolejki(KeyEvent keyEvent, boolean czyWcisniety) {
		try {
			kolejkaBlokujaca.put(new ZdarzeniePrzycisku(keyEvent.getSource(), keyEvent.getKeyCode(), czyWcisniety));
		// dodac wszystkie inne kontrole bledow
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BlockingQueue<ZdarzenieGry> getKolejkaBlokujaca() {
		return kolejkaBlokujaca;
	}
}