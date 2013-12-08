package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import statek.StatekBohatera;

import model.Model;

public class KontrolerZderzen implements ActionListener {

	public KontrolerZderzen(Model model) {
		StatekBohatera statekBohatera = model.getStatekBohatera();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
