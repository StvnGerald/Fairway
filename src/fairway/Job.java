/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairway;
import java.util.ArrayList;

/**
 *
 * @author steve
 */
public class Job {
    private String title;
    private String description;
    private String companyName;
    private String kategori;
    private String lokasiKerja;
    private String hireDate;
    private String gaji;
    private String syaratPelamar;
    private String expireDate;
    private int limitPenerimaan;
    //
    private ArrayList<Jobseeker> applicants = new ArrayList<>();
    //
    public Job(String title, String kategori, String description, String companyName, String lokasiKerja, String syaratPelamar, String hireDate, String gaji, String expireDate, int limitPenerimaan) {
        this.title = title;
        this.description = description;
        this.companyName = companyName;
        this.kategori = kategori;
        this.lokasiKerja = lokasiKerja;
        this.hireDate = hireDate;
        this.gaji = gaji;
        this.syaratPelamar = syaratPelamar;
        this.expireDate = expireDate;
        this.limitPenerimaan = limitPenerimaan;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getKategori() {
        return kategori;
    }

    public String getSyaratPelamar() {
        return syaratPelamar;
    }

    public String getLokasiKerja() {
        return lokasiKerja;
    }

    public int getLimitPenerimaan() {
        return limitPenerimaan;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getGaji() {
        return gaji;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setLokasiKerja(String lokasiKerja) {
        this.lokasiKerja = lokasiKerja;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public void setSyaratPelamar(String syaratPelamar) {
        this.syaratPelamar = syaratPelamar;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public void setLimitPenerimaan(int limitPenerimaan) {
        this.limitPenerimaan = limitPenerimaan;
    }

    public void setApplicants(ArrayList<Jobseeker> applicants) {
        this.applicants = applicants;
    }
    
    public void displayJobDetails() {
        System.out.println("\n=== Detail Lowongan ===");
        System.out.println("Judul Pekerjaan   : " + title);
        System.out.println("Deskripsi         : " + description);
        System.out.println("Perusahaan        : " + companyName);
        System.out.println("Kategori          : " + kategori);
        System.out.println("Lokasi Kerja      : " + lokasiKerja);
        System.out.println("Tanggal Mulai     : " + hireDate);
        System.out.println("Gaji              : " + gaji);
        System.out.println("Syarat Pelamar    : " + syaratPelamar);       
        System.out.println("Batas Pendaftaran : " + expireDate);
        System.out.println("Kuota Penerimaan  : " + limitPenerimaan);
    }
    //
    public ArrayList<Jobseeker> getApplicants() {
        return applicants;
    }

    public void addApplicant(Jobseeker jobseeker) {
        applicants.add(jobseeker);
    }

    public void displayApplicants() {
        if (applicants.isEmpty()) {
            System.out.println("Belum ada pelamar.");
        } else {
            System.out.println("\n=== Daftar Pelamar ===");
            for (int i = 0; i < applicants.size(); i++) {
                System.out.println((i + 1) + ". " + applicants.get(i).getUsername());
            }
        }
    }
    //
}
