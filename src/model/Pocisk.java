package model;

import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

/**
 * Reprezentuje pocisk wystrzelony przez statek bohatera. 
 * Pocisk posiada współrzędne oraz przesunięcie.
 * 
 * @author Marek Majde.
 */
public class Pocisk extends ObiektGry
{

    /** Współrzędne pocisku na mapie. */
    private Wspolrzedne wspolrzedne;
    /** Przesunięcie pocisku. */
    private final Przesuniecie przesuniecie;

    /**
     * Konstruuje nowy pocisk w określonym miejscu poruszający się do przodu.
     * 
     * @param wspolrzedne - miejsce pocisku na mapie.
     * @param predkosc - wartość prędkości pocisku.
     */
    public Pocisk(final Wspolrzedne wspolrzedne, final double predkosc)
    {
        this.wspolrzedne = wspolrzedne;
        this.przesuniecie = new Przesuniecie(predkosc, 0, 0, 0);
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
     * Zwraca przesunięcie pocisku.
     * 
     * @return przesunięcie pocisku klasy Przesunięcie.
     */
    public Przesuniecie getPrzesuniecie()
    {
        return przesuniecie;
    }
}
