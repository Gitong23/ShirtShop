package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import M.CustomerDB;
import M.CustomerManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txt_id;
	private JTextField txt_name;
	private JTextField txt_surname;
	private JTextField txt_phone;
	private JTextField txt_address;
	private JTextField txt_kat;
	private JTextField txt_kwang;
	private JTextField txt_provice;
	private JTextField txt_zipCode;
	private JPasswordField passwordField;
	private JPasswordField passwordField_re;
	private CustomerDB customer_register;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setBackground(SystemColor.text);
		setTitle("Register");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 648, 613);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_id = new JLabel("ID");
		lb_id.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_id.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_id.setBounds(78, 62, 101, 27);
		contentPane.add(lb_id);
		
		JLabel lb_password = new JLabel("Password");
		lb_password.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_password.setBounds(72, 111, 107, 27);
		contentPane.add(lb_password);
		
		JLabel lb_password_re = new JLabel("Password Re");
		lb_password_re.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_password_re.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_password_re.setBounds(34, 148, 145, 27);
		contentPane.add(lb_password_re);
		
		JLabel lb_name = new JLabel("Name");
		lb_name.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_name.setBounds(34, 185, 145, 27);
		contentPane.add(lb_name);
		
		JLabel lb_surname = new JLabel("Surname");
		lb_surname.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_surname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_surname.setBounds(34, 222, 145, 27);
		contentPane.add(lb_surname);
		
		JLabel lb_phone = new JLabel("Phone");
		lb_phone.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_phone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_phone.setBounds(34, 259, 145, 27);
		contentPane.add(lb_phone);
		
		JLabel lb_address = new JLabel("Address");
		lb_address.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_address.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_address.setBounds(34, 308, 145, 27);
		contentPane.add(lb_address);
		
		JLabel lb_Kat = new JLabel("Kat");
		lb_Kat.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_Kat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_Kat.setBounds(34, 352, 145, 27);
		contentPane.add(lb_Kat);
		
		JLabel lb_Kwang = new JLabel("Kwang");
		lb_Kwang.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_Kwang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_Kwang.setBounds(34, 393, 145, 27);
		contentPane.add(lb_Kwang);
		
		JLabel lb_Provice = new JLabel("Provice");
		lb_Provice.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_Provice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_Provice.setBounds(34, 430, 145, 27);
		contentPane.add(lb_Provice);
		
		JLabel lb_zipcoe = new JLabel("Zip Code");
		lb_zipcoe.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_zipcoe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_zipcoe.setBounds(34, 472, 145, 27);
		contentPane.add(lb_zipcoe);
		
		txt_id = new JTextField();
		txt_id.setBounds(189, 66, 398, 27);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(189, 185, 398, 27);
		contentPane.add(txt_name);
		
		txt_surname = new JTextField();
		txt_surname.setColumns(10);
		txt_surname.setBounds(189, 226, 398, 27);
		contentPane.add(txt_surname);
		
		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(189, 267, 398, 27);
		contentPane.add(txt_phone);
		
		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(189, 312, 398, 27);
		contentPane.add(txt_address);
		
		txt_kat = new JTextField();
		txt_kat.setColumns(10);
		txt_kat.setBounds(189, 356, 398, 27);
		contentPane.add(txt_kat);
		
		txt_kwang = new JTextField();
		txt_kwang.setColumns(10);
		txt_kwang.setBounds(189, 397, 398, 27);
		contentPane.add(txt_kwang);
		
		txt_provice = new JTextField();
		txt_provice.setColumns(10);
		txt_provice.setBounds(189, 434, 398, 27);
		contentPane.add(txt_provice);
		
		txt_zipCode = new JTextField();
		txt_zipCode.setColumns(10);
		txt_zipCode.setBounds(189, 476, 398, 27);
		contentPane.add(txt_zipCode);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(189, 111, 398, 27);
		contentPane.add(passwordField);
		
		passwordField_re = new JPasswordField();
		passwordField_re.setBounds(189, 148, 398, 27);
		contentPane.add(passwordField_re);
		
		JButton btn_register = new JButton("Register");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txt_id.getText().equals("")) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "Input your ID");
					return;
				}
				
				String password = new String(passwordField.getPassword());
				String password_re = new String(passwordField_re.getPassword());
				
				if((password == null) || password.equals("")) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "Inputyour Password");
					return;
				}
				
				if(!password.equals(password_re)) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "Re password not same the Password");
					return;
				}
				
				customer_register = new CustomerDB();
				
				if(!(CustomerManager.isHasBeenUsedId(txt_id.getText().trim()))) {
					customer_register.customer_id = 0;
					customer_register.user_id = txt_id.getText().trim();
					customer_register.user_password = password;
					customer_register.user_type = "user";
					customer_register.user_name = txt_name.getText().trim();
					customer_register.user_surname = txt_surname.getText().trim();
					customer_register.user_phone = txt_phone.getText().trim();
					customer_register.address = txt_address.getText().trim();
					customer_register.kat = txt_kat.getText().trim();
					customer_register.kwang = txt_kwang.getText().trim();
					customer_register.provice = txt_provice.getText().trim();
					customer_register.zipCode = txt_zipCode.getText().trim();
					
					CustomerManager.saveNewCustomer(customer_register);
					JOptionPane.showMessageDialog(RegisterFrame.this, "Register Success...");
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(RegisterFrame.this, "This Id has been used");
				}
				
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_register.setBounds(469, 527, 118, 27);
		contentPane.add(btn_register);
		
		JButton btn_Cancel = new JButton("Cancel");
		btn_Cancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_Cancel.setBounds(341, 527, 118, 27);
		contentPane.add(btn_Cancel);
	}
}
