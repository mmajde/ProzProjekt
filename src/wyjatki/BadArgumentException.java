package wyjatki;

/**
 * Wywoływany jeśli podane argumenty do funkcji lub konstruktora są błędne.
 * 
 * @author Marek Majde.
 * 
 */
public class BadArgumentException extends RuntimeException
{
    /**
     * Konstruuje wyjątek z informacją o błędzie.
     */
    public BadArgumentException()
    {
        super("Podane argumenty do funkcji są błędne.");
    }
}
