package stg;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    
    public static String dosyaOku(String dosyaYolu) throws IOException {
        StringBuilder icerik = new StringBuilder();
        int satirSayisi = 0;
        
        // UTF-8 ile türkçe karakter okuma imkanı
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(dosyaYolu), StandardCharsets.UTF_8)) {
            
            String satir;
            while ((satir = reader.readLine()) != null) {
                satirSayisi++;
                icerik.append(satir).append(" ");
            }
        }
        
        // içeriğin boş olma ihtimaline karşı
        if (icerik.length() == 0) {
            
            try (BufferedReader reader = Files.newBufferedReader(
                    Paths.get(dosyaYolu), Charset.forName("Windows-1254"))) {
                
                String satir;
                while ((satir = reader.readLine()) != null) {
                    satirSayisi++;
                    icerik.append(satir).append(" ");
                }
            }
        }
        
        // 2. senaryoda da içeriğin boş olması durumunda alınan son önlem
        if (icerik.length() == 0) {
            try (BufferedReader reader = Files.newBufferedReader(
                    Paths.get(dosyaYolu), Charset.forName("ISO-8859-9"))) {
                
                String satir;
                while ((satir = reader.readLine()) != null) {
                    satirSayisi++;
                    icerik.append(satir).append(" ");
                }
            }
        }
        
        return icerik.toString();
    }
}
