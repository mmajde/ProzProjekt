package widok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import uzytkowe.Makieta;
import uzytkowe.Wspolrzedne;

public class PoleBitwy extends JPanel {

	private final String SCIEZKA_IKONY_BOHATERA = "//home//majde//java_workspace//Statki_kosmiczne//images//bohaternext1.png";
	private final String SCIEZKA_IKONY_WROGA = "//home//majde//java_workspace//Statki_kosmiczne//images//wrog.png";
	private final String SCIEZKA_IKONY_POCISKU = "//home//majde//java_workspace//Statki_kosmiczne//images//missile.png";
	
	private Image obrazekBohatera;
	private Image obrazekWroga;
	private Image obrazekPocisku;
	private Makieta makieta;
	
	public PoleBitwy() {
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		
		ustawObrazkiObiektowGry();

    	setVisible(true);
	}

	public void ustawObrazkiObiektowGry() {
		obrazekBohatera = ustawIkone(SCIEZKA_IKONY_BOHATERA);
		obrazekWroga = ustawIkone(SCIEZKA_IKONY_WROGA);
		obrazekPocisku = ustawIkone(SCIEZKA_IKONY_POCISKU);
	}

	private Image ustawIkone(String sciezkaIkony) {
		ImageIcon ikona = new ImageIcon(sciezkaIkony);
		return ikona.getImage();
	}

	public void rysujPoleBitwy(Makieta makieta) {
		this.makieta = makieta;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D rysownik = (Graphics2D)g;
		
		if(makieta == null) {
			return;
		}
		
		if(makieta.getWspolrzedneStatkuBohatera() != null) {
			rysujObiekty(obrazekBohatera, opakujWspolrzedneBohateraWListe(), rysownik);
		}

		if(makieta.getWspolrzedneStatkowWroga() != null) {
			rysujObiekty(obrazekWroga, makieta.getWspolrzedneStatkowWroga(), rysownik);
		}
		if(makieta.getWspolrzednePociskow() != null) {
			rysujObiekty(obrazekPocisku, makieta.getWspolrzednePociskow(), rysownik);
		}
		
	}

	private List<Wspolrzedne> opakujWspolrzedneBohateraWListe() {
		List<Wspolrzedne> opakowanieWspolrzednychBohatera = new ArrayList<Wspolrzedne>();
		opakowanieWspolrzednychBohatera.add(makieta.getWspolrzedneStatkuBohatera());
		return opakowanieWspolrzednychBohatera;
	}
	
	private void rysujObiekty(Image obrazekObiektu, List<Wspolrzedne> listaWspolrzednychObiektu, Graphics2D rysownik) {
		for(Wspolrzedne wspolrzedneObiektu : listaWspolrzednychObiektu) {
			rysownik.drawImage(obrazekObiektu, (int)wspolrzedneObiektu.getX(), (int)wspolrzedneObiektu.getY(), this);
		}
	}
	
}
