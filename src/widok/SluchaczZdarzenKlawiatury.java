package widok;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import uzytkowe.kolejkablokujaca.KolejkaBlokujaca;
import zdarzenia.ZdarzeniePrzycisku;

public class SluchaczZdarzenKlawiatury extends KeyAdapter  {
	
	public SluchaczZdarzenKlawiatury() {
		KolejkaBlokujaca.stworzKolejke();
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		wstawDoKolejki(keyEvent, true);
	}

	public void keyReleased(KeyEvent keyEvent) {
		wstawDoKolejki(keyEvent, false);
	}

	public void wstawDoKolejki(KeyEvent keyEvent, boolean czyWcisniety) {
		try {
			KolejkaBlokujaca.wstawZdarzenieGry(new ZdarzeniePrzycisku(keyEvent.getSource(), keyEvent.getKeyCode(), czyWcisniety));
		// dodac wszystkie inne kontrole bledow
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}