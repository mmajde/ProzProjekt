package pl.edu.pw.elka.majde.marek.wyjatki;

/**
 * Wywoływany jeśli podane argumenty do funkcji lub konstruktora są błędne.
 * 
 * @author Marek Majde.
 * 
 */
public class ZlyArgumentException extends RuntimeException
{
    /**
     * Konstruuje wyjątek z informacją o błędzie.
     */
    public ZlyArgumentException()
    {
        super("Podane argumenty do funkcji są błędne.");
    }
}
