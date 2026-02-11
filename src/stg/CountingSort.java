package stg;

import java.util.ArrayList;
import java.util.Collections; //TimSort kullanılan bir kütüphane (ilk halini inceler ve parçalar halinde sıralı bütünler var mı bunları tespit eder ve buna göre sıralama yapar)
import java.util.List;

public class CountingSort {
    
    // alfabede okuması gereken türkçe karakterleri küçük haliyle sınırlıyoruz sıralama ölçütüne x gibi türkçede yer almayan karakterler dahil edilmiyor metniTemizle() fonksiyonumuzda yer alan harfleri küçük şekilde okumamızı sağlar
    private static final String TURKCE_ALFABE = "abcçdefgğhıijklmnoöprsştuüvyz";
    private static final int ALFABE_BOYUTU = TURKCE_ALFABE.length();
    
    /**
     * Counting Sort algoritması ile harf frekanslarını hesaplar
     * Big O Analizi:
     * - Zaman: O(n + k) where n = metin uzunluğu, k = alfabe boyutu
     * - Alan: O(k) where k = alfabe boyutu (29 harf)
     */
    public static List<CharFrequency> sayVeSirala(String metin) {
        
        // Adım 1: Counting array oluştur - O(k)
        int[] sayac = new int[ALFABE_BOYUTU];
        
        // Harf indexlerini tutan map
        int[] harfIndex = new int[Character.MAX_VALUE];
        for (int i = 0; i < ALFABE_BOYUTU; i++) {
            harfIndex[TURKCE_ALFABE.charAt(i)] = i;
        }
        
        // Adım 2: Metni tara ve harfleri say - O(n)
        int toplamHarf = 0;
        for (int i = 0; i < metin.length(); i++) {
            char c = metin.charAt(i);
            int index = harfIndex[c];
            if (index > 0 || c == 'a') { // 'a' için özel kontrol (index=0)
                sayac[index]++;
                toplamHarf++;
            }
        }
        
        // Adım 3: Sonuçları listeye çevir - O(k)
        List<CharFrequency> sonuclar = new ArrayList<>();
        for (int i = 0; i < ALFABE_BOYUTU; i++) {
            if (sayac[i] > 0) {
                char harf = TURKCE_ALFABE.charAt(i);
                double yuzde = (sayac[i] * 100.0) / toplamHarf;
                sonuclar.add(new CharFrequency(harf, sayac[i], yuzde));
            }
        }
        
        // Adım 4: Frekansa göre sırala (büyükten küçüğe) - O(k log k)
        Collections.sort(sonuclar);
        
        return sonuclar;
    }
    
    /**
     * Metni temizler: sadece harfleri bırakır, küçük harfe çevirir
     * Big O: O(n)
     */
    public static String metniTemizle(String metin) {
        StringBuilder temiz = new StringBuilder();
        
        for (int i = 0; i < metin.length(); i++) {
            char c = Character.toLowerCase(metin.charAt(i));
            
            // Sadece Türkçe alfabedeki harfleri al
            if (TURKCE_ALFABE.indexOf(c) != -1) {
                temiz.append(c);
            }
        }
        
        return temiz.toString();
    }
}
