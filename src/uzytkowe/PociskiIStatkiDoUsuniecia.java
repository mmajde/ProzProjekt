package uzytkowe;

import java.util.List;

import statek.Pocisk;
import statek.StatekWroga;

public class PociskiIStatkiDoUsuniecia {

	private List<StatekWroga> statkiWrogaDoUsuniecia;
	private List<Pocisk> pociskiDoUsuniecia;
	
	public PociskiIStatkiDoUsuniecia(List<StatekWroga> statkiWrogaDoUsuniecia, List<Pocisk> pociskiDoUsuniecia) {
		this.statkiWrogaDoUsuniecia = statkiWrogaDoUsuniecia;
		this.pociskiDoUsuniecia = pociskiDoUsuniecia;
	}

	public List<StatekWroga> getStatkiWrogaDoUsuniecia() {
		return statkiWrogaDoUsuniecia;
	}

	public void setStatkiWrogaDoUsuniecia(List<StatekWroga> statkiWrogaDoUsuniecia) {
		this.statkiWrogaDoUsuniecia = statkiWrogaDoUsuniecia;
	}

	public List<Pocisk> getPociskiDoUsuniecia() {
		return pociskiDoUsuniecia;
	}

	public void setPociskiDoUsuniecia(List<Pocisk> pociskiDoUsuniecia) {
		this.pociskiDoUsuniecia = pociskiDoUsuniecia;
	}
	
	
}
