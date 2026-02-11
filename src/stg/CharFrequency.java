package stg;

public class CharFrequency implements Comparable<CharFrequency> {
    private char harf;
    private int frekans;
    private double yuzde;
    
    public CharFrequency(char harf, int frekans, double yuzde) {
        this.harf = harf;
        this.frekans = frekans;
        this.yuzde = yuzde;
    }
    
    public char getHarf() {
        return harf;
    }
    
    public int getFrekans() {
        return frekans;
    }
    
    public double getYuzde() {
        return yuzde;
    }
    
    @Override
    public int compareTo(CharFrequency other) {
        // en sık kullanılan harften en az kullanılan harfe sıralama
        return Integer.compare(other.frekans, this.frekans);
    }
    
    @Override
    public String toString() {
        return String.format("%c: %d kez (%%%5.2f)", harf, frekans, yuzde);
    }
}
