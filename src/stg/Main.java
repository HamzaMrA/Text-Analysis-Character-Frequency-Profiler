package stg;

import java.util.List;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("COUNTING SORT - HARF FREKANS ANALİZİ");
        System.out.println("Algoritma Analizi Projesi - Bilgisayar Mühendisliği");
        System.out.println("=".repeat(60));
        System.out.println();

        // Dosya yolunu buraya girin
        String dosyaYolu = "C:\\Users\\yhamz\\OneDrive\\Desktop\\finalD.txt"; // Kendi dosya yolunuzu yazın
        
        // Çalışma dizinini göster
        System.out.println("💻 Çalışma dizini: " + System.getProperty("user.dir"));
        System.out.println();
        
        try {
            // Dosya kontrolü
            File dosya = new File(dosyaYolu);
            System.out.println("📂 Dosya aranıyor: " + dosyaYolu);
            System.out.println("   Tam yol: " + dosya.getAbsolutePath());
            
            if (!dosya.exists()) {
                System.err.println("❌ HATA: Dosya bulunamadı!");
                System.err.println("\n🔍 Lütfen kontrol edin:");
                System.err.println("  1. Dosya adı doğru mu? (büyük/küçük harf duyarlı)");
                System.err.println("  2. Dosya uzantısı .txt mi? (.txt.txt olabilir)");
                System.err.println("  3. Dosya bu konumda mı: " + dosya.getAbsolutePath());
                System.err.println("\n💡 Alternatif: Tam yolu kullanın");
                System.err.println("   Örnek: C:/Users/KullaniciAdi/Desktop/don.txt");
                return;
            }
            
            if (!dosya.canRead()) {
                System.err.println("❌ HATA: Dosya okunamıyor (izin sorunu)");
                return;
            }
            
            System.out.println("✓ Dosya bulundu!");
            
            // 1. Dosyayı oku
            String metin = FileReader.dosyaOku(dosyaYolu);
            System.out.println("✓ Dosya başarıyla okundu!");
            System.out.println("  Toplam karakter: " + metin.length());
            System.out.println();

            // 2. Metni analiz et
            TextAnalyzer analyzer = new TextAnalyzer(metin);
            
            // 3. Counting Sort uygula ve sonuçları al
            List<CharFrequency> sonuclar = analyzer.analizeEt();
            
            // 4. Sonuçları göster
            analyzer.sonuclariGoster(sonuclar);
            
        } catch (Exception e) {
            System.err.println("❌ HATA: " + e.getMessage());
            System.err.println("\nDosya yolu kontrolü:");
            System.err.println("- Dosya adı: " + dosyaYolu);
            System.err.println("- Dosya .txt formatında olmalı");
            System.err.println("- Dosya projenizle aynı klasörde olmalı");
            System.err.println("  (veya tam yolu yazın: C:/Users/.../metin.txt)");
        }
    }
}
