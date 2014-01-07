package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import statek.StatekBohatera;
import uzytkowe.Makieta;
import uzytkowe.Przesuniecie;
import uzytkowe.Wspolrzedne;
import uzytkowe.Wymiary;

public class SilnikBohatera {	

	private final Logger LOG = Logger.getLogger("log");
	
	private final double SZEROKOSC_STATKU = 15.0;
	private final double DLUGOSC_STATKU = 35.0;
	
	private StatekBohatera statekBohatera;
	private Wspolrzedne wspolrzedneBohatera;
	private Przesuniecie przesuniecieBohatera;
	private Wymiary wymiaryBohatera;
	private List<Pocisk> pociski;
	private Makieta makieta;
	private double szerokoscMapy;
	private double wysokoscMapy;
	
	public SilnikBohatera(Makieta makieta) {
		//czy tutaj throw new RuntimeException()?
		wymiaryBohatera = new Wymiary(SZEROKOSC_STATKU, DLUGOSC_STATKU);
		statekBohatera = new StatekBohatera(wymiaryBohatera);
		this.makieta = makieta;
		szerokoscMapy = makieta.getRozmiar().getWidth();
		wysokoscMapy = makieta.getRozmiar().getHeight();
		wspolrzedneBohatera = dolnySrodekMapy();
		przesuniecieBohatera = new Przesuniecie();
		pociski = new CopyOnWriteArrayList<Pocisk>();
	}

	private Wspolrzedne dolnySrodekMapy() {
		double srodekMapy = makieta.getRozmiar().getWidth()/2;
		double dolMapy = makieta.getRozmiar().getHeight() - statekBohatera.getWymiary().getWysokosc()*2;
		return new Wspolrzedne(srodekMapy, dolMapy);
	}

	/**
	 * Dodaje kolejny pocisk do listy pocisków 
	 * @param wspolrzedne pocisku
	 * @param szybkosc pocisku
	 */
	public void dodajPocisk(Wspolrzedne wspolrzedne, double szybkosc) {
		try {
			pociski.add(new Pocisk(wspolrzedne, szybkosc));
		} catch(UnsupportedOperationException | IllegalArgumentException | NullPointerException | ClassCastException e) {
			throw new RuntimeException();
		}
	}
	
	public void usunPocisk(Pocisk pocisk) throws NullPointerException{
		try {
			pociski.remove(pocisk);
		} catch(NullPointerException e) {
			LOG.info("Pocisk nie może być nullem. " + e.getMessage());
		} catch(ClassCastException | UnsupportedOperationException e) {
			throw new RuntimeException();
		}
	}
	
	public List<Wspolrzedne> getWspolrzednePociskow() {
		List<Wspolrzedne> wspolrzednePociskow = new ArrayList<Wspolrzedne>();
		for(Pocisk pocisk : pociski) {
			dodajWspolrzednePociskuDoListy(wspolrzednePociskow, pocisk);
		}
		return wspolrzednePociskow;
	}

	private void dodajWspolrzednePociskuDoListy(List<Wspolrzedne> wspolrzednePociskow, Pocisk pocisk) {
		try {
			wspolrzednePociskow.add(pocisk.getWspolrzedne());
		} catch(UnsupportedOperationException | IllegalArgumentException | NullPointerException | ClassCastException e) {
			throw new RuntimeException();
		}
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
		Wspolrzedne noweWspolrzedneBohatera = RuchObiektem.przesunObiekt(wspolrzedneBohatera, przesuniecieBohatera);
		if(czyBohaterNaMapie(noweWspolrzedneBohatera)) {
			wspolrzedneBohatera = noweWspolrzedneBohatera;
		}
		for(Pocisk pocisk : pociski) {
			Wspolrzedne noweWspolrzednePocisku = RuchObiektem.przesunObiekt(pocisk.getWspolrzedne(), pocisk.getPrzesuniecie());
			pocisk.setWspolrzedne(noweWspolrzednePocisku);
		}
	}
	
	private boolean czyBohaterNaMapie(Wspolrzedne noweWspolrzedne) {
		if(noweWspolrzedne.getX() > szerokoscMapy - wymiaryBohatera.getSzerokosc()*2 || noweWspolrzedne.getX() < 0
				|| noweWspolrzedne.getY() > wysokoscMapy - wymiaryBohatera.getWysokosc()*2 || noweWspolrzedne.getY() < 0) {
			return false; 
		}
		return true;
	}

	private boolean czyPociskNaMapie(Pocisk pocisk) {
		return pocisk.getWspolrzedne().getY() > 0;
	}
	
	public Wymiary getWymiaryBohatera() {
		return wymiaryBohatera;
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
