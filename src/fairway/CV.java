/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairway;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ACER
 */
public class CV {
    private String name;
    private String birthDate;
    private String riwayatPendidikan;
    private String departemen;
    private String riwayatEXP;
    private String deskripsi;

    public CV(String name, String birthDate, String riwayatPendidikan, String departemen, String riwayatEXP, String deskripsi) {
        this.name = name;
        this.birthDate = birthDate;
        this.riwayatPendidikan = riwayatPendidikan;
        this.departemen = departemen;
        this.riwayatEXP = riwayatEXP;
        this.deskripsi = deskripsi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getRiwayatPendidikan() {
        return riwayatPendidikan;
    }

    public void setRiwayatPendidikan(String riwayatPendidikan) {
        this.riwayatPendidikan = riwayatPendidikan;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public String getRiwayatEXP() {
        return riwayatEXP;
    }

    public void setRiwayatEXP(String riwayatEXP) {
        this.riwayatEXP = riwayatEXP;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
        public int countAge() {
        if (birthDate == null || birthDate.isEmpty()) {
            return 0; // Jika tanggal lahir belum diisi
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birth = LocalDate.parse(birthDate, formatter);
        LocalDate now = LocalDate.now();
        return Period.between(birth, now).getYears();
    }
        public String toString() {
        return "CV {" +
                "Nama='" + name + '\'' +
                ", Tanggal Lahir='" + birthDate + '\'' +
                ", Umur='" + countAge() + '\'' +
                ", Riwayat Pendidikan='" + riwayatPendidikan + '\'' +
                ", Departemen='" + departemen + '\'' +
                ", Riwayat Pengalaman='" + riwayatEXP + '\'' +
                ", Deskripsi='" + deskripsi + '\'' +
                '}';
    }

}
