package widok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import statek.StatekBohatera;
import statek.StatekWroga;
import uzytkowe.Wspolrzedne;

public class PoleBitwy extends JPanel {

	// 0x0000ff
	private final String SCIEZKA_IKONY_BOHATERA = "//home//majde//java_workspace//Statki_kosmiczne//images//bohaternext1.png";
	private final String SCIEZKA_IKONY_WROGA = "//home//majde//java_workspace//Statki_kosmiczne//images//wrog.png";
	
	private StatekBohatera statekBohatera;
	private List<StatekWroga> statkiWroga = new ArrayList<StatekWroga>(0);
	private Image ikonaBohatera;
	private Image ikonaWroga;
	
	/* Konstruktor inicjalizujący główne parametry sterujące polem bitwy */
	public PoleBitwy() {
		setBackground(Color.BLACK);
		
    	ustawIkoneBohatera();
    	ustawIkoneWroga();
    	
    	statekBohatera = new StatekBohatera(new Wspolrzedne(270, 530));

    	setVisible(true);
	}

	private void ustawIkoneBohatera() {
		ImageIcon ikona = new ImageIcon(SCIEZKA_IKONY_BOHATERA);
    	ikonaBohatera = ikona.getImage();
	}
	
	private void ustawIkoneWroga() {
		ImageIcon ikona = new ImageIcon(SCIEZKA_IKONY_WROGA);
		ikonaWroga = ikona.getImage();
	}
	
	public void ustawWrogieStatki(List<StatekWroga> statkiWroga) {
		this.statkiWroga = statkiWroga;
	}

	public void rysujPoleBitwy(StatekBohatera statekBohatera) {
		this.statekBohatera = statekBohatera;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(ikonaBohatera, (int)statekBohatera.getX(), (int)statekBohatera.getY(), this);
		
		//if(statkiWroga != null) {
			for(StatekWroga statekWroga : statkiWroga) {
				g2d.drawImage(ikonaWroga, (int)statekWroga.getX(), (int)statekWroga.getY(), this);
			}
		//}
	}
	
}
