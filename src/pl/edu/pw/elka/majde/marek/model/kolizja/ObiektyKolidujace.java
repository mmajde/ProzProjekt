package pl.edu.pw.elka.majde.marek.model.kolizja;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.elka.majde.marek.model.obiekty.Pocisk;
import pl.edu.pw.elka.majde.marek.model.obiekty.statki.StatekWroga;


/**
 * Przechowuje obiekty które brały udział w kolizji.
 * Zawiera: 
 *      listę statków wroga które brały udział w zderzeniu.
 *      listę pocisków które trafiły w statek wroga.
 *      informację czy bohater brał udział w kolizji.
 * 
 * @author Marek Majde.
 *
 */
public class ObiektyKolidujace
{
    /** Statki wroga biorące udział w kolizji. */
    private List<StatekWroga> statkiWrogaKolidujace;
    /** Pociski biorące udział w kolziji. */
    private List<Pocisk> pociskiKolidujace;
    /** Przechowuje informację czy bohater brał udział w zderzeniu. */
    private boolean zderzenieBohatera;
    
    /**
     * Konstruktor bezargumentowy.
     */
    public ObiektyKolidujace()
    {
        this.statkiWrogaKolidujace = new ArrayList<StatekWroga>();
        this.pociskiKolidujace = new ArrayList<Pocisk>();
        this.zderzenieBohatera = false;
    }
    
    /**
     * Konstruktor tworzący obiekt, który przechowuje obiekty w grze biorące udział w zderzeniach.
     * 
     * @param statkiWrogaKolidujace - lista statków, które biorą udział w kolizjach.
     * @param pociskiKolidujace - lista pocisków, które biorą udział w kolizjach.
     * @param zderzenieBohatera - True jeśli bohater bierze udział w zderzeniu. False w przeciwnym przypadku
     */
    public ObiektyKolidujace(final List<StatekWroga> statkiWrogaKolidujace, 
            final List<Pocisk> pociskiKolidujace, final boolean zderzenieBohatera)
    {
        this.statkiWrogaKolidujace = statkiWrogaKolidujace;
        this.pociskiKolidujace = pociskiKolidujace;
        this.zderzenieBohatera = zderzenieBohatera;
    }
    
    /**
     * Dodaje do listy pocisków kolidujących wszystkie elementy z lisy będącej argumentem funkcji.
     * Bezpieczniejsze niż setPociskiKolidujace ponieważ nie nadpisuje ewentualnie istniejącej listy.
     * 
     * @param dodatkowePociskiKolidujace - lista dodatkowych pocisków kolidujących.
     * 
     * @throws NullPointerException jeśli kolekcja zawiera argumenty będące nullem,
     *      a lista na to nie pozwala lub jeśli kolekcja jest nullem.
     */
    public void dodajDodatkowePociskiKolidujace(List<Pocisk> dodatkowePociskiKolidujace) 
            throws NullPointerException
    {
        this.pociskiKolidujace.addAll(dodatkowePociskiKolidujace);
    }
    
    /**
     * Dodaje do listy statków kolidujących wszystkie elementy z lisy będącej argumentem funkcji.
     * Bezpieczniejsze niż setStatkiWrogaKolidujace ponieważ nie nadpisuje ewentualnie istniejącej listy.
     * 
     * @param dodatkoweStatkiWrogaKolidujace - lista dodatkowych statków wroga kolidujących.
     * 
     * @throws NullPointerException jeśli kolekcja zawiera argumenty będące nullem,
     *      a lista na to nie pozwala lub jeśli kolekcja jest nullem.
     */
    public void dodajDodatkoweStatkiWrogaKolidujace(List<StatekWroga> dodatkoweStatkiWrogaKolidujace) 
            throws NullPointerException
    {
        this.statkiWrogaKolidujace.addAll(dodatkoweStatkiWrogaKolidujace);
    }
    
    /**
     * Sprawdza czy bohater brał udział w zderzeniu.
     * 
     * @return True jeśli bohater bierze udział w zderzeniu. False w przeciwnym przypadku.
     */
    public boolean isZderzenieBohatera()
    {
        return this.zderzenieBohatera;
    }
    
    /**
     * Ustawia informację o zderzeniu bohatera.
     * 
     * @param zderzenieBohatera - true jeśli bohater bierze udział w zderzeniu. False  w przeciwnym przypadku.
     */
    public void setZderzenieBohatera(boolean zderzenieBohatera)
    {
        this.zderzenieBohatera = zderzenieBohatera;
    }
    
    /**
     * Zwraca listę z pociskami biorącymi udział w kolizji.
     * 
     * @return listę z pociskami biorącymi udział w kolizji.
     */
    public List<Pocisk> getPociskiKolidujace()
    {
        return this.pociskiKolidujace;
    }

    /**
     * Ustawia listę pocisków kolidujących.
     * 
     * @param pociskiKolidujace - lista z pociskami biorącymi udział w kolizji.
     */
    public void setPociskiKolidujace(List<Pocisk> pociskiKolidujace)
    {
        this.pociskiKolidujace = pociskiKolidujace;
    }
    
    /**
     * Zwraca listę ze statkami biorącymi udział w kolizji.
     * 
     * @return listę ze statkami biorącymi udział w kolizji.
     */
    public List<StatekWroga> getStatkiWrogaKolidujace()
    {
        return this.statkiWrogaKolidujace;
    }
    
    /**
     * Ustawia listę statków kolidujących.
     * 
     * @param statkiWrogaKolidujace - lista ze statkami biorącymi udział w kolizji.
     */
    public void setStatkiWrogaKolidujace(List<StatekWroga> statkiWrogaKolidujace)
    {
        this.statkiWrogaKolidujace = statkiWrogaKolidujace;
    }
}
