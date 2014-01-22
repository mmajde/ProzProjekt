package pl.edu.pw.elka.majde.marek.uzytkowe;

/**
 * Definiuje wartości przesunięć.
 * 
 * @author Marek Majde.
 */
public class Przesuniecie
{
    private double dx;
    private double dy;
    
    /**
     * Konstruuje przesunięcie z wartościami w określonych kierunkach.
     * 
     * @param dx - wartość przesunięcia na osi x.
     * @param dy - wartość przesunięcia na osi y.
     */
    public Przesuniecie(final double dx, final double dy) 
    {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return wartość przesunięcia na osi x.
     */
    public double getDx()
    {
        return this.dx;
    }

    /**
     * Ustawia wartość przesunięcia na osi x.
     * 
     * @param dx - wartość przesunięcia na osi x. 
     */
    public void setDx(double dx)
    {
        this.dx = dx;
    }

    /**
     * @return wartość przesunięcia na osi y.
     */
    public double getDy()
    {
        return this.dy;
    }

    /**
     * Ustawia wartość przesunięcia na osi y.
     * 
     * @param dy - wartość przesunięcia na osi y.
     */
    public void setDy(double dy)
    {
        this.dy = dy;
    }
}
