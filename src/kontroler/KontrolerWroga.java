package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Wrog;
import statek.StatekWroga;
import widok.Widok;

public class KontrolerWroga implements ActionListener {

	private Wrog wrog;
	private int maxLiczbaWrogow;
	private Widok widok;
	
	public KontrolerWroga(Widok widok, Wrog wrog) {
		this.widok = widok;
		this.wrog = wrog;
		maxLiczbaWrogow = wrog.getMaxLiczbaWrogow();
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		wrog.czyscMape();
		int aktualnaLiczbaWrogow = wrog.getAktualnaLiczbaWrogow();
		if(aktualnaLiczbaWrogow < maxLiczbaWrogow) {
			wrog.stworzWrogow();
		}
		List<StatekWroga> statkiWroga = wrog.getStatkiWroga();
		// jakiś wyjątek jeżeli pole Bitwy jest nullem
		widok.getPoleBitwy().ustawWrogieStatki(statkiWroga);
		wrog.przesunStatkiWroga();
	}
	
	
}
