package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import User.User;
import User.Category;
import User.Activity;
import DBManager.JdbcConnector;

public class ActivityScreen extends JPanel {
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> activityComboBox;
    private JTextField detailsTextField;
    private JButton submitButton;
    private JLabel challengeLabel;
    private JTextArea challengeDetailsTextArea;
    private User user;
    private JLabel lblNewLabel;

    public ActivityScreen(User user) {
        setBackground(new Color(245, 245, 245)); 
        setSize(980, 502);
        this.user = user;
        setLayout(null);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(109, 83, 150, 30);
        categoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        categoryLabel.setForeground(new Color(0, 51, 102));  
        add(categoryLabel);

        String[] categories = {"Transport", "Energy", "Food"};
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setBounds(219, 83, 250, 35);
        categoryComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        categoryComboBox.setBackground(new Color(240, 240, 240)); 
        categoryComboBox.setForeground(new Color(0, 51, 102));  
        categoryComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));  
        add(categoryComboBox);

        JLabel activityLabel = new JLabel("Activity:");
        activityLabel.setBounds(109, 137, 150, 30);
        activityLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        activityLabel.setForeground(new Color(0, 51, 102));
        add(activityLabel);

        activityComboBox = new JComboBox<>();
        activityComboBox.setBounds(219, 137, 250, 35);
        activityComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        activityComboBox.setBackground(new Color(240, 240, 240));
        activityComboBox.setForeground(new Color(0, 51, 102));
        activityComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        add(activityComboBox);

        JLabel detailsLabel = new JLabel("Details:");
        detailsLabel.setBounds(109, 191, 150, 30);
        detailsLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        detailsLabel.setForeground(new Color(0, 51, 102));
        add(detailsLabel);

        detailsTextField = new JTextField();
        detailsTextField.setBounds(219, 191, 250, 35);
        detailsTextField.setForeground(Color.GRAY);
        detailsTextField.setText("Enter details...");
        detailsTextField.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        detailsTextField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));  // Light border
        add(detailsTextField);
        
        detailsTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (detailsTextField.getText().equals("Enter details...")) {
                    detailsTextField.setText("");
                    detailsTextField.setForeground(Color.BLACK);
                    detailsTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (detailsTextField.getText().isEmpty()) {
                    detailsTextField.setForeground(Color.GRAY);
                    detailsTextField.setFont(new Font("Segoe UI", Font.ITALIC, 16));
                    detailsTextField.setText("Enter details...");
                }
            }
        });

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(181, 248, 150, 40);
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        submitButton.setBackground(new Color(0, 123, 255)); // Blue color
        submitButton.setForeground(Color.WHITE); // White text
        submitButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));  // Blue border
        submitButton.setFocusPainted(false);  // Remove focus border
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = (String) categoryComboBox.getSelectedItem();
                String activity = (String) activityComboBox.getSelectedItem();
                String details = detailsTextField.getText();

                JOptionPane.showMessageDialog(ActivityScreen.this,
                        "You selected:\n" +
                                "Category: " + category + "\n" +
                                "Activity: " + activity + "\n" +
                                "Details: " + details);

                JdbcConnector.WriteActivity(user, makeNewActivity(category, activity, details));
            }
        });
        add(submitButton);

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateActivityOptions();
                updateDetailsPlaceholder();
            }
        });

        challengeLabel = new JLabel("Today's Challenge", SwingConstants.CENTER);
        challengeLabel.setBounds(493, 76, 420, 40);
        challengeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        challengeLabel.setForeground(new Color(0, 51, 102));
        add(challengeLabel);

        challengeDetailsTextArea = new JTextArea();
        challengeDetailsTextArea.setBounds(567, 123, 277, 150);
        challengeDetailsTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        challengeDetailsTextArea.setLineWrap(true);
        challengeDetailsTextArea.setWrapStyleWord(true);
        challengeDetailsTextArea.setBackground(new Color(255, 255, 255)); // White background
        challengeDetailsTextArea.setEditable(false);
        challengeDetailsTextArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));  // Light border
        add(challengeDetailsTextArea);

        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/Images/focus.png")));
        lblNewLabel.setBounds(813, 78, 38, 40);
        add(lblNewLabel);

        showDailyChallenge();
    }

    private void showDailyChallenge() {
        String dailyChallenge = JdbcConnector.getDailyChallenge();
        challengeDetailsTextArea.setText(dailyChallenge);
    }

    private void updateActivityOptions() {
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        activityComboBox.removeAllItems();

        if ("Transport".equals(selectedCategory)) {
            activityComboBox.addItem("Bike");
            activityComboBox.addItem("Car");
            activityComboBox.addItem("Bus");
            activityComboBox.addItem("Tram");
            activityComboBox.addItem("Train");
            activityComboBox.addItem("Plane");
            activityComboBox.addItem("Walk");
        } else if ("Energy".equals(selectedCategory)) {
            activityComboBox.addItem("Electricity");
            activityComboBox.addItem("Gas");
            activityComboBox.addItem("Solar");
        } else if ("Food".equals(selectedCategory)) {
            activityComboBox.addItem("Vegetarian");
            activityComboBox.addItem("Vegan");
            activityComboBox.addItem("Ketto");
        }
    }

    // Update details placeholder based on selected category and activity
    private void updateDetailsPlaceholder() {
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        String selectedActivity = (String) activityComboBox.getSelectedItem();

        if ("Transport".equals(selectedCategory)) {
            detailsTextField.setText("Enter distance in kilometers...");
        } else if ("Energy".equals(selectedCategory)) {
            if ("Electricity".equals(selectedActivity)) {
                detailsTextField.setText("Enter consumption in kWh...");
            } else if ("Gas".equals(selectedActivity)) {
                detailsTextField.setText("Enter consumption in cubic meters...");
            } else if ("Solar".equals(selectedActivity)) {
                detailsTextField.setText("Enter energy produced...");
            } else {
                detailsTextField.setText("Enter details...");
            }
        } else if ("Food".equals(selectedCategory)) {
            detailsTextField.setText("Enter the number of meals eaten...");
        }

        detailsTextField.setForeground(Color.GRAY);
        detailsTextField.setFont(new Font("Segoe UI", Font.ITALIC, 16));
    }

    // Create a new activity object based on the selected category, activity, and details
    private Activity makeNewActivity(String category, String activity, String details) {
        if ("Transport".equals(category)) {
            switch (activity) {
                case "Car":
                    return new Activity(Category.Transport.Car, LocalDate.now(), Double.parseDouble(details) * 0.192);
                case "Bus":
                    return new Activity(Category.Transport.Bus, LocalDate.now(), Double.parseDouble(details) * 0.080);
                case "Plane":
                    return new Activity(Category.Transport.Plane, LocalDate.now(), Double.parseDouble(details) * 0.285);
                case "Tram":
                    return new Activity(Category.Transport.Tram, LocalDate.now(), Double.parseDouble(details) * 0.060);
                case "Bike":
                    return new Activity(Category.Transport.Bike, LocalDate.now(), Double.parseDouble(details) * 0);
                case "Walk":
                    return new Activity(Category.Transport.Walk, LocalDate.now(), Double.parseDouble(details) * 0.041);
                case "Train":
                    return new Activity(Category.Transport.Train, LocalDate.now(), Double.parseDouble(details) * 0);
            }
        } else if ("Energy".equals(category)) {
            switch (activity) {
                case "Gas":
                    return new Activity(Category.Energy.Gas, LocalDate.now(), Double.parseDouble(details) * 0.9);
                case "Solar":
                    return new Activity(Category.Energy.Solar, LocalDate.now(), Double.parseDouble(details) * 0);
                case "Electricity":
                    return new Activity(Category.Energy.Electricity, LocalDate.now(), Double.parseDouble(details) * 0.4);
            }
        } else if ("Food".equals(category)) {
            switch (activity) {
                case "Ketto":
                    return new Activity(Category.Food.Ketto, LocalDate.now(), Double.parseDouble(details) * 2.5);
                case "Vegan":
                    return new Activity(Category.Food.Vegan, LocalDate.now(), Double.parseDouble(details) * 1.6);
                case "Vegetarian":
                    return new Activity(Category.Food.Vegetarian, LocalDate.now(), Double.parseDouble(details) * 2.5);
            }
        }
        return null;
    }
}
