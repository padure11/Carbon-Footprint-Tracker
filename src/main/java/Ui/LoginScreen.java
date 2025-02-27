package Ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.AWTException;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.MenuItem;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

import Encrypt.Encryption;
import User.User;
import DBManager.JdbcConnector;

/**
 * Clasa ce are ca scop punctul principala de intare in aplicatie, punct din care porneste utilizatorul
 */
public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253, 247, 244));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(0, 0, 420, 700);
		contentPane.add(panel);
		panel.setBackground(new Color(142, 180, 134));
		panel.setLayout(null);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(LoginScreen.class.getResource("/Images/carbon-footprint (3).png")));
		iconLabel.setBounds(82, 222, 256, 256);
		panel.add(iconLabel);
		
		JLabel Title = new JLabel("LOGIN");
		Title.setFont(new Font("SansSerif", Font.BOLD, 36));
		Title.setBounds(758, 55, 143, 87);
		contentPane.add(Title);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(581, 242, 464, 40);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		usernameTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            passwordTextField.requestFocus(); 	
		        }
		    }
		});
		
		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(581, 377, 464, 40);
		contentPane.add(passwordTextField);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(581, 219, 76, 13);
		contentPane.add(usernameLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(581, 354, 85, 13);
		contentPane.add(passwordLabel);
		
		loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username;
				String password;
				username = usernameTextField.getText();
				password = Encryption.encrypt(passwordTextField.getText());

				if (JdbcConnector.Login(username, password)) {
					MainScreen ms = new MainScreen(JdbcConnector.GetUser(username, password));
					ms.show();
					
					dispose();
					
				}
			}
		});
		loginButton.setBackground(new Color(142, 180, 134));
		loginButton.setBounds(581, 503, 101, 39);
		contentPane.add(loginButton);
		
		JLabel noAccLabel = new JLabel("I don't have an account");
		noAccLabel.setBounds(581, 603, 162, 13);
		contentPane.add(noAccLabel);
		
		JButton loginToSignUpButton = new JButton("SIGN UP");
		loginToSignUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SignUpScreen sus = new SignUpScreen();
				sus.show();
				
				dispose();
			}
		});
		loginToSignUpButton.setBounds(732, 599, 85, 21);
		contentPane.add(loginToSignUpButton);
		
	}

}
