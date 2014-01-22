package pl.edu.pw.elka.majde.marek.model;

import java.awt.Dimension;

import pl.edu.pw.elka.majde.marek.model.silniki.SilnikBohatera;
import pl.edu.pw.elka.majde.marek.model.silniki.SilnikKolizji;
import pl.edu.pw.elka.majde.marek.model.silniki.SilnikWroga;
import pl.edu.pw.elka.majde.marek.uzytkowe.Makieta;
import pl.edu.pw.elka.majde.marek.uzytkowe.Przesuniecie;

/**
 * Główna klasa wzorca MVC.
 * 
 * @author Marek Majde.
 * 
 */
public class Model
{
    /** Zarządza kolizjami w grze. */
    private final SilnikKolizji silnikKolizji;
    /** Zarządza bohaterem i pociskami wystrzelonymi przez bohatera. */
    private final SilnikBohatera silnikBohatera;
    /** Zarządza wrogiem. */
    private final SilnikWroga silnikWroga;
    /** Przechowuje współrzędne wszystkich obiektów na mapie. */
    private final Makieta makieta;
    /** Informuje czy gra powinna być zakończona. */
    private boolean koniecGry;
    /** Prędkość z jaką przesuwa się bohater na mapie. */
    private final double predkoscBohatera;

    /**
     * Konstuktor z rozmiarem wymaganym do zainicjalizowania makiety.
     * 
     * @param rozmiar - rozmiar określąjący wielkość makiety.
     */
    public Model(final Dimension rozmiar)
    {
        this.makieta = new Makieta(rozmiar);
        this.silnikWroga = new SilnikWroga(makieta);
        this.silnikBohatera = new SilnikBohatera(makieta);
        this.silnikKolizji = new SilnikKolizji(silnikWroga, silnikBohatera);
        this.koniecGry = false;
        this.predkoscBohatera = 5.0;
    }

    /**
     * Przesuwa bohatera w prawo na mapie.
     */
    public void przesunBohateraWPrawo()
    {
        silnikBohatera.przesunBohatera(new Przesuniecie(predkoscBohatera, 0));
    }
    
    /**
     * Przesuwa bohatera w lewo na mapie.
     */
    public void przesunBohateraWLewo()
    {
        silnikBohatera.przesunBohatera(new Przesuniecie(-predkoscBohatera, 0));
    }
    
    /**
     * Przesuwa bohatera w górę na mapie.
     */
    public void przesunBohateraWGore()
    {
        silnikBohatera.przesunBohatera(new Przesuniecie(0, -predkoscBohatera));
    }
    
    /**
     * Przesuwa bohatera w dół na mapie.
     */
    public void przesunBohateraWDol()
    {
        silnikBohatera.przesunBohatera(new Przesuniecie(0, predkoscBohatera));
    }
    
    /**
     * Zwraca makietę.
     * 
     * @return zwraca makietę, która przechowuje wszystkie współrzędne obiektów w grze.
     */
    public Makieta getMakieta()
    {
        return makieta;
    }
    
    /**
     * Ustawia bohatera na makiecie.
     */
    public void ustawBohateraNaMakiecie()
    {
        silnikBohatera.ustawBohateraNaMakiecie();
    }
    
    /**
     * Uruchamia wsyzstkie silniki (silnik wroga, silnik bohatera oraz silnik gry).
     */
    public void uruchomSilniki()
    {
        if(silnikKolizji.kontrolaKolizji()) 
        {
            koniecGry = true;
        }
        silnikWroga.dzialaj();
        silnikBohatera.dzialaj();
    }
    
    /**
     * Dodaje pociski wystrzelone przez bohatera.
     */
    public void dodajPocisk()
    {
        silnikBohatera.dodajPocisk(silnikBohatera.getWspolrzedneStatkuBohatera().przesun(20,0) , 3);
    }
    
    /**
     * Informuje czy gra powinna być zakończona.
     * 
     * @return True jeśli gra powinna być zakończona. False w przeciwnym przypadku.
     */
    public boolean czyKoniecGry() 
    {
        return koniecGry;
    }
}
