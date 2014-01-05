package model.strategia;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import model.Pocisk;
import model.RuchObiektem;
import statek.StatekBohatera;
import uzytkowe.Makieta;
import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;
import uzytkowe.Wymiary;

public class SilnikBohatera {	
	
	private StatekBohatera statekBohatera;
	private List<Pocisk> pociski;
	private Wspolrzedne wspolrzedneBohatera;
	private Przesuniecie przesuniecieBohatera;
	private Makieta makieta;
	private Wymiary wymiaryStatku;
	
	public SilnikBohatera(Makieta makieta) {
		// magic numbers
		wymiaryStatku = new Wymiary(25.0, 25.0);
		statekBohatera = new StatekBohatera(wymiaryStatku);
		this.makieta = makieta;
		wspolrzedneBohatera = new Wspolrzedne(makieta.getRozmiar().getWidth()/2, makieta.getRozmiar().getHeight()-60);
		przesuniecieBohatera = new Przesuniecie();
		pociski = new CopyOnWriteArrayList<Pocisk>();
	}

	public void dodajPocisk(Wspolrzedne wspolrzedne, double szybkosc) {
		try {
			pociski.add(new Pocisk(wspolrzedne, szybkosc));
		} catch(UnsupportedOperationException | IllegalArgumentException | NullPointerException | ClassCastException e) {
			throw new RuntimeException();
		}
	}
	
	public void usunPocisk(Pocisk pocisk) {
		try {
			pociski.remove(pocisk);
		} catch(ClassCastException e) {
			System.out.println("Pocisk błędnego typu. " + e.getMessage());
		} catch(NullPointerException e) {
			System.out.println("Pocisk nie może być nullem. " + e.getMessage());
		} catch(UnsupportedOperationException e) {
			throw new RuntimeException();
		}
		
	}
	
	public List<Wspolrzedne> getWspolrzednePociskow() {
		List<Wspolrzedne> wspolrzednePociskow = new ArrayList<Wspolrzedne>();
		for(Pocisk pocisk : pociski) {
			wspolrzednePociskow.add(pocisk.getWspolrzedne());
		}
		return wspolrzednePociskow;
	}
	
	public void dzialaj() {	
		przesunBohateraIPociski();
		usunPociskiZaMapa();
		ustawBohateraIPociskiNaMakiecie();
	}

	private void usunPociskiZaMapa() {
		for(Pocisk pocisk : pociski) {
			if(!czyPociskNaMapie(pocisk)) {
				usunPocisk(pocisk);
			}
		}
	}

	private void ustawBohateraIPociskiNaMakiecie() {
		makieta.setWspolrzedneStatkuBohatera(wspolrzedneBohatera);
		makieta.setWspolrzednePociskow(getWspolrzednePociskow());
	}

	private void przesunBohateraIPociski() {
		wspolrzedneBohatera = RuchObiektem.przesunObiekt(wspolrzedneBohatera, przesuniecieBohatera);
		for(Pocisk pocisk : pociski) {
			pocisk.setWspolrzedne(RuchObiektem.przesunObiekt(pocisk.getWspolrzedne(), pocisk.getPrzesuniecie()));
		}
	}
	
	private boolean czyPociskNaMapie(Pocisk pocisk) {
		// do zmiany magic number
		return pocisk.getWspolrzedne().getY() > 0;
	}
	
	public Wymiary getWymiaryStatku() {
		return wymiaryStatku;
	}

	public Wspolrzedne getWspolrzedneBohatera() {
		return wspolrzedneBohatera;
	}
	
	public Przesuniecie getPrzesuniecieBohatera() {
		return przesuniecieBohatera;
	}

	public StatekBohatera getStatekBohatera() {
		return statekBohatera;
	}
	
	public List<Pocisk> getPociski() {
		return pociski;
	}
	
}
