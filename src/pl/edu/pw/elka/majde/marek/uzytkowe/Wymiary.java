package pl.edu.pw.elka.majde.marek.uzytkowe;

import pl.edu.pw.elka.majde.marek.wyjatki.ZlyArgumentException;

/**
 * Przechowuje wymiary danego obiektu w grze (szerokość oraz wysokość).
 * 
 * @author Marek Majde.
 * 
 */
public class Wymiary
{

    /** Szerokość obiektu. */
    private double szerokosc;
    /** Wysokość obiektu. */
    private double wysokosc;

    /**
     * Tworzy obiekt przechowujący wymiary danego obiektu w grze.
     * 
     * @param szerokosc - szerokosc danego obiektu.
     * @param wysokosc - wysokosc danego obiektu.
     * 
     * @throws ZlyArgumentException jeśli szerokosc lub wysokosc sa mniejsze od zera.
     */
    public Wymiary(double szerokosc, double wysokosc) throws ZlyArgumentException
    {
        if (szerokosc < 0 || wysokosc < 0)
        {
            throw new ZlyArgumentException();
        }
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
    }

    /**
     * Zwraca szerokość jako liczbe double.
     * 
     * @return szerokość obiektu.
     */
    public double getSzerokosc()
    {
        return szerokosc;
    }

    /**
     * Ustawia szerokość obiektu.
     * 
     * @param szerokosc obiektu.
     */
    public void setSzerokosc(double szerokosc)
    {
        this.szerokosc = szerokosc;
    }

    /**
     * Zwraca wysokość obiektu jako liczbe double.
     * 
     * @return wyoskość obiektu.
     */
    public double getWysokosc()
    {
        return wysokosc;
    }

    /**
     * Ustawia wysokość obiektu.
     * 
     * @param wysokosc obiektu
     */
    public void setWysokosc(double wysokosc)
    {
        this.wysokosc = wysokosc;
    }
}
