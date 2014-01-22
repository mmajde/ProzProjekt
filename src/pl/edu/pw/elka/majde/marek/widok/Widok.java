package pl.edu.pw.elka.majde.marek.widok;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingQueue;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import pl.edu.pw.elka.majde.marek.uzytkowe.Makieta;
import pl.edu.pw.elka.majde.marek.zdarzenia.GameEvent;

/**
 * Klasa Widok wzorca MVC.
 * Inicjalizuje wyświetlany panel oraz tworzy pole bitwy.
 * 
 * @author Marek Majde.
 *
 */
public class Widok
{
    /** Wyświetla główne okno gry. */
    private PanelGry panelGry;
    
    /**
     * Konstruuje widok, dodając panel z grą oraz słuchacza klawiatury.
     * 
     * @param kolejkaBlokujaca - wstawiane są do niej zdarzenia gry.
     * @param rozmiar - rozmiar mapy w grze.
     */
    public Widok(final BlockingQueue<GameEvent> kolejkaBlokujaca, final Dimension rozmiar)
    {
        panelGry = new PanelGry(kolejkaBlokujaca, rozmiar);  
    }
    
    /**
     * Rysuje obiekty w grze na podstawie współrzędnych zawartych w makiecie.
     * 
     * @param makieta - przechowuje współrzędne obiektów w grze.
     */
    public void rysujObiektyWGrze(final Makieta makieta)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                panelGry.rysujObiekty(makieta);
            }
        });
    }
    
    /**
     * Wyświetla informacje w momencie gdy gra ma być zakończona.
     */
    public void zakonczGre()
    {
        try
        {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run()
                {
                    JOptionPane.showMessageDialog(null, "Przegrałeś! Gra zostanie zakończona.");
                }
            });
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
