package Ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import Ui.Chart;
import User.User;
import DBManager.JdbcConnector;

public class MainScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DailyChallenge page2;
    private ActivityScreen page1;
    private Chart page3;
    private LeaderBoardScreen page4;
    public User user;
    private boolean notificationSent = false;

    public MainScreen(User user) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 700);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        page1 = new ActivityScreen(user);
        page2 = new DailyChallenge();
        page3 = new Chart(user.getId());
        page4 = new LeaderBoardScreen();
        
        page1.setLocation(220, 198);
        page2.setLocation(220, 198);
        page3.setLocation(220, 198);
        page4.setLocation(220, 198);
        
        contentPane.add(page1);
        contentPane.add(page2);
        contentPane.add(page3);
        contentPane.add(page4);
        page1.setLayout(null);
        page3.setLayout(null);
        page4.setLayout(null);
        
        page1.setVisible(false);
        page2.setVisible(true);
        page3.setVisible(false);
        page4.setVisible(false);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(56, 74, 94));
        panel.setBounds(0, 0, 220, 700);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JButton btnNewButton = new JButton("Add Activity");
        btnNewButton.setBackground(new Color(56, 74, 94));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnNewButton.setFocusPainted(false); // Elimină bordura când este apăsat
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                page1.setVisible(true);
                page2.setVisible(false);
                page3.setVisible(false);
                page4.setVisible(false);
            }
        });
        btnNewButton.setBounds(10, 267, 200, 40);
        btnNewButton.setBorder(new LineBorder(Color.WHITE));
        panel.add(btnNewButton);
        
        JButton btnPage = new JButton("Overview");
        btnPage.setBackground(new Color(56, 74, 94));
        btnPage.setForeground(Color.WHITE);
        btnPage.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnPage.setFocusPainted(false); 
        btnPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                page1.setVisible(false);
                page2.setVisible(true);
                page3.setVisible(false);
                page4.setVisible(false);
            }
        });
        btnPage.setBounds(10, 236, 200, 40);
        btnPage.setBorder(new LineBorder(Color.WHITE));
        panel.add(btnPage);
        
        JLabel lblNewLabel = new JLabel("TRACKER");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(10, 36, 200, 40);
        panel.add(lblNewLabel);
        
        JButton btnNewButton_1 = new JButton("Your Chart");
        btnNewButton_1.setBackground(new Color(56, 74, 94));
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnNewButton_1.setFocusPainted(false); // Elimină bordura când este apăsat
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                page1.setVisible(false);
                page2.setVisible(false);
                page3.setVisible(true);
                page4.setVisible(false);
            }
        });
        btnNewButton_1.setBounds(10, 298, 200, 40);
        btnNewButton_1.setBorder(new LineBorder(Color.WHITE));
        panel.add(btnNewButton_1);
        
        JButton btnNewButton_1_1 = new JButton("LeaderBoard");
        btnNewButton_1_1.setBackground(new Color(56, 74, 94));
        btnNewButton_1_1.setForeground(Color.WHITE);
        btnNewButton_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnNewButton_1_1.setFocusPainted(false); 
        btnNewButton_1_1.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                page1.setVisible(false);
                page2.setVisible(false);
                page3.setVisible(false);
                page4.updateLeaderboard();
                page4.setVisible(true);
            }
        });
        btnNewButton_1_1.setBounds(10, 329, 200, 40);
        btnNewButton_1_1.setBorder(new LineBorder(Color.WHITE));
        panel.add(btnNewButton_1_1);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(MainScreen.class.getResource("/Images/radar.png")));
        lblNewLabel_1.setBounds(33, 68, 164, 152);
        panel.add(lblNewLabel_1);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(220, 198, 980, 502);
        contentPane.add(layeredPane);
        layeredPane.setLayout(new CardLayout(0, 0));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(230, 212, 185));
        panel_1.setBounds(220, 81, 980, 118);
        contentPane.add(panel_1);
        
        JLabel usernameLabel = new JLabel(user.getUsername());
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setBounds(902, 27, 190, 39);
        contentPane.add(usernameLabel);
        
        JLabel usernameIconLabel = new JLabel("");
        usernameIconLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/Images/person.png")));
        usernameIconLabel.setBounds(1099, 20, 64, 51);
        contentPane.add(usernameIconLabel);
        
        LocalDate todayDate = LocalDate.now();
        JLabel dateLabel = new JLabel(todayDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        dateLabel.setBounds(270, 27, 220, 39);
        contentPane.add(dateLabel);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(MainScreen.class.getResource("/Images/calendar.png")));
        lblNewLabel_2.setBounds(230, 20, 44, 51);
        contentPane.add(lblNewLabel_2);
        
        createSystemTray(user.getId());
    }

    private void createSystemTray(int id) {
        if (SystemTray.isSupported()) {
            SystemTray systemTray = SystemTray.getSystemTray();
            TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("icon.png"), "Carbon Footprint Tracker");

            PopupMenu popupMenu = new PopupMenu();
            
            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            popupMenu.add(exitItem);

            trayIcon.setPopupMenu(popupMenu);

            try {
                systemTray.add(trayIcon);

                Timer timer = new Timer(10000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double emissions = JdbcConnector.getCurrentEmissions(id); 
                        double emissionThreshold = 100.0; 
                        System.out.println("Emisii curente: " + emissions); 

                        if (emissions > emissionThreshold && !notificationSent) {
                            System.out.println("Trimitem notificarea!"); 
                            trayIcon.displayMessage("Emisii depășite!", "Ai depășit pragul de emisii: " + emissions, TrayIcon.MessageType.WARNING);
                            notificationSent = true; 
                        } else if (emissions <= emissionThreshold) {
                            System.out.println("Emisiile nu au depășit pragul."); 
                        }
                    }
                });
                timer.start();

            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("System Tray is not supported.");
        }
    }
}
