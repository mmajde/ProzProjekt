package model;

import java.awt.Image;

import javax.swing.ImageIcon;

import statek.StatekBohatera;
import uzytkowe.Wspolrzedne;

public class Bohater extends ObiektGry {
	
	private final String SCIEZKA_IKONY_BOHATERA = "//home//majde//java_workspace//Statki_kosmiczne//images//bohaternext1.png";
	
	// to wyrzucic do StatekBohatera
	private StatekBohatera statekBohatera;
	private Image ikonaBohatera;
	
	public Bohater() {
		stworzBohatera(new Wspolrzedne(270d, 530d));
		ustawIkoneBohatera();
	}
	
	private void ustawIkoneBohatera() {
		ImageIcon ikona = new ImageIcon(SCIEZKA_IKONY_BOHATERA);
    	ikonaBohatera = ikona.getImage();
	}

	public StatekBohatera getStatekBohatera() {
		return statekBohatera;
	}

	private void stworzBohatera(Wspolrzedne wspolrzedne) {
		statekBohatera = new StatekBohatera(wspolrzedne);
	}
	
	public Image getIkonaBohatera() {
		return ikonaBohatera;
	}

	public int getGranice() {
		return 0;
	}
	
}
