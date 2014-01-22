package pl.edu.pw.elka.majde.marek.model.silniki;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import pl.edu.pw.elka.majde.marek.model.obiekty.statki.StatekWroga;
import pl.edu.pw.elka.majde.marek.uzytkowe.Makieta;
import pl.edu.pw.elka.majde.marek.uzytkowe.Wspolrzedne;
import pl.edu.pw.elka.majde.marek.uzytkowe.Wymiary;

/**
 * Tworzy nowe statki wroga oraz kontroluje ich ustawienie na mapie.
 * 
 * @author Marek Majde.
 * 
 */
public class SilnikWroga
{
    /** Określa maksymalną dopuszczalną liczbę statków na mapie. */
    private final int MAX_LICZBA_STATKOW = 30;
    /** Wartość o którą przesuwa się każdy statek wroga. */
    private final double DLUGOSC_RUCHU = 1.0;
    /** Szerokość statku na mapie. */
    private final double SZEROKOSC_STATKU = 30.0;
    /** Wysokość statku na mapie. */
    private final double WYSOKOSC_STATKU = 20.0;

    /** Liczba statków na mapie w danym momencie. */
    private int aktualnaLiczbaStatkow;
    /** Generator współrzędnych dla rozmieszczania nowych statków. */
    private final Random generatorWspolrzednych;
    /** Mapa wszystkich statków wraz z ich współrzędnymi. */
    private final Map<StatekWroga, Wspolrzedne> statkiIWspolrzedne;
    /** Wymiary statku wroga. */
    private final Wymiary wymiaryStatku;
    /** Makieta przechowująca wszystkie współrzędne obiektów na mapie. */
    private final Makieta makieta;
    /** Szerokość mapy. */
    private final double szerokoscMapy;
    /** Wysokość mapy. */
    private final double wysokoscMapy;

    /**
     * Konstruktor.
     * 
     * @param makieta - przechowuje wspolrzedne statkow.
     */
    public SilnikWroga(final Makieta makieta)
    {
        this.makieta = makieta;
        this.aktualnaLiczbaStatkow = 0;
        this.statkiIWspolrzedne = new ConcurrentHashMap<StatekWroga, Wspolrzedne>(0);
        this.wymiaryStatku = new Wymiary(SZEROKOSC_STATKU, WYSOKOSC_STATKU);
        this.szerokoscMapy = makieta.getRozmiar().getWidth();
        this.wysokoscMapy = makieta.getRozmiar().getHeight();
        this.generatorWspolrzednych = new Random();
    }

    /**
     * Wowyłuje metody kontrolujace ustawieniem statkow wroga na mapie i na makiecie.
     */
    public void dzialaj()
    {
        stworzStatki();
        przesunStatki();
        usunStatkiZaMapa();
        ustawStatkiNaMakiecie();
    }

    /**
     * Usuwa statek wroga z listy statków.
     * 
     * @param statekWroga - obiekt który ma być usunięty.
     */
    public void usunStatek(final StatekWroga statekWroga)
    {
        try
        {
            statkiIWspolrzedne.remove(statekWroga);
            aktualnaLiczbaStatkow--;
        } catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Zwraca mapę statków wraz z ich współrzędnymi.
     * 
     * @return statki wroga zmapowane ze swoimi współrzędnymi.
     */
    public Map<StatekWroga, Wspolrzedne> getStatkiOrazIchWspolrzedne()
    {
        return statkiIWspolrzedne;
    }

    /**
     * Zwraca wymiary statku wroga.
     * 
     * @return wymiary statku wroga.
     */
    public Wymiary getWymiaryStatkuWroga()
    {
        return wymiaryStatku;
    }
    
    /**
     * Usuwa statki wroga które znajdują się za mapą.
     */
    private void usunStatkiZaMapa()
    {
        for (Map.Entry<StatekWroga, Wspolrzedne> wspolrzedneStatku : statkiIWspolrzedne.entrySet())
        {
            if (czyStatekZaMapa(wspolrzedneStatku.getValue()))
            {
                try
                {
                    usunStatek(wspolrzedneStatku.getKey());
                } catch (IllegalStateException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Określa czy statek o podanych współrzędnych znajduje się na mapie czy poza nią.
     * 
     * @param wspolrzedneStatku - współrzędne statku którego obecność na mapie sprawdzamy.
     * 
     * @return True jeśli statek znajduje się na mapie. False w przeciwnym przypadku.
     */
    private boolean czyStatekZaMapa(final Wspolrzedne wspolrzedneStatku)
    {
        return wspolrzedneStatku.getY() > makieta.getRozmiar().getHeight();
    }

    /**
     * Metoda tworzy statki jeśli ich aktualna ilość jest mniejsza niż maksymalna możliwa ilość statków.
     */
    private void stworzStatki()
    {
        while (aktualnaLiczbaStatkow < MAX_LICZBA_STATKOW)
        {
            stworzStatekWroga();
        }
    }

    /**
     * Generuje współrzędne dla nowego statku.
     * 
     * @return Wspołrzędne na mapie dla nowego statku.
     */
    private Wspolrzedne generujWspolrzedne()
    {
        double x = generatorWspolrzednych.nextDouble() * szerokoscMapy;
        double y = generatorWspolrzednych.nextDouble() * wysokoscMapy + wysokoscMapy / 2;
        x = x < 0 ? -x : x;
        y = y < 0 ? y : -y;
        
        return new Wspolrzedne(x, y);
    }

    /**
     * Tworzy nowy statek wroga na mapie.
     * 
     * @param wspolrzedneStatkuWroga - współrzędne nowo tworzonego statku wroga.
     */
    private void stworzStatekWroga()
    {
        try
        {
            statkiIWspolrzedne.put(new StatekWroga(wymiaryStatku), generujWspolrzedne());
            aktualnaLiczbaStatkow++;
        } catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Przesuwa wszystkie statki wroga znajdujące się na mapie.
     */
    private void przesunStatki()
    {
        for (Map.Entry<StatekWroga, Wspolrzedne> statekIWspolrzedne : statkiIWspolrzedne.entrySet())
        {
            Wspolrzedne noweWspolrzedne = statekIWspolrzedne.getValue().przesun(0, DLUGOSC_RUCHU);
            ustawWspolrzedneStatku(statekIWspolrzedne, noweWspolrzedne);
        }
    }

    /**
     * Ustawia nowe współrzędne dla danego statku.
     * 
     * @param statekWrogaIWspolrzedne - para zawierająca statek wroga oraz jego współrzędne na mapie.
     * @param noweWspolrzedne - nowe współrzędne po przesunięciu.
     */
    private void ustawWspolrzedneStatku(final Map.Entry<StatekWroga, Wspolrzedne> statekWrogaIWspolrzedne,
            final Wspolrzedne noweWspolrzedne)
    {
        try
        {
            statekWrogaIWspolrzedne.setValue(noweWspolrzedne);
        } catch (IllegalStateException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Zapisuje współrzędne statków wroga na makiecie.
     */
    private void ustawStatkiNaMakiecie()
    {
            List<Wspolrzedne> wspolrzedneStatkow = 
                    new ArrayList<Wspolrzedne>(statkiIWspolrzedne.values());
            makieta.setWspolrzedneStatkowWroga(wspolrzedneStatkow);
    }
}
