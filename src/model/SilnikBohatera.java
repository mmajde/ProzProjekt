package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import statek.StatekBohatera;
import uzytkowe.Makieta;
import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;
import uzytkowe.Wymiary;

/**
 * Zarządza bohaterem w grze oraz wystrzelonymi przez bohatera pociskami.
 * Przesuwa bohatera i pociski oraz ustawia te obiekty na makiecie.
 * 
 * @author Marek Majde.
 * 
 */
public class SilnikBohatera
{
    /** Szerokość statku bohatera w grze. */
    private final double SZEROKOSC_STATKU = 15.0;
    /** Długość statku bohatera w grze. */
    private final double DLUGOSC_STATKU = 35.0;

    /** Statek Bohatera. */
    private final StatekBohatera statekBohatera;
    /** Współrzędne statku bohatera na mapie. */
    private Wspolrzedne wspolrzedneBohatera;
    /** Wymiary statku bohatera. */
    private final Wymiary wymiaryBohatera;
    /** Lista z pociskami wystrzelonymi przez bohatera. */
    private final List<Pocisk> pociski;
    /** Makieta przechowująca wszystkie współrzędne obiektów na mapie. */
    private final Makieta makieta;
    /** Szerokość mapy. */
    private final double szerokoscMapy;
    /** Wysokość mapy. */
    private final double wysokoscMapy;

    /**
     * Konstruuje silnik bohatera.
     * 
     * @param makieta - współrzędne obiektów umieszczane są na makiecie. 
     */
    public SilnikBohatera(final Makieta makieta)
    {
        this.wymiaryBohatera = new Wymiary(SZEROKOSC_STATKU, DLUGOSC_STATKU);
        this.statekBohatera = new StatekBohatera(wymiaryBohatera);
        this.makieta = makieta;
        this.szerokoscMapy = makieta.getRozmiar().getWidth();
        this.wysokoscMapy = makieta.getRozmiar().getHeight();
        this.wspolrzedneBohatera = dolnySrodekMapy();
        this.pociski = new CopyOnWriteArrayList<Pocisk>();
    }


    /**
     * Uruchamia silnik bohatera. Przesuwa pociski wystrzelone przez bohatera.
     * Usuwa pociski za mapą oraz ustawia bohatera i pociski na makiecie. 
     */
    public void dzialaj()
    {
        przesunPociski();
        usunPociskiZaMapa();
        ustawBohateraNaMakiecie();
        ustawPociskiNaMakiecie();
    }

    /**
     * Dodaje kolejny pocisk do listy pocisków.
     * 
     * @param wspolrzedne pocisku - miejsce na mapie gdzie umieszczony będzie kolejny pocisk.
     * @param szybkosc pocisku - szybkość z jaką porusza się pocisk.
     */
    public void dodajPocisk(final Wspolrzedne wspolrzedne, final double szybkosc)
    {
            pociski.add(new Pocisk(wspolrzedne, szybkosc));
    }

