/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairway;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Agung
 */
public class Searching {
    private String kategori;
    private String jobName;
    private String gaji;
    private String lokasiKerja;


    public Searching(String kategori, String jobName, String gaji, String lokasiKerja) {
        this.kategori = kategori;
        this.jobName = jobName;
        this.gaji = gaji;
        this.lokasiKerja = lokasiKerja;
    }


    public List<Job> cariLowongan(List<Job> jobList) {
        return filteringList(jobList);
    }

    private List<Job> filteringList(List<Job> jobList) {
        List<Job> filteredJobs = new ArrayList<>();
        for (Job job : jobList) {
            boolean match = true;

            if (kategori != null && !kategori.isEmpty() && !job.getKategori().equalsIgnoreCase(kategori)) {
                match = false;
            }

            if (jobName != null && !jobName.isEmpty() && !job.getTitle().toLowerCase().contains(jobName.toLowerCase())) {
                match = false;
            }

            if (gaji != null && !gaji.isEmpty() && !job.getGaji().equalsIgnoreCase(gaji)) {
                match = false;
            }

            if (lokasiKerja != null && !lokasiKerja.isEmpty() && !job.getLokasiKerja().equalsIgnoreCase(lokasiKerja)) {
                match = false;
            }

            if (match) {
                filteredJobs.add(job);
            }
        }
        return filteredJobs;
    }
}
