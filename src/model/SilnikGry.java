package model;

import statek.StatekWroga;

/**
 * Główna klasa uruchamiająca wszystkie silniki w grze (silnik wroga oraz silnik bohatera).
 * Odpowiedzielna również za kolizje w grze.
 * 
 * @author Marek Majde.
 */
public class SilnikGry
{
    /** Generowanie, kontrola i poruszanie statków wroga. */
    private final SilnikWroga silnikWroga;
    /** Kontrola i poruszanie statku bohatera. */
    private final SilnikBohatera silnikBohatera;
    /** Sprawdzanie kolizji między obiektami w grze. */
    private final Kolizja kolizja;
    /** Kontrola zakończenia gry. */
    private boolean koniecGry;

    /**
     * Konstruuje silnik gry.
     * 
     * @param silnikWroga - odpowiada za generowanie, kontrolę i poruszanie statków wroga.
     * @param silnikBohatera - kontrola i poruszanie statku bohatera.
     */
    public SilnikGry(final SilnikWroga silnikWroga, final SilnikBohatera silnikBohatera)
    {
        this.silnikWroga = silnikWroga;
        this.silnikBohatera = silnikBohatera;
        this.koniecGry = false;
        this.kolizja = new Kolizja(silnikWroga.getWymiaryStatkuWroga(), 
                silnikBohatera.getWymiaryStatkuBohatera());
    }

    /**
	 * Działanie silnika gry. Metoda uruchamia pozostałe silniki (silnik bohatera i silnik wroga),
	 * sprawdza kolizje w grze i usuwa obiekty biorące udział w kolizjach.
	 */
    public void dzialaj()
    {
        uruchomSilniki();
        sprawdzKolizje();
        usunKolidujacePociskiIWrogow();
    }

    /**
	 * Uruchamia silnik bohatera oraz silnik wroga.
	 */
    private void uruchomSilniki()
    {
        silnikWroga.dzialaj();
        silnikBohatera.dzialaj();
    }
    
    /**
     * Wywołuje metodę z klasy kolizja sprawdzającą wszystkie kolizje w grze. 
     */
    private void sprawdzKolizje()
    {
        kolizja.sprawdzKolizje(silnikWroga.getStatkiOrazIchWspolrzedne(), 
                silnikBohatera.getWspolrzedneBohatera(), silnikBohatera.getPociski());
    }

    /**
	 * Usuwa pociski oraz statki wroga które biorą udział w zderzeniach.
	 */
    private void usunKolidujacePociskiIWrogow()
    {
        for (StatekWroga statekWrogaDoUsuniecia : kolizja.getStatkiWrogaDoUsuniecia())
        {
            silnikWroga.usunStatek(statekWrogaDoUsuniecia);
        }
        for (Pocisk pociskDoUsuniecia : kolizja.getPociskiDoUsuniecia())
        {
            silnikBohatera.usunPocisk(pociskDoUsuniecia);
        }
    }

    /**
     * Sprawdza czy gra z jakiegoś powodu powinna być zakończona.
     * 
     * @return True jeśli gra powinna być zakończona. False w przeciwnym przypadku.
     */
    public boolean czyKoniec()
    {
        return koniecGry;
    }

}
