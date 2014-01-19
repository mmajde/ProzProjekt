package widok;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Klasa Widok wzorca MVC.
 * Inicjalizuje wyświetlany panel oraz tworzy pole bitwy.
 * 
 * @author Marek Majde.
 *
 */
public class Widok extends JFrame
{
    /** Szerokość mapy. */
    private final int SZEROKOSC = 600;
    /** Wysokość mapy. */
    private final int WYSOKOSC = 600;
    /** Przechowuje rozmiar mapy. */
    private final Dimension ROZMIAR;
    /** Rysuje elementy w grze na mapie. */
    private PoleBitwy poleBitwy;
    /** Nasłuchuje zdarzeń użytkownika z klawiatury. */
    private SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury;

    /**
     * Konstruuje widok, dodając panel z grą oraz słuchacza klawiatury.
     */
    public Widok()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                sluchaczZdarzenKlawiatury = new SluchaczZdarzenKlawiatury();
                poleBitwy = new PoleBitwy();
                add(poleBitwy);
                addKeyListener(sluchaczZdarzenKlawiatury);
            }
        });
        
        this.ROZMIAR = new Dimension(WYSOKOSC, SZEROKOSC);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(ROZMIAR);
        setTitle("Statki kosmiczne");
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
    }
    
    /**
     * Zwraca pole bitwy, które udostępnia metody rysujące obiekty w grze.
     * 
     * @return pole bitwy.
     */
    public PoleBitwy getPoleBitwy()
    {
        return poleBitwy;
    }

    /** Zwraca rozmiar mapy.
     * 
     * @return rozmiar mapy.
     */
    public Dimension getRozmiar()
    {
        return ROZMIAR;
    }
}
