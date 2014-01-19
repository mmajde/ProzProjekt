package uzytkowe.kolejkablokujaca;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import wyjatki.NullBlockingQueueException;
import zdarzenia.ZdarzenieGry;

/**
 * Wszystkie klasy korzystają z jednej kolejki blokującej. Dlatego przed jakimkolwiek działaniem na
 * kolejce wymagane jest wywołanie metody stowrzKolejke(), która tworzy kolejkę jeżeli wcześniej nie
 * była ona utworzona.
 */
public final class KolejkaBlokujaca
{

    private static final int ROZMIAR_KOLEJKI = 10;
    private static BlockingQueue<ZdarzenieGry> kolejkaBlokujaca;

    /**
     * Konstruktor prywatny.
     */
    private KolejkaBlokujaca() {}

    /** Tworzenie kolejki do obsługi zdarzeń z gry jeśli wcześniej nie była utworzona */
    public static synchronized void stworzKolejke()
    {
        if (kolejkaBlokujaca == null)
        {
            kolejkaBlokujaca = new ArrayBlockingQueue<ZdarzenieGry>(ROZMIAR_KOLEJKI);
        }
    }

    /**
     * Pobiera kolejne Zdarzenie Przycisku z kolejki blokujacej
     * 
     * @return kolejne Zdarzenie Przycisku lub null w przypadku gdy nie ma więcej zdarzeń
     * @throws NullBlockingQueueException
     *             jeśli kolejka nie została wcześniej utworzona
     */
    public static ZdarzenieGry wezNastepneZdarzenieGry() throws NullBlockingQueueException
    {
        if (kolejkaBlokujaca == null)
        {
            throw new NullBlockingQueueException();
        }
        return kolejkaBlokujaca.poll();
    }

    /**
     * Wstawia nowe Zdarzenie Gry do kolejki jeśli jest w niej wolne miejsce, jeśli nie ma wolnych
     * miejsc czeka na zwolnienie
     * 
     * @param zdarzenieGry
     *            nowe Zdarzenie Gry
     * 
     * @throws NullPointerException
     *             jeśli zdarzenieGry jest nullem
     * @throws NullBlockingQueueException
     *             jeśli kolejka nie została wcześniej utworzona
     * @throws InterruptedException
     *             jeśli proces został przerwany podczas czekania
     * @throws IllegalArgumentException
     *             jeśli jakaś właściwość argumentu zabrania mu być wstawionym do kolejki
     * @throws ClassCastException
     *             jeśli klasa elementu wstawianego nie jest zgodna z klasa przechowywanych
     *             elementów w kolejce
     */
    public static void wstawZdarzenieGry(ZdarzenieGry zdarzenieGry) throws InterruptedException,
            NullBlockingQueueException
    {
        if (kolejkaBlokujaca == null)
        {
            throw new NullBlockingQueueException();
        }
        kolejkaBlokujaca.put(zdarzenieGry);
    }

    /**
     * Sprawdza czy kolejka jest pusta
     * 
     * @throws NullBlockingQueueException
     *             jeśli kolejka nie została wcześniej utworzona
     */
    public static boolean czyPusta() throws NullBlockingQueueException
    {
        if (kolejkaBlokujaca == null)
        {
            throw new NullBlockingQueueException();
        }
        return kolejkaBlokujaca.isEmpty();
    }

}
