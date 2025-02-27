package Ui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

import User.User;
import DBManager.JdbcConnector;
import Encrypt.Encryption;

/**
 * Clasa ce modeleaza meniul de SignUp
 */
public class SignUpScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField passwordTextField;
	private JLabel usernameLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JButton signUpButton;
	private JLabel AccLabel;
	private JTextField emailTextField;
	private JTextField usernameTextField;

	/**
	 * Create the frame.
	 */
	public SignUpScreen() {
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
		iconLabel.setIcon(new ImageIcon(LoginScreen.class.getResource("/Images/biodegradable.png")));
		iconLabel.setBounds(82, 222, 256, 256);
		panel.add(iconLabel);
		
		JLabel TitleLabel = new JLabel("SIGN UP");
		TitleLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
		TitleLabel.setBounds(732, 55, 182, 87);
		contentPane.add(TitleLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(582, 432, 464, 40);
		contentPane.add(passwordTextField);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(582, 176, 76, 13);
		contentPane.add(usernameLabel);
		
		emailLabel = new JLabel("Email");
		emailLabel.setBounds(582, 292, 45, 13);
		contentPane.add(emailLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(581, 409, 85, 13);
		contentPane.add(passwordLabel);
		
		signUpButton = new JButton("SIGN UP");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username;
				String password;
				String email;
				username = usernameTextField.getText();
				password = passwordTextField.getText();
				email = emailTextField.getText();
				JdbcConnector.WriteUser(username, Encryption.encrypt(password), email);
				
				MainScreen ms = new MainScreen(JdbcConnector.GetUser(username, Encryption.encrypt(password)));
				ms.show();
				
				dispose();
			}
		});
		signUpButton.setBackground(new Color(142, 180, 134));
		signUpButton.setBounds(581, 503, 101, 39);
		contentPane.add(signUpButton);
		
		AccLabel = new JLabel("I already have an account");
		AccLabel.setBounds(581, 603, 162, 13);
		contentPane.add(AccLabel);
		
		JButton signInButton = new JButton("SIGN IN");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen ls = new LoginScreen();
				ls.show();
				
				dispose();
				
			}
		});
		signInButton.setBounds(732, 599, 85, 21);
		contentPane.add(signInButton);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(582, 315, 464, 40);
		contentPane.add(emailTextField);
		emailTextField.addKeyListener(new KeyListener() {
			
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
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(582, 199, 464, 40);
		contentPane.add(usernameTextField);
		usernameTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					emailTextField.requestFocus();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            emailTextField.requestFocus(); 
		        }
			}
		});
	}
}
