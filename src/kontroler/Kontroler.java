package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import model.Model;
import uzytkowe.Makieta;
import uzytkowe.Przesuniecie;
import uzytkowe.kolejkablokujaca.KolejkaBlokujaca;
import widok.Widok;
import zdarzenia.KolejnyMomentZdarzenie;
import zdarzenia.KoniecGryZdarzenie;
import zdarzenia.WcisnietaSpacjaZdarzenie;
import zdarzenia.WcisnietyPrzyciskWDolZdarzenie;
import zdarzenia.WcisnietyPrzyciskWGoreZdarzenie;
import zdarzenia.WcisnietyPrzyciskWLewoZdarzenie;
import zdarzenia.WcisnietyPrzyciskWPrawoZdarzenie;
import zdarzenia.ZdarzenieGry;

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
    private final Map<Class<? extends ZdarzenieGry>, Strategia> mapaStrategii;
    /** Licznik informujący o kolejnym momencie. */
    private final Timer licznikCzasu;

    /**
     * Konstruuje widok, model oraz zarządza strategiami.
     */
    public Kontroler()
    {
        this.widok = new Widok();
        this.model = new Model(widok.getRozmiar());
        
        this.mapaStrategii = new HashMap<Class<? extends ZdarzenieGry>, Strategia>();
        organizujStrategie();

        this.licznikCzasu = new Timer(10, new ActionListener() {
          public void actionPerformed(final ActionEvent event) {
            try {
              KolejkaBlokujaca.wstawZdarzenieGry(new KolejnyMomentZdarzenie());
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
        while (true)
        {
            ZdarzenieGry nastepneZdarzenieGry = KolejkaBlokujaca.wezNastepneZdarzenieGry();
            if(nastepneZdarzenieGry != null) 
            {
                Strategia strategiaDzialania = mapaStrategii.get(nastepneZdarzenieGry.getClass());
                if(strategiaDzialania != null)
                {
                    strategiaDzialania.dzialanie();
                }
            }
        }
    }

    /**
     * Tworzy wszystkie strategie w grze do obsługi zdarzeń.
     */
    public void organizujStrategie()
    {
        mapaStrategii.put(
                WcisnietyPrzyciskWGoreZdarzenie.class, new PrzesunBohateraWGore());
        mapaStrategii.put(
                WcisnietyPrzyciskWDolZdarzenie.class, new PrzesunBohateraWDol());
        mapaStrategii.put(
                WcisnietyPrzyciskWPrawoZdarzenie.class, new PrzesunBohateraWPrawo());
        mapaStrategii.put(
                WcisnietyPrzyciskWLewoZdarzenie.class, new PrzesunBohateraWLewo());
        mapaStrategii.put(
                WcisnietaSpacjaZdarzenie.class, new GenerujPocisk());
        mapaStrategii.put(
                KolejnyMomentZdarzenie.class, new UruchomSilniki());
        mapaStrategii.put(
                KoniecGryZdarzenie.class, new ZakonczGre());
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
            model.przesunBohatera(new Przesuniecie(3, 0, 0, 0));
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
            model.przesunBohatera(new Przesuniecie(0, 3, 0, 0));
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
            model.przesunBohatera(new Przesuniecie(0, 0, 3, 0));
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
            model.przesunBohatera(new Przesuniecie(0, 0, 0, 3));
        }
    }
    
    /**
     * Uruchamia silnikiModelu. Reakcja na KolejnyMomentZdarzenie.
     */
    class UruchomSilniki implements Strategia
    {
        @Override 
        public void dzialanie()
        {
            model.uruchomSilniki();
            Makieta makieta = model.getMakieta();
            widok.getPoleBitwy().rysujPoleBitwy(makieta);
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
    
    /**
     * Kończy grę.
     */
    class ZakonczGre implements Strategia
    {
        @Override
        public void dzialanie()
        {
            System.exit(0);
        }
    }
}
