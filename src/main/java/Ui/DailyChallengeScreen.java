package Ui;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBManager.JdbcConnector;
import User.User;

public class DailyChallengeScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private User user;

    private JLabel challengeLabel;
    private JTextArea challengeDetailsTextArea;

    public DailyChallengeScreen(User user) {
        setBackground(new Color(253, 247, 244));
        setSize(980, 502);
        setLayout(null);
        this.user = user;

        
        challengeLabel = new JLabel("Today's Challenge", SwingConstants.CENTER);
        challengeLabel.setBounds(20, 30, 320, 30);
        challengeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        challengeLabel.setForeground(new Color(0, 51, 102));
        add(challengeLabel);

        
        challengeDetailsTextArea = new JTextArea();
        challengeDetailsTextArea.setBounds(50, 70, 251, 95);
        challengeDetailsTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        challengeDetailsTextArea.setLineWrap(true);
        challengeDetailsTextArea.setWrapStyleWord(true);
        challengeDetailsTextArea.setBackground(new Color(253, 247, 244));
        challengeDetailsTextArea.setEditable(false);
        add(challengeDetailsTextArea);

        
        showDailyChallenge();
    }

    
    private void showDailyChallenge() {
        String dailyChallenge = JdbcConnector.getDailyChallenge();
        challengeDetailsTextArea.setText(dailyChallenge);
    }
}
