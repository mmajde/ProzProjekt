package kolejkablokujaca;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import zdarzenia.ZdarzeniePrzycisku;

public class KolejkaBlokujaca {

	private final int rozmiarKolejki = 3;
	private BlockingQueue<ZdarzeniePrzycisku> kolejkaBlokujaca;
	
	public KolejkaBlokujaca() {
		this.kolejkaBlokujaca = new ArrayBlockingQueue<ZdarzeniePrzycisku>(rozmiarKolejki);
	}
	
	public BlockingQueue<ZdarzeniePrzycisku> getKolejkaBlokujaca() {
		return kolejkaBlokujaca;
	}

//	public void setKolejkaBlokujaca(BlockingQueue<StanPrzycisku> kolejkaBlokujaca) {
//		this.kolejkaBlokujaca = kolejkaBlokujaca;
//	}
	
}
