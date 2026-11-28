package otopark.yonetim;

import java.util.ArrayList;

public class OtoparkYonetici {

    private ArrayList<GirisKaydi> tamamlananlar = new ArrayList<>();

    // --- diger alanlar ve metotlar---

    public void siralamaTest() {
        ArrayList<GirisKaydi> sirali = SiralamaIslemleri.girisSaatineGore(tamamlananlar);
        ArrayList<GirisKaydi> pahaliya = SiralamaIslemleri.toplamUcretineGore(tamamlananlar);
    }
}