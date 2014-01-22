package pl.edu.pw.elka.majde.marek.widok;

import java.awt.Dimension;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;

import pl.edu.pw.elka.majde.marek.uzytkowe.Makieta;
import pl.edu.pw.elka.majde.marek.zdarzenia.GameEvent;

/**
 * Klasa wyświetlająca główne okno gry.
 * Zawiera także słuchacza zdarzeń klawiatury oraz pole bitwy rysujące obiekty w grze.
 * 
 * @author Marek Majde.
 */
public class PanelGry extends JFrame {
    
    /** Nasłuchuje zdarzeń użytkownika z klawiatury. */
    private SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury;
    /** Rysuje elementy w grze na mapie. */
    private PoleBitwy poleBitwy;
    
    /**
     * Konstruuje panel gry. Panel gry zarządza rysowaniem obiektów na polu bitwy. 
     */
    public PanelGry(final BlockingQueue<GameEvent> kolejkaBlokujaca, final Dimension rozmiar) 
    {
        this.sluchaczZdarzenKlawiatury = new SluchaczZdarzenKlawiatury(kolejkaBlokujaca);
        this.poleBitwy = new PoleBitwy();
        
        add(poleBitwy);
        addKeyListener(sluchaczZdarzenKlawiatury); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(rozmiar);
        setTitle("Statki kosmiczne");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Rysuje obiekty w grze.
     * 
     * @param makieta - przechowuje współrzędne obiektów w grze.
     */
    public void rysujObiekty(Makieta makieta) 
    {
        poleBitwy.rysujMakiete(makieta);
    }
}