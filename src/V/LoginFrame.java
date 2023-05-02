package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import M.CustomerManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txt_id;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JButton btnRegister;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setTitle("Custom Shop Log in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 264);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_id = new JLabel("ID :");
		lb_id.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_id.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_id.setBounds(26, 42, 123, 31);
		contentPane.add(lb_id);
		
		JLabel lb_id_1 = new JLabel("Password :");
		lb_id_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_id_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_id_1.setBounds(26, 89, 123, 31);
		contentPane.add(lb_id_1);
		
		txt_id = new JTextField();
		txt_id.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_id.setBounds(159, 42, 265, 29);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		btnNewButton = new JButton("LOG IN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txt_id.getText().trim();
				String pass = new String(passwordField.getPassword());
				
				if(CustomerManager.checkLoging(id, pass)) {
					Main main = new Main();
					main.setVisible(true);
					LoginFrame.this.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(LoginFrame.this, "Wrong Id or Password");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(105, 180, 114, 31);
		contentPane.add(btnNewButton);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame rf = new RegisterFrame();
				rf.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnRegister.setBounds(249, 180, 114, 31);
		contentPane.add(btnRegister);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(158, 89, 266, 29);
		contentPane.add(passwordField);
	}
}
