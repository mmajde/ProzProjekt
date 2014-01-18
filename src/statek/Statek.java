package statek;

import model.ObiektGry;
import uzytkowe.Wymiary;

/**
 * Nadrzędna klasa dla wszystkich statków grze.
 * 
 * @author Marek Majde.
 */
public class Statek extends ObiektGry
{
    /** Przechowuje wymiary danego statku. */
    private final Wymiary wymiary;

    /**
     * Konstruuje nowy statek.
     * 
     * @param wymiary - określa szerokość oraz wysokość każdego statku.
     */
    public Statek(Wymiary wymiary)
    {
        this.wymiary = wymiary;
    }

    /**
     * Zwraca wymiary statku.
     * 
     * @return wymiary statku.
     */
    public Wymiary getWymiary()
    {
        return wymiary;
    }

}
