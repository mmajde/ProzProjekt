package widok;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import uzytkowe.kolejkablokujaca.KolejkaBlokujaca;
import wyjatki.NullBlockingQueueException;
import zdarzenia.WcisnietyPrzyciskWDolZdarzenie;
import zdarzenia.WcisnietyPrzyciskWGoreZdarzenie;
import zdarzenia.WcisnietyPrzyciskWLewoZdarzenie;
import zdarzenia.WcisnietyPrzyciskWPrawoZdarzenie;
import zdarzenia.ZdarzenieGry;
import zdarzenia.ZdarzeniePrzycisku;

/**
 * Dodaje zdarzenia klawiatury do kolejki blokującej
 */
public class SluchaczZdarzenKlawiatury extends KeyAdapter
{
    /** Przechowuje pary: numer przycisku - zdarzenie klawiatury */
    final Map<Integer, ZdarzenieKlawiatury> mapaPrzyciskow = new HashMap<Integer, ZdarzenieKlawiatury>();
    /** Wartość dodawana do klucza w przypadku puszczenia przycisku */
    final int WARTOSC_DODAWANA = 57;

    private final Logger LOG = Logger.getLogger("log");

    public SluchaczZdarzenKlawiatury()
    {
        KolejkaBlokujaca.stworzKolejke();
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
                } catch (NullBlockingQueueException | InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void keyPressed(final KeyEvent keyEvent)
    {
        try 
        {
            ZdarzenieKlawiatury wcisnietyPrzycisk = mapaPrzyciskow.get(keyEvent.getKeyCode());
            wcisnietyPrzycisk.dodajZdarzenieDoKolejki();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
//        try
//        {
//            wstawDoKolejki(keyEvent, true);
//        } catch (InterruptedException e)
//        {
//            LOG.info("Watek przerwany podczas oczekiwania. " + e.getMessage());
//        }
    }

    public void keyReleased(KeyEvent keyEvent)
    {
        try
        {
            wstawDoKolejki(keyEvent, false);
        } catch (InterruptedException e)
        {
            if (czyPrzyciskPoruszajacyBohaterem(keyEvent))
            {
                throw new RuntimeException();
            } else
            {
                LOG.info("Watek przerwany podczas oczekiwania. " + e.getMessage());
            }
        }
    }

    private boolean czyPrzyciskPoruszajacyBohaterem(KeyEvent keyEvent)
    {
        return keyEvent.getKeyCode() == KeyEvent.VK_LEFT || keyEvent.getKeyCode() == KeyEvent.VK_RIGHT
                || keyEvent.getKeyCode() == KeyEvent.VK_UP || keyEvent.getKeyCode() == KeyEvent.VK_DOWN;
    }

    public void wstawDoKolejki(KeyEvent keyEvent, boolean czyWcisniety) throws InterruptedException
    {
        try
        {
            KolejkaBlokujaca.wstawZdarzenieGry(new ZdarzeniePrzycisku(keyEvent.getSource(), keyEvent
                    .getKeyCode(), czyWcisniety));
        } catch (NullBlockingQueueException e)
        {
            LOG.info("Kolejka blokująca nie została wcześniej utworzona. " + e.getMessage());
        } catch (NullPointerException | IllegalArgumentException | ClassCastException e)
        {
            throw new RuntimeException();
        }

    }

    private void wstawZdarzenieDoKolejki(ZdarzenieGry noweZdarzenieGry)
    {
        
    }

}