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
   public Tarife(Tarife kopya) {
	   this.saatlikUcret = saatlikUcret;
	   this.ucretsizDakika = ucretsizDakika;
   }
   //SÜRE FARKINDAN ÜCRET HESAPLAMA
 
}
