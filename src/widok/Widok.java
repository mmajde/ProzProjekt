package widok;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import statek.StatekBohatera;

public class Widok extends JFrame {

	private final Dimension ROZMIAR = new Dimension(800, 600);
	
	private PoleBitwy poleBitwy;
	private SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury;
//	private Timer timer;
	
	public BlockingQueue<KeyEvent> kolejkaBlokujaca = new ArrayBlockingQueue<KeyEvent>(1);
	public KeyEvent poprzednieZdarzenie; 
	public boolean lewoPuszczone = false;
	public boolean lewoWcisniete = false;
	
	public Widok() {
		//add(new Tlo());
		//poleBitwy = new PoleBitwy();
		//add(poleBitwy);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(ROZMIAR);
		setTitle("Statki kosmiczne");
		setResizable(false);
		setLocationRelativeTo(null);
		
		dodajSluchaczaZdarzenKlawiatury(new SluchaczZdarzenKlawiatury());
		
		setVisible(true);
		
//		timer = new Timer(500, this);
//		timer.start();
	}
	
	public void dodajPanel(JPanel panel) {
		add(panel);
	}
	
	/* Metoda tworząca pole bitwy */
	public void stworzPoleBitwy() {
		poleBitwy = new PoleBitwy();
		//timer.addActionListener(poleBitwy);
		add(poleBitwy);
	}
	
	public PoleBitwy getPoleBitwy() {
		return poleBitwy;
	}
	
	/* Metoda rysująca statek bohatera */
	/* Pomyśleć czy nie zrobić z tego DTO */
	public void rysujPoleBitwy(StatekBohatera statekBohatera) throws Exception {
		// tymczasowe ustawienie bohatera;
		if(poleBitwy == null)
			throw new Exception();
		poleBitwy.rysujPoleBitwy(statekBohatera);
	}
	
	/* Metoda dodająca słuchacza przyciskanych klawiszy */
	public void dodajSluchaczaZdarzenKlawiatury(SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury) {
		this.sluchaczZdarzenKlawiatury = sluchaczZdarzenKlawiatury;
		addKeyListener(sluchaczZdarzenKlawiatury);
	}

	/* Metoda zwracająca słuchacza przyciskanych klawiszy */
	public SluchaczZdarzenKlawiatury getSluchaczaZdarzenKlawiatury() {
		return sluchaczZdarzenKlawiatury;
	}


//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// tutaj będzie ruszanie wrogami co tyle ile odlicza timer
//		// statek pobierany z modelu
//		System.out.println("Akcja");
//		//
//		
//		repaint();
//	}
//	
//	public void paint(Graphics g) {
//		super.paint(g);
//		
//		//poleBitwy.rysujPoleBitwy(new StatekBohatera(new Wspolrzedne(270, 530)));	
//	}
	
}
