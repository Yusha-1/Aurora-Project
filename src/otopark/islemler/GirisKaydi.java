package otopark.islemler;
import java.time.LocalDateTime;
import otopark.model.Arac;
import otopark.model.ParkYeri;

public class GirisKaydi {
     private int kayitId;
     private Arac arac;
     private ParkYeri parkYeri;
     private LocalDateTime girisZamani;
     private LocalDateTime cikisZamani;
     private Tarife uygulananTarife;
     private double ucret;

     private static int idSayac=1 ;
    
public GirisKaydi(Arac arac , ParkYeri parkYeri, Tarife guncelTarife ){
     this.kayitId = kayitId;
     this.arac = arac;
     this.parkYeri = parkYeri;
     this.girisZamani = LocalDateTime.now();
     this.cikisZamani = null;
     //İLERDE GÜNCEL TARİFE DEĞİŞSE BiLE BU ARABA GİRİŞ ZAMANI TARİFESİ SABİT KALACAK O YÜZDEN KOPYALIYORUZ
     this.uygulananTarife = new Tarife(guncelTarife);
     this.ucret = 0.0;
      
     if (this.parkYeri!=null)
        this.parkYeri.doldur();
    }   

   public boolean isAktif(){
     return this.cikisZamani==null;
   }

   public void cikisYap(LocalDateTime cikisZamani){
     if (!isAktif() || cikisZamani==null){
        return ;
     }
     this.cikisZamani=cikisZamani;
     this.ucret= this.uygulananTarife.ucretHesapla(this.girisZamani, this.cikisZamani);

     if (this.parkYeri != null)
        this.parkYeri.bosalt();
   }
    //GETTER METHODLARI

     public int getKayitId() { return kayitId;}
     public Arac getArac() { return arac;}
     public ParkYeri getParkYeri() { return parkYeri;}
     public LocalDateTime getGirisZamani(){return girisZamani;}
     public LocalDateTime getCikisZamani(){return cikisZamani;}
     public Tarife getUygulananTarife() {return uygulananTarife;}
     public double getUcret() {return ucret;}
}
