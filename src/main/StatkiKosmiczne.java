package main;

import kontroler.Kontroler;

/**
 * Zawiera funkcję main uruchamiającą grę.
 * 
 * @author Marek Majde.
 *
 */
public class StatkiKosmiczne
{
    // wrzucić obiekt gry i pocisk do jadnego pakietu ze statkami. wszystko w modelu. do kolejnego pakietu silniki.
    public static void main(String[] args)
    {
        final Kontroler kontroler = new Kontroler();
        kontroler.dzialanieGry();
    }

}
