package V;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;
import M.DesignDB;
import M.DesignManager;
import M.ShirtManager;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.swing.SwingConstants;

public class Main extends JFrame {

	private JPanel contentPane;
	public static CustomerDB customerDB = CustomerManager.getCustomer();
	public static JTable table;
	private JScrollPane scrollPane;
	public static ArrayList<DesignDB> allDesignList;
	public static DesignDB selectedDesign;
	public static DesignPanel designPanel = new DesignPanel();
	private JButton btn_PrintImg;
	private JButton btn_printInvoice;
	private JButton btn_edit_customer;
	private JButton btn_edit_company;
	private JButton btn_status;
	
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1091, 577);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_create = new JButton("Create New");
		btn_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomFrame cf = new CustomFrame();
				cf.setVisible(true);
			}
		});
		btn_create.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_create.setBounds(10, 27, 126, 29);
		contentPane.add(btn_create);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(169, 57, 170, 415);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRowCount() < 1) { //Not Found
					return;
				}
				int index = table.getSelectedRow();
				selectedDesign = allDesignList.get(index);
				refreshImg();
			}
		});
		scrollPane.setViewportView(table);
		
		
		designPanel = new DesignPanel((Image) null);
		designPanel.setBounds(349, 10, 720, 470);
		contentPane.add(designPanel);
		
		JLabel lb_yourDesign = new JLabel("Your Design");
		lb_yourDesign.setHorizontalAlignment(SwingConstants.CENTER);
		lb_yourDesign.setFont(new Font("Tahoma", Font.BOLD, 18));
		lb_yourDesign.setBounds(169, 18, 170, 29);
		contentPane.add(lb_yourDesign);
		
		JButton btn_edit = new JButton("Edit Design");
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(selectedDesign == null)return;
				
				EditShirt editFrame = new EditShirt();
				editFrame.setVisible(true);
			}
		});
		btn_edit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_edit.setBounds(10, 126, 126, 29);
		contentPane.add(btn_edit);
		
		JButton btn_delete = new JButton("Delete Design");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedDesign == null)return;
				DesignManager.deleteDesign(selectedDesign);
				ShirtManager.deleteShirt(selectedDesign);
				loadMainTable();
				selectedDesign = null;
				designPanel.clearScreen();
			}
		});
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_delete.setBounds(10, 175, 126, 29);
		contentPane.add(btn_delete);
		
		JButton btn_newOrder = new JButton("New Order");
		btn_newOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFrame orderFrame = new OrderFrame();
				setVisible(false);
				orderFrame.setVisible(true);
			}
		});
		btn_newOrder.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_newOrder.setBounds(10, 77, 126, 29);
		contentPane.add(btn_newOrder);
		
		btn_PrintImg = new JButton("Print Selected Image");
		btn_PrintImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc = new JFileChooser();
				CustomFrame.setFileFilter(fc);
				fc.setDialogTitle("Choose location to save");
				int status = fc.showSaveDialog(Main.this);
				
				if(status == JFileChooser.APPROVE_OPTION) {
					File outputfile =fc.getSelectedFile();
					BufferedImage myImg = createImage(designPanel);
					
					try {
						ImageIO.write(myImg, "png", outputfile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_PrintImg.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_PrintImg.setBounds(834, 490, 235, 29);
		contentPane.add(btn_PrintImg);
		
		btn_printInvoice = new JButton("Print Invoice");
		btn_printInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintInvoiceFrame pf = new PrintInvoiceFrame();
				pf.setVisible(true);
			}
		});
		btn_printInvoice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_printInvoice.setBounds(10, 221, 126, 29);
		contentPane.add(btn_printInvoice);
		
		JButton btn_exit = new JButton("Exit");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_exit.setBounds(10, 497, 126, 29);
		contentPane.add(btn_exit);
		
		btn_edit_customer = new JButton("Edit Customer");
		btn_edit_customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit_customer f = new edit_customer();
				f.setVisible(true);
			}
		});
		btn_edit_customer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_edit_customer.setBounds(10, 458, 126, 29);
		contentPane.add(btn_edit_customer);
		
		btn_status = new JButton("Order status");
		btn_status.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusFrame  f = new StatusFrame();
				f.setVisible(true);
			}
		});
		btn_status.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_status.setBounds(10, 266, 126, 29);
		contentPane.add(btn_status);
		
		btn_edit_company = new JButton("Edit Company");
		btn_edit_company.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCompany f = new EditCompany();
				f.setVisible(true);
			}
		});
		btn_edit_company.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_edit_company.setBounds(10, 313, 126, 29);
		contentPane.add(btn_edit_company);
		
		JLabel lb_yourDesign_1 = new JLabel("Save .PNG only");
		lb_yourDesign_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_yourDesign_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lb_yourDesign_1.setBounds(654, 490, 170, 29);
		contentPane.add(lb_yourDesign_1);
		
		if(customerDB.user_type.equals("user")) {
			btn_status.setVisible(false);
			btn_edit_company.setVisible(false);
		}
		
		loadMainTable();
	}
	
	public static void loadMainTable() {
		allDesignList = DesignManager.getAlluserDesign(customerDB.customer_id);
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("No.");
		model.addColumn("Design Name");
		int count =1;
		for(DesignDB u: allDesignList) {
			model.addRow(new Object[] {count, u.design_name});
			count++;
		}
		
		table.setModel(model);
		
		if(selectedDesign == null)return;
		refreshImg();
	}
	
	public static void refreshImg() {
		if(selectedDesign.shirt_background_id == 1 ) {
			try {
				designPanel.img_shirtBlackground = ImageIO.read(new File("whiteShirt.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(selectedDesign.shirt_background_id == 2) {
			try {
				designPanel.img_shirtBlackground = ImageIO.read(new File("blackShirt.jpg"));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		designPanel.setImg1_front_large(selectedDesign.img1_front_large);
		designPanel.setImg2_back_large(selectedDesign.img2_back_large);
		designPanel.setImg3_front_small(selectedDesign.img3_front_small);
		designPanel.setImg4_back_small(selectedDesign.img4_back_small);
	}
	
	public BufferedImage createImage(JPanel panel) {

	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    g.dispose();
	    return bi;
	}
}
