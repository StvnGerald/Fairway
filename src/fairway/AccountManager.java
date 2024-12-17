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
class AccountManager {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Job> jobListings = new ArrayList<>(); // Daftar semua lowongan pekerjaan

    public boolean register(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return false; // Username sudah ada
            }
        }
        users.add(user);
        return true;
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Login berhasil
            }
        }
        return null; // Login gagal
    }

    public void addJob(Job job) {
        jobListings.add(job);
    }

    public ArrayList<Job> getJobListings() {
        return jobListings;
    }
}
