package model;

import java.awt.Dimension;

import uzytkowe.Makieta;
import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

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
    }

    /**
     * Przesuwa bohatera na mapie.
     */
    public void przesunBohatera(Przesuniecie przesuniecieBohatera)
    {
        silnikBohatera.przesunBohatera(przesuniecieBohatera);
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
        silnikKolizji.dzialaj();
        silnikWroga.dzialaj();
        silnikBohatera.dzialaj();
    }
    
    /**
     * Dodaje pociski wystrzelone przez bohatera.
     */
    public void dodajPocisk()
    {
        double x = silnikBohatera.getWspolrzedneStatkuBohatera().getX() + 20;
        double y = silnikBohatera.getWspolrzedneStatkuBohatera().getY();

        silnikBohatera.dodajPocisk(new Wspolrzedne(x, y), 3);
    }
}
