package uzytkowe.kolejkablokujaca;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import wyjatki.NullBlockingQueueException;
import zdarzenia.ZdarzenieGry;

/**
 *  Wszystkie klasy korzystają z jednej kolejki blokującej. Dlatego przed jakimkolwiek działaniem na kolejce
 *  wymagane jest wywołanie metody stowrzKolejke(), która tworzy kolejkę jeżeli wcześniej nie była ona utworzona.
 */
public final class KolejkaBlokujaca {

	private static final int ROZMIAR_KOLEJKI = 10;
	static int i = 0;
	private static BlockingQueue<ZdarzenieGry> kolejkaBlokujaca;
	
	private KolejkaBlokujaca() {}
	
	/** Tworzenie kolejki do obsługi zdarzeń z gry */
	public static synchronized void stworzKolejke() {
		if(kolejkaBlokujaca == null) {
			kolejkaBlokujaca = new ArrayBlockingQueue<ZdarzenieGry>(ROZMIAR_KOLEJKI);
		}
	}

	/** Pobiera kolejne Zdarzenie Przycisku z kolejki blokujacej
	 * @return kolejne Zdarzenie Przycisku lub null w przypadku gdy nie ma więcej zdarzeń
	 * @throws NullBlockingQueueException - jeśli kolejka nie została wcześniej utworzona
	 */
	public static ZdarzenieGry wezNastepneZdarzenieGry() {
		if(kolejkaBlokujaca == null) {
			throw new NullBlockingQueueException();
		}
		return kolejkaBlokujaca.poll();
	}
	
	/** Wstawia nowe Zdarzenie Gry do kolejki jeśli jest w niej wolne miejsce, 
	 * jeśli nie ma wolnych miejsc czeka na zwolnienie
	 * @param zdarzenieGry - nowe Zdarzenie Gry
	 * @throws NullPointerException - jeśli zdarzenieGry jest nullem
	 * @throws NullBlockingQueueException - jeśli kolejka nie została wcześniej utworzona
	 * @throws InterruptedException - jeśli proces został przerwany podczas czekania
	 * @throws IllegalArgumentException - jeśli jakaś właściwość argumentu zabrania mu być wstawionym do kolejki
	 * @throws ClassCastException - jeśli klasa elementu wstawianego nie jest zgodna z klasa przechowywanych elementów w kolejce
	 */
	public static void wstawZdarzenieGry(ZdarzenieGry zdarzenieGry) throws InterruptedException {
		if(kolejkaBlokujaca == null) {
			throw new NullBlockingQueueException();
		}
		
		for(ZdarzenieGry zdarzenie : kolejkaBlokujaca) {
			if(zdarzenie.equals(zdarzenieGry)) {
				return;
			}
		}
		
		kolejkaBlokujaca.put(zdarzenieGry);
		
		for(ZdarzenieGry zdarzenie : kolejkaBlokujaca) {
			System.out.println(zdarzenie.getClass().getName());
		}
	}
	
}
