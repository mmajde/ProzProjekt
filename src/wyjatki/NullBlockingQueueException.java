package wyjatki;

/**
 * Rzucany jeśli kolejka blokująca jest używana a nie została wcześniej stworzona
 */
public class NullBlockingQueueException extends RuntimeException {

	public NullBlockingQueueException() {
		super();
	}
}
