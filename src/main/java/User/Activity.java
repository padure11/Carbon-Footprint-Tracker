package User;

import java.time.LocalDate;

import User.Category;

/**
 * Clasa ce modeleaza structur unei activitatii facute de utilizator
 */
public class Activity {

	private Object category;
	private LocalDate doneDate;
	private double carbonFootprint;
	private int id_user;
	
	
	/**
	 * Constructor al clasei Activity creea o activitate din categoria Transport
	 * @param category            categoria activitatii;
	 * @param doneDate			  data cand a fost facuta
	 * @param carbonFootprint     emisii de carbon
	 */
	public Activity(Category.Transport category, LocalDate doneDate, double carbonFootprint) {
		this.doneDate = doneDate;
		this.category = category;
		this.carbonFootprint = carbonFootprint;
	}
	
	/**
	 * Constructor al clasei Activity creea o activitate din categoria Food
	 * @param category            categoria activitatii;
	 * @param doneDate			  data cand a fost facuta
	 * @param carbonFootprint     emisii de carbon
	 */
	public Activity(Category.Food category, LocalDate doneDate, double carbonFootprint) {
		this.doneDate = doneDate;
		this.category = category;
		this.carbonFootprint = carbonFootprint;
	}
	
	/**
	 * Constructor al clasei Activity creea o activitate din categoria Energy
	 * @param category            categoria activitatii;
	 * @param doneDate			  data cand a fost facuta
	 * @param carbonFootprint     emisii de carbon
	 */
	public Activity(Category.Energy category, LocalDate doneDate, double carbonFootprint) {
		this.doneDate = doneDate;
		this.category = category;
		this.carbonFootprint = carbonFootprint;
	}
	
	@Override
	public String toString() {
		return "[category=" + category + ", doneDate=" + doneDate + " cu emisiile=" + carbonFootprint + "]";
	}
	
	/**
	 * Getter pentru atributul doneDate
	 * @return    data in care a fost facuta o activitate
	 */
	public LocalDate getDoneDate() {
		return doneDate;
	}
	
	/**
	 * Getter pentru atribitul carbonFootprint
	 * @return    emisii de carbon
	 */
	public double getCarbonFootprint() {
		return carbonFootprint;
	}
	
	/**
	 * Getter pentru atribitul category
	 * @return    categoria din care face parte activitatea
	 */
	public Object getCategory() {
		return category;
	}
	
	
	/**
	 * Metoda ce imi returneaza numele unei categorii 
	 * din care face parte o activitate
	 * @return		numele categoriei din care face parte o activitate
	 */
	public String getCategoryName() {
		if (category instanceof Category.Transport) {
			return "Transport";
		}
		else if (category instanceof Category.Energy) {
			return "Energy";
		}
		else {
			return "Food";
		}
	}
	
	/**
	 * Metoda ce returneaza numele activitatii
	 * @return     numele activiatii
	 */
	public String getActivityName() {
		if (category instanceof Category.Transport) {
	        return ((Category.Transport) category).name();
	    } else if (category instanceof Category.Energy) {
	        return ((Category.Energy) category).name();
	    } else if (category instanceof Category.Food) {
	        return ((Category.Food) category).name();
	    } else {
	        return "Unknown Activity";
	    }
	}
}

