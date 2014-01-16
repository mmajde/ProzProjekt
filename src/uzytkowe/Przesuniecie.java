package uzytkowe;

/**
 * Definiuje wartości przesunięć obiektów w grze w czeterech kierunkach (góra, dół, prawo, lewo).
 * 
 * @author Marek Majde.
 * 
 */
public class Przesuniecie
{
    /** Wartość przesunięcia w górę. */
    private double gora;
    /** Wartość przesunięcia w dół. */
    private double dol;
    /** Wartość przesunięcia w prawo. */
    private double prawo;
    /** Wartość przesunięcia w lewo. */
    private double lewo;

    /**
     * Konstruuje obiekt przechowujący wartości przesunięć w czterech kierunkach 
     * i inicjalizuje je zerami.
     */
    public Przesuniecie()
    {
        this.gora = 0d;
        this.dol = 0d;
        this.prawo = 0d;
        this.lewo = 0d;
    }

    /** 
     * Konstruuje obiekt przechowujący wartości przesunięć w czterech kierunkach.
     * 
     * @param gora - wartość przesunięcia w górę
     * @param dol - wartość przesunięcia w dół.
     * @param prawo - wartość przesunięcia w prawo.
     * @param lewo - wartość przesunięcia w lewo.
     */
    public Przesuniecie(double gora, double dol, double prawo, double lewo)
    {
        this.gora = -gora;
        this.dol = dol;
        this.prawo = prawo;
        this.lewo = -lewo;
    }

    /**
     * Zwraca wartość przesunięcia w górę.
     * 
     * @return wartość przesunięcie w górę.
     */
    public double getGora()
    {
        return gora;
    }

    /**
     * Ustawia wartość przesunięcia w górę.
     * 
     * @param gora - wartość przesunięcia w górę.
     */
    public void setGora(double gora)
    {
        this.gora = gora;
    }

    /**
     * Zwraca wartość przesunięcia w dół.
     * 
     * @return wartość przesunięcie w dół.
     */
    public double getDol()
    {
        return dol;
    }

    /**
     * Ustawia wartość przesunięcia w dół.
     * 
     * @param dol - wartość przesunięcia w dół.
     */
    public void setDol(double dol)
    {
        this.dol = dol;
    }

    /**
     * Zwraca wartość przesunięcia w prawo.
     * 
     * @return wartość przesunięcie w prawo.
     */
    public double getPrawo()
    {
        return prawo;
    }

    /**
     * Ustawia wartość przesunięcia w prawo.
     * 
     * @param prawo - wartość przesunięcia prawo.
     */
    public void setPrawo(double prawo)
    {
        this.prawo = prawo;
    }

    /**
     * Zwraca wartość przesunięcia w lewo.
     * 
     * @return wartość przesunięcie w lewo.
     */
    public double getLewo()
    {
        return lewo;
    }

    /**
     * Ustawia wartość przesunięcia w lewo.
     * 
     * @param lewo - wartość przesunięcia lewo.
     */
    public void setLewo(double lewo)
    {
        this.lewo = lewo;
    }
}
