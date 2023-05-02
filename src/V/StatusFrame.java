package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.InvoiceDB;
import M.InvoiceManager;

import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatusFrame extends JFrame {

	private JPanel contentPane;
	private JTable table_orderSelect;
	private ArrayList<InvoiceDB> invoice_list ;
	private InvoiceDB selectInvoice;
	private JButton btn_cancel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatusFrame frame = new StatusFrame();
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
	public StatusFrame() {
		setTitle("Order Status");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 668, 409);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_selectOrder = new JScrollPane();
		scrollPane_selectOrder.setBounds(51, 54, 545, 258);
		contentPane.add(scrollPane_selectOrder);
		
		table_orderSelect = new JTable();
		table_orderSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table_orderSelect.getSelectedRowCount() < 1) { //Not Found
					return;
				}
				int index = table_orderSelect.getSelectedRow();
				selectInvoice = invoice_list.get(index);
			}
		});
		scrollPane_selectOrder.setViewportView(table_orderSelect);
		
		JButton btn_confirm = new JButton("Confirm Order");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectInvoice == null) {
					JOptionPane.showMessageDialog(StatusFrame.this, "Please Select Invoice int table");
					return;
				}
				
				InvoiceManager.confirmStatus(selectInvoice);
				selectInvoice = null;
				loadSelectOrder();
			}
		});
		btn_confirm.setBackground(new Color(128, 255, 128));
		btn_confirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_confirm.setBounds(452, 322, 143, 27);
		contentPane.add(btn_confirm);
		
		btn_cancel = new JButton("Cancel Order");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectInvoice == null) {
					JOptionPane.showMessageDialog(StatusFrame.this, "Please Select Invoice int table");
					return;
				}
				
				InvoiceManager.cancelStatus(selectInvoice);
				selectInvoice = null;
				loadSelectOrder();
			}
		});
		btn_cancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_cancel.setBackground(new Color(255, 128, 128));
		btn_cancel.setBounds(299, 322, 143, 27);
		contentPane.add(btn_cancel);
		
		JButton btn_exit = new JButton("Exit");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_exit.setBackground(new Color(128, 255, 255));
		btn_exit.setBounds(51, 322, 106, 27);
		contentPane.add(btn_exit);
		
		JLabel lblNewLabel = new JLabel("All Customer Order");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(51, 10, 545, 34);
		contentPane.add(lblNewLabel);
		loadSelectOrder();
	}
	
	public void loadSelectOrder() {
		invoice_list = InvoiceManager.getAllInvoice();
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("No.");
		model.addColumn("Customer Id");	
		model.addColumn("Status");
		model.addColumn("Date");
		int count =1;
		for(InvoiceDB u : invoice_list) {
			model.addRow(new Object[] {count, u.customer_id, u.status, u.date});
			count++;
		}
		
		table_orderSelect.setModel(model);
	}
}
