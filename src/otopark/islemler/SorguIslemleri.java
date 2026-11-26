package otopark.islemler;

import java.time.LocateDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import otopark.model.GirisKaydi;
import otopark.model.Arac;
import otopark.model.ParkYeri;

public class SorgulIslemleri{

    public ArrayList<GirisKaydi> tarihAraligi(List<GirisKaydi> kayitlar,
                                                        LocalDateTime bas,
                                                        LocalDateTime bit){
        ArrayList<GirisKaydi> sonuc = new ArrayList<>();

        if(kayitlar == null || bas == null || bit == null bas.isAfter(bit)){
            return sonuc;
        }
    }    

    for(GirisKaydi kayit : kayitlar){
        LocalDateTime giris = kayit.getGirisZamani();

        if(!giris.isBefore(bas) && ! giris.isAfter(bit)){
            sonuc.add(kayit);
        }
    }
    return sonuc;
}

public ArrayList<GirisKaydi> tarihAraligi(List<GirisKaydi> kayitlar, 
                                                    LocalDate bas, 
                                                    LocalDate bit){
    if(bas == null || bit == null){
        returnnew ArrayList<>();
    }
    return tarihAraligi(kayitlar,bas.atStartOfDay(),bit.atTime(23,59,59,999_999_999));
}
