package otopark.model;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class GirisKaydi {
    private final int kayitId;
    private final Arac arac;
    private final ParkYeri parkYeri;
    private final LocalDateTime girisZamani;
    private LocalDateTime cikisZamani;
    private final Tarife uygulananTarife;
    private double ucret;

    private static int idSayac = 1;

    // NORMAL KULLANIM: giriş zamanı "şimdi" olur
    public GirisKaydi(Arac arac, ParkYeri parkYeri, Tarife guncelTarife) {
        this(arac, parkYeri, guncelTarife, LocalDateTime.now());
    }

    // TEST / GEÇMİŞ TARİHLİ KAYIT İÇİN: giriş zamanı dışarıdan verilir
    public GirisKaydi(Arac arac, ParkYeri parkYeri, Tarife guncelTarife, LocalDateTime girisZamani) {
        Objects.requireNonNull(arac, "Araç boş olamaz.");
        Objects.requireNonNull(guncelTarife, "Tarife boş olamaz.");
        Objects.requireNonNull(girisZamani, "Giriş zamani boş olamaz.");

        this.kayitId = idSayac++;
        this.arac = arac;
        this.parkYeri = parkYeri;
        this.girisZamani = girisZamani;
        this.cikisZamani = null;
        // İLERİDE GÜNCEL TARİFE DEĞİŞSE BİLE BU KAYDIN TARİFESİ SABİT KALSIN DİYE KOPYALIYORUZ
        this.uygulananTarife = new Tarife(guncelTarife);
        this.ucret = 0.0;

        if (this.parkYeri != null) {
            this.parkYeri.doldur();
        }
    }

    public boolean isAktif() {
        return this.cikisZamani == null;
    }

    public void cikisYap(LocalDateTime cikisZamani) {
        if (!isAktif()) {
            throw new IllegalStateException("Bu araç zaten cikis yapmis. Kayit id: " + kayitId);
        }
        hesaplaVeAta(cikisZamani);

        if (this.parkYeri != null) {
            this.parkYeri.bosalt();
        }
    }

    // UML'DEKİ cikisZamaniGuncelle(): çıkış sonradan düzeltilirse ücret yeniden hesaplanır
    // (park yeri zaten boşaltıldığı için tekrar bosalt() çağrılmaz)
    public void cikisZamaniGuncelle(LocalDateTime yeniCikisZamani) {
        if (isAktif()) {
            throw new IllegalStateException("Cikis yapmamis kaydin cikis zamani güncellenemez.");
        }
        hesaplaVeAta(yeniCikisZamani);
    }

    private void hesaplaVeAta(LocalDateTime cikis) {
        Objects.requireNonNull(cikis, "Cikis zamani boş olamaz.");
        if (cikis.isBefore(this.girisZamani)) {
            throw new IllegalArgumentException("Cikis zamani giriş zamanindan önce olamaz.");
        }
        this.cikisZamani = cikis;
        this.ucret = this.uygulananTarife.ucretHesapla(Duration.between(this.girisZamani, cikis));
    }

    // GETTER METHODLARI
    public int getKayitId() { return kayitId; }
    public Arac getArac() { return arac; }
    public ParkYeri getParkYeri() { return parkYeri; }
    public LocalDateTime getGirisZamani() { return girisZamani; }
    public LocalDateTime getCikisZamani() { return cikisZamani; }
    public Tarife getUygulananTarife() { return uygulananTarife; }
    public double getUcret() { return ucret; }

    @Override
    public String toString() {
        return "GirisKaydi{id=" + kayitId
                + ", arac=" + arac
                + ", parkYeri=" + parkYeri
                + ", giris=" + girisZamani
                + ", cikis=" + cikisZamani
                + ", ucret=" + ucret + "}";
    }
}


