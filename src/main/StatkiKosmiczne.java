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
    // sciagnac javadoca!
    // illegalargumentexception zrobić własny
    // @created w javadoc
    // invoke later w widoku w konstruktorze
    // wrzucić obiekt gry i pocisk do jadnego pakiety ze statkami. wszystko w modelu. do kolejnego pakietu silniki.
    public static void main(String[] args)
    {
        final Kontroler kontroler = new Kontroler();
        kontroler.dzialanieGry();
    }

}
