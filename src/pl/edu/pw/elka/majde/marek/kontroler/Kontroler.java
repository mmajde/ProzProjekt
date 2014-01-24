package pl.edu.pw.elka.majde.marek.kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.Timer;

import pl.edu.pw.elka.majde.marek.model.Model;
import pl.edu.pw.elka.majde.marek.uzytkowe.Makieta;
import pl.edu.pw.elka.majde.marek.widok.Widok;
import pl.edu.pw.elka.majde.marek.zdarzenia.*;

/**
 * Klasa modelu MVC.
 * 
 * @author Marek Majde.
 *
 */
public class Kontroler 
{
    /** Widok gry. */
    private final Widok widok;
    /** Obliczenia gry. */
    private final Model model;
    /** Mapa przechowująca strategie do określonych klas zdarzeń. */
    private final Map<Class<? extends GameEvent>, Strategia> mapaStrategii;
    /** Licznik informujący o kolejnym momencie. */
    private final Timer licznikCzasu;
    /** Kolejka blokująca przechowująca zdarzenia gry. */
    private final BlockingQueue<GameEvent> kolejkaBlokujaca;

    /**
     * Konstruuje widok, model oraz zarządza strategiami.
     */
    public Kontroler()
    {
        this.kolejkaBlokujaca = new ArrayBlockingQueue<GameEvent>(10);
        this.widok = new Widok(kolejkaBlokujaca);
        this.model = new Model();
        this.mapaStrategii = new HashMap<Class<? extends GameEvent>, Strategia>();
        organizujStrategie();

        this.licznikCzasu = new Timer(10, new ActionListener() {
          public void actionPerformed(final ActionEvent event) {
            try 
            {
                kolejkaBlokujaca.put(new KolejnyMomentEvent());
            }
            catch(InterruptedException | IllegalArgumentException  e) {
              e.printStackTrace();
            }  
          }
        });   
        
        licznikCzasu.start();
    }
    
    /**
     * Zarządza działaniem gry. Pobiera zdarzenia i znajduje odpowiednią strategię działania.
     */
    public void dzialanieGry()
    {
        while(true)
        {
            try
            {
                GameEvent nastepneZdarzenieGry = kolejkaBlokujaca.take();
                Strategia strategiaDzialania = mapaStrategii.get(nastepneZdarzenieGry.getClass());
                strategiaDzialania.dzialanie();
                kontrolaZakonczeniaGry();
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sprawdza w każdym kolejnym momencie czy gra nie powinna być zakończona.
     * Jeśli tak, kończy grę.
     */
    private void kontrolaZakonczeniaGry()
    {
        if(model.czyKoniecGry())
        {
            licznikCzasu.stop();
            widok.zakonczGre();
            System.exit(0);
        }
    }

    /**
     * Tworzy wszystkie strategie w grze do obsługi zdarzeń.
     */
    private void organizujStrategie()
    {
        mapaStrategii.put(WcisnietyPrzyciskWGoreEvent.class, new PrzesunBohateraWGore());
        mapaStrategii.put(WcisnietyPrzyciskWDolEvent.class, new PrzesunBohateraWDol());
        mapaStrategii.put(WcisnietyPrzyciskWPrawoEvent.class, new PrzesunBohateraWPrawo());
        mapaStrategii.put(WcisnietyPrzyciskWLewoEvent.class, new PrzesunBohateraWLewo());
        mapaStrategii.put(WcisnietaSpacjaEvent.class, new GenerujPocisk());
        mapaStrategii.put(KolejnyMomentEvent.class, new UruchomSilniki());
    }

    /**
     * Interfejs posiadający metodę realizującą określone strategie 
     * w reakcji na dane zdarzenia.
     * 
     */
    interface Strategia 
    {
        /**
         * Nadpisywana metoda. Każda klasa implementująca interfejs określa
         * swoją strategię działania. 
         */
        public void dzialanie();
    }
    
    /**
     * Umożliwia przesunięcie bohatera w górę.
     */
    class PrzesunBohateraWGore implements Strategia
    {
        @Override
        public void dzialanie()
        {
            model.przesunBohateraWGore();
        }
    }
    
    /**
     * Umożliwia przesunięcie bohatera w dół.
     */
    class PrzesunBohateraWDol implements Strategia
    {
        @Override
        public void dzialanie()
        {
            model.przesunBohateraWDol();
        }
    }
    
    /**
     * Umożliwia przesunięcie bohatera w prawo.
     */
    class PrzesunBohateraWPrawo implements Strategia
    {
        @Override
        public void dzialanie()
        {
            model.przesunBohateraWPrawo();
        }
    }

    /**
     * Umożliwia przesunięcie bohatera w lewo.
     */
    class PrzesunBohateraWLewo implements Strategia
    {
        @Override
        public void dzialanie()
        {
            model.przesunBohateraWLewo();
        }
    }
    
    /**
     * Uruchamia silnikiModelu. Reakcja na KolejnyMomentEvent.
     */
    class UruchomSilniki implements Strategia
    {
        @Override 
        public void dzialanie()
        {
            model.uruchomSilniki();
            Makieta makieta = model.getMakieta();
            widok.rysujObiektyWGrze(makieta);
        }
    }
    
    /**
     * Generuje nowy pocisk.
     */
    class GenerujPocisk implements Strategia
    {
        @Override
        public void dzialanie()
        {
            model.dodajPocisk();
        }
    }
}
