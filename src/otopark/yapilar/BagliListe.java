package otopark.yapilar;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class BagliListe<T> implements Iterable<T> {

    private static class Dugum<T> {
        T veri;
        Dugum<T> sonraki;

        Dugum(T veri) {
            this.veri = veri;
        }
    }

    private Dugum<T> bas;
    private Dugum<T> son;
    private int boyut;

    public void sonaEkle(T veri) {
        Dugum<T> yeni = new Dugum<>(veri);
        if (bas == null){
            bas = yeni;
            son = yeni;
        }else{
            son.sonraki = yeni;
            son = yeni;
        }
        boyut++;
    }

    public boolean sil(T veri) {
        Dugum<T> onceki = null;
        Dugum<T> simdiki = bas;
        while (simdiki != null) {
            if (simdiki.veri == veri || (veri != null && veri.equals(simdiki.veri))) {
                if (onceki == null) {
                    bas = simdiki.sonraki;
                } else {
                    onceki.sonraki = simdiki.sonraki;
                }
                if (simdiki == son) {
                    son = onceki;
                }
                boyut--;
                return true;
            }
            onceki = simdiki;
            simdiki = simdiki.sonraki;
        }
        return false;
    }

    public T getir (int indeks){
        if(indeks<0 || indeks>=boyut){
             throw new IndexOutOfBoundsException("Geçersiz indeks: " + indeks);
        }
        Dugum<T> simdiki = bas;
        for (int i = 0; i < indeks; i++){
            simdiki = simdiki.sonraki;
        }
        return simdiki.veri;
    }

    public BagliListe<T> kopyala() {
        BagliListe<T> kopya = new BagliListe<>();
        for (Dugum<T> d = bas; d != null; d = d.sonraki) {
            kopya.sonaEkle(d.veri);
        }
        return kopya;
    }

    public void diziyeAktar(T[] hedef){
        if (hedef.length < boyut){
            throw new IllegalArgumentException("Dizi çok küçük.");
        }
        int i = 0;
        for (Dugum<T> d = bas; d != null; d = d.sonraki) {
            hedef[i++] = d.veri;
        }
    }
     public static <E> BagliListe<E> diziden(E[] dizi) {
        BagliListe<E> liste = new BagliListe<>();
        for (E eleman : dizi) {
            liste.sonaEkle(eleman);
        }
        return liste;
    }

    public int boyut(){
        return boyut;
    }
    public boolean bosMu(){
        return boyut == 0;
    }
    @Override 
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            private Dugum<T> simdiki = bas;

            @Override 
            public boolean hasNext() {
                return simdiki != null;
            }

            @Override
            public T next(){
                if (simdiki == null){
                    throw new NoSuchElementException();
                }
                T veri = simdiki.veri;
                simdiki = simdiki.sonraki;
                return veri;
            }
        };
    }
}    