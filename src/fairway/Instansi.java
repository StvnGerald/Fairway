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
class Instansi extends User {
    private String companyName;
    private ArrayList<Job> jobs = new ArrayList<>(); // Daftar pekerjaan yang dibuat oleh instansi

    public Instansi(String username, String password, String companyName) {
        super(username, password);
        this.companyName = companyName;
    }

    @Override
    public String getRole() {
        return "Instansi";
    }

    public String getCompanyName() {
        return companyName;
    }

    public void createJob(String jobTitle, String kategori, String jobDescription, String lokasiKerja, String syaratPelamar, String hireDate, String gaji, String expireDate, int limitPenerimaan) {
        jobs.add(new Job(jobTitle, kategori, jobDescription, this.companyName, lokasiKerja, syaratPelamar, hireDate, gaji, expireDate, limitPenerimaan));
        System.out.println("Lowongan pekerjaan berhasil dibuat: " + jobTitle);
    }

    public void viewJobs() {
        System.out.println("Lowongan pekerjaan yang dibuat:");
        if (jobs.isEmpty()) {
            System.out.println("Belum ada lowongan.");
        } else {
            for (Job job : jobs) {
                System.out.println("- " + job.getTitle() + " (oleh " + job.getCompanyName() + ")");
            }
        }
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }
}
