package otopark.islemler;

import java.time.LocalDateTime;

public class Tarife {
   private double saatlikUcret;
   private int ucretsizDakika;
   
   //TEMEL YAPILANDIRICI
   public Tarife(double saatlikUcret , int ucretsizDakika) {
	   this.saatlikUcret = saatlikUcret;
	   this.ucretsizDakika = ucretsizDakika;
   }
   //KOPYA İŞLEMİ İÇİN ÇAĞIRILACAK YAPILANDIRICI
  public Tarife(Tarife diger){
   //KOPYA İŞLEMİ YANİ new Tarife(kopya) GirisKaydı yapılandırıcısında yapılacak
      this.saatlikUcret=saatlikUcret;
      this.ucretsizDakika=ucretsizDakika;

  }
   //SÜRE FARKINDAN ÜCRET HESAPLAMA FONKSİYONU 
  public double ucretHesapla(LocalDateTime giris,LocalDateTime cikis){
    int yilFarki = cikis.getYear() - giris.getYear();
    int gunFarki = cikis.getDayOfYear() - giris.getDayOfYear() +(yilFarki * 365);
    int saatFarki = cikis.getHour() - giris.getHour();
    int dakikaFarki = cikis.getMinute() - giris.getMinute();

    long toplamDakika = (gunFarki * 24 * 60L) + (saatFarki * 60L) + dakikaFarki;

    if (toplamDakika <0) 
      toplamDakika =0;

    if(toplamDakika <=ucretsizDakika)
      return 0.0;

   long ucretliDakika = toplamDakika - ucretsizDakika;
     return (ucretliDakika /60.0) * saatlikUcret; 
  }
   public double getSaatlikUcret() { return saatlikUcret ;} 
   public int getucretsizDakika()  { return ucretsizDakika ;}

}
