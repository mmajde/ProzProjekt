package wyjatki;

/**
 * Rzucany jeśli kolejka blokująca jest używana a nie została wcześniej stworzona.
 * 
 * @author Marek Majde.
 */
public class NullBlockingQueueException extends RuntimeException {

	/**
	 * Konstruuje wyjątek z informacją o błędzie.
	 */
	public NullBlockingQueueException() {
		super("Kolejka powinna być wcześniej utworzona metodą StworzKolejke.");
	}
}
