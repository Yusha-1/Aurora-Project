package otopark.model;

public class Arac {
	
	//Aracın belirleyici özelliklerini ekledim
	private String plaka;
	private String sahipAdi;
	private String telefon;
	
	//Araçtan nesne üretmek için constructor yazdım
	public Arac(String plaka,String sahipAdi,String telefon){
		
		this.plaka = plaka;
		this.sahipAdi = sahipAdi;
		this.telefon = telefon;
	}
	//                PLAKA GETTER VE SETTER
	//Private olan değerlere erişim sağlanması ve değiştirilebilmesi için getter setter metodları lazım
	public String getPlaka() {  //değer dönmesi için String oldu
		return plaka;
	}
	
	//Setter private değere erişim için kullandım değer dönenmez gelecek değeri eşitler
	public void setPlaka(String plaka) {
		this.plaka=plaka;
	}
	
	
	//               SAHİPADİ GETTER  VE SETTER
	public String getSahipAdi() {
		return sahipAdi;
	
	}
	public void setSahipAdi(String isim) {
		this.sahipAdi = isim;
	}
	
	//               TELEFON GETTER VE SETTER
	public String getTelefon() {
		return telefon;
	}
	
	public void setTelefon(String telefonNo) {
		this.telefon = telefonNo;
	}
}
	
	

