package model;

import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;

/**
 * Klasa nadrzędna wszystkich obiektów grze.
 * Posiada metodę przesuwającą obiekty.
 * 
 * @author Marek Majde.
 * 
 */
public abstract class ObiektGry
{
    /**
     * Przesuwa obiekt w nowe miejsce na podstawie parametrów funkcji.
     * 
     * @param wspolrzedneObiektu - aktualne wspólrzedne obiektu.
     * @param przesuniecieObiektu - określa w jaką stronę i o ile ma przesunąć się dany obiekt.
     * 
     * @return nowe współrzędne po przesunięciu obiektu.
     */
    public Wspolrzedne przesun(final Wspolrzedne wspolrzedneObiektu, 
            final Przesuniecie przesuniecieObiektu) 
    {
        double nowyX = wspolrzedneObiektu.getX() + przesuniecieObiektu.getPrawo() + przesuniecieObiektu.getLewo();
        double nowyY = wspolrzedneObiektu.getY() + przesuniecieObiektu.getGora() + przesuniecieObiektu.getDol();
            
        return new Wspolrzedne(nowyX, nowyY);
    }
}
