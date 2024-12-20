/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairway;

/**
 *
 * @author ACER
 */
public class CounterNilai {
    private int jumlahSoal;
    private int jumlahBenar;
    private boolean isLulus;
    private boolean isBukaMateri;

    public CounterNilai(int jumlahSoal, int jumlahBenar, boolean isLulus, boolean isBukaMateri) {
        this.jumlahSoal = 0;
        this.jumlahBenar = 0;
        this.isLulus = false;
        this.isBukaMateri = false;
    }
    public int countBenar() {
        return jumlahBenar;
    }

    public void countNilai() {

        if (jumlahSoal != 0) {
            double nilai = (double) jumlahBenar / jumlahSoal * 100;
            isLulus = nilai >= 75; 
        } else {
            isLulus = false;
        }
    }

    public void setIsLulus(boolean isLulus) {
        this.isLulus = isLulus;
    }

    public boolean getIsLulus() {
        return isLulus;
    }

    public int getJumlahSoal() {
        return jumlahSoal;
    }

    public void setJumlahSoal(int jumlahSoal) {
        this.jumlahSoal = jumlahSoal;
    }

    public int getJumlahBenar() {
        return jumlahBenar;
    }

    public void setJumlahBenar(int jumlahBenar) {
        this.jumlahBenar = jumlahBenar;
    }

    public boolean getIsBukaMateri() {
        return isBukaMateri;
    }

    public void setIsBukaMateri(boolean isBukaMateri) {
        this.isBukaMateri = isBukaMateri;
    }
    
}
