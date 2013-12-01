package widok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import statek.StatekBohatera;
import statek.StatekWroga;
import uzytkowe.Wspolrzedne;

public class PoleBitwy extends JPanel {

	// 0x0000ff
	private final String SCIEZKA_IKONY_BOHATERA = "//home//majde//java_workspace//Statki_kosmiczne//images//bohaternext1.png";
	
	private StatekBohatera statekBohatera;
	private StatekWroga statekWroga;
	private Image ikonaBohatera;
	
	/* Konstruktor inicjalizujący główne parametry sterujące polem bitwy */
	public PoleBitwy() {
		setBackground(Color.BLACK);
		
    	ustawIkoneBohatera();
    	
    	statekBohatera = new StatekBohatera(new Wspolrzedne(270, 530));

    	setVisible(true);
	}

	private void ustawIkoneBohatera() {
		ImageIcon ii = new ImageIcon(SCIEZKA_IKONY_BOHATERA);
    	ikonaBohatera = ii.getImage();
	}

	public void rysujPoleBitwy(StatekBohatera statekBohatera) {
		this.statekBohatera = statekBohatera;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(ikonaBohatera, (int)statekBohatera.getX(), (int)statekBohatera.getY(), this);
	}
	
}
