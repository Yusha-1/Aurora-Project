package otopark.model;

public class ParkYeri {

	
	//  park yeri sinifi ozellikleri
	
	private String yerNo;
	private boolean dolu;

	// constructor tanimlayip nesne olusturduk
	public ParkYeri(String yerNo) {
		this.yerNo = yerNo;
		this.dolu = false ;    //en basta bos oldugu icin false.
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