    /**
     * Usuwa dany pocisk z listy pocisków.
     * 
     * @param pocisk - pocisk który ma być usunięty z listy.
     */
    public void usunPocisk(final Pocisk pocisk)
    {
        try
        {
            pociski.remove(pocisk);
        } catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Generuje listę ze współrzędnymi wszystkich pocisków na podstawie listy pocisków.
     * 
     * @return listę ze współrzędymi pocisków na mapie.
     */
    public List<Wspolrzedne> getWspolrzednePociskow()
    {
        List<Wspolrzedne> wspolrzednePociskow = new ArrayList<Wspolrzedne>();
        for (Pocisk pocisk : pociski)
        {
            dodajWspolrzednePociskuDoListy(wspolrzednePociskow, pocisk);
        }
        return wspolrzednePociskow;
    }
    
    /**
     * Zwraca wymiary statku bohatera.
     * 
     * @return wymiary statku.
     */
    public Wymiary getWymiaryStatkuBohatera()
    {
        return wymiaryBohatera;
    }

    /**
     * Zwraca współrzędne statku bohatera.
     *  
     * @return współrzędne statku.
     */
    public Wspolrzedne getWspolrzedneStatkuBohatera()
    {
        return wspolrzedneBohatera;
    }

    /**
     * Zwraca statek bohatera.
     * 
     * @return statek bohatera.
     */
    public StatekBohatera getStatekBohatera()
    {
        return statekBohatera;
    }

    /**
     * Zwraca pociski wystrzelone przez bohatera.
     * 
     * @return listę z pociskami.
     */
    public List<Pocisk> getPociski()
    {
        return pociski;
    }
    
    /**
     * Przesuwa bohatera oraz wszystkie pociski.
     */
    public void przesunBohatera(Przesuniecie przesuniecieBohatera)
    {
        Wspolrzedne noweWspolrzedneBohatera = 
                statekBohatera.przesun(wspolrzedneBohatera, przesuniecieBohatera);
        if (czyBohaterNaMapie(noweWspolrzedneBohatera))
        {
            wspolrzedneBohatera = noweWspolrzedneBohatera;
        }
    }
    
    /**
     * Przesuwa pociski na mapie.
     */
    public void przesunPociski()
    {
        for (Pocisk pocisk : pociski)
        {
            Wspolrzedne noweWspolrzednePocisku = 
                    pocisk.przesun(pocisk.getWspolrzedne(), pocisk.getPrzesuniecie());
            pocisk.setWspolrzedne(noweWspolrzednePocisku);
        }
    }

    /**
     * Dodaje współrzędne danego pocisku do listy ze wszystkimi współrzędnymi pocisków.
     * 
     * @param wspolrzednePociskow - lista ze współrzędnymi wszystkich pocisków.
     * @param pocisk - obiekt który ma być dodany do listy.
     */
    private void dodajWspolrzednePociskuDoListy(List<Wspolrzedne> wspolrzednePociskow,
            final Pocisk pocisk)
    {
        try
        {
            wspolrzednePociskow.add(pocisk.getWspolrzedne());
        } catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Oblicza współrzędne dolnego środka mapy.
     * 
     * @return współrzędne dolnego środka mapy.
     */
    private Wspolrzedne dolnySrodekMapy()
    {
        double srodekMapy = makieta.getRozmiar().getWidth() / 2;
        double dolMapy = makieta.getRozmiar().getHeight() 
                - (statekBohatera.getWymiary().getWysokosc()* 2);
        
        return new Wspolrzedne(srodekMapy, dolMapy);
    }
    
    /**
	 * Przegląda listę pocisków i usuwa te leżące poza mapą.
	 */
    private void usunPociskiZaMapa()
    {
        for (Pocisk pocisk : pociski)
        {
            if (!czyPociskNaMapie(pocisk))
            {
                usunPocisk(pocisk);
            }
        }
    }

    /**
	 * Ustawia wszystkie pociski na makiecie.
	 */
    public void ustawPociskiNaMakiecie()
    {
        makieta.setWspolrzednePociskow(getWspolrzednePociskow());
    }
    
    /**
     * Ustawia bohatera na makiecie.
     */
    public void ustawBohateraNaMakiecie() {
        makieta.setWspolrzedneStatkuBohatera(wspolrzedneBohatera);
    }
    
    /**
     * Sprawdza czy nowe współrzędne bohatera znajdują się na mapie czy poza nią.
     * 
     * @param noweWspolrzedneBohatera - nowe współrzędne wyliczone po przesunięciu bohatera.
     * 
     * @return True jeśli nowe współrzędne bohatera znajdują się na mapie. False w przeciwnym przypadku.
     */
    private boolean czyBohaterNaMapie(final Wspolrzedne noweWspolrzedneBohatera)
    {
        if (noweWspolrzedneBohatera.getX() > szerokoscMapy - wymiaryBohatera.getSzerokosc() * 2
                || noweWspolrzedneBohatera.getY() > wysokoscMapy - wymiaryBohatera.getWysokosc() * 2
                || noweWspolrzedneBohatera.getX() < 0 || noweWspolrzedneBohatera.getY() < 0)
        {
            return false;
        }
        return true;
    }

    /**
     * Sprawdza czy pocisk znajduje się na mapie.
     * 
     * @param pocisk - obiekt którego obecność na mapie sprawdzamy.
     * 
     * @return True jeśli pocisk znajduje się na mapie. False w przeciwnym przypadku.
     */
    private boolean czyPociskNaMapie(final Pocisk pocisk)
    {
        return pocisk.getWspolrzedne().getY() > 0;
    }
}
