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
class Jobseeker extends User {
    private String cv; // cv jobseeker
    private ArrayList<String> appliedJobs = new ArrayList<>(); // Daftar lowongan yang udah dilamar

    public Jobseeker(String username, String password) {
        super(username, password);
    }
    
    public Jobseeker(String username, String password, String cv) {
        super(username, password);
        this.cv = cv;
    }

    @Override
    public String getRole() {
        return "JobSeeker";
    }
    
    public void setCV(String cv) {
        this.cv = cv;
    }

    public String getCv() {
        return cv;
    }

    // Lamar kerja
    public void applyJob(String jobTitle) {
        appliedJobs.add(jobTitle);
        System.out.println("Berhasil melamar pekerjaan: " + jobTitle);
    }

    //Melihat lsit yang terlamar
    public void viewAppliedJobs() {
        System.out.println("Pekerjaan yang sudah dilamar:");
        if (appliedJobs.isEmpty()) {
            System.out.println("Belum ada lamaran.");
        } else {
            for (int i = 0; i < appliedJobs.size(); i++) {
                System.out.println((i + 1) + ". " + appliedJobs.get(i));
            }
        }
    }
    //
    public void applyJob(Job job) { //Overloading
        job.addApplicant(this);
        System.out.println("Berhasil melamar pekerjaan: " + job.getTitle());
    }
    //
}
