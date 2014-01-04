package uzytkowe;

/**
 * Określa miejsce w przestrzeni w którym znajduje się dany obiekt gry
 */
public class Wspolrzedne {

	private double x;
	private double y;
	
	public Wspolrzedne(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}	
	
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(x).append(" ");
		stringBuffer.append(y);
		
		return stringBuffer.toString();
	}
	
}
