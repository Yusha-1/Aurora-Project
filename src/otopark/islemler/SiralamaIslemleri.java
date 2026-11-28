import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import otopark.islemler.SiralamaIslemleri;

public class SiralamaIslemleri {

    //     Giris saatine gore (Merge Sort, O(n log n), stabil) 
	
    public static ArrayList<GirisKaydi> girisSaatineGore(List<GirisKaydi> kayitlar) {
        ArrayList<GirisKaydi> kopya = new ArrayList<>(kayitlar); // orijinal liste bozulmasın
        if (kopya.size() > 1) {
            mergeSort(kopya, 0, kopya.size() - 1);
        }
        return kopya;
    }

    private static void mergeSort(ArrayList<GirisKaydi> liste, int sol, int sag) {
        if (sol >= sag) return;
        int orta = sol + (sag - sol) / 2;
        mergeSort(liste, sol, orta);
        mergeSort(liste, orta + 1, sag);
        birlestir(liste, sol, orta, sag);
    }

    private static void birlestir(ArrayList<GirisKaydi> liste, int sol, int orta, int sag) {
        ArrayList<GirisKaydi> gecici = new ArrayList<>(sag - sol + 1);
        int i = sol, j = orta + 1;

        while (i <= orta && j <= sag) {
            LocalDateTime a = liste.get(i).getGirisZamani();
            LocalDateTime b = liste.get(j).getGirisZamani();
            
            // esit zamanlarda sira korunur (stabil)
            
            if (a.compareTo(b) <= 0) {
                gecici.add(liste.get(i++));
            } else {
                gecici.add(liste.get(j++));
            }
        }
        while (i <= orta) gecici.add(liste.get(i++));
        while (j <= sag)  gecici.add(liste.get(j++));

        for (int k = 0; k < gecici.size(); k++) {
            liste.set(sol + k, gecici.get(k));
        }
    }

    // ---- Toplam ucrete gore (Quick Sort, ortalama O(n log n)) ----
    
    public static ArrayList<GirisKaydi> toplamUcretineGore(List<GirisKaydi> kayitlar) {
        ArrayList<GirisKaydi> kopya = new ArrayList<>(kayitlar);
        if (kopya.size() > 1) {
            quickSort(kopya, 0, kopya.size() - 1);
        }
        return kopya;
    }

    private static void quickSort(ArrayList<GirisKaydi> liste, int dusuk, int yuksek) {
        if (dusuk >= yuksek) return;
        int p = bol(liste, dusuk, yuksek);
        quickSort(liste, dusuk, p - 1);
        quickSort(liste, p + 1, yuksek);
    }

    private static int bol(ArrayList<GirisKaydi> liste, int dusuk, int yuksek) {
    	
        //  sirali veride en kotu durumu (O(n^2)) onler
    	
        int orta = dusuk + (yuksek - dusuk) / 2;
        if (liste.get(orta).getUcret() < liste.get(dusuk).getUcret())  swap(liste, orta, dusuk);
        if (liste.get(yuksek).getUcret() < liste.get(dusuk).getUcret()) swap(liste, yuksek, dusuk);
        if (liste.get(yuksek).getUcret() < liste.get(orta).getUcret())  swap(liste, yuksek, orta);
        swap(liste, orta, yuksek); // pivotu sona al

        double pivot = liste.get(yuksek).getUcret();
        int i = dusuk - 1;
        for (int j = dusuk; j < yuksek; j++) {
            if (liste.get(j).getUcret() <= pivot) {
                i++;
                swap(liste, i, j);
            }
        }
        swap(liste, i + 1, yuksek);
        return i + 1;
    }

    private static void swap(ArrayList<GirisKaydi> liste, int a, int b) {
        GirisKaydi t = liste.get(a);
        liste.set(a, liste.get(b));
        liste.set(b, t);
    }
}