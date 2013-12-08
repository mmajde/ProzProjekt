package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import model.Wrog;
import statek.StatekWroga;
import widok.PoleBitwy;

public class KontrolerWroga implements ActionListener {

	private Wrog wrog;
	private int maxLiczbaWrogow;
	private PoleBitwy poleBitwy;
	
	public KontrolerWroga(PoleBitwy poleBitwy) {
		this.poleBitwy = poleBitwy;
		wrog = new Wrog();
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
		poleBitwy.ustawWrogieStatki(statkiWroga);
		wrog.przesunStatkiWroga();
	}
	
	
}
