package widok;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;

public class Widok extends JFrame {

	private final int SZEROKOSC = 600;
	private final int WYSOKOSC = 600;
	private final Dimension ROZMIAR = new Dimension(WYSOKOSC, SZEROKOSC);
	
	private PoleBitwy poleBitwy;
	private SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury;
	public BlockingQueue<KeyEvent> kolejkaBlokujaca = new ArrayBlockingQueue<KeyEvent>(1);
	
	public Widok() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(ROZMIAR);
		setTitle("Statki kosmiczne");
		setResizable(false);
		setLocationRelativeTo(null);
		
		dodajSluchaczaZdarzenKlawiatury(new SluchaczZdarzenKlawiatury());
		stworzPoleBitwy();
		
		setVisible(true);
	}

	public void stworzPoleBitwy() {
		poleBitwy = new PoleBitwy();
		add(poleBitwy);
	}
	
	public PoleBitwy getPoleBitwy() {
		return poleBitwy;
	}
	
	public void dodajSluchaczaZdarzenKlawiatury(SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury) {
		this.sluchaczZdarzenKlawiatury = sluchaczZdarzenKlawiatury;
		addKeyListener(sluchaczZdarzenKlawiatury);
	}

	public SluchaczZdarzenKlawiatury getSluchaczZdarzenKlawiatury() {
		return sluchaczZdarzenKlawiatury;
	}
	
	public Dimension getRozmiar() {
		return ROZMIAR;
	}
	
}
