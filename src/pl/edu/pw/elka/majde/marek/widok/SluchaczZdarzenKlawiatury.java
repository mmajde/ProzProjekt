package pl.edu.pw.elka.majde.marek.widok;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.edu.pw.elka.majde.marek.zdarzenia.*;

/**
 * Dodaje zdarzenia klawiatury do kolejki blokującej.
 */
public class SluchaczZdarzenKlawiatury extends KeyAdapter
{
    /** Przechowuje pary: numer przycisku - zdarzenie klawiatury */
    final Map<Integer, ZdarzenieKlawiatury> mapaPrzyciskow = new HashMap<Integer, ZdarzenieKlawiatury>();
    /** Kolejka blokująca przechowująca zdarzenia gry. */
    private final BlockingQueue<GameEvent> kolejkaBlokujaca;
    
    /**
     * Tworzy kolejke blokującą oraz wypełnia mapę przycisków.
     */
    public SluchaczZdarzenKlawiatury(BlockingQueue<GameEvent> kolejkaBlokujaca)
    {
        this.kolejkaBlokujaca = kolejkaBlokujaca;
        wypelnijMapePrzyciskow();
    }

    /**
     * Metoda wypełniająca mapę przycisków. Powiązuje kod wciśniętego przycisku
     * z określonym zdarzeniem.
     */
    public void wypelnijMapePrzyciskow() 
    {   
        dodajZdarzenieDlaPrzycisku(KeyEvent.VK_UP, new WcisnietyPrzyciskWGoreEvent());
        dodajZdarzenieDlaPrzycisku(KeyEvent.VK_DOWN, new WcisnietyPrzyciskWDolEvent());
        dodajZdarzenieDlaPrzycisku(KeyEvent.VK_RIGHT, new WcisnietyPrzyciskWPrawoEvent());
        dodajZdarzenieDlaPrzycisku(KeyEvent.VK_LEFT, new WcisnietyPrzyciskWLewoEvent());
        dodajZdarzenieDlaPrzycisku(KeyEvent.VK_SPACE, new WcisnietaSpacjaEvent());
    }

    /**
     * Dodaje do mapy przycisków odpowiednie zdarzenia klawiatury, 
     * które zawierają metodę wstawiającą odpowiednie zdarzenie gry do kolejki blokującej.
     * 
     * @param kodPrzycisku - wartość integer wciśniętego przycisku będąca kluczem mapy.
     * @param noweZdarzenieGry - zdarzenie gry wstawiane do kolejki blokującej w przypadku wciśnięcia
     * danego przycisku.
     */
    private void dodajZdarzenieDlaPrzycisku(final int kodPrzycisku, final GameEvent noweZdarzenieGry)
    {
        mapaPrzyciskow.put(kodPrzycisku, new ZdarzenieKlawiatury() {
            @Override
            public void dodajZdarzenieDoKolejki()
            {
                try
                {
                    kolejkaBlokujaca.put(noweZdarzenieGry);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
     */
    public void keyPressed(final KeyEvent keyEvent)
    {
        ZdarzenieKlawiatury wcisnietyPrzycisk = mapaPrzyciskow.get(keyEvent.getKeyCode());
        if(wcisnietyPrzycisk != null)
        {
            wcisnietyPrzycisk.dodajZdarzenieDoKolejki();
        }
    }
}