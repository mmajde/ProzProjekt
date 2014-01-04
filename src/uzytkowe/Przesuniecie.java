package uzytkowe;

/**
 * Definiuje wartości przesunięć w czeterech kierunkach (góra, dół, prawo, lewo) danych obiektów w grze
 */
public class Przesuniecie {

	private double gora;
	private double dol;
	private double prawo;
	private double lewo;
	
	public Przesuniecie() {
		this.gora = 0d;
		this.dol = 0d;
		this.prawo = 0d;
		this.lewo = 0d;
	}
	
	public Przesuniecie(double gora, double dol, double prawo, double lewo) {
		this.gora = -gora;
		this.dol = dol;
		this.prawo = prawo;
		this.lewo = -lewo;
	}
	
	public double getGora() {
		return gora;
	}

	public void setGora(double gora) {
		this.gora = gora;
	}

	public double getDol() {
		return dol;
	}

	public void setDol(double dol) {
		this.dol = dol;
	}

	public double getPrawo() {
		return prawo;
	}

	public void setPrawo(double prawo) {
		this.prawo = prawo;
	}

	public double getLewo() {
		return lewo;
	}

	public void setLewo(double lewo) {
		this.lewo = lewo;
	}
	
}
