package pl.edu.pw.elka.majde.marek.uzytkowe;

/**
 * Określa miejsce w przestrzeni w którym znajduje się dany obiekt gry.
 * 
 * @author Marek Majde.
 * 
 */
public class Wspolrzedne
{
    /** Współrzędna x. */
    private double x;
    /** Współrzędna y. */
    private double y;

    /** Tworzy obiekt przechowujący współrzędne danego obiektu w grze. */
    public Wspolrzedne(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Zwraca współrzędną x na mapie danego obiektu.
     * 
     * @return współrzędna x.
     */
    public double getX()
    {
        return x;
    }

    /**
     * Ustawia współrzędna x na mapie danego obiektu.
     * 
     * @param x - współrzędna x
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * Zwraca współrzędną y na mapie danego obiektu.
     * 
     * @return współrzędna y.
     */
    public double getY()
    {
        return y;
    }

    /**
     * Ustawia współrzędna y na mapie danego obiektu.
     * 
     * @param y - współrzędna y
     */
    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * Metoda zwraca nowe współrzędne przesunięte o wektor dx,dy.
     * 
     * @param dx - przesunięcie w osi X.
     * @param dy - przesunięcie w osi Y.
     * 
     * @return nowy obiekt współrzędnych przesunięty o wektor wejściowy.
     */
    public Wspolrzedne przesun(double dx, double dy)
    {
        return new Wspolrzedne(this.x + dx, this.y + dy);
    }
}
