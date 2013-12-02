package kolejkablokujaca;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import uzytkowe.StanPrzycisku;

public class KolejkaBlokujaca {

	private final int rozmiarKolejki = 3;
	private BlockingQueue<StanPrzycisku> kolejkaBlokujaca;
	
	public KolejkaBlokujaca() {
		this.kolejkaBlokujaca = new ArrayBlockingQueue<StanPrzycisku>(rozmiarKolejki);
	}
	
	public BlockingQueue<StanPrzycisku> getKolejkaBlokujaca() {
		return kolejkaBlokujaca;
	}

//	public void setKolejkaBlokujaca(BlockingQueue<StanPrzycisku> kolejkaBlokujaca) {
//		this.kolejkaBlokujaca = kolejkaBlokujaca;
//	}
	
}
