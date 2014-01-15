package model;

import java.awt.Dimension;

import uzytkowe.Makieta;
import uzytkowe.kolejkablokujaca.KolejkaBlokujaca;
import wyjatki.NullBlockingQueueException;
import zdarzenia.ZdarzenieGry;

/**
 * Główna klasa wzorca MVC.
 * 
 * @author Marek Majde.
 * 
 */
public class Model
{
    /** Zarządza grą. */
    private final SilnikGry silnikGry;
    /** Przechowuje strategie określonych zdarzeń w grze. */
    private final Strateg strateg;
    /** Przechowuje współrzędne wszystkich obiektów na mapie. */
    private final Makieta makieta;

    /**
     * Konstuktor z rozmiarem wymaganym do zainicjalizowania makiety.
     * 
     * @param rozmiar - rozmiar określąjący wielkość makiety.
     */
    public Model(final Dimension rozmiar)
    {
        this.makieta = new Makieta(rozmiar);
        SilnikWroga silnikWroga = new SilnikWroga(makieta);
        SilnikBohatera silnikBohatera = new SilnikBohatera(makieta);
        this.silnikGry = new SilnikGry(silnikWroga, silnikBohatera);
        this.strateg = new Strateg(silnikBohatera);
    }
// do zmiany 
    /**
     * Uruchamia główny silnik gry i zarządza zdarzeniami gry.
     * 
     * @throws NullBlockingQueueException - jeśli kolejka blokująca nie została wcześniej utworzona
     */
    public void dzialanieModelu() throws NullBlockingQueueException
    {
        silnikGry.dzialaj();
        while (czyIstniejeZdarzenieWKolejce())
        {
            ZdarzenieGry nastepneZdarzenieGry = KolejkaBlokujaca.wezNastepneZdarzenieGry();
            strateg.dzialaj(nastepneZdarzenieGry);
        }
    }

    // do zmiany
    /**
     * @return
     * 
     * @throws NullBlockingQueueException
     */
    private boolean czyIstniejeZdarzenieWKolejce() throws NullBlockingQueueException
    {
        return !KolejkaBlokujaca.czyPusta();
    }

    /**
     * Zwraca makietę.
     * 
     * @return zwraca makietę, która przechowuje wszystkie współrzędne obiektów w grze.
     */
    public Makieta getMakieta()
    {
        return makieta;
    }

    /**
     * Sprawdza czy gra powinna się zakończyć.
     * 
     * @return True jeśli gra powinna się zakończyć. False w przecziwnym przypadku.
     */
    public boolean sprawdzCzyKoniecGry()
    {
        return silnikGry.czyKoniec();
    }
}
