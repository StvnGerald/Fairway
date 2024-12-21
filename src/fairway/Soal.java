package fairway;

import java.util.List;

public class Soal {
    private String soal;
    private boolean jawabanBenar;
    private List<String> opsi;

    public Soal(String soal, boolean jawabanBenar, List<String> opsi) {
        this.soal = soal;
        this.jawabanBenar = jawabanBenar;
        this.opsi = opsi;
    }
    public String getSoal() {
        return soal;
    }
    public void setSoal(String soal) {
        this.soal = soal;
    }
    public boolean getJawabanBenar() {
        return jawabanBenar;
    }
    public void setJawabanBenar(boolean jawabanBenar) {
        this.jawabanBenar = jawabanBenar;
    }
    public List<String> getOpsi() {
        return opsi;
    }
    public void setOpsi(List<String> opsi) {
        this.opsi = opsi;
    }
    
}
