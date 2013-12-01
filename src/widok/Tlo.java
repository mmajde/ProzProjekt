package widok;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Tlo extends JPanel{

	private final String SCIEZKA = "//home//majde//java_workspace//Statki_kosmiczne//images//tlo.jpeg";
	private BufferedImage tlo;
	
	public Tlo() {
		setBackground(Color.BLACK);
		/*
		try {
			tlo = ImageIO.read(new File(SCIEZKA));
		} catch (IOException e) {
			System.err.println("Błąd przy pobieraniu tla. " + e.getMessage());
		}
		*/
	}
	
	
	
	/* metoda zwracająca ścieżke do tła */
}
