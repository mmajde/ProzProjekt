package pl.edu.pw.elka.majde.marek.model.obiekty.statki;

import pl.edu.pw.elka.majde.marek.model.obiekty.ObiektGry;
import pl.edu.pw.elka.majde.marek.uzytkowe.Wymiary;
import pl.edu.pw.elka.majde.marek.wyjatki.ZlyArgumentException;

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
     *
     * @throws ZlyArgumentException jeśli wymiary są nullem.
     */
    public Statek(Wymiary wymiary) throws ZlyArgumentException
    {
        if(wymiary == null)
        {
            throw new ZlyArgumentException();
        }
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
