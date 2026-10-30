package otopark.model;

public class ParkYeri {

	
	// park yeri sınıfı özellikleri
	
	private String yerNo;
	private boolean dolu;

	// constructor tanımlayıp nesne oluşturduk
	public ParkYeri(String yerNo) {
		this.yerNo = yerNo;
		this.dolu = false ;    //en başta boş oldugu için false.
	}
	
	public void doldur() {
		this.dolu = true;
	}
	
	public void bosalt() {
		this.dolu = false;
		
	}
	public boolean isDolu () {
		return dolu;
	}
	
	
	
	
	
}
