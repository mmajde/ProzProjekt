package zdarzenia;

import java.util.EventObject;

public abstract class ZdarzenieGry extends EventObject {

	public ZdarzenieGry(Object source) {
		super(source);
	}
	
	public abstract int hashCode();
	public abstract boolean equals(Object obiekt);

}
