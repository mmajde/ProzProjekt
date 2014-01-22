package pl.edu.pw.elka.majde.marek.model.kolizja;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.edu.pw.elka.majde.marek.model.obiekty.Pocisk;
import pl.edu.pw.elka.majde.marek.model.obiekty.statki.StatekWroga;
import pl.edu.pw.elka.majde.marek.uzytkowe.Wspolrzedne;
import pl.edu.pw.elka.majde.marek.uzytkowe.Wymiary;



/**
 * Obsługa kolizji w grze. Znajdują się tutaj wszystkie metody potrzebne do sprawdzenia czy
 * określony Obiekt Gry bierze udział w kolizji i czy powinien zostać usunięty.
 * 
 * @author Marek Majde.
 * 
 */
public class Kolizja
{
    /** Wymiary statku bohatera. */    
    private final Wymiary wymiaryStatkuBohatera;
    /** Wymiary statku wroga. */
    private final Wymiary wymiaryStatkuWroga;
    /** Obiekty które powinny być usunięte z gry z powodu kolizji. */
    private ObiektyKolidujace obiektyKolidujace;

    /**
     * Konstruuje obiekt typu Kolizja.
     */
    public Kolizja(final Wymiary wymiaryStatkuBohatera, final Wymiary wymiaryStatkuWroga)
    {
        this.wymiaryStatkuBohatera = wymiaryStatkuBohatera;
        this.wymiaryStatkuWroga = wymiaryStatkuWroga;
    }


    /** 
     * Sprawdza kolizje pomiędzy statkiem bohatera, statkami wroga oraz pociskami bohatera.
     * Zwraca obiekt klasy ObiektyKolidujące przechowujący wszystkie informacje o obiektach biorących
     * udział w kolizjach.
     * 
     * @param statkiWrogaIWspolrzedne - pary statków wroga wraz z ich współrzędnymi na mapie.
     * @param wspolrzedneBohatera - współrzędne statku bohatera.
     * @param pociski - lista pocisków w grze.
     * 
     * @return Obiekt klasy ObiektyKolidujące przechowujący elementy biorące udział w kolizji.
     */
    public ObiektyKolidujace pobierzKolizje(Map<StatekWroga, Wspolrzedne> statkiWrogaIWspolrzedne,
            Wspolrzedne wspolrzedneBohatera, List<Pocisk> pociski)
    {
        obiektyKolidujace = new ObiektyKolidujace();
        
        sprawdzKolizjePociskowZWrogami(statkiWrogaIWspolrzedne, pociski);
        sprawdzKolizjeWrogaZBohaterem(statkiWrogaIWspolrzedne, wspolrzedneBohatera);
        
        return obiektyKolidujace;
        
    }
    
    /**
     * Sprawdza czy jest kolizja bohatera z wrogim statkiem.
     * Jeżeli tak, dodaje wrogi statek do pola obiektyKolidujace i zapisuje informację o zderzeniu tamże.
     *  
     * @param statkiWroga - mapa przechowująca dla każdego obiektu statku jego współrzędne.
     * @param wspolrzedneBohatera - miejsce na mapie w którym znajduje się bohater.
     */
    private void sprawdzKolizjeWrogaZBohaterem(final Map<StatekWroga, Wspolrzedne> statkiWroga,
            final Wspolrzedne wspolrzedneBohatera)
    {
        List<StatekWroga> statkiWrogaKolidujace = new ArrayList<StatekWroga>();
        Rectangle obszarBohatera = stworzObszarZajmowanyPrzezBohatera(
                wspolrzedneBohatera);
        for (Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne : statkiWroga.entrySet())
        {
            Rectangle obszarWroga = stworzObszarZajmowanyPrzezWroga(
                    statekIWspolrzedne.getValue());
            if (czyBohaterUderzaWWroga(obszarBohatera, obszarWroga))
            {
                dodajStatekWrogaDoUsuniecia(statkiWrogaKolidujace, statekIWspolrzedne);
                obiektyKolidujace.setZderzenieBohatera(true);
            }
        }
        obiektyKolidujace.dodajDodatkoweStatkiWrogaKolidujace(statkiWrogaKolidujace);
    }

    /**
     * Sprawdza czy jest kolizja któregoś z wrogich statków z pociskiem. 
     * Jeżeli tak, dodaje oba obiekty do pola obiektyKolidujace.
     * 
     * @param statkiWroga - mapa przechowująca dla każdego statku jego współrzędne.
     * @param pociski - lista przechowujaca wszystkie pociski wystrzelone przez bohatera.
     */
    private void sprawdzKolizjePociskowZWrogami(final Map<StatekWroga, Wspolrzedne> statkiWroga,
            final List<Pocisk> pociski)
    {
        List<Pocisk> pociskiKolidujace = new ArrayList<Pocisk>();
        List<StatekWroga> statkiWrogaKolidujace = new ArrayList<StatekWroga>();
        for (Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne : statkiWroga.entrySet())
        {
            Rectangle zajmowanyObszarWroga = stworzObszarZajmowanyPrzezWroga(
                    statekIWspolrzedne.getValue());
            for (Pocisk pocisk : pociski)
            {
                if (czyPociskTrafilWStatek(zajmowanyObszarWroga, pocisk.getWspolrzedne()))
                {
                    dodajStatekWrogaDoUsuniecia(statkiWrogaKolidujace, statekIWspolrzedne);
                    dodajPociskDoUsuniecia(pociskiKolidujace, pocisk);
                }
            }
        }
        obiektyKolidujace.dodajDodatkoweStatkiWrogaKolidujace(statkiWrogaKolidujace);
        obiektyKolidujace.setPociskiKolidujace(pociskiKolidujace);
    }

    /**
     * Pomocnicza metoda dodająca do listy pocisków nowy pocisk do usunięcia.
     * 
     * @param pociskiKolidujace - lista pocisków biorących udział w kolizji.
     * @param pocisk - obiekt klasy Pocisk który ma być dodany do lisy pocisków do usunięcia.
     */
    private void dodajPociskDoUsuniecia(final List<Pocisk> pociskiKolidujace, final Pocisk pocisk)
    {
        try
        {
            pociskiKolidujace.add(pocisk);
        } catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Dodaje do listy statków nowy obiekt do usunięcia.
     * 
     * @param statekIWspolrzedne - para obiektów StatekWroga wraz z jego Wspolrzędnymi.
     *          Obiekt klasy StatekWroga dodawany jest do listy statków wroga kolidujących. 
     * @param statkiWrogaKolidujace - statki biorące udział w kolizji.
     */
    private void dodajStatekWrogaDoUsuniecia(final List<StatekWroga> statkiWrogaKolidujace,
            final Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne)
    {
        try
        {
            statkiWrogaKolidujace.add(statekIWspolrzedne.getKey());
        } catch (IllegalStateException | NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Sprawdza czy obiekt bohatera zderza się z danym statkiem wroga.
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
        Rectangle obszar = new Rectangle((int) wspolrzedneStatkuBohatera.getX() + 10,
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
        Rectangle obszar = new Rectangle((int) wspolrzedneStatkuWroga.getX() + 10,
                (int) wspolrzedneStatkuWroga.getY(), (int) wymiaryStatkuWroga.getSzerokosc(),
                (int) wymiaryStatkuWroga.getWysokosc());
        return obszar;
    }
}
