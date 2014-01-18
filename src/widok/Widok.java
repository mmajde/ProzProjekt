package widok;

import java.awt.Dimension;

import javax.swing.JFrame;

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
    private final Dimension ROZMIAR = new Dimension(WYSOKOSC, SZEROKOSC);
    /** Rysuje elementy w grze na mapie. */
    private final PoleBitwy poleBitwy;
    /** Nasłuchuje zdarzeń użytkownika z klawiatury. */
    private final SluchaczZdarzenKlawiatury sluchaczZdarzenKlawiatury;

    public Widok()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(ROZMIAR);
        setTitle("Statki kosmiczne");
        setResizable(false);
        setLocationRelativeTo(null);

        this.sluchaczZdarzenKlawiatury = new SluchaczZdarzenKlawiatury();
        this.poleBitwy = new PoleBitwy();

        addKeyListener(sluchaczZdarzenKlawiatury);
        add(poleBitwy);

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
