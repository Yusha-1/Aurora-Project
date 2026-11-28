package otopark.yapilar;

import java.util.Comparator;

public class MinYigin<T> {


    private Object[] dizi = new Object[16];
    private int boyut = 0;
    private final Comparator<T> karsilastirici;

    public MinYigin(Comparator<T> karsilastirici) {

        this.karsilastirici = karsilastirici;
    }

    public void ekle(T eleman){

        if (boyut == dizi.length){
            buyut();
        }
        dizi[boyut] = eleman;
        boyut++;
        yukariTasi(boyut - 1);
    }

    @SuppressWarnings("unchecked") //"Bu işlemin tür güvenliği (type safety) açısından riskli olabileceğini biliyorum ama sorumluluğu alıyorum, burada uyarı gösterme"

    public T cikar() {
        if (boyut == 0) {
            return null;
        }
        T kok = (T) dizi[0];
        boyut--;
        dizi[0] = dizi[boyut];   // son elemanı köke al
        dizi[boyut] = null;
        if (boyut > 0) {
            asagiTasi(0);
        }
        return kok;
    }

    public boolean sil (T eleman){
        for (int i = 0; i < boyut; i++){
            if (dizi[i] == eleman){
                boyut--;
                if(i == boyut){
                    dizi[boyut] = null;
                    return true;
                }
                dizi[i] = dizi[boyut];
                dizi[boyut] = null;
                yukariTasi(i);
                asagiTasi(i);
                return true;
            }
        }
        return false;
    }

    public int boyut(){
        return boyut;
    }
    public boolean bosMu(){
        return boyut == 0;
    }

    private void yukariTasi(int k){
        while (k > 0){
            int ebeveyn = (k - 1) / 2;
            if (kiyasla(k,ebeveyn) >= 0){
                break;
            }
            takas(k,ebeveyn);
            k = ebeveyn;
        }
    }

    private void asagiTasi(int k){
        while (true) {
            int sol = 2 * k + 1;
            if (sol >= boyut) {
                break;
            }
            int sag = sol + 1;
            int kucuk = sol;
            if (sag < boyut && kiyasla(sag, sol) < 0) {
                kucuk = sag;
            }
            if (kiyasla(k, kucuk) <= 0) {
                break;
            }
            takas(k, kucuk);
            k = kucuk;
        }   
    }

    @SuppressWarnings("unchecked")
    private int kiyasla(int a, int b){
        return karsilastirici.compare((T) dizi[a], (T) dizi[b]);
    }

    private void takas(int a, int b){
        Object gecici = dizi[a];
        dizi[a] = dizi[b];
        dizi[b] = gecici;
    }

    private void buyut() {
        Object[] yeni = new Object[dizi.length * 2];
        System.arraycopy(dizi, 0, yeni, 0, boyut);
        dizi = yeni;
    }
}