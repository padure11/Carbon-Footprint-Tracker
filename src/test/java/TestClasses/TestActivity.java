package TestClasses;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import User.Category;
import User.Activity;
import User.User;

class ActivityTest {

    @Test
    void testGetterDate() {
    	Category.Transport transportCategory = Category.Transport.Car;
        LocalDate doneDate = LocalDate.now();
        double carbonFootprint = 12.5;

        Activity activity = new Activity(transportCategory, doneDate, carbonFootprint);
        
        assertEquals(doneDate, activity.getDoneDate());
    }
    
    @Test
    void testGetterCarbonFootprint() {
    	Category.Transport transportCategory = Category.Transport.Car;
        LocalDate doneDate = LocalDate.now();
        double carbonFootprint = 12.5;

        Activity activity = new Activity(transportCategory, doneDate, carbonFootprint);
        
        assertEquals(carbonFootprint, activity.getCarbonFootprint());
    }
	
    @Test
    void testConstructorForTransportCategory() {
        Category.Transport transportCategory = Category.Transport.Car;
        LocalDate doneDate = LocalDate.now();
        double carbonFootprint = 12.5;

        Activity activity = new Activity(transportCategory, doneDate, carbonFootprint);

        assertEquals(doneDate, activity.getDoneDate());
        assertEquals(carbonFootprint, activity.getCarbonFootprint());
        assertEquals(Category.Transport.Car, activity.getCategory());
    }

    @Test
    void testConstructorForFoodCategory() {
        Category.Food foodCategory = Category.Food.Ketto;
        LocalDate doneDate = LocalDate.now();
        double carbonFootprint = 5.0;

        Activity activity = new Activity(foodCategory, doneDate, carbonFootprint);

        assertEquals(doneDate, activity.getDoneDate());
        assertEquals(carbonFootprint, activity.getCarbonFootprint());
        assertEquals(Category.Food.Ketto, activity.getCategory());
        
    }

    @Test
    void testConstructorForEnergyCategory() {
        Category.Energy energyCategory = Category.Energy.Electricity;
        LocalDate doneDate = LocalDate.now();
        double carbonFootprint = 25.0;

        Activity activity = new Activity(energyCategory, doneDate, carbonFootprint);

        assertEquals(doneDate, activity.getDoneDate());
        assertEquals(carbonFootprint, activity.getCarbonFootprint());
        assertEquals(Category.Energy.Electricity, activity.getCategory());
    }
}

