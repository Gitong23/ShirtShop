package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.Company;
import M.CompanyManager;
import M.CustomerDB;
import M.InvoiceDB;
import M.InvoiceManager;
import M.Invoice_Detail;
import M.Invoice_Detail_Manager;
import M.ShirtOrder;

import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrintInvoiceFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane_selectOrder;
	private JTable table_orderSelect;
	private ArrayList<InvoiceDB> invoice_list ;
	private ArrayList<Invoice_Detail> invoice_detail_list;
	private Company company;
	private InvoiceDB selectInvoice;
	private JPanel panel_invoice;
	private JLabel lb_invoice;
	private JLabel lb_Billto;
	private JLabel lb_client1;
	private JScrollPane scrollPane_invoice_detail;
	private JTable table_invoice_detail;
	private JLabel lb_client2;
	private JLabel lb_client3;
	private JLabel lb_com;
	private JLabel lb_com1;
	private JLabel lb_com2;
	private JLabel lb_com3;
	private JLabel lb_total;
	private JLabel lb_vat;
	private JLabel lb_vat_1;
	private JLabel lb_n_total;
	private JLabel lb_n_vat;
	private JLabel lb_n_net;
	private JButton btn_print;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintInvoiceFrame frame = new PrintInvoiceFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PrintInvoiceFrame() {
		
		setTitle("Invoice");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 838, 774);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_selectOrder = new JLabel("Select Order");
		lb_selectOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lb_selectOrder.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb_selectOrder.setBounds(25, 25, 186, 21);
		contentPane.add(lb_selectOrder);
		
		scrollPane_selectOrder = new JScrollPane();
		scrollPane_selectOrder.setBounds(10, 56, 222, 638);
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
				loadInvoiceDetail(selectInvoice.invoice_id);
			}
		});
		scrollPane_selectOrder.setViewportView(table_orderSelect);
		
		panel_invoice = new JPanel();
		panel_invoice.setBackground(new Color(255, 255, 255));
		panel_invoice.setBounds(261, 41, 553, 653);
		contentPane.add(panel_invoice);
		panel_invoice.setLayout(null);
		
		lb_invoice = new JLabel("Invoice");
		lb_invoice.setHorizontalAlignment(SwingConstants.LEFT);
		lb_invoice.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_invoice.setBounds(231, 10, 97, 28);
		panel_invoice.add(lb_invoice);
		
		lb_Billto = new JLabel("Bill To");
		lb_Billto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_Billto.setBounds(10, 31, 127, 28);
		panel_invoice.add(lb_Billto);
		
		lb_client1 = new JLabel("xxxx");
		lb_client1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_client1.setBounds(10, 59, 215, 20);
		panel_invoice.add(lb_client1);
		
		scrollPane_invoice_detail = new JScrollPane();
		scrollPane_invoice_detail.setBounds(10, 141, 533, 420);
		panel_invoice.add(scrollPane_invoice_detail);
		
		table_invoice_detail = new JTable();
		scrollPane_invoice_detail.setViewportView(table_invoice_detail);
		
		lb_client2 = new JLabel("xxxx");
		lb_client2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_client2.setBounds(10, 82, 215, 20);
		panel_invoice.add(lb_client2);
		
		lb_client3 = new JLabel("null null null null null");
		lb_client3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_client3.setBounds(10, 100, 215, 20);
		panel_invoice.add(lb_client3);
		
		loadSelectOrder();
		
		lb_client1.setText(Main.customerDB.user_name+" "+Main.customerDB.user_surname);
		lb_client2.setText(Main.customerDB.address+" "+Main.customerDB.kat+" "+Main.customerDB.kwang);
		lb_client3.setText(Main.customerDB.provice+" "+Main.customerDB.zipCode+" "+Main.customerDB.user_phone);
		
		lb_com = new JLabel("Bill To");
		lb_com.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_com.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_com.setBounds(404, 31, 139, 28);
		panel_invoice.add(lb_com);
		
		lb_com1 = new JLabel("null null");
		lb_com1.setHorizontalAlignment(SwingConstants.LEFT);
		lb_com1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_com1.setBounds(377, 59, 176, 20);
		panel_invoice.add(lb_com1);
		
		lb_com2 = new JLabel("null null");
		lb_com2.setHorizontalAlignment(SwingConstants.LEFT);
		lb_com2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_com2.setBounds(377, 82, 155, 20);
		panel_invoice.add(lb_com2);
		
		lb_com3 = new JLabel("null null");
		lb_com3.setHorizontalAlignment(SwingConstants.LEFT);
		lb_com3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_com3.setBounds(377, 100, 166, 20);
		panel_invoice.add(lb_com3);
		
		company = CompanyManager.getCompanyInfo();
		lb_com.setText(company.name);
		lb_com1.setText(company.address+" "+company.road+" "+company.ket);
		lb_com2.setText(company.kwang+" "+company.provice+" "+company.pos_id);
		lb_com3.setText(company.phone+"  "+company.email);
		
		lb_total = new JLabel("Total Price : ");
		lb_total.setHorizontalAlignment(SwingConstants.LEFT);
		lb_total.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_total.setBounds(30, 571, 84, 28);
		panel_invoice.add(lb_total);
		
		lb_vat = new JLabel("VAT 7% :");
		lb_vat.setHorizontalAlignment(SwingConstants.LEFT);
		lb_vat.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_vat.setBounds(30, 591, 105, 28);
		panel_invoice.add(lb_vat);
		
		lb_vat_1 = new JLabel("NET Price :");
		lb_vat_1.setHorizontalAlignment(SwingConstants.LEFT);
		lb_vat_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_vat_1.setBounds(30, 609, 105, 28);
		panel_invoice.add(lb_vat_1);
		
		lb_n_total = new JLabel("0");
		lb_n_total.setHorizontalAlignment(SwingConstants.LEFT);
		lb_n_total.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lb_n_total.setBounds(122, 571, 191, 22);
		panel_invoice.add(lb_n_total);
		
		lb_n_vat = new JLabel("0");
		lb_n_vat.setHorizontalAlignment(SwingConstants.LEFT);
		lb_n_vat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lb_n_vat.setBounds(122, 593, 191, 22);
		panel_invoice.add(lb_n_vat);
		
		lb_n_net = new JLabel("0");
		lb_n_net.setHorizontalAlignment(SwingConstants.LEFT);
		lb_n_net.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lb_n_net.setBounds(122, 611, 191, 22);
		panel_invoice.add(lb_n_net);
		
		btn_print = new JButton("Print Invoice");
		btn_print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(new InvoicePrint(PrintInvoiceFrame.this));
				boolean doPrint = job.printDialog();
				if(doPrint) {
					try {
						job.print();
					}
					catch(PrinterException ee) {
						
					}
				}
			}
		});
		btn_print.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_print.setBounds(663, 704, 151, 23);
		contentPane.add(btn_print);
		
		JButton btn_exit = new JButton("Exit");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_exit.setBounds(10, 704, 151, 23);
		contentPane.add(btn_exit);
	}
	
	public void loadSelectOrder() {
		invoice_list = InvoiceManager.getAllInvoiceCustomer(Main.customerDB.customer_id);
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("No.");
		model.addColumn("Status");
		model.addColumn("Date");
		int count =1;
		for(InvoiceDB u : invoice_list) {
			model.addRow(new Object[] {count, u.status, u.date});
			count++;
		}
		
		table_orderSelect.setModel(model);
	}
	
	public void loadInvoiceDetail(int invoice_id) {
		invoice_detail_list = Invoice_Detail_Manager.getAllInvoice_Detail(invoice_id);
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("No.");
		model.addColumn("Design Name");
		model.addColumn("Size");
		model.addColumn("Unit Price");
		model.addColumn("Qauntity");
		model.addColumn("Price");
		int count =1;
		double sum=0;
		for(Invoice_Detail u: invoice_detail_list) {
			model.addRow(new Object[] {count, u.shirt_name, u.shirt_size, u.unit_price, u.quantity, u.total_price});
			count++;
			sum += u.total_price;
		}
		
		double vat = sum*0.07;
		vat = (Math.round(vat*100))/100.0;
		double net = sum+vat;
		
		table_invoice_detail.setModel(model);
		//lb_n_total.setText(sum+"");
		lb_n_total.setText(String.format("%.2f", sum));
		lb_n_vat.setText(String.format("%.2f", vat));
		lb_n_net.setText(String.format("%.2f", net));
	}
	
	class InvoicePrint implements Printable{
		
		PrintInvoiceFrame xframe;
		public InvoicePrint(PrintInvoiceFrame frame) {
			xframe = frame;
		}
		
		@Override
		public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
			// TODO Auto-generated method stub
			
			if(page > 0 ) {
				return NO_SUCH_PAGE;
			}
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());
			
			g2d.drawLine(20, 150, 580, 150);
			g2d.drawLine(20, 670, 580, 670);
			
			for(int i=0; i<xframe.panel_invoice.getComponentCount(); i++) {
				
				Component c = xframe.panel_invoice.getComponent(i);
				
				if(c instanceof JLabel) {
					JLabel l = (JLabel)c;
					if(i<10) {
						g2d.setFont(l.getFont());
						g2d.drawString(l.getText()
								, (int)(l.getLocation().getX()+10)
								, (int)(l.getLocation().getY()+l.getHeight()));
					}
					else {
						g2d.setFont(l.getFont());
						g2d.drawString(l.getText()
								, (int)(l.getLocation().getX())
								, (int)(l.getLocation().getY()+l.getHeight()+100));
					}
					
				}
			}
			
			
			//print table column
			int x =20;
			int y = (int)(xframe.scrollPane_invoice_detail.getLocation().getY())+40;
			for(int i=0; i<xframe.table_invoice_detail.getColumnCount(); i++) {
				g2d.setFont(xframe.table_invoice_detail.getFont());
				g2d.drawString(xframe.table_invoice_detail.getColumnName(i), x, y);
				
				x+= xframe.table_invoice_detail.getColumn(xframe.table_invoice_detail.getColumnName(i)).getWidth()+10;
			}
			
			
			y+=30;
			
			for(int j=0; j<xframe.table_invoice_detail.getRowCount(); j++) {
				
				//print each column in row
				x =20;
				for(int i=0; i<xframe.table_invoice_detail.getColumnCount(); i++) {		
					if(xframe.table_invoice_detail.getColumnName(i).equals("image")) {
						//print column is image
						BufferedImage image = (BufferedImage) xframe.table_invoice_detail.getValueAt(j, i);
						g.drawImage(image, x, y-40, x+120, y+120, 0, 0, image.getWidth(), image.getHeight(), null);
					}
					else {
						//colunm value is String 
						g2d.setFont(xframe.table_invoice_detail.getFont());
						g2d.drawString(""+xframe.table_invoice_detail.getValueAt(j, i), x, y);
					}

					x+= xframe.table_invoice_detail.getColumn(xframe.table_invoice_detail.getColumnName(i)).getWidth()+10;
				}
				
				y+= xframe.table_invoice_detail.getRowHeight(j)+5;
			}
			
			return PAGE_EXISTS;
		}
	}
}
