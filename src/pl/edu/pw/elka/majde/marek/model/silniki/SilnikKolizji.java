package pl.edu.pw.elka.majde.marek.model.silniki;

import pl.edu.pw.elka.majde.marek.model.kolizja.Kolizja;
import pl.edu.pw.elka.majde.marek.model.kolizja.ObiektyKolidujace;
import pl.edu.pw.elka.majde.marek.model.obiekty.Pocisk;
import pl.edu.pw.elka.majde.marek.model.obiekty.statki.StatekWroga;

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
	 * 
     * @return True w przypadku gdy gra powinna być zakończona. False w przeciwnym przypadku.
	 */
    public boolean kontrolaKolizji()
    {
        ObiektyKolidujace obiektyKolidujace = pobierzKolizje();
        usunKolidujacePociskiIWrogow(obiektyKolidujace);
        
        return czyZakonczycGre(obiektyKolidujace);
    }

    /**
     * Pomocnicza metoda informująca na podstawie parametru czy gra powinna być zakończona.
     * 
     * @param obiektyKolidujace - obiekty które biorą udział w kolizjach.
     * 
     * @return True jeśli gra powinna być zakończona. False w przeciwnym przypadku.
     */
    private boolean czyZakonczycGre(ObiektyKolidujace obiektyKolidujace)
    {
        if(obiektyKolidujace.isZderzenieBohatera())
        {
            silnikBohatera.usunBohatera();
            return true;
        }
        return false;
    }

    /**
     * Wywołuje metodę z klasy kolizja pobierającą wszystkie kolizje w grze. 
     * 
     * @return Obiekt przechowujący obiekty biorące udział w kolizjach.
     */
    private ObiektyKolidujace pobierzKolizje()
    {
        return kolizja.pobierzKolizje(silnikWroga.getStatkiOrazIchWspolrzedne(), 
                silnikBohatera.getWspolrzedneStatkuBohatera(), silnikBohatera.getPociski());
    }

    /**
	 * Usuwa pociski oraz statki wroga które biorą udział w zderzeniach.
	 */
    private void usunKolidujacePociskiIWrogow(ObiektyKolidujace obiektyKolidujace)
    {
        for (StatekWroga statekWrogaDoUsuniecia : obiektyKolidujace.getStatkiWrogaKolidujace())
        {
            silnikWroga.usunStatek(statekWrogaDoUsuniecia);
        }
        for (Pocisk pociskDoUsuniecia : obiektyKolidujace.getPociskiKolidujace())
        {
            silnikBohatera.usunPocisk(pociskDoUsuniecia);
        }
    }
}
