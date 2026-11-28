package otopark.model;
import java.time.Duration;

public class Tarife {
   private double saatlikUcret;
   private int ucretsizDakika;
   
   //TEMEL YAPILANDIRICI
   public Tarife(double saatlikUcret , int ucretsizDakika) { 

    if(saatlikUcret<0) 
       throw new IllegalArgumentException("Saatlik ücret negatif olamaz.");

    if(ucretsizDakika<0)
       throw new IllegalArgumentException("Ücretsiz dakika negatif olamaz.");

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
  public double ucretHesapla(Duration sure ){

   long toplamDakika = sure.toMinutes();
  
    if (toplamDakika <0) 
      toplamDakika =0;

    if(toplamDakika <=ucretsizDakika)
      return 0.0;

   long ucretliDakika = toplamDakika - ucretsizDakika;
     return (ucretliDakika /60.0) * saatlikUcret; 
  }
   public double getSaatlikUcret() { return saatlikUcret ;} 
   public int getucretsizDakika()  { return ucretsizDakika ;}

   @Override 
   public String toString(){
     
     return "Tarife {saatlikUcret=" + saatlikUcret + ", ucretsizDakika=" + ucretsizDakika + "}";
   }

}

