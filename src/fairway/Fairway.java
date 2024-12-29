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
                            String username = scanner.nextLine();
                            System.out.print("Masukkan password: ");
                            String password = scanner.nextLine();

                            switch (userType) {
                                case 1:
                                    System.out.print("Masukkan CV (contoh: CV.pdf): ");
                                    String cv = scanner.nextLine();
                                    jobSeeker = new Jobseeker(username, password, cv);
                                    if (accountManager.register(jobSeeker)) {
                                        System.out.println("Pendaftaran berhasil sebagai JobSeeker!");
                                    } else {
                                        System.out.println("Username sudah digunakan, coba lagi.");
                                    }
                                    break;
                                case 2:
                                    System.out.print("Masukkan nama perusahaan: ");
                                    String companyName = scanner.nextLine();
                                    User instansi = new Instansi(username, password, companyName);
                                    SortedJob.put(companyName, new ArrayList<>());
                                    if (accountManager.register(instansi)) {
                                        System.out.println("Pendaftaran berhasil sebagai Instansi!");
                                    } else {
                                        System.out.println("Username sudah digunakan, coba lagi.");
                                    }
                                    break;
                                default:
                                    System.out.println("Tipe user tidak valid!");
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Input tidak valid. Harus berupa angka.");
                            scanner.nextLine(); // Bersihkan input
                        }
                        break;

                    case 2: // Login
                        try {
                            System.out.print("Masukkan username: ");
                            String logUsername = scanner.nextLine();
                            System.out.print("Masukkan password: ");
                            String logPassword = scanner.nextLine();

                            User loggedInUser = accountManager.login(logUsername, logPassword);
                            if (loggedInUser != null) {
                                System.out.println("Login berhasil sebagai " + loggedInUser.getRole() + "!");
                                if (loggedInUser instanceof Jobseeker) {
                                    jobSeeker = (Jobseeker) loggedInUser;
                                    jobseekerMenu(scanner, (Jobseeker) jobSeeker, accountManager);
                                } else if (loggedInUser instanceof Instansi) {
                                    instansiMenu(scanner, (Instansi) loggedInUser, accountManager);
                                }
                            } else {
                                System.out.println("Username atau password salah.");
                            }
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
                        String instansi = scanner.nextLine();
                        for (Job J : SortedJob.get(instansi)) {
                            J.displayJobDetails();
                        }
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
                            for (Job job : filteredJobs) {
                                job.displayJobDetails();
                            }
                        }
                        break;
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
                        System.out.print("Masukkan syaratPelamar: ");
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
                        break;
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
