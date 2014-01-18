package widok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import uzytkowe.Makieta;
import uzytkowe.Wspolrzedne;

/**
 * Przechowuje makietę oraz obrazki do rysowania elementów na mapie.
 * 
 * @author Marek Majde.
 * 
 */
public class PoleBitwy extends JPanel
{
    /** Ścieżka do ikony bohatera w systemie. */
    private final String SCIEZKA_IKONY_BOHATERA = "//home//majde//java_workspace//Statki_kosmiczne//images//bohaternext1.png";
    /** Ścieżka do ikony wroga w systemie. */
    private final String SCIEZKA_IKONY_WROGA = "//home//majde//java_workspace//Statki_kosmiczne//images//enemy.png";
    /** Ścieżka do ikony pocisku w systemie. */
    private final String SCIEZKA_IKONY_POCISKU = "//home//majde//java_workspace//Statki_kosmiczne//images//pocisk.png";
    /** Ścieżka do ikony tła w systemie. */
    private final String SCIEZKA_TLA = "//home//majde//java_workspace//Statki_kosmiczne//images//gwiazdy3.png";

    /** Obrazek bohatera wyświetlany w grze. */
    private final Image obrazekBohatera;
    /** Obrazek wroga wyświetlany w grze. */
    private final Image obrazekWroga;
    /** Obrazek pocisku wyświetlany w grze. */
    private final Image obrazekPocisku;
    /** Obrazek tła wyświetlany w grze. */
    private final Image tlo;
    /** Przechowuje współrzędne obiektów. */
    private Makieta makieta;

    /**
     * Konstruuje pole bitwy. Ustawia tło oraz obrazki elementów w grze. 
     */
    public PoleBitwy()
    {
        this.tlo = ustawObraz(SCIEZKA_TLA);
        this.obrazekBohatera = ustawObraz(SCIEZKA_IKONY_BOHATERA);
        this.obrazekWroga = ustawObraz(SCIEZKA_IKONY_WROGA);
        this.obrazekPocisku = ustawObraz(SCIEZKA_IKONY_POCISKU);

        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        setVisible(true);
    }

    /**
     * Zwraca obrazek na podstawie scieżki do ikony w systemie.
     * 
     * @param sciezkaIkony - ścieżka do ikony w systemie.
     * 
     * @return obrazek utworzony na podstawie ścieżki do ikony.
     */
    private Image ustawObraz(String sciezkaIkony)
    {
        ImageIcon ikona = new ImageIcon(sciezkaIkony);
        return ikona.getImage();
    }

    /**
     * Rysuje elementy w grze ze współrzędnymi z makiety.
     */
    public void rysujPoleBitwy(Makieta makieta)
    {
        this.makieta = makieta;
        repaint();
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D rysownik = (Graphics2D) g;

        if (makieta == null)
        {
            return;
        }

        rysownik.drawImage(tlo, 0, 0, this);

        if (makieta.getWspolrzednePociskow() != null)
        {
            rysujObiekty(obrazekPocisku, makieta.getWspolrzednePociskow(), rysownik);
        }

        if (makieta.getWspolrzedneStatkowWroga() != null)
        {
            rysujObiekty(obrazekWroga, makieta.getWspolrzedneStatkowWroga(), rysownik);
        }

        if (makieta.getWspolrzedneStatkuBohatera() != null)
        {
            rysownik.drawImage(obrazekBohatera, 
                    (int) makieta.getWspolrzedneStatkuBohatera().getX(),
                    (int) makieta.getWspolrzedneStatkuBohatera().getY(), this);
        }

    }

    /**
     * Rysuje obiekt na podstawie podanych parametrów.
     * 
     * @param obrazekObiektu - obrazek w grze danego obiektu.
     * @param listaWspolrzednychObiektu - współrzedne wszystkich obiektów które
     *          mają być narysowane.
     * @param rysownik - obiekt klasy Graphics2D rysujący obiekty.
     */
    private void rysujObiekty(Image obrazekObiektu, 
            List<Wspolrzedne> listaWspolrzednychObiektu, Graphics2D rysownik)
    {
        for (Wspolrzedne wspolrzedneObiektu : listaWspolrzednychObiektu)
        {
            rysownik.drawImage(obrazekObiektu, (int) wspolrzedneObiektu.getX(),
                    (int) wspolrzedneObiektu.getY(), this);
        }
    }

}
