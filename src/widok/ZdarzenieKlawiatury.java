package widok;

/**
 * Reprezentuje zdarzenia klawiatury. Posiada metodę dodającą określone zdarzenie do kolejki blokującej.
 * 
 * @author Marek Majde
 *
 */
public abstract class ZdarzenieKlawiatury
{
    /**
     * Dodaje określone zdarzenie do kolejki blokującej, zależnie od wciśniętego przycisku klawiatury.
     */
    public abstract void dodajZdarzenieDoKolejki(); 
}
