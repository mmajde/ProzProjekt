package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import statek.StatekWroga;
import uzytkowe.Wspolrzedne;
import uzytkowe.Wymiary;

/**
 * Obsługa kolizji w grze. Znajdują się tutaj wszystkie metody potrzebne do sprawdzenia czy
 * określony Obiekt Gry bierze udział w kolizji i czy powinien zostać usunięty.
 * 
 * @author Marek Majde.
 * 
 */
public class Kolizja
{
    /** Przechowuje obiekty typu Statek Wroga przeznaczone do usunięcia. */ 
    private List<StatekWroga> statkiWrogaDoUsuniecia;
    /** Przechowuje wszystkie obiekty typu Pocisk przeznaczone do usunięcia. */
    private List<Pocisk> pociskiDoUsuniecia;
    /** Wymiary statku bohatera. */
    private final Wymiary wymiaryStatkuBohatera;
    /** Wymiary statku wroga. */
    private final Wymiary wymiaryStatkuWroga;

    /**
     * Konstruuje obiekt typu Kolizja.
     */
    public Kolizja(final Wymiary wymiaryStatkuBohatera, final Wymiary wymiaryStatkuWroga)
    {
        this.statkiWrogaDoUsuniecia = new ArrayList<StatekWroga>();
        this.pociskiDoUsuniecia = new ArrayList<Pocisk>();
        this.wymiaryStatkuBohatera = wymiaryStatkuBohatera;
        this.wymiaryStatkuWroga = wymiaryStatkuWroga;
    }


    /** Sprawdza kolizje pomiędzy statkiem bohatera, statkami wroga oraz pociskami bohatera.
     * 
     * @param statkiWrogaIWspolrzedne - pary statków wroga wraz z ich współrzędnymi na mapie.
     * @param wspolrzedneBohatera - współrzędne statku bohatera.
     * @param pociski - lista pocisków w grze.
     */
    public void sprawdzKolizje(Map<StatekWroga, Wspolrzedne> statkiWrogaIWspolrzedne,
            Wspolrzedne wspolrzedneBohatera, List<Pocisk> pociski)
    {
        statkiWrogaDoUsuniecia.clear();
        pociskiDoUsuniecia.clear();
        
        sprawdzKolizjeWrogaZBohaterem(statkiWrogaIWspolrzedne, wspolrzedneBohatera);
        sprawdzKolizjePociskowZWrogami(statkiWrogaIWspolrzedne, pociski);
    }
    
    /**
     * Sprawdza czy jest kolizja bohatera z wrogim statkiem.
     * Jeżeli tak, dodaje oba obiekty do listy. 
     * 
     * @param statkiWroga - mapa przechowująca dla każdego obiektu statku jego współrzędne.
     * @param wspolrzedneBohatera - miejsce na mapie w którym znajduje się bohater.
     * 
     * @return True jeśli była kolizja. False w przeciwnym przypadku. 
     */
    private boolean sprawdzKolizjeWrogaZBohaterem(
            final Map<StatekWroga, Wspolrzedne> statkiWroga, final Wspolrzedne wspolrzedneBohatera)
    {
        Rectangle obszarBohatera = stworzObszarZajmowanyPrzezBohatera(
                wspolrzedneBohatera);
        for (Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne : statkiWroga.entrySet())
        {
            Rectangle obszarWroga = stworzObszarZajmowanyPrzezWroga(
                    statekIWspolrzedne.getValue());
            if (czyBohaterUderzaWWroga(obszarBohatera, obszarWroga))
            {
                dodajStatekWrogaDoUsuniecia(statekIWspolrzedne);
                return true;
            }
        }
        return false;
    }

    /**
     * Sprawdza czy jest kolizja któregoś z wrogich statków z pociskiem. 
     * Jeżeli tak, dodaje oba obiekty do listy. 
     * 
     * @param statkiWroga - mapa przechowująca dla każdego obiektu statku jego współrzędne.
     * @param pociski - lista przechowujaca wszystkie pociski wystrzelone przez bohatera.
     */
    private void sprawdzKolizjePociskowZWrogami(
            final Map<StatekWroga, Wspolrzedne> statkiWroga, final List<Pocisk> pociski)
    {
        for (Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne : statkiWroga.entrySet())
        {
            Rectangle zajmowanyObszarWroga = stworzObszarZajmowanyPrzezWroga(
                    statekIWspolrzedne.getValue());
            for (Pocisk pocisk : pociski)
            {
                if (czyPociskTrafilWStatek(zajmowanyObszarWroga, pocisk.getWspolrzedne()))
                {
                    dodajStatekWrogaDoUsuniecia(statekIWspolrzedne);
                    dodajPociskDoUsuniecia(pocisk);
                }
            }
        }
    }
    
