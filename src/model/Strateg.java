package model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import model.strategia.GenerujPocisk;
import model.strategia.Strategia;
import model.strategia.UstawPrzesuniecieBohatera;
import model.strategia.UstawPrzesuniecieBohateraWDol;
import model.strategia.UstawPrzesuniecieBohateraWGore;
import model.strategia.UstawPrzesuniecieBohateraWLewo;
import model.strategia.UstawPrzesuniecieBohateraWPrawo;
import zdarzenia.ZdarzenieGry;
import zdarzenia.ZdarzeniePrzycisku;

public class Strateg
{

    private final double RUCH_BOHATERA = 1d;
    private final double ZATRZYMANIE_BOHATERA = 0d;
    private final Map<ZdarzenieGry, Strategia> mapaStrategii = new HashMap<ZdarzenieGry, Strategia>(0);

    /**
     * @param silnikBohatera
     */
    public Strateg(SilnikBohatera silnikBohatera)
    {
        organizujStrategie(silnikBohatera);
    }

    /**
     * Tworzy wszystkie strategie w grze do obsługi zdarzeń
     * 
     * @param silnikBohatera
     *            - wymagany aby możliwe było wykonywanie przesunięć i generowanie pocisków
     */
    public void organizujStrategie(SilnikBohatera silnikBohatera)
    {
        UstawPrzesuniecieBohatera.PrzypiszPrzesuniecie(silnikBohatera.getPrzesuniecieBohatera());

        // zrobic 10 roznych zdarzen przycisku i potem .class
        dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_UP, true),
                new UstawPrzesuniecieBohateraWGore(-RUCH_BOHATERA));
        // dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_DOWN, true),
        // UstawPrzesuniecieBohateraWDol.class);
        mapaStrategii.put(new ZdarzeniePrzycisku(this, KeyEvent.VK_DOWN, true),
                new UstawPrzesuniecieBohateraWDol());
        dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_LEFT, true),
                new UstawPrzesuniecieBohateraWLewo(-RUCH_BOHATERA));
        dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_RIGHT, true),
                new UstawPrzesuniecieBohateraWPrawo(RUCH_BOHATERA));

        // wcisniecie przycisku w gore, ale wtedy w widoku trzeba klasyfikowac jakie to wcisniecie
        dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_UP, false),
                new UstawPrzesuniecieBohateraWGore(ZATRZYMANIE_BOHATERA));
        // dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_DOWN, false), new
        // UstawPrzesuniecieBohateraWDol(ZATRZYMANIE_BOHATERA));
        dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_LEFT, false),
                new UstawPrzesuniecieBohateraWLewo(ZATRZYMANIE_BOHATERA));
        dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_RIGHT, false),
                new UstawPrzesuniecieBohateraWPrawo(ZATRZYMANIE_BOHATERA));

        dodajStrategie(new ZdarzeniePrzycisku(this, KeyEvent.VK_SPACE, false), new GenerujPocisk(
                silnikBohatera));
    }

    private void dodajStrategie(ZdarzenieGry zdarzenieGry, Strategia strategia)
            throws NullPointerException
    {
        try
        {
            mapaStrategii.put(zdarzenieGry, strategia);
        } catch (UnsupportedOperationException | ClassCastException | IllegalArgumentException e)
        {
            throw new RuntimeException();
        }
    }

    public void dzialaj(ZdarzenieGry zdarzenieGry)
    {
        if (zdarzenieGry == null)
        {
            return;
        }
        Strategia strategia = pobierzStrategie(zdarzenieGry);
        if (strategia != null)
        {
            strategia.dzialanie();
        }
    }

    private Strategia pobierzStrategie(ZdarzenieGry zdarzenieGry)
    {
        Strategia strategia;
        try
        {
            strategia = mapaStrategii.get(zdarzenieGry);
        } catch (NullPointerException | ClassCastException e)
        {
            throw new RuntimeException();
        }
        return strategia;
    }

}
