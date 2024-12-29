package fairway;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountManager {
    private ArrayList<User> users = new ArrayList<>();
    private HashMap<String, ArrayList<Job>> jobListings = new HashMap<>(); // Daftar semua lowongan pekerjaan berdasarkan instansi

    // Pendaftaran user (Jobseeker atau Instansi)
    public boolean register(User user) throws IllegalArgumentException {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("Username sudah digunakan. Pilih username lain.");
            }
        }
        users.add(user);
        return true;
    }

    // Proses login untuk Jobseeker atau Instansi
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Login berhasil
            }
        }
        return null; // Login gagal
    }

    // Menambahkan lowongan pekerjaan untuk instansi
    public void addJob(Job job) {
        ArrayList<Job> jobs = jobListings.get(job.getCompanyName());
        if (jobs != null) {
            jobs.add(job);
        }
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
    public ArrayList<Job> getJobsByInstansi(String instansiName) {
        return jobListings.getOrDefault(instansiName, new ArrayList<>());
    }
}
