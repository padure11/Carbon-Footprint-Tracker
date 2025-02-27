package User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Encrypt.Encryption;

import User.Activity;


/**
 * Clasa ce modeleaza conceptul de utilizator in aplicatie
 */
public class User {

	private int id;
	private String username;
	private String password;
	private String email;
	private List<Activity> activities;
	
	public User() {};
	
	/**
	 * Constructorul clasei User. Ce va face un obiect de tip user 
	 * Folosit pentru a intra in aplicatie 
	 * @param username     numele utilizatorului 
	 * @param password	   parola utilizatorului
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = Encryption.encrypt(password);
		this.id = Encryption.randomIdUser();
		this.activities = new ArrayList <> ();
	}
	
	/**
	 * Constructorul clasei User. Ce va face un obiect de tip user 
	 * Folosit pentru a updata activitatiile unui utilizator
	 * @param username     numele utilizatorului
	 * @param password     parola utilizatorului
	 * @param id           id-ul  utilizatorului
	 */
	public User(String username, String password, int id) {
		this.username = username;
		this.password = password;
		this.activities = new ArrayList <> ();
		this.id = id;
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("User: ");
		output.append(username);
		output.append(" a facut activitatiile: ");
		for (Activity a: activities) {
			output.append(a.toString());
		}
		return output.toString();
	}
	
	
	/**
	 * Metoda ce adauga in activitatii in lista de activitati a user-ului
	 * @param activity     activitatea care este adaugata
	 */
	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}
	
	/**
	 * Getter pentru atributul username
	 * @return     numele utilizatorului
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Setter pentru atributul username
	 * @param Username   numele utilizatorului
	 */
	public void setUsername(String Username) {
		this.username = Username;
	}
	
	/**
	 * Getter pentru atributul id
	 * @return     id-ul utilizatorului
	 */
	public int getId () {
		return id;
	}
	
	/**
	 * Getter pentru atributul password
	 * @return     password-ul utilizatorului
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Getter pentru atributul activities
	 * @return     lista de activitati
	 */
	public List<Activity> getActivities() {
		return activities;
	}
	
	/**
	 * Functie ce calculeaza totalul emiisilor de carbon 
	 * din activitatiile user-ului dintr-o anumita 
	 * data calendaristica
	 * @param date      data 
	 * @return			totalul emiisilor de carbon dintr-o anumita zi
	 */
	public double getCarbonFootprintForDate(LocalDate date) {
		double carbonFootprint = 0;
		for (Activity a : activities) {
			if (a.getDoneDate().equals(date)) {
				carbonFootprint = carbonFootprint + a.getCarbonFootprint();
			}
		}
		return carbonFootprint;
	}
	
	/**
	 * Metoda ce determina totalul emisiilor de carbon
	 * din activitatill user-ului
	 * @return           totalul emisiilor de carbon 
	 */
	public double getTotalCarbonFootprint() {
		double totalCarbonFootprint = 0;
		for (Activity a : activities) {
			totalCarbonFootprint = totalCarbonFootprint + a.getCarbonFootprint();
		}
		return totalCarbonFootprint;
	}

}
