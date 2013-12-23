package model.strategia;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import model.RuchObiektem;
import statek.Pocisk;
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
//		tutaj try catch
		pociski.add(new Pocisk(wspolrzedne, szybkosc));
	}
	
	public void usunPocisk(Pocisk pocisk) {
//		tutaj try catch
		pociski.remove(pocisk);
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

	public void usunPociskiZaMapa() {
		for(Pocisk pocisk : pociski) {
			if(!czyPociskNaMapie(pocisk)) {
				usunPocisk(pocisk);
			}
		}
	}

	public void ustawBohateraIPociskiNaMakiecie() {
		makieta.setWspolrzedneStatkuBohatera(wspolrzedneBohatera);
		makieta.setWspolrzednePociskow(getWspolrzednePociskow());
	}

	public void przesunBohateraIPociski() {
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
