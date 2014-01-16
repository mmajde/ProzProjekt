package uzytkowe;

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
     * @throws IllegalArgumentException szerokosc lub wysokosc jest ujemna.
     */
    // zmienic na moj wyjatek
    public Wymiary(double szerokosc, double wysokosc) throws IllegalArgumentException
    {
        if (szerokosc < 0 || wysokosc < 0)
        {
            throw new IllegalArgumentException();
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
