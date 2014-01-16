package zdarzenia;

/**
 * Klasa przechowuje kod klawisza wraz z jego stanem (wciśnięty lub puszczony)
 */
public class ZdarzeniePrzycisku extends ZdarzenieGry
{

    /** Określa zdarzenie wcisniecia lub puszczenia klawisza */
    private int kodPrzycisku;
    /** Określa czy zdarzenie klawisza to przyciśnięcie czy puszczenie */
    private boolean wcisniety;

    /**
     * Konstruuje nowy Stan Przycisku
     * 
     * @param kodPrzycisku
     *            - kod klawisza pobrany klasy KeyEvent
     * @param czyWcisniety
     *            - wartość boolean definiująca wciśnięcie (true) lub puszczenie (false)
     */
    public ZdarzeniePrzycisku(Object source, int kodPrzycisku, boolean czyWcisniety)
    {
        this.kodPrzycisku = kodPrzycisku;
        this.wcisniety = czyWcisniety;
    }

    public int hashCode()
    {
        return kodPrzycisku;
    }

    public boolean equals(Object obiekt)
    {
        if (obiekt == null)
            return false;
        if (obiekt == this)
            return true;
        if (!(obiekt instanceof ZdarzeniePrzycisku))
            return false;

        ZdarzeniePrzycisku zdarzeniePrzycisku = (ZdarzeniePrzycisku) obiekt;
        if ((this.kodPrzycisku == zdarzeniePrzycisku.kodPrzycisku && this.wcisniety == zdarzeniePrzycisku.wcisniety))
        {
            return true;
        }

        return false;
    }
}
