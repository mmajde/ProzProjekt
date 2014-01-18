package widok;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import uzytkowe.kolejkablokujaca.KolejkaBlokujaca;
import zdarzenia.*;

/**
 * Dodaje zdarzenia klawiatury do kolejki blokującej.
 */
public class SluchaczZdarzenKlawiatury extends KeyAdapter
{
    /** Przechowuje pary: numer przycisku - zdarzenie klawiatury */
    final Map<Integer, ZdarzenieKlawiatury> mapaPrzyciskow = new HashMap<Integer, ZdarzenieKlawiatury>();
    
    /**
     * Tworzy kolejke blokującą oraz wypełnia mapę przycisków.
     */
    public SluchaczZdarzenKlawiatury()
    {
        KolejkaBlokujaca.stworzKolejke();
        wypelnijMapePrzyciskow();
    }

    /**
     * Metoda wypełniająca mapę przycisków. Powiązuje kod wciśniętego przycisku
     * z określonym zdarzeniem.
     */
    public void wypelnijMapePrzyciskow() 
    {   
        dodajZdarzenieDlaPrzycisku(KeyEvent.VK_UP, new WcisnietyPrzyciskWGoreZdarzenie());
        dodajZdarzenieDlaPrzycisku(KeyEvent.VK_DOWN, new WcisnietyPrzyciskWDolZdarzenie());
        dodajZdarzenieDlaPrzycisku(KeyEvent.VK_RIGHT, new WcisnietyPrzyciskWPrawoZdarzenie());
        dodajZdarzenieDlaPrzycisku(KeyEvent.VK_LEFT, new WcisnietyPrzyciskWLewoZdarzenie());
        dodajZdarzenieDlaPrzycisku(KeyEvent.VK_SPACE, new WcisnietaSpacjaZdarzenie());
    }

    /**
     * Dodaje do mapy przycisków odpowiednie zdarzenia klawiatury, 
     * które zawierają metodę wstawiającą odpowiednie zdarzenie gry do kolejki blokującej.
     * 
     * @param kodPrzycisku - wartość integer wciśniętego przycisku będąca kluczem mapy.
     * @param noweZdarzenieGry - zdarzenie gry wstawiane do kolejki blokującej w przypadku wciśnięcia
     * danego przycisku.
     */
    private void dodajZdarzenieDlaPrzycisku(final int kodPrzycisku, final ZdarzenieGry noweZdarzenieGry)
    {
        mapaPrzyciskow.put(kodPrzycisku, new ZdarzenieKlawiatury() {
            @Override
            public void dodajZdarzenieDoKolejki()
            {
                try
                {
                    KolejkaBlokujaca.wstawZdarzenieGry(noweZdarzenieGry);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void keyPressed(final KeyEvent keyEvent)
    {
        ZdarzenieKlawiatury wcisnietyPrzycisk = mapaPrzyciskow.get(keyEvent.getKeyCode());
        if(wcisnietyPrzycisk != null)
        {
            wcisnietyPrzycisk.dodajZdarzenieDoKolejki();
        }
    }
}