package model.strategia;

import model.SilnikBohatera;
import uzytkowe.Wspolrzedne;

public class GenerujPocisk implements Strategia
{

    private final double SZYBKOSC_POCISKU = 1.5;
    private SilnikBohatera silnikBohatera;

    public GenerujPocisk(SilnikBohatera silnikBohatera)
    {
        this.silnikBohatera = silnikBohatera;
    }

    @Override
    public void dzialanie()
    {
        double x = silnikBohatera.getWspolrzedneStatkuBohatera().getX() + 20;
        double y = silnikBohatera.getWspolrzedneStatkuBohatera().getY();

        silnikBohatera.dodajPocisk(new Wspolrzedne(x, y), SZYBKOSC_POCISKU);
    }

}
