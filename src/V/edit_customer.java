package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import M.CustomerManager;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class edit_customer extends JFrame {

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_surname;
	private JTextField txt_phone;
	private JTextField txt_address;
	private JTextField txt_kat;
	private JTextField txt_kwang;
	private JTextField txt_provice;
	private JTextField txt_zipCode;
	private JButton btn_Confirm;
	private JButton btn_Cancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					edit_customer frame = new edit_customer();
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
	public edit_customer() {
		setTitle("Edit Customer");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 645, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(SystemColor.info);
		contentPane_1.setBounds(0, 0, 631, 610);
		contentPane.add(contentPane_1);
		
		JLabel lb_name = new JLabel("Name");
		lb_name.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_name.setBounds(34, 23, 145, 27);
		contentPane_1.add(lb_name);
		
		JLabel lb_surname = new JLabel("Surname");
		lb_surname.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_surname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_surname.setBounds(34, 60, 145, 27);
		contentPane_1.add(lb_surname);
		
		JLabel lb_phone = new JLabel("Phone");
		lb_phone.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_phone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_phone.setBounds(34, 97, 145, 27);
		contentPane_1.add(lb_phone);
		
		JLabel lb_address = new JLabel("Address");
		lb_address.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_address.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_address.setBounds(34, 146, 145, 27);
		contentPane_1.add(lb_address);
		
		JLabel lb_Kat = new JLabel("Kat");
		lb_Kat.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_Kat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_Kat.setBounds(34, 190, 145, 27);
		contentPane_1.add(lb_Kat);
		
		JLabel lb_Kwang = new JLabel("Kwang");
		lb_Kwang.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_Kwang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_Kwang.setBounds(34, 231, 145, 27);
		contentPane_1.add(lb_Kwang);
		
		JLabel lb_Provice = new JLabel("Provice");
		lb_Provice.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_Provice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_Provice.setBounds(34, 268, 145, 27);
		contentPane_1.add(lb_Provice);
		
		JLabel lb_zipcoe = new JLabel("Zip Code");
		lb_zipcoe.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_zipcoe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_zipcoe.setBounds(34, 310, 145, 27);
		contentPane_1.add(lb_zipcoe);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(189, 23, 398, 27);
		contentPane_1.add(txt_name);
		
		txt_surname = new JTextField();
		txt_surname.setColumns(10);
		txt_surname.setBounds(189, 64, 398, 27);
		contentPane_1.add(txt_surname);
		
		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(189, 105, 398, 27);
		contentPane_1.add(txt_phone);
		
		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(189, 150, 398, 27);
		contentPane_1.add(txt_address);
		
		txt_kat = new JTextField();
		txt_kat.setColumns(10);
		txt_kat.setBounds(189, 194, 398, 27);
		contentPane_1.add(txt_kat);
		
		txt_kwang = new JTextField();
		txt_kwang.setColumns(10);
		txt_kwang.setBounds(189, 235, 398, 27);
		contentPane_1.add(txt_kwang);
		
		txt_provice = new JTextField();
		txt_provice.setColumns(10);
		txt_provice.setBounds(189, 272, 398, 27);
		contentPane_1.add(txt_provice);
		
		txt_zipCode = new JTextField();
		txt_zipCode.setColumns(10);
		txt_zipCode.setBounds(189, 314, 398, 27);
		contentPane_1.add(txt_zipCode);
		
		btn_Confirm = new JButton("Comfirm");
		btn_Confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(edit_customer.this, "Do you want to change") == JOptionPane.YES_OPTION) {
					Main.customerDB.user_name = txt_name.getText().trim();
					Main.customerDB.user_surname = txt_surname.getText().trim();
					Main.customerDB.user_phone = txt_phone.getText().trim();
					Main.customerDB.address = txt_address.getText().trim();
					Main.customerDB.kat = txt_kat.getText().trim();
					Main.customerDB.kwang = txt_kwang.getText().trim();
					Main.customerDB.provice = txt_provice.getText().trim();
					Main.customerDB.zipCode = txt_zipCode.getText().trim();
					CustomerManager.editCustomer(Main.customerDB);
					JOptionPane.showMessageDialog(edit_customer.this, "Update Success");
					setVisible(false);
				}
			}
		});
		btn_Confirm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_Confirm.setBounds(469, 359, 118, 27);
		contentPane_1.add(btn_Confirm);
		
		btn_Cancel = new JButton("Cancel");
		btn_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_Cancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_Cancel.setBounds(341, 359, 118, 27);
		contentPane_1.add(btn_Cancel);
		
		txt_name.setText(Main.customerDB.user_name);
		txt_surname.setText(Main.customerDB.user_surname);
		txt_phone.setText(Main.customerDB.user_name);
		txt_address.setText(Main.customerDB.address);
		txt_kat.setText(Main.customerDB.kat);
		txt_kwang.setText(Main.customerDB.kwang);
		txt_provice.setText(Main.customerDB.provice);
		txt_zipCode.setText(Main.customerDB.zipCode);
		
	}
}
