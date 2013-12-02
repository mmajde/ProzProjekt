package widok;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.Timer;

import statek.StatekBohatera;
import uzytkowe.Wspolrzedne;

public class Widok extends JFrame {

	private final Dimension ROZMIAR = new Dimension(600, 600);
	
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
	
	/* Metoda tworząca pole bitwy */
	public void stworzPoleBitwy() {
		poleBitwy = new PoleBitwy();
		//timer.addActionListener(poleBitwy);
		add(poleBitwy);
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
