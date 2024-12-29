package fairway;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountManager {
    private ArrayList<User> users = new ArrayList<>();
    private HashMap<String, ArrayList<Job>> jobListings = new HashMap<>(); // Daftar semua lowongan pekerjaan berdasarkan instansi

    // Pendaftaran user (Jobseeker atau Instansi)
    public boolean register(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User tidak boleh null.");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong.");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong.");
        }

        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("Username sudah digunakan. Pilih username lain.");
            }
        }
        users.add(user);

        // Jika user adalah instansi, inisialisasi daftar pekerjaan
        if (user instanceof Instansi) {
            Instansi instansi = (Instansi) user;
            jobListings.put(instansi.getCompanyName(), new ArrayList<>());
        }

        return true;
    }

    // Proses login untuk Jobseeker, Instansi, atau Admin
    public User login(String username, String password) throws IllegalArgumentException {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username atau password tidak boleh kosong.");
        }
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Login berhasil
            }
        }
        throw new IllegalArgumentException("Login gagal. Username atau password salah.");
    }

    // Menambahkan lowongan pekerjaan untuk instansi
    public void addJob(Job job) throws IllegalArgumentException {
        if (job == null || job.getCompanyName() == null || job.getCompanyName().isEmpty()) {
            throw new IllegalArgumentException("Job atau nama perusahaan tidak valid.");
        }

        ArrayList<Job> jobs = jobListings.get(job.getCompanyName());
        if (jobs == null) {
            throw new IllegalArgumentException("Perusahaan tidak ditemukan. Pastikan perusahaan terdaftar.");
        }

        jobs.add(job);
    }

    // Mengambil daftar lowongan pekerjaan untuk Jobseeker
    public ArrayList<Job> getJobListings() {
        ArrayList<Job> allJobs = new ArrayList<>();
        // Menggabungkan semua lowongan dari berbagai instansi
        for (ArrayList<Job> jobs : jobListings.values()) {
            allJobs.addAll(jobs);
        }
        return allJobs;
    }

    // Menampilkan lowongan berdasarkan instansi tertentu
    public ArrayList<Job> getJobsByInstansi(String instansiName) throws IllegalArgumentException {
        if (instansiName == null || instansiName.isEmpty()) {
            throw new IllegalArgumentException("Nama instansi tidak boleh kosong.");
        }
        return jobListings.getOrDefault(instansiName, new ArrayList<>());
    }
}
