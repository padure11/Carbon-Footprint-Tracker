package DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import User.User;
import User.Activity;
import User.Category;

public class JdbcConnector {
    
    /**
     * Face conexiunea la baza de date.
     * 
     * @return Conexiunea la baza de date
     */
    public static Connection JdbcConnector() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javap", "root", "admin");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Obtine toti utilizatorii din baza de date.
     * 
     * @return ResultSet care contine utilizatorii
     */
    public static ResultSet GetUsers() {
        
        try {
            Connection connection = JdbcConnector();
            
            Statement  statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Verifica daca un utilizator exista in baza de date pe baza username-ului si parolei.
     * 
     * @param username numele utilizatorului
     * @param password parola utilizatorului
     * @return true daca utilizatorul exista, false in caz contrar
     */
    public static boolean Login(String username, String password) {
        ResultSet resultSet = GetUsers();
        try {
            while(resultSet.next()) {
                if (resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Obtine un utilizator din baza de date pe baza username-ului si parolei.
     * 
     * @param username numele utilizatorului
     * @param password parola utilizatorului
     * @return utilizatorul gasit sau null daca nu exista
     */
    public static User GetUser(String username, String password) {
        ResultSet resultSet = GetUsers();
        try {
            while(resultSet.next()) {
                String usr = resultSet.getString("username");
                String psr = resultSet.getString("password");
                int id = resultSet.getInt("id");
                if (usr.equals(username) && psr.equals(password)) {
                        User u = new User(usr, psr, id);
                        return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    /**
     * Obtine top 10 utilizatori cu cele mai mici emisii de carbon.
     * 
     * @return un map cu username-ul utilizatorilor si emisiile acestora
     */
    public static Map<String, Double> GetLeaderBoard() {
        Connection connection = JdbcConnector();
        Map leaderboard = new HashMap<> ();
        try {
            Statement  statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT u.username, SUM(a.carbon_footprint) AS total_carbon_footprint FROM users u JOIN Activities a ON u.id = a.id_user GROUP BY u.username ORDER BY total_carbon_footprint ASC LIMIT 10");
            
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                double crb = resultSet.getDouble("total_carbon_footprint");
                leaderboard.put(username, crb);
            }
            
            List<Map.Entry<String, Double>> sortedList = new ArrayList<>(leaderboard.entrySet());
            sortedList.sort(Map.Entry.comparingByValue()); 
            
            Map<String, Double> sortedLeaderboard = new HashMap<>();
            for (Map.Entry<String, Double> entry : sortedList) {
                sortedLeaderboard.put(entry.getKey(), entry.getValue());
            }
            System.out.println(sortedLeaderboard);
            return leaderboard;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Adauga un nou utilizator in baza de date.
     * 
     * @param username numele utilizatorului
     * @param password parola utilizatorului
     * @param email adresa de email a utilizatorului
     */
    public static void WriteUser(String username, String password, String email) {
        String insertQuery = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        
        try (Connection connection = JdbcConnector(); 
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
                
            int rowsAffected = preparedStatement.executeUpdate();
                
            if (rowsAffected > 0) {
                System.out.println("User inserted successfully.");
            } else {
                System.out.println("Error inserting user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Adauga o activitate a unui utilizator in baza de date.
     * 
     * @param user utilizatorul care a realizat activitatea
     * @param act activitatea realizata de utilizator
     */
    public static void WriteActivity(User user, Activity act) {
        String insertQuery = "INSERT INTO activities (id_user, categorie, done_date, carbon_footprint) VALUES (?, ?, ?, ?)";
        
        System.out.println("salut");
        try (Connection connection = JdbcConnector(); 
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                
            System.out.println(user.getId() + act.getCategoryName() + Date.valueOf(act.getDoneDate()) + act.getCarbonFootprint());
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, act.getCategoryName());
            preparedStatement.setDate(3, Date.valueOf(act.getDoneDate()));
            preparedStatement.setDouble(4, act.getCarbonFootprint());
                
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected);
                
            if (rowsAffected > 0) {
                System.out.println("Activity inserted successfully.");
            } else {
                System.out.println("Error inserting activity.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Obtine provocarea zilnica pentru utilizator.
     * 
     * @return textul provocarii zilnice
     */
    public static String getDailyChallenge() {
        String challenge = "";
        String query = "SELECT challenge_text FROM daily_challenges WHERE date = ?";

        try (Connection connection = JdbcConnector();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            LocalDate today = LocalDate.now();
            System.out.println(today);
            preparedStatement.setDate(1, Date.valueOf(today));

            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                challenge = resultSet.getString("challenge_text");
            } else {
                challenge = "No challenge available for today.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return challenge;
    }
    
    /**
     * Marcheaza o provocare ca fiind completata de un utilizator.
     * 
     * @param userId ID-ul utilizatorului
     * @param challengeText textul provocarii care a fost completata
     * @return true daca provocarea a fost marcata cu succes, false in caz contrar
     */
    public static boolean markChallengeAsCompleted(int userId, String challengeText) {
        String query = "INSERT INTO user_challenges (user_id, challenge_id) " +
                       "SELECT ?, id FROM daily_challenges WHERE challenge_text = ?";
        try (Connection conn = JdbcConnector(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, challengeText);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Obtine lista de provocari completate de un utilizator.
     * 
     * @param userId ID-ul utilizatorului
     * @return un vector de string-uri care contin textul provocarilor completate
     */
    public static String[] getCompletedChallenges(int userId) {
        String query = "SELECT challenge_text FROM daily_challenges " +
                       "JOIN user_challenges ON daily_challenges.id = user_challenges.challenge_id " +
                       "WHERE user_challenges.user_id = ? ORDER BY user_challenges.completed_at DESC";
        try (Connection conn = JdbcConnector(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            List<String> challenges = new ArrayList<>();
            while (rs.next()) {
                challenges.add(rs.getString("challenge_text"));
            }
            return challenges.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
    
    /**
     * Obtine emisiile de carbon curente pentru un utilizator.
     * 
     * @param userId ID-ul utilizatorului
     * @return totalul emisiilor de carbon realizate de utilizator in ziua curenta
     */
    public static double getCurrentEmissions(int userId) {
        double totalEmissions = 0.0;
        String query = "SELECT SUM(carbon_footprint) AS total_emissions FROM Activities WHERE id_user = ? AND done_date = CURRENT_DATE";

        try (Connection connection = JdbcConnector(); 
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, userId); 

            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                totalEmissions = resultSet.getDouble("total_emissions");  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalEmissions;
    }
}