    /**
     * Zwraca listę statków wroga do usunięcia.
     * 
     * @return zwraca Listę ze wszystkimi statkami wroga które biorą udział w kolizji
     * i powinny być usunięte.
     */
    public List<StatekWroga> getStatkiWrogaDoUsuniecia()
    {
        return this.statkiWrogaDoUsuniecia;
    }

    /**
     * Zwraca listę pocisków do usunięcia.
     * 
     * @return zwraca Listę ze wszystkimi pociskami które biorą udział w kolizji
     * i powinny być usunięte.
     */

    public List<Pocisk> getPociskiDoUsuniecia()
    {
        return this.pociskiDoUsuniecia;
    }

    /**
     * Pomocnicza metoda dodająca do listy pocisków nowy pocisk do usunięcia.
     * 
     * @param pocisk - obiekt klasy Pocisk który ma być dodany do lisy pocisków do usunięcia.
     */
    private void dodajPociskDoUsuniecia(final Pocisk pocisk)
    {
        try
        {
            pociskiDoUsuniecia.add(pocisk);
        } catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Dodaje do listy statków nowy statek do usunięcia.
     * 
     * @param statekIWspolrzedne - para obiektów StatekWroga wraz z jego Wspolrzędnymi.
     * Obiekt klasy StatekWroga dodawany jest do listy statków do usunięcia. 
     */
    private void dodajStatekWrogaDoUsuniecia(final Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne)
    {
        try
        {
            statkiWrogaDoUsuniecia.add(statekIWspolrzedne.getKey());
        } catch (IllegalStateException | NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Sprawdza czy statek bohatera zderza się z danym statkiem wroga.
     * 
     * @param obszarBohatera - obszar na mapie zajmowany przez statek bohatera.
     * @param obszarWroga - obszar na mapie zajmowany przez dany statek wroga.
     * 
     * @return True jeśli jest zderzenie. False w przeciwnym przypadku.
     */
    private boolean czyBohaterUderzaWWroga(final Rectangle obszarBohatera, final Rectangle obszarWroga)
    {
        return obszarWroga.intersects(obszarBohatera);
    }

    /**
     * Sprawdza czy statek wroga zderza został trafiony przez pocisk.
     * 
     * @param obszarWroga - obszar na mapie zajmowany przez dany statek wroga.
     * @param wspolrzednePocisku - miejsce na mapie w którym znajduje się dany pocisk. 
     * 
     * @return True jeśli jest trafienie. False w przeciwnym przypadku.
     */
    private boolean czyPociskTrafilWStatek(final Rectangle zajmowanyObszarWroga, 
            final Wspolrzedne wspolrzednePocisku)
    {
        return zajmowanyObszarWroga.contains(wspolrzednePocisku.getX(), wspolrzednePocisku.getY());
    }

    /**
     * Tworzy obszar zajmowany przez statek bohatera.
     * 
     * @param wspolrzedneStatkuBohatera - miejsce na mapie gdzie znajduje się statek bohatera.
     * 
     * @return Obszar zajmowany przez statek bohatera klasy Rectangle.
     */
    private Rectangle stworzObszarZajmowanyPrzezBohatera(final Wspolrzedne wspolrzedneStatkuBohatera)
    {
        Rectangle obszar = new Rectangle((int) wspolrzedneStatkuBohatera.getX() + 15,
                (int) wspolrzedneStatkuBohatera.getY() + 5, (int) wymiaryStatkuBohatera.getSzerokosc(),
                (int) wymiaryStatkuBohatera.getWysokosc());
        return obszar;
    }

    /**
     * Tworzy obszar zajmowany przez statek wroga.
     * 
     * @param wspolrzedneStatkuWroga - miejsce na mapie gdzie znajduje się statek wroga.
     * 
     * @return Obszar zajmowany przez statek wroga klasy Rectangle.
     */
    private Rectangle stworzObszarZajmowanyPrzezWroga(final Wspolrzedne wspolrzedneStatkuWroga)
    {
        Rectangle obszar = new Rectangle((int) wspolrzedneStatkuWroga.getX() + 4,
                (int) wspolrzedneStatkuWroga.getY(), (int) wymiaryStatkuWroga.getSzerokosc(),
                (int) wymiaryStatkuWroga.getWysokosc());
        return obszar;
    }
}
