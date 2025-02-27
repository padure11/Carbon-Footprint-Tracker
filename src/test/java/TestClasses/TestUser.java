package TestClasses;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import User.User;
import User.Activity;
import User.Category;

class UserTest {
	
	@Test
	void testGetterAddActivities() {
		User user = new User("testuser", "testuser");
		
		Activity a = new Activity(Category.Transport.Car, LocalDate.now(), 12.5);
		Activity b = new Activity(Category.Transport.Bike, LocalDate.now(), 0);
		Activity c = new Activity(Category.Transport.Walk, LocalDate.now(), 0);
		
		
		List<Activity> act = new ArrayList <> ();
		
		act.add(a);
		act.add(b);
		act.add(c);
		
		user.addActivity(a);
		user.addActivity(b);
		user.addActivity(c);
		
		assertEquals(act, user.getActivities());
	}

    @Test
    void testConstructor() {
        User user = new User("testUser", "testPassword");

        assertEquals("testUser", user.getUsername());
    }

    @Test
    void testAddActivity() {
        
    	User user = new User("testUser", "testPassword");
        
        Activity activity = new Activity(Category.Transport.Car, LocalDate.now(), 12.5);

        user.addActivity(activity);
        
        List<Activity> activities = user.getActivities(); 

        assertNotNull(activities, "Lista de activități nu ar trebui să fie null.");
        assertEquals(1, activities.size(), "Lista de activități ar trebui să conțină exact 1 activitate.");
        assertEquals(activity, activities.get(0), "Activitatea adăugată nu coincide cu cea din listă.");
    }

    @Test
    void testGetCarbonFootprintForDate() {
        User user = new User("testUser", "testPassword");

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        
        Activity activity1 = new Activity(Category.Energy.Gas, today, 10.0);
        Activity activity2 = new Activity(Category.Transport.Car, today, 20.5);
        Activity activity3 = new Activity(Category.Energy.Solar, tomorrow, 5.0);

        user.addActivity(activity1);
        user.addActivity(activity2);
        user.addActivity(activity3);

        
        double todayCarbonFootprint = user.getCarbonFootprintForDate(today);
        assertEquals(30.5, todayCarbonFootprint);

        
        double tomorrowCarbonFootprint = user.getCarbonFootprintForDate(tomorrow);
        assertEquals(5.0, tomorrowCarbonFootprint);

        
        double noActivityFootprint = user.getCarbonFootprintForDate(today.minusDays(1));
        assertEquals(0.0, noActivityFootprint);
    }
}

