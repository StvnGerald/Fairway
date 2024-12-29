/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fairway;
import java.util.ArrayList;
/**
 *
 * @author steve
 */
public interface IAccountManagerBiz {
    public User login(String username, String password);
    public void addJob(Job job);
    public ArrayList<Job> getJobListings();
}
