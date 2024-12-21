/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairway;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class Admin extends User {
    private String nama;
    private UserAddress address;
    // Koleksi data lowongan dan pengguna
    private List<String> lowonganList;
    private List<String> penggunaList;

    public Admin(String nama, UserAddress address, List<String> lowonganList, List<String> penggunaList, String username, String password) {
        super(username, password);
        this.nama = nama;
        this.address = address;
        this.lowonganList = new ArrayList<>();
        this.penggunaList = new ArrayList<>();
    }
    public void tambahLowongan(String lowongan) {
        lowonganList.add(lowongan);
        System.out.println("Lowongan " + lowongan + " berhasil ditambahkan.");
    }
    public void tambahPengguna(String pengguna) {
        penggunaList.add(pengguna);
        System.out.println("Pengguna " + pengguna + " berhasil ditambahkan.");
    }

    // Maintain pengguna (contoh menghapus pengguna)
    public void maintainUser(String pengguna) {
        if (penggunaList.remove(pengguna)) {
            System.out.println("Pengguna " + pengguna + " berhasil dihapus.");
        } else {
            System.out.println("Pengguna " + pengguna + " tidak ditemukan.");
        }
    }

    // Set alamat baru
    public void setAddress(UserAddress address) {
        this.address = address;
        System.out.println("Alamat berhasil diperbarui ke: " + address);
    }

    // Getter untuk data
    public List<String> getLowonganList() {
        return lowonganList;
    }

    public List<String> getPenggunaList() {
        return penggunaList;
    }

    @Override
    public String getRole() {
        return "Admin";
    }
    
}
