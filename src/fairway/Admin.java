package fairway;

import java.util.ArrayList;
import java.util.List;

// Representasi lowongan pekerjaan
class Lowongan {
    private String nama;
    private boolean approved;

    public Lowongan(String nama) {
        this.nama = nama;
        this.approved = false;
    }

    public String getNama() {
        return nama;
    }

    public boolean isApproved() {
        return approved;
    }

    public void approve() {
        this.approved = true;
    }

    @Override
    public String toString() {
        return "Lowongan{" + "nama='" + nama + '\'' + ", approved=" + approved + '}';
    }
}

public class Admin extends User {
    private String nama;
    private UserAddress address;
    private List<Lowongan> lowonganList;
    private List<String> penggunaList;

    public Admin(String nama, UserAddress address, String username, String password) {
        super(username, password);
        this.nama = nama;
        this.address = address;
        this.lowonganList = new ArrayList<>();
        this.penggunaList = new ArrayList<>();
    }

    public Admin(String username, String password, String nama) {
        super(username, password);
        this.nama = nama;
        this.lowonganList = new ArrayList<>();
        this.penggunaList = new ArrayList<>();
    }
    
    public void tambahLowongan(String namaLowongan) {
        lowonganList.add(new Lowongan(namaLowongan));
        System.out.println("Lowongan " + namaLowongan + " berhasil ditambahkan.");
    }

    public void approveLowongan(String namaLowongan) {
        for (Lowongan lowongan : lowonganList) {
            if (lowongan.getNama().equals(namaLowongan)) {
                lowongan.approve();
                System.out.println("Lowongan " + namaLowongan + " berhasil disetujui.");
                return;
            }
        }
        System.out.println("Lowongan " + namaLowongan + " tidak ditemukan.");
    }

    public void listLowongan() {
        for (Lowongan lowongan : lowonganList) {
            System.out.println(lowongan);
        }
    }

    // Metode lain tidak berubah
    public void tambahPengguna(String pengguna) {
        penggunaList.add(pengguna);
        System.out.println("Pengguna " + pengguna + " berhasil ditambahkan.");
    }

    public void maintainUser(String pengguna) {
        if (penggunaList.remove(pengguna)) {
            System.out.println("Pengguna " + pengguna + " berhasil dihapus.");
        } else {
            System.out.println("Pengguna " + pengguna + " tidak ditemukan.");
        }
    }

    public void setAddress(UserAddress address) {
        this.address = address;
        System.out.println("Alamat berhasil diperbarui ke: " + address);
    }

    public List<Lowongan> getLowonganList() {
        return lowonganList;
    }

    public List<String> getPenggunaList() {
        return penggunaList;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    Object getJobListings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
