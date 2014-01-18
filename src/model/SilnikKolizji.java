package model;

import statek.StatekWroga;

/**
 * Odpowiedzialna za sprawdzanie kolizje w grze.
 * 
 * @author Marek Majde.
 */
public class SilnikKolizji
{
    /** Generowanie, kontrola i poruszanie statków wroga. */
    private final SilnikWroga silnikWroga;
    /** Kontrola i poruszanie statku bohatera. */
    private final SilnikBohatera silnikBohatera;
    /** Sprawdzanie kolizji między obiektami w grze. */
    private final Kolizja kolizja;

    /**
     * Konstruuje silnik kolizji.
     * 
     * @param silnikWroga - odpowiada za generowanie, kontrolę i poruszanie statków wroga.
     * @param silnikBohatera - kontrola i poruszanie statku bohatera.
     */
    public SilnikKolizji(final SilnikWroga silnikWroga, final SilnikBohatera silnikBohatera)
    {
        this.silnikWroga = silnikWroga;
        this.silnikBohatera = silnikBohatera;
        this.kolizja = new Kolizja(silnikWroga.getWymiaryStatkuWroga(), 
                silnikBohatera.getWymiaryStatkuBohatera());
    }

    /**
	 * Sprawdza kolizje w grze i usuwa obiekty biorące udział w kolizjach.
	 * Sprawdza czy gra nie powinna się zakończyć z powodu kolizji.
	 */
    public void dzialaj()
    {
        sprawdzKolizje();
        usunKolidujacePociskiIWrogow();
    }

    /**
     * Wywołuje metodę z klasy kolizja sprawdzającą wszystkie kolizje w grze. 
     */
    private void sprawdzKolizje()
    {
        kolizja.sprawdzKolizje(silnikWroga.getStatkiOrazIchWspolrzedne(), 
                silnikBohatera.getWspolrzedneStatkuBohatera(), silnikBohatera.getPociski());
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
}
