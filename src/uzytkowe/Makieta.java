package uzytkowe;

import java.awt.Dimension;
import java.util.List;

/**
 * Przechowuje współrzędne wszystkich obiektów znajdujących się na mapie.
 * Przechowuje także rozmiar mapy.
 * 
 * @author Marek Majde.
 * 
 */
public class Makieta
{
    /** Rozmiar mapy rysowanej przez widok. */
    private final Dimension rozmiar;
    /** Lista ze współrzędnymi statków wroga. */
    private List<Wspolrzedne> wspolrzedneStatkowWroga;
    /** Współrzędne statku bohatera. */
    private Wspolrzedne wspolrzedneStatkuBohatera;
    /** Lista ze współrzędnymi pocisków. */
    private List<Wspolrzedne> wspolrzednePociskow;

    /**
     * Konstruuje makietę o określonym rozmiarze. 
     * 
     * @param rozmiar - wielkość makiety.
     */
    public Makieta(Dimension rozmiar)
    {
        this.rozmiar = rozmiar;
    }

    /**
     * Zwraca współrzędne wystrzelonych pocisków.
     * 
     * @return listę ze współrzędnymi pocisków.
     */
    public List<Wspolrzedne> getWspolrzednePociskow()
    {
        return wspolrzednePociskow;
    }

    /**
     * Ustawia współrzędne wystrzelonych pocisków na makiecie.
     * 
     * @param wspolrzednePociskow - lista ze współrzędnymi pocisków.
     */
    public void setWspolrzednePociskow(List<Wspolrzedne> wspolrzednePociskow)
    {
        this.wspolrzednePociskow = wspolrzednePociskow;
    }

    /**
     * Zwraca współrzędne statku bohatera.
     * 
     * @return współrzędne statku bohatera.
     */
    public Wspolrzedne getWspolrzedneStatkuBohatera()
    {
        return wspolrzedneStatkuBohatera;
    }

    /**
     * Ustawia współrzędne statku bohatera na makiecie.
     * 
     * @param wspolrzedneStatkuBohatera - współrzędne bohatera na makiecie.
     */
    public void setWspolrzedneStatkuBohatera(Wspolrzedne wspolrzedneStatkuBohatera)
    {
        this.wspolrzedneStatkuBohatera = wspolrzedneStatkuBohatera;
    }

    /**
     * Zwraca współrzędne statków wroga.
     * 
     * @return listę ze współrzędnymi statków wroga.
     */
    public List<Wspolrzedne> getWspolrzedneStatkowWroga()
    {
        return wspolrzedneStatkowWroga;
    }

    /**
     *  Ustawia współrzędne statków wroga na makiecie.
     * 
     * @param wspolrzedneStatkowWroga - lista ze współrzędnymi statków wroga.
     */
    public void setWspolrzedneStatkowWroga(List<Wspolrzedne> wspolrzedneStatkowWroga)
    {
        this.wspolrzedneStatkowWroga = wspolrzedneStatkowWroga;
    }

    /**
     * Zwraca rozmiar makiety.
     * 
     * @return obiekt klasy Dimension przechowujący rozmiar makiety.
     */
    public Dimension getRozmiar()
    {
        return rozmiar;
    }

}
