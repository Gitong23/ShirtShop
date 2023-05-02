package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import M.Company;
import M.CompanyManager;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditCompany extends JFrame {

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_address;
	private JTextField txt_road;
	private JTextField txt_ket;
	private JTextField txt_kwang;
	private JTextField txt_provice;
	private JTextField txt_posId;
	private JTextField txt_phone;
	private JTextField txt_email;
	private Company company;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCompany frame = new EditCompany();
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
	public EditCompany() {
		setTitle("Edit Company");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 481, 492);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_name = new JLabel("Name :");
		lb_name.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_name.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb_name.setBounds(10, 47, 118, 30);
		contentPane.add(lb_name);
		
		JLabel lb_address = new JLabel("Address :");
		lb_address.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_address.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb_address.setBounds(10, 87, 118, 30);
		contentPane.add(lb_address);
		
		JLabel lb_road = new JLabel("Road :");
		lb_road.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_road.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb_road.setBounds(10, 127, 118, 30);
		contentPane.add(lb_road);
		
		JLabel lb_ket = new JLabel("Ket :");
		lb_ket.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_ket.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb_ket.setBounds(10, 167, 118, 30);
		contentPane.add(lb_ket);
		
		JLabel lb_kwang = new JLabel("Kwang :");
		lb_kwang.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_kwang.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb_kwang.setBounds(10, 207, 118, 30);
		contentPane.add(lb_kwang);
		
		JLabel lb_provice = new JLabel("Provice :");
		lb_provice.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_provice.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb_provice.setBounds(10, 247, 118, 30);
		contentPane.add(lb_provice);
		
		JLabel lb_posId = new JLabel("Pos id :");
		lb_posId.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_posId.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb_posId.setBounds(10, 287, 118, 30);
		contentPane.add(lb_posId);
		
		JLabel lb_phone = new JLabel("Phone  :");
		lb_phone.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_phone.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb_phone.setBounds(10, 327, 118, 30);
		contentPane.add(lb_phone);
		
		JLabel lb_email = new JLabel("Email  :");
		lb_email.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_email.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb_email.setBounds(10, 367, 118, 30);
		contentPane.add(lb_email);
		
		txt_name = new JTextField();
		txt_name.setBounds(137, 47, 284, 28);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(137, 85, 284, 28);
		contentPane.add(txt_address);
		
		txt_road = new JTextField();
		txt_road.setColumns(10);
		txt_road.setBounds(138, 127, 284, 28);
		contentPane.add(txt_road);
		
		txt_ket = new JTextField();
		txt_ket.setColumns(10);
		txt_ket.setBounds(138, 167, 284, 28);
		contentPane.add(txt_ket);
		
		txt_kwang = new JTextField();
		txt_kwang.setColumns(10);
		txt_kwang.setBounds(138, 207, 284, 28);
		contentPane.add(txt_kwang);
		
		txt_provice = new JTextField();
		txt_provice.setColumns(10);
		txt_provice.setBounds(138, 249, 284, 28);
		contentPane.add(txt_provice);
		
		txt_posId = new JTextField();
		txt_posId.setColumns(10);
		txt_posId.setBounds(138, 287, 284, 28);
		contentPane.add(txt_posId);
		
		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(138, 327, 284, 28);
		contentPane.add(txt_phone);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(138, 367, 284, 28);
		contentPane.add(txt_email);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(10, 418, 118, 30);
		contentPane.add(btnNewButton);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				company.name = txt_name.getText().trim();
				company.address = txt_address.getText().trim();
				company.road = txt_phone.getText().trim();
				company.ket = txt_ket.getText().trim();
				company.kwang = txt_kwang.getText().trim();
				company.provice = txt_provice.getText().trim();
				company.pos_id = txt_posId.getText().trim();
				company.phone = txt_phone.getText().trim();
				company.email = txt_email.getText().trim();
				
				CompanyManager.editCompany(company);
				JOptionPane.showMessageDialog(EditCompany.this, "Update Success");
			}
		});
		btnConfirm.setBackground(new Color(128, 255, 128));
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirm.setBounds(328, 418, 118, 30);
		contentPane.add(btnConfirm);
		
		company = CompanyManager.getCompanyInfo();
		txt_name.setText(company.name);
		txt_address.setText(company.address);
		txt_road.setText(company.road);
		txt_ket.setText(company.ket);
		txt_kwang.setText(company.kwang);
		txt_provice.setText(company.provice);
		txt_posId.setText(company.pos_id);
		txt_phone.setText(company.phone);
		txt_email.setText(company.email);

	}
}
