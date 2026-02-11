package stg;

import java.util.List;

public class TextAnalyzer {
    private String orijinalMetin;
    private String temizMetin;
    private long baslangicZamani;
    private long bitisZamani;
    
    public TextAnalyzer(String metin) {
        this.orijinalMetin = metin;
    }
    
    public List<CharFrequency> analizeEt() {
        System.out.println("🔄 Analiz başlıyor...");
        System.out.println();
        
        // Performans ölçümü başlat
        baslangicZamani = System.nanoTime();
        
        // 1. Metni temizle (sadece harfler)
        temizMetin = CountingSort.metniTemizle(orijinalMetin);
        
        // 2. Counting Sort uygula
        List<CharFrequency> sonuclar = CountingSort.sayVeSirala(temizMetin);
        
        // Performans ölçümü bitir
        bitisZamani = System.nanoTime();
        
        System.out.println("✓ Analiz tamamlandı!");
        System.out.println();
        
        return sonuclar;
    }
    
    public void sonuclariGoster(List<CharFrequency> sonuclar) {
        // İstatistikler
        System.out.println("=".repeat(60));
        System.out.println("📊 İSTATİSTİKLER");
        System.out.println("=".repeat(60));
        System.out.println("Orijinal metin uzunluğu    : " + orijinalMetin.length() + " karakter");
        System.out.println("Temizlenmiş metin uzunluğu : " + temizMetin.length() + " harf");
        System.out.println("Filtrelenen karakterler    : " + (orijinalMetin.length() - temizMetin.length()));
        System.out.println("Benzersiz harf sayısı      : " + sonuclar.size());
        
        double calismaSuresi = (bitisZamani - baslangicZamani) / 1_000_000.0; // ms'ye çevir
        System.out.println("Çalışma süresi             : " + String.format("%.4f", calismaSuresi) + " ms");
        System.out.println();
        
        // Big O Analizi
        System.out.println("=".repeat(60));
        System.out.println("⚡ ALGORİTMA KARMAŞIKLIĞI (BIG O ANALİZİ)");
        System.out.println("=".repeat(60));
        System.out.println("Zaman Karmaşıklığı:");
        System.out.println("  • Metin temizleme    : O(n)         [n = " + orijinalMetin.length() + "]");
        System.out.println("  • Counting array     : O(k)         [k = 29 (Türkçe alfabe)]");
        System.out.println("  • Frekans sayma      : O(n)         [n = " + temizMetin.length() + "]");
        System.out.println("  • Sıralama           : O(k log k)   [k = " + sonuclar.size() + "]");
        System.out.println("  • TOPLAM             : O(n + k log k) ≈ O(n)");
        System.out.println();
        System.out.println("Alan Karmaşıklığı:");
        System.out.println("  • Counting array     : O(k)         [k = 29]");
        System.out.println("  • Sonuç listesi      : O(k)         [k = " + sonuclar.size() + "]");
        System.out.println("  • TOPLAM             : O(k) = O(1)  [sabit boyut]");
        System.out.println();
        System.out.println("💡 Verimlilik: Counting Sort, alfabedeki harf sayısı sabit");
        System.out.println("   olduğundan (k=29), lineer zamanda O(n) çalışır.");
        System.out.println();
        
        // Harf frekansları
        System.out.println("=".repeat(60));
        System.out.println("📈 HARF FREKANSLARI (EN SIK → EN AZ)");
        System.out.println("=".repeat(60));
        System.out.printf("%-6s %-8s %-12s %-8s %s\n", "Sıra", "Harf", "Kullanım", "Yüzde", "Görsel");
        System.out.println("-".repeat(60));
        
        int sira = 1;
        for (CharFrequency cf : sonuclar) {
            // Bar grafiği oluştur
            int barUzunluk = (int)(cf.getYuzde() / 2); // Her 2% için 1 karakter
            String bar = "█".repeat(Math.max(1, barUzunluk));
            
            System.out.printf("%-6d %-8c %-12d %%%5.2f   %s\n", 
                sira++, 
                cf.getHarf(), 
                cf.getFrekans(), 
                cf.getYuzde(),
                bar);
        }
        
        System.out.println("=".repeat(60));
        
        // En çok ve en az kullanılan harfler
        if (!sonuclar.isEmpty()) {
            System.out.println();
            System.out.println("🏆 EN ÇOK KULLANILAN: " + sonuclar.get(0).getHarf() + 
                             " (" + sonuclar.get(0).getFrekans() + " kez)");
            System.out.println("🔻 EN AZ KULLANILAN : " + 
                             sonuclar.get(sonuclar.size()-1).getHarf() + 
                             " (" + sonuclar.get(sonuclar.size()-1).getFrekans() + " kez)");
        }
    }
}