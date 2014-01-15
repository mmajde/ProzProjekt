package widok;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.logging.Logger;

import uzytkowe.kolejkablokujaca.KolejkaBlokujaca;
import wyjatki.NullBlockingQueueException;
import zdarzenia.ZdarzeniePrzycisku;

/**
 * Dodaje zdarzenia klawiatury do kolejki blokującej
 */
public class SluchaczZdarzenKlawiatury extends KeyAdapter
{

    private final Logger LOG = Logger.getLogger("log");

    public SluchaczZdarzenKlawiatury()
    {
        KolejkaBlokujaca.stworzKolejke();
    }

    public void keyPressed(KeyEvent keyEvent)
    {
        try
        {
            wstawDoKolejki(keyEvent, true);
        } catch (InterruptedException e)
        {
            LOG.info("Watek przerwany podczas oczekiwania. " + e.getMessage());
        }
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

}