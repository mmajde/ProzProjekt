package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bohater;
import model.Wrog;

public class KontrolerZderzen implements ActionListener {

	private Bohater bohater;
	private Wrog wrog;
	
	public KontrolerZderzen(Bohater bohater, Wrog wrog) {
		bohater.getGranice();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
