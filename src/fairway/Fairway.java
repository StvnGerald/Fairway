/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fairway;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author steve
 */
public class Fairway {

    /**
     * @param args the command line arguments
     */
    static HashMap<String, ArrayList<Job>> SortedJob = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        User jobSeeker;

        // Menambahkan admin default untuk testing
        Admin admin = new Admin("admin", "admin123", "Admin");
        accountManager.register(admin);

        while (true) {
            try {
                System.out.println("\n=== Sistem Login ===");
                System.out.println("1. Daftar");
                System.out.println("2. Login");
                System.out.println("3. Login sebagai Admin");
                System.out.println("4. Keluar");
                System.out.print("Pilih opsi: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Mengkonsumsi newline

                switch (choice) {
                    case 1: // Pendaftaran
                        try {
                            System.out.println("\nPilih tipe akun:");
                            System.out.println("1. JobSeeker");
                            System.out.println("2. Instansi");
                            System.out.print("Pilih tipe: ");
                            int userType = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Masukkan username: ");
                            String username = scanner.nextLine().trim();
                            if (username.isEmpty()) {
                                System.out.println("Username tidak boleh kosong. Silakan coba lagi.");
                                continue; // Kembali ke awal loop
                            }

                            System.out.print("Masukkan password: ");
                            String password = scanner.nextLine().trim();
                            if (password.isEmpty()) {
                                System.out.println("Password tidak boleh kosong. Silakan coba lagi.");
                                continue; // Kembali ke awal loop
                            }

                            switch (userType) {
                                case 1:
                                    System.out.print("Masukkan CV (contoh: CV.pdf): ");
                                    String cv = scanner.nextLine();
                                    jobSeeker = new Jobseeker(username, password, cv);
                                    accountManager.register(jobSeeker);
                                    System.out.println("Pendaftaran berhasil sebagai JobSeeker!");
                                    break;
                                case 2:
                                    System.out.print("Masukkan nama perusahaan: ");
                                    String companyName = scanner.nextLine();
                                    if (companyName.isEmpty()) {
                                        System.out.println("Nama perusahaan tidak boleh kosong. Silakan coba lagi.");
                                        continue;
                                    }
                                    User instansi = new Instansi(username, password, companyName);
                                    SortedJob.put(companyName, new ArrayList<>());
                                    accountManager.register(instansi);
                                    System.out.println("Pendaftaran berhasil sebagai Instansi!");
                                    break;
                                default:
                                    System.out.println("Tipe user tidak valid!");
                                    break;
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (InputMismatchException e) {
                            System.out.println("Input tidak valid. Harus berupa angka.");
                            scanner.nextLine(); // Bersihkan input
                        }
                        break;

                    case 2: // Login
                        try {
                            System.out.print("Masukkan username: ");
                            String logUsername = scanner.nextLine().trim();
                            if (logUsername.isEmpty()) {
                                System.out.println("Username tidak boleh kosong. Silakan coba lagi.");
                                continue;
                            }

                            System.out.print("Masukkan password: ");
                            String logPassword = scanner.nextLine().trim();
                            if (logPassword.isEmpty()) {
                                System.out.println("Password tidak boleh kosong. Silakan coba lagi.");
                                continue;
                            }

                            User loggedInUser = accountManager.login(logUsername, logPassword);
                            System.out.println("Login berhasil sebagai " + loggedInUser.getRole() + "!");
                            if (loggedInUser instanceof Jobseeker) {
                                jobSeeker = (Jobseeker) loggedInUser;
                                jobseekerMenu(scanner, (Jobseeker) jobSeeker, accountManager);
                            } else if (loggedInUser instanceof Instansi) {
                                instansiMenu(scanner, (Instansi) loggedInUser, accountManager);
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (InputMismatchException e) {
                            System.out.println("Input tidak valid. Harus berupa teks.");
                            scanner.nextLine(); // Bersihkan input
                        }
                        break;

                    case 3: // Login sebagai Admin
                        System.out.print("Masukkan username admin: ");
                        String adminUsername = scanner.nextLine();
                        System.out.print("Masukkan password admin: ");
                        String adminPassword = scanner.nextLine();

                        User loggedInAdmin = accountManager.login(adminUsername, adminPassword);
                        if (loggedInAdmin != null && loggedInAdmin instanceof Admin) {
                            Admin adminUser = (Admin) loggedInAdmin;
                            System.out.println("Login berhasil sebagai Admin!");
                            adminMenu(scanner, adminUser);
                        } else {
                            System.out.println("Username atau password salah.");
                        }
                        break;

                    case 4: // Keluar
                        System.out.println("Terima kasih telah menggunakan fairway.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Pilihan tidak valid, coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harus berupa angka.");
                scanner.nextLine(); // Bersihkan input
            }
        }
    }

    private static void jobseekerMenu(Scanner scanner, Jobseeker jobSeeker, AccountManager accountManager) {
        try {
            OUTER:
            while (true) {
                System.out.println("\n=== Menu JobSeeker ===");
                System.out.println("1. Lihat lowongan");
                System.out.println("2. Lihat lamaran saya");
                System.out.println("4. Lihat lowongan berdasarkan instansi");
                System.out.println("5. Cari lowongan");
                System.out.println("3. Logout");
                System.out.print("Pilih opsi: ");
                int jobSeekerChoice = scanner.nextInt();
                scanner.nextLine();
                switch (jobSeekerChoice) {
                    case 1:
                        System.out.println("Lowongan tersedia:");
                        ArrayList<Job> jobListings = accountManager.getJobListings();
                        if (jobListings.isEmpty()) {
                            System.out.println("Belum ada lowongan.");
                        } else {
                            for (int i = 0; i < jobListings.size(); i++) {
                                Job job = jobListings.get(i);
                                System.out.println((i + 1) + ". " + job.getTitle() + " (oleh " + job.getCompanyName() + ")");
                            }
                        }
                        System.out.print("Pilih nomor lowongan untuk melihat detail (0 untuk kembali): ");
                        int jobChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (jobChoice == 0) {
                            continue;
                        } else if (jobChoice > 0 && jobChoice <= jobListings.size()) {
                            Job selectedJob = jobListings.get(jobChoice - 1);
                            selectedJob.displayJobDetails();

                            while (true) {
                                System.out.println("\n1. Lamar pekerjaan ini");
                                System.out.println("2. Kembali ke daftar lowongan");
                                System.out.print("Pilih opsi: ");
                                int detailChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (detailChoice == 1) {
                                    System.out.println("Berhasil melamar pekerjaan: " + selectedJob.getTitle());
                                    jobSeeker.applyJob(selectedJob.getTitle());
                                    break;
                                } else if (detailChoice == 2) {
                                    break;
                                } else {
                                    System.out.println("Pilihan tidak valid, coba lagi.");
                                }
                            }
                        } else {
                            System.out.println("Nomor lowongan tidak valid.");
                        }
                        break;
                    case 2:
                        jobSeeker.viewAppliedJobs();
                        break;
                    case 3:
                        break OUTER;
                    case 4:
                        System.out.print("Masukkan nama instansi: ");
                        String instansi = scanner.nextLine();
                        ArrayList<Job> jobsByInstansi = SortedJob.get(instansi);

                        if (jobsByInstansi == null || jobsByInstansi.isEmpty()) {
                            System.out.println("Tidak ada lowongan untuk instansi ini.");
                        } else {
                            System.out.println("Lowongan yang tersedia untuk instansi " + instansi + ":");
                            for (int i = 0; i < jobsByInstansi.size(); i++) {
                                Job job = jobsByInstansi.get(i);
                                System.out.println((i + 1) + ". " + job.getTitle() + " (oleh " + job.getCompanyName() + ")");
                            }

                        System.out.print("Pilih nomor lowongan untuk melihat detail (0 untuk kembali): ");
                        jobChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (jobChoice == 0) {
                            break;
                        } else if (jobChoice > 0 && jobChoice <= jobsByInstansi.size()) {
                            Job selectedJob = jobsByInstansi.get(jobChoice - 1);
                            selectedJob.displayJobDetails();

                            while (true) {
                                System.out.println("\n1. Lamar pekerjaan ini");
                                System.out.println("2. Kembali ke daftar lowongan");
                                System.out.print("Pilih opsi: ");
                                int detailChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (detailChoice == 1) {
                                    System.out.println("Berhasil melamar pekerjaan: " + selectedJob.getTitle());
                                    jobSeeker.applyJob(selectedJob.getTitle());
                                    break;
                                } else if (detailChoice == 2) {
                                    break;
                                } else {
                                    System.out.println("Pilihan tidak valid, coba lagi.");
                                }
                            }
                        } else {
                            System.out.println("Nomor lowongan tidak valid.");
                        }
                    }
                    break;

                    case 5:
                        System.out.print("Masukkan kategori (tekan Enter jika tidak ada): ");
                        String kategori = scanner.nextLine();
                        System.out.print("Masukkan nama pekerjaan (tekan Enter jika tidak ada): ");
                        String jobName = scanner.nextLine();
                        System.out.print("Masukkan gaji (tekan Enter jika tidak ada): ");
                        String gaji = scanner.nextLine();
                        System.out.print("Masukkan lokasi kerja (tekan Enter jika tidak ada): ");
                        String lokasiKerja = scanner.nextLine();

                        Searching searching = new Searching(kategori, jobName, gaji, lokasiKerja);
                        List<Job> filteredJobs = searching.cariLowongan(accountManager.getJobListings());

                        if (filteredJobs.isEmpty()) {
                            System.out.println("Tidak ada lowongan yang sesuai dengan kriteria Anda.");
                        } else {
                            System.out.println("Lowongan yang sesuai:");
                            for (int i = 0; i < filteredJobs.size(); i++) {
                                Job job = filteredJobs.get(i);
                                System.out.println((i + 1) + ". " + job.getTitle() + " (oleh " + job.getCompanyName() + ")");
                            }

                            System.out.print("Pilih nomor lowongan untuk melihat detail (0 untuk kembali): ");
                            jobChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (jobChoice == 0) {
                                break;
                            } else if (jobChoice > 0 && jobChoice <= filteredJobs.size()) {
                                Job selectedJob = filteredJobs.get(jobChoice - 1);
                                selectedJob.displayJobDetails();

                                while (true) {
                                    System.out.println("\n1. Lamar pekerjaan ini");
                                    System.out.println("2. Kembali ke daftar hasil filter");
                                    System.out.print("Pilih opsi: ");
                                    int detailChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    if (detailChoice == 1) {
                                        System.out.println("Berhasil melamar pekerjaan: " + selectedJob.getTitle());
                                        jobSeeker.applyJob(selectedJob.getTitle());
                                        break;
                                    } else if (detailChoice == 2) {
                                        break;
                                    } else {
                                        System.out.println("Pilihan tidak valid, coba lagi.");
                                    }
                                }
                            } else {
                                System.out.println("Nomor lowongan tidak valid.");
                            }
                        }
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Harus berupa angka.");
            scanner.nextLine(); // Bersihkan input
        }
    }

    private static void instansiMenu(Scanner scanner, Instansi instansi, AccountManager accountManager) {
        try {
            OUTER_1:
            while (true) {
                System.out.println("\n=== Menu Instansi ===");
                System.out.println("1. Buat lowongan");
                System.out.println("2. Lihat lowongan saya");
                System.out.println("3. Logout");
                System.out.print("Pilih opsi: ");
                int instansiChoice = scanner.nextInt();
                scanner.nextLine();
                switch (instansiChoice) {
                    case 1:
                        System.out.print("Masukkan judul lowongan: ");
                        String jobTitle = scanner.nextLine();
                        System.out.print("Masukkan deskripsi lowongan: ");
                        String jobDescription = scanner.nextLine();
                        System.out.print("Masukkan kategori lowongan: ");
                        String kategori = scanner.nextLine();
                        System.out.print("Masukkan lokasi kerja: ");
                        String lokasiKerja = scanner.nextLine();
                        System.out.print("Masukkan syarat pelamar : ");
                        String syaratPelamar = scanner.nextLine();
                        System.out.print("Masukkan waktu penerimaan: ");
                        String hireDate = scanner.nextLine();
                        System.out.print("Masukkan gaji: ");
                        String gaji = scanner.nextLine();
                        System.out.print("Masukkan maksimal waktu pelamaran: ");
                        String expireDate = scanner.nextLine();
                        System.out.print("Masukkan jumlah orang yang diterima: ");
                        int limitPenerimaan = scanner.nextInt();
                        Job job = new Job(jobTitle, kategori, jobDescription, instansi.getCompanyName(), lokasiKerja, syaratPelamar, hireDate, gaji, expireDate, limitPenerimaan);
                        accountManager.addJob(job);
                        SortedJob.get(instansi.getCompanyName()).add(job);
                        instansi.createJob(jobTitle, kategori, jobDescription, lokasiKerja, syaratPelamar, hireDate, gaji, expireDate, limitPenerimaan);
                        break;
                    case 2:
                        instansi.viewJobs();
                        System.out.print("Masukkan nomor lowongan untuk detail (0 untuk kembali): ");
                        int jobIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
/////
                        if (jobIndex >= 0 && jobIndex < instansi.getJobs().size()) {
                            Job selectedJob = instansi.getJobs().get(jobIndex);
                            while (true) {
                                selectedJob.displayJobDetails();
                                System.out.println("\n1. Edit Lowongan");
                                System.out.println("2. Lihat Pelamar");
                                System.out.println("3. Seleksi Pelamar");
                                System.out.println("4. Kembali");
                                System.out.print("Pilih opsi: ");
                                int detailChoice = scanner.nextInt();
                                scanner.nextLine();
                           
                                switch (detailChoice) {
                                    case 1:
                                        System.out.print("Masukkan judul lowongan baru: ");
                                        selectedJob.setTitle(scanner.nextLine());
                                        System.out.print("Masukkan deskripsi baru: ");
                                        selectedJob.setDescription(scanner.nextLine());
                                        System.out.print("Masukkan kategori baru: ");
                                        selectedJob.setKategori(scanner.nextLine());
                                        System.out.print("Masukkan lokasi kerja baru: ");
                                        selectedJob.setLokasiKerja(scanner.nextLine());
                                        System.out.print("Masukkan tanggal penerimaan baru: ");
                                        selectedJob.setHireDate(scanner.nextLine());
                                        System.out.println("Masukkan gaji baru: ");
                                        selectedJob.setGaji(scanner.nextLine());
                                        System.out.println("Masukkan syarat pelamar baru: ");
                                        selectedJob.setSyaratPelamar(scanner.nextLine());
                                        System.out.println("Masukkan batas pengajuan lamaran baru: ");
                                        selectedJob.setExpireDate(scanner.nextLine());
                                        System.out.println("Masukkan jumlah orang diterima yang baru: ");
                                        selectedJob.setLimitPenerimaan(scanner.nextInt());
                                        System.out.println("Lowongan berhasil diperbarui.");
                                        break;
                                    /*
                                    case 2:
                                        selectedJob.displayApplicants();
                                        break;
                                    case 3:
                                        if (selectedJob.getApplicants().size() <= selectedJob.getLimitPenerimaan()) {
                                            System.out.println("Semua pelamar diterima.");
                                    } else {
                                            System.out.println("Pilih pelamar untuk diterima:");
                                            for (int i = 0; i < selectedJob.getApplicants().size(); i++) {
                                                System.out.println((i + 1) + ". " + selectedJob.getApplicants().get(i).getUsername());
                                               }
                                            System.out.print("Masukkan nomor pelamar yang diterima (pisahkan dengan koma): ");
                                            String[] selectedIndexes = scanner.nextLine().split(",");
                                            System.out.println("Pelamar yang diterima:");
                                            for (String index : selectedIndexes) {
                                                int idx = Integer.parseInt(index.trim()) - 1;
                                                if (idx >= 0 && idx < selectedJob.getApplicants().size()) {
                                                    System.out.println("- " + selectedJob.getApplicants().get(idx).getUsername());
                                                }
                                            }
                                        }
                                        break;
*/
                                    case 4:
                                    break;
                                    default:
                                        System.out.println("Pilihan tidak valid.");
                                    }
                                if (detailChoice == 4) break;
                            }
                        } else if (jobIndex != -1) {
                            System.out.println("Nomor lowongan tidak valid.");
                            }
                        break;
/////
                    case 3:
                        break OUTER_1;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Harus berupa angka.");
            scanner.nextLine(); // Bersihkan input
        }
    }

    private static void adminMenu(Scanner scanner, Admin admin) {
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Lihat lowongan yang menunggu persetujuan");
            System.out.println("2. Keluar");
            System.out.print("Pilih opsi: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();

            switch (adminChoice) {
                case 1:
                    // Tampilkan daftar lowongan yang menunggu persetujuan
                    admin.listLowongan();

                    System.out.print("Masukkan nama lowongan untuk disetujui: ");
                    String namaLowongan = scanner.nextLine();
                    admin.approveLowongan(namaLowongan);
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

}
