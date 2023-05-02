package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.DesignDB;
import M.InvoiceManager;
import M.Invoice_Detail_Manager;
import M.OrderDetailDB;
import M.ShirtManager;
import M.ShirtOrder;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Image;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderFrame extends JFrame {

	private JPanel contentPane;
	//private DesignPanel designPanel;
	private JScrollPane scrollPane_myDesign;
	private JButton btn_exit;
	private JLabel lb_quantity;
	private JLabel lb_size;
	private JComboBox comboBox_size;
	
	private String size[] = new String[] {"S", "M", "L", "XL", "XXL"};
	private JScrollPane scrollPane;
	private JLabel lb_myCart;
	private JButton btn_add;
	private JButton btn_remove;
	private JLabel lb_totalPrice;
	private JLabel lb_totalPrice_1;
	private JLabel lb_totalPrice_2;
	private JTextField txt_totalPrice;
	private JTextField txt_vat;
	private JTextField txt_netPrice;
	private JTable table_cart;
	private ArrayList<ShirtOrder> shirt_list = new ArrayList<ShirtOrder>();
	
	private int selectInCartIdx = -1;
	//private ArrayList<OrderDetailDB> orderDetailDB_list;
	private JSpinner spinner;
	
	
	public static void main (String [] args) {
		OrderFrame f = new OrderFrame();
		f.setVisible(true);
	}

	public OrderFrame() {
		setTitle("Create Order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1314, 701);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//designPanel = new DesignPanel((Image) null);
		Main.designPanel.setBounds(10, 10, 720, 470);
		contentPane.add(Main.designPanel);
		
		scrollPane_myDesign = new JScrollPane();
		scrollPane_myDesign.setBounds(761, 38, 184, 335);
		contentPane.add(scrollPane_myDesign);
		
		//table_myDesign = new JTable();
		scrollPane_myDesign.setViewportView(Main.table);
		
		JLabel lb_myDesign = new JLabel("My Design");
		lb_myDesign.setHorizontalAlignment(SwingConstants.CENTER);
		lb_myDesign.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb_myDesign.setBounds(761, 10, 184, 24);
		contentPane.add(lb_myDesign);
		
		btn_exit = new JButton("Exit");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main f = new Main();
				f.setVisible(true);
			}
		});
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_exit.setBounds(10, 600, 130, 40);
		contentPane.add(btn_exit);
		
		lb_quantity = new JLabel("Quantity");
		lb_quantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_quantity.setBounds(761, 399, 102, 24);
		contentPane.add(lb_quantity);
		
		lb_size = new JLabel("Size");
		lb_size.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_size.setBounds(761, 433, 102, 24);
		contentPane.add(lb_size);
		
		spinner = new JSpinner();
		spinner.setBackground(new Color(128, 255, 128));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 19));
		spinner.setBounds(856, 399, 89, 27);
		contentPane.add(spinner);
		
		comboBox_size = new JComboBox(size);
		comboBox_size.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_size.setBounds(856, 433, 89, 27);
		contentPane.add(comboBox_size);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(986, 38, 289, 335);
		contentPane.add(scrollPane);
		
		table_cart = new JTable();
		table_cart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table_cart.getSelectedRowCount() < 1) { //Not Found
					return;
				}
				selectInCartIdx = table_cart.getSelectedRow();
			}
		});
		scrollPane.setViewportView(table_cart);
		
		lb_myCart = new JLabel("My Cart");
		lb_myCart.setHorizontalAlignment(SwingConstants.CENTER);
		lb_myCart.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb_myCart.setBounds(986, 10, 289, 24);
		contentPane.add(lb_myCart);
		
		btn_add = new JButton("Add");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Main.selectedDesign == null)return;
				
				int idxSelect = comboBox_size.getSelectedIndex();
				
				ShirtOrder shirtDB = new ShirtOrder();
				shirtDB.design_id = Main.selectedDesign.design_id;
				shirtDB.design_name = Main.selectedDesign.design_name;
				shirtDB.shirt_size = size[idxSelect];
				shirtDB.qty = Integer.parseInt(spinner.getValue().toString()); 
				shirtDB.price = shirtDB.calPrice(Main.selectedDesign);
				shirtDB.shirt_id = ShirtManager.getShirtId(shirtDB.design_id, shirtDB.shirt_size);
				
				//check if already has in the list
				int searchIdx = -1;
				for(int i=0; i<shirt_list.size(); i++) {
					ShirtOrder x = shirt_list.get(i);
					if( (x.design_id == shirtDB.design_id) && (x.shirt_size.equals(shirtDB.shirt_size))) searchIdx = i;
				}
				
				if(searchIdx >= 0)shirt_list.remove(searchIdx);
				
				shirt_list.add(shirtDB);
				loadCartTable();
			}
		});
		btn_add.setBackground(new Color(128, 255, 0));
		btn_add.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_add.setBounds(969, 399, 67, 48);
		contentPane.add(btn_add);
		
		btn_remove = new JButton("Remove");
		btn_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(selectInCartIdx < 0)return;
				if(shirt_list.size()== 0)return;
				if(shirt_list.size() <= selectInCartIdx)return;
				
				shirt_list.remove(selectInCartIdx);
				loadCartTable();
				selectInCartIdx = -1;
			}
		});
		btn_remove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_remove.setBackground(new Color(255, 128, 128));
		btn_remove.setBounds(1154, 383, 121, 40);
		contentPane.add(btn_remove);
		
		lb_totalPrice = new JLabel(": Total Price");
		lb_totalPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lb_totalPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_totalPrice.setBounds(1137, 479, 138, 24);
		contentPane.add(lb_totalPrice);
		
		lb_totalPrice_1 = new JLabel(": VAT 7%");
		lb_totalPrice_1.setHorizontalAlignment(SwingConstants.LEFT);
		lb_totalPrice_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_totalPrice_1.setBounds(1137, 517, 138, 24);
		contentPane.add(lb_totalPrice_1);
		
		lb_totalPrice_2 = new JLabel(": NET Price");
		lb_totalPrice_2.setHorizontalAlignment(SwingConstants.LEFT);
		lb_totalPrice_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_totalPrice_2.setBounds(1137, 553, 138, 24);
		contentPane.add(lb_totalPrice_2);
		
		txt_totalPrice = new JTextField();
		txt_totalPrice.setText("0");
		txt_totalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_totalPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_totalPrice.setEditable(false);
		txt_totalPrice.setBounds(969, 483, 158, 26);
		contentPane.add(txt_totalPrice);
		txt_totalPrice.setColumns(10);
		
		txt_vat = new JTextField();
		txt_vat.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_vat.setText("0");
		txt_vat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_vat.setEditable(false);
		txt_vat.setColumns(10);
		txt_vat.setBounds(969, 521, 158, 26);
		contentPane.add(txt_vat);
		
		txt_netPrice = new JTextField();
		txt_netPrice.setText("0");
		txt_netPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_netPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_netPrice.setEditable(false);
		txt_netPrice.setColumns(10);
		txt_netPrice.setBounds(969, 555, 158, 26);
		contentPane.add(txt_netPrice);
		
		JButton btn_confirm = new JButton("Confirm");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(shirt_list.size() == 0) {
					JOptionPane.showMessageDialog(OrderFrame.this, "Your Cart is empty");
					return;
				}
				
				if(JOptionPane.showConfirmDialog(OrderFrame.this, "Do you want to add new order?") == JOptionPane.YES_OPTION) {
					InvoiceManager.saveNewInvoice();
					Invoice_Detail_Manager.saveNewInvoiceDetail(shirt_list, InvoiceManager.getLastestInvoiceId());
					Main f = new Main();
					f.setVisible(true);
					setVisible(false);
				}
			}
		});
		btn_confirm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_confirm.setBounds(1083, 600, 151, 40);
		contentPane.add(btn_confirm);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 128));
		panel.setBounds(218, 505, 514, 149);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Price Rate");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBackground(new Color(128, 255, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 10, 118, 20);
		panel.add(lblNewLabel);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(new Color(0, 0, 255));
		lblSize.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSize.setBounds(10, 40, 118, 20);
		panel.add(lblSize);
		
		JLabel lblScreenPosition = new JLabel("Screen Position");
		lblScreenPosition.setForeground(new Color(0, 0, 255));
		lblScreenPosition.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblScreenPosition.setBounds(298, 10, 159, 20);
		panel.add(lblScreenPosition);
		
		JLabel lblS = new JLabel("S  :+200 ฿");
		lblS.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblS.setBounds(10, 70, 118, 20);
		panel.add(lblS);
		
		JLabel lblM = new JLabel("M  :+225 ฿");
		lblM.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblM.setBounds(10, 94, 118, 20);
		panel.add(lblM);
		
		JLabel lblL = new JLabel("L   :+250 ฿");
		lblL.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblL.setBounds(10, 119, 118, 20);
		panel.add(lblL);
		
		JLabel lblX = new JLabel("XL :+275 ฿");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblX.setBounds(139, 70, 118, 20);
		panel.add(lblX);
		
		JLabel lblXxl = new JLabel("XXL :+300 ฿");
		lblXxl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXxl.setBounds(139, 94, 118, 20);
		panel.add(lblXxl);
		
		JLabel lblFrontLarge = new JLabel("Front Large: +300 ฿");
		lblFrontLarge.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFrontLarge.setBounds(297, 40, 207, 20);
		panel.add(lblFrontLarge);
		
		JLabel lblFrontLarge_1 = new JLabel("Back Large: +300 ฿");
		lblFrontLarge_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFrontLarge_1.setBounds(298, 70, 207, 20);
		panel.add(lblFrontLarge_1);
		
		JLabel lblFrontLarge_2 = new JLabel("Front Small:+100 ฿");
		lblFrontLarge_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFrontLarge_2.setBounds(297, 100, 207, 20);
		panel.add(lblFrontLarge_2);
		
		JLabel lblFrontLarge_2_1 = new JLabel("Back Small: +200 ฿");
		lblFrontLarge_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFrontLarge_2_1.setBounds(297, 126, 207, 20);
		panel.add(lblFrontLarge_2_1);
		
	
	}
	
	public void loadCartTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("No.");
		model.addColumn("Design Name");
		model.addColumn("Size");
		model.addColumn("Quntity");
		model.addColumn("Price");
		
		int count = 1;
		for(ShirtOrder u: shirt_list) {
			model.addRow(new Object[] {count, u.design_name, u.shirt_size, u.qty, u.price});
			count++;
		}
		table_cart.setModel(model);
		calTotalPrice();
	}
	
	public void calTotalPrice() {
		
		double sum=0;
		for(int i=0; i<shirt_list.size(); i++) {
			ShirtOrder shirtOrder = shirt_list.get(i);
			sum += shirtOrder.price;
		}
		
		double vat7 = round2(sum*0.07);
		txt_totalPrice.setText(String.format("%.2f", sum));
		txt_vat.setText(String.format("%.2f", vat7));
		txt_netPrice.setText(String.format("%.2f", sum+vat7));
	}
	
	public double round2(double n) {
		return ((double)Math.round(n*100))/100;
	}
}


























