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
 * @author Marek Majde
 * 
 */
public class SilnikBohatera
{

    private final double SZEROKOSC_STATKU = 15.0;
    private final double DLUGOSC_STATKU = 35.0;

    private StatekBohatera statekBohatera;
    private Wspolrzedne wspolrzedneBohatera;
    private Przesuniecie przesuniecieBohatera;
    private Wymiary wymiaryBohatera;
    private List<Pocisk> pociski;
    private Makieta makieta;
    private double szerokoscMapy;
    private double wysokoscMapy;

    public SilnikBohatera(final Makieta makieta)
    {
        this.wymiaryBohatera = new Wymiary(SZEROKOSC_STATKU, DLUGOSC_STATKU);
        this.statekBohatera = new StatekBohatera(wymiaryBohatera);
        this.makieta = makieta;
        this.szerokoscMapy = makieta.getRozmiar().getWidth();
        this.wysokoscMapy = makieta.getRozmiar().getHeight();
        this.wspolrzedneBohatera = dolnySrodekMapy();
        this.przesuniecieBohatera = new Przesuniecie();
        this.pociski = new CopyOnWriteArrayList<Pocisk>();
    }

    /**
     * @return
     */
    private Wspolrzedne dolnySrodekMapy()
    {
        double srodekMapy = makieta.getRozmiar().getWidth() / 2;
        double dolMapy = makieta.getRozmiar().getHeight() - statekBohatera.getWymiary().getWysokosc()
                * 2;
        return new Wspolrzedne(srodekMapy, dolMapy);
    }

    /**
     * Dodaje kolejny pocisk do listy pocisków
     * 
     * @param wspolrzedne pocisku
     * @param szybkosc pocisku
     */
    public void dodajPocisk(final Wspolrzedne wspolrzedne, final double szybkosc)
    {
        try
        {
            pociski.add(new Pocisk(wspolrzedne, szybkosc));
        } catch (UnsupportedOperationException | IllegalArgumentException | NullPointerException
                | ClassCastException e)
        {
            throw new RuntimeException();
        }
    }

    /**
     * @param pocisk
     * 
     * @throws NullPointerException
     */
    public void usunPocisk(final Pocisk pocisk) throws NullPointerException
    {
        try
        {
            pociski.remove(pocisk);
        } catch (NullPointerException e)
        {
            e.printStackTrace();
        } catch (ClassCastException | UnsupportedOperationException e)
        {
            throw new RuntimeException();
        }
    }

    /**
     * @return
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
     * @param wspolrzednePociskow
     * @param pocisk
     */
    private void dodajWspolrzednePociskuDoListy(List<Wspolrzedne> wspolrzednePociskow,
            final Pocisk pocisk)
    {
        try
        {
            wspolrzednePociskow.add(pocisk.getWspolrzedne());
        } catch (UnsupportedOperationException | IllegalArgumentException 
                | NullPointerException | ClassCastException e)
        {
            throw new RuntimeException();
        }
    }

    /**
	 * 
	 */
    public void dzialaj()
    {
        przesunBohateraIPociski();
        usunPociskiZaMapa();
        ustawBohateraIPociskiNaMakiecie();
    }

    /**
	 * 
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
	 * 
	 */
    private void ustawBohateraIPociskiNaMakiecie()
    {
        makieta.setWspolrzedneStatkuBohatera(wspolrzedneBohatera);
        makieta.setWspolrzednePociskow(getWspolrzednePociskow());
    }

    /**
	 * 
	 */
    private void przesunBohateraIPociski()
    {
        Wspolrzedne noweWspolrzedneBohatera = statekBohatera.przesun(
                wspolrzedneBohatera, przesuniecieBohatera);
        if (czyBohaterNaMapie(noweWspolrzedneBohatera))
        {
            wspolrzedneBohatera = noweWspolrzedneBohatera;
        }
        for (Pocisk pocisk : pociski)
        {
            Wspolrzedne noweWspolrzednePocisku = pocisk.przesun(
                    pocisk.getWspolrzedne(), pocisk.getPrzesuniecie());
            pocisk.setWspolrzedne(noweWspolrzednePocisku);
        }
    }

    /**
     * @param noweWspolrzedne
     * @return
     */
    private boolean czyBohaterNaMapie(final Wspolrzedne noweWspolrzedne)
    {
        if (noweWspolrzedne.getX() > szerokoscMapy - wymiaryBohatera.getSzerokosc() * 2
                || noweWspolrzedne.getX() < 0
                || noweWspolrzedne.getY() > wysokoscMapy - wymiaryBohatera.getWysokosc() * 2
                || noweWspolrzedne.getY() < 0)
        {
            return false;
        }
        return true;
    }

    /**
     * @param pocisk
     * @return
     */
    private boolean czyPociskNaMapie(final Pocisk pocisk)
    {
        return pocisk.getWspolrzedne().getY() > 0;
    }

    /**
     * @return
     */
    public Wymiary getWymiaryStatkuBohatera()
    {
        return wymiaryBohatera;
    }

    /**
     * @return
     */
    public Wspolrzedne getWspolrzedneBohatera()
    {
        return wspolrzedneBohatera;
    }

    /**
     * @return
     */
    public Przesuniecie getPrzesuniecieBohatera()
    {
        return przesuniecieBohatera;
    }

    /**
     * @return
     */
    public StatekBohatera getStatekBohatera()
    {
        return statekBohatera;
    }

    /**
     * @return
     */
    public List<Pocisk> getPociski()
    {
        return pociski;
    }
}
