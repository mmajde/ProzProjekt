package widok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import uzytkowe.Makieta;
import uzytkowe.Wspolrzedne;

public class PoleBitwy extends JPanel {

	private final String SCIEZKA_IKONY_BOHATERA = "//home//majde//java_workspace//Statki_kosmiczne//images//bohaternext1.png";
	private final String SCIEZKA_IKONY_WROGA = "//home//majde//java_workspace//Statki_kosmiczne//images//wrog.png";
	
	private Image ikonaBohatera;
	private Image ikonaWroga;
	private Makieta makieta;
	
	public PoleBitwy() {
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		
    	ustawIkoneBohatera();
    	ustawIkoneWroga();

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

	public void rysujPoleBitwy(Makieta makieta) {
		this.makieta = makieta;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		if(makieta.getWspolrzedneStatkuBohatera() != null) {
			g2d.drawImage(ikonaBohatera, (int)makieta.getWspolrzedneStatkuBohatera().getX(), (int)makieta.getWspolrzedneStatkuBohatera().getY(), this);
		}

		if(makieta.getWspolrzedneStatkowWroga() != null) {
			for(Wspolrzedne wspolrzedneStatkuWroga : makieta.getWspolrzedneStatkowWroga()) {
				g2d.drawImage(ikonaWroga, (int)wspolrzedneStatkuWroga.getX(), (int)wspolrzedneStatkuWroga.getY(), this);
			}
		}
		
	}
	
}
