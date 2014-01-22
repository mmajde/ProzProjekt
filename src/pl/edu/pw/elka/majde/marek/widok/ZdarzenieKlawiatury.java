package pl.edu.pw.elka.majde.marek.widok;

/**
 * Reprezentuje zdarzenia klawiatury. 
 * Posiada metodę którą nadpisują klasy dziedziczącę,
 * dodając określone zdarzenie do kolejki blokującej.
 * 
 * @author Marek Majde.
 *
 */
public abstract class ZdarzenieKlawiatury
{
    /**
     * Dodaje określone zdarzenie do kolejki blokującej, 
     * zależnie od wciśniętego przycisku klawiatury.
     */
    public abstract void dodajZdarzenieDoKolejki(); 
}
