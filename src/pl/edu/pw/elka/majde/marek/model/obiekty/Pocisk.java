package pl.edu.pw.elka.majde.marek.model.obiekty;

import pl.edu.pw.elka.majde.marek.uzytkowe.Przesuniecie;
import pl.edu.pw.elka.majde.marek.uzytkowe.Wspolrzedne;

/**
 * Reprezentuje pocisk wystrzelony przez bohatera. 
 * Pocisk posiada współrzędne oraz przesunięcie.
 * 
 * @author Marek Majde.
 */
public class Pocisk extends ObiektGry
{

    /** Współrzędne pocisku na mapie. */
    private Wspolrzedne wspolrzedne;
    /** Przesunięcie pocisku. */
    private final Przesuniecie przesunieciePocisku;

    /**
     * Konstruuje nowy pocisk w określonym miejscu poruszający się do przodu.
     * 
     * @param wspolrzedne - miejsce pocisku na mapie.
     * @param predkosc - wartość prędkości pocisku.
     */
    public Pocisk(final Wspolrzedne wspolrzedne, final double predkosc)
    {
        this.wspolrzedne = wspolrzedne;
        this.przesunieciePocisku = new Przesuniecie(0, -predkosc);
    }

    /**
     * Zwraca współrzędne pocisku.
     * 
     * @return aktualne położenie pocisku.
     */
    public Wspolrzedne getWspolrzedne()
    {
        return wspolrzedne;
    }

    /**
     * Ustawia współrzędne pocisku.
     * 
     * @param wspolrzedne - położenie pocisku.
     */
    public void setWspolrzedne(Wspolrzedne wspolrzedne)
    {
        this.wspolrzedne = wspolrzedne;
    }

    /**
     * @return przesunięcie pocisku
     */
    public Przesuniecie getPrzesunieciePocisku()
    {
        return przesunieciePocisku;
    }
}
