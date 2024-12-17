/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairway;

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
    
}
