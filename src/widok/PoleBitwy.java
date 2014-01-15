package widok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import uzytkowe.Makieta;
import uzytkowe.Wspolrzedne;

/**
 * Przechowuje makietę oraz obrazki do rysowania elementów na mapie
 */
public class PoleBitwy extends JPanel
{

    private final Logger LOG = Logger.getLogger("log");

    private final String SCIEZKA_IKONY_BOHATERA = "//home//majde//java_workspace//Statki_kosmiczne//images//bohaternext1.png";
    private final String SCIEZKA_IKONY_WROGA = "//home//majde//java_workspace//Statki_kosmiczne//images//enemy.png";
    private final String SCIEZKA_IKONY_POCISKU = "//home//majde//java_workspace//Statki_kosmiczne//images//pocisk.png";
    private final String SCIEZKA_TLA = "//home//majde//java_workspace//Statki_kosmiczne//images//gwiazdy3.png";

    private Image obrazekBohatera;
    private Image obrazekWroga;
    private Image obrazekPocisku;
    private Image tlo;
    private Makieta makieta;

    public PoleBitwy()
    {
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        ustawObrazkiObiektowGry();
        ustawTlo();

        setVisible(true);
    }

    private void ustawTlo()
    {
        this.tlo = ustawObraz(SCIEZKA_TLA);
    }

    public void ustawObrazkiObiektowGry()
    {
        this.obrazekBohatera = ustawObraz(SCIEZKA_IKONY_BOHATERA);
        this.obrazekWroga = ustawObraz(SCIEZKA_IKONY_WROGA);
        this.obrazekPocisku = ustawObraz(SCIEZKA_IKONY_POCISKU);
    }

    private Image ustawObraz(String sciezkaIkony)
    {
        ImageIcon ikona = new ImageIcon(sciezkaIkony);
        return ikona.getImage();
    }

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
            rysujObiekty(obrazekBohatera, opakujWspolrzedneStatkuBohateraWListe(), rysownik);
        }

    }

    private List<Wspolrzedne> opakujWspolrzedneStatkuBohateraWListe()
    {
        List<Wspolrzedne> opakowanieWspolrzednychBohatera = new ArrayList<Wspolrzedne>();
        try
        {
            opakowanieWspolrzednychBohatera.add(makieta.getWspolrzedneStatkuBohatera());
        } catch (NullPointerException e)
        {
            LOG.info("Wspolrzedne statku bohatera nie moga byc nullem. " + e.getMessage());
        } catch (UnsupportedOperationException | ClassCastException | IllegalArgumentException e)
        {
            throw new RuntimeException();
        }
        return opakowanieWspolrzednychBohatera;
    }

    private void rysujObiekty(Image obrazekObiektu, List<Wspolrzedne> listaWspolrzednychObiektu,
            Graphics2D rysownik)
    {
        for (Wspolrzedne wspolrzedneObiektu : listaWspolrzednychObiektu)
        {
            rysownik.drawImage(obrazekObiektu, (int) wspolrzedneObiektu.getX(),
                    (int) wspolrzedneObiektu.getY(), this);
        }
    }

}
