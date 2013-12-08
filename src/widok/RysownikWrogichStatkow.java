package widok;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import statek.StatekWroga;

public class RysownikWrogichStatkow extends JPanel {

	private final String SCIEZKA_IKONY_BOHATERA = "//home//majde//java_workspace//Statki_kosmiczne//images//bohaternext1.png";

	private List<StatekWroga> statkiWroga;
	private Image ikonaWroga;
	
	public RysownikWrogichStatkow() {
		ImageIcon ikona = new ImageIcon(SCIEZKA_IKONY_BOHATERA);
    	ikonaWroga = ikona.getImage();
    	
    	setVisible(true);
	}
	
	public void rysujWrogieStatki(List<StatekWroga> statkiWroga) {
		this.statkiWroga = statkiWroga;
		//repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		Random rand = new Random();
		int n = rand.nextInt(10);
		for(StatekWroga statekWroga : statkiWroga) {
			g2d.drawImage(ikonaWroga, (int)statekWroga.getX(), (int)statekWroga.getY(), this);
		}
		g2d.drawImage(ikonaWroga, (int)statkiWroga.get(n).getX(), (int)statkiWroga.get(n).getY(), this);
	}
}
