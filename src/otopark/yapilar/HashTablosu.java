package otopark.yapilar;

public class HashTablosu<K, V> {

    private static class Girdi<K, V> {
        final K anahtar;
        V deger;
        Girdi<K, V> sonraki;

        Girdi(K anahtar, V deger, Girdi<K, V> sonraki) {
            this.anahtar = anahtar;
            this.deger = deger;
            this.sonraki = sonraki;
        }
    }

    private static final int BASLANGIC_KAPASITE = 16;
    private static final double YUK_FAKTORU = 0.75;

    private Girdi<K, V>[] kovalar;
    private int boyut;

    public HashTablosu() {
        kovalar = yeniDizi(BASLANGIC_KAPASITE);
    }

    public void koy(K anahtar, V deger){
        if (anahtar == null){
            throw new IllegalArgumentException("Anahtar null olamaz.");
        }
        Girdi<K, V> mevcut = bul(anahtar);
        if (mevcut != null){
            mevcut.deger = deger;
            return;
        }
        int i = indeksBul(anahtar, kovalar.length);
        kovalar[i] = new Girdi<>(anahtar, deger, kovalar[i]);
        boyut++;
        if(boyut > kovalar.length * YUK_FAKTORU){
            genislet();
        }
    }

    public V getir(K anahtar){
        Girdi<K, V> g = bul(anahtar);
        return g == null ? null : g.deger;
    }

    public boolean iceriyor (K anahtar){
        return bul(anahtar) != null;
    }

    public V sil (K anahtar) {
        if (anahtar == null){
            return null;
        }
        int i = indeksBul(anahtar, kovalar.length);
        Girdi<K, V> onceki = null;
        Girdi<K, V> g = kovalar[i];
        while (g != null){
            if (g.anahtar.equals(anahtar)){
                if (onceki == null){
                    kovalar[i] = g.sonraki;
                }else{
                    onceki.sonraki = g.sonraki;
                }
                boyut--;
                return g.deger;
            }
            onceki = g;
            g = g.sonraki;
        }
        return null;
    }

        public BagliListe<V> degerler(){
        BagliListe<V> liste = new BagliListe<>();
        for (Girdi<K, V> kova : kovalar){
            for (Girdi<K, V> g = kova; g != null; g = g.sonraki){
                liste.sonaEkle(g.deger);
            }
        }
        return liste;
    }

    public int boyut(){
        return boyut;
    }

    @SuppressWarnings({"unchecked"})
    private Girdi<K, V>[] yeniDizi(int kapasite) {
        return (Girdi<K, V>[]) new Girdi[kapasite];
    }

    private int indeksBul(K anahtar, int kapasite) {
        return (anahtar.hashCode() & 0x7fffffff) % kapasite;
    }

    private Girdi<K, V> bul(K anahtar){
        if (anahtar == null){
            return null;
        }
        int i = indeksBul(anahtar, kovalar.length);
        for (Girdi<K, V> g = kovalar[i]; g != null; g = g.sonraki) {
            if (g.anahtar.equals(anahtar)) {
                return g;
            }
        }
        return null;
    }

        private void genislet() {
        Girdi<K, V>[] eski = kovalar;
        Girdi<K, V>[] yeni = yeniDizi(eski.length * 2);
        for (Girdi<K, V> kova : eski){
            Girdi<K, V> g = kova;
            while (g != null){
                Girdi<K, V> sonraki = g.sonraki;
                int j = indeksBul(g.anahtar, yeni.length);
                g.sonraki = yeni[j];
                yeni[j] = g;
                g = sonraki;
            }
        }
        kovalar = yeni;
    }
}

