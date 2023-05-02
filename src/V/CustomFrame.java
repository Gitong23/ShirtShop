package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import M.DesignDB;
import M.DesignManager;
import M.ShirtManager;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CustomFrame extends JFrame {

	protected JPanel contentPane;
	protected ImagePanel imagePanel;
	protected BufferedImage patternExample;
	protected DesignDB designDB = new DesignDB();
	protected DesignPanel designPanel;
	protected BufferedImage shirt_bg;
	protected JTextField txt_path1;
	protected JTextField txt_path2;
	protected JTextField txt_path3;
	protected JTextField txt_path4;
	protected JTextField txt_name;
	protected JLabel lb_des_1;
	protected JButton btn_Exit;
	protected JButton btn_actionDB;
	protected JLabel lb_Name;
	protected JLabel lb_Select;
	protected JLabel lb_example;
	private JButton btn_img2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomFrame frame = new CustomFrame();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CustomFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1184, 755);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JButton btn_img1 = new JButton("Select Img1");
		btn_img1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				setFileFilter(fc);
				
				int returnVal = fc.showOpenDialog(CustomFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					txt_path1.setText(f.getAbsolutePath());
					try {
						designPanel.setImg1_front_large(ImageIO.read(f));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_img1.setHorizontalAlignment(SwingConstants.LEFT);
		btn_img1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_img1.setBounds(32, 78, 120, 30);
		contentPane.add(btn_img1);
		
		btn_img2 = new JButton("Select Img2");
		btn_img2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				setFileFilter(fc);
				
				int returnVal = fc.showOpenDialog(CustomFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					txt_path2.setText(f.getAbsolutePath());
					try {
						designPanel.setImg2_back_large(ImageIO.read(f));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_img2.setHorizontalAlignment(SwingConstants.LEFT);
		btn_img2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_img2.setBounds(32, 118, 120, 30);
		contentPane.add(btn_img2);
		
		JButton btn_img3 = new JButton("Select Img3");
		btn_img3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				setFileFilter(fc);
				
				int returnVal = fc.showOpenDialog(CustomFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					txt_path3.setText(f.getAbsolutePath());
					try {
						designPanel.setImg3_front_small(ImageIO.read(f));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_img3.setHorizontalAlignment(SwingConstants.LEFT);
		btn_img3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_img3.setBounds(32, 158, 120, 30);
		contentPane.add(btn_img3);
		
		JButton btn_img4 = new JButton("Select Img4");
		btn_img4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				setFileFilter(fc);
				
				int returnVal = fc.showOpenDialog(CustomFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					txt_path4.setText(f.getAbsolutePath());
					try {
						designPanel.setImg4_back_small(ImageIO.read(f));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_img4.setHorizontalAlignment(SwingConstants.LEFT);
		btn_img4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_img4.setBounds(32, 198, 120, 30);
		contentPane.add(btn_img4);
		
		try {
			patternExample = ImageIO.read(new File("pattern4.png"));
			shirt_bg = ImageIO.read(new File("whiteShirt.jpg")); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imagePanel = new ImagePanel(patternExample);
		imagePanel.setBounds(814, 53, 346, 286);
		contentPane.add(imagePanel);
		
		designPanel = new DesignPanel(shirt_bg);
		designDB.shirt_background_id = 1; //defualt value
		designPanel.setBounds(32, 238, 720, 470);
		contentPane.add(designPanel);
		
		lb_example = new JLabel("Shirt Screen Example");
		lb_example.setForeground(Color.RED);
		lb_example.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_example.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_example.setBounds(857, 10, 289, 33);
		contentPane.add(lb_example);
		
		lb_Select = new JLabel("Select Color of Shirt");
		lb_Select.setHorizontalAlignment(SwingConstants.LEFT);
		lb_Select.setForeground(Color.RED);
		lb_Select.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_Select.setBounds(857, 375, 236, 33);
		contentPane.add(lb_Select);
		
		JButton btn_white = new JButton("White");
		btn_white.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shirt_bg = ImageIO.read(new File("whiteShirt.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				designPanel.setBackgroundShirt(shirt_bg);
				designDB.shirt_background_id = 1;
			}
		});
		btn_white.setBackground(Color.WHITE);
		btn_white.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_white.setBounds(814, 418, 141, 30);
		contentPane.add(btn_white);
		
		JButton btn_black = new JButton("Black");
		btn_black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shirt_bg = ImageIO.read(new File("blackShirt.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				designPanel.setBackgroundShirt(shirt_bg);
				designDB.shirt_background_id = 2;
			}
		});
		btn_black.setForeground(Color.WHITE);
		btn_black.setBackground(Color.BLACK);
		btn_black.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_black.setBounds(965, 418, 141, 30);
		contentPane.add(btn_black);
		
		txt_path1 = new JTextField();
		txt_path1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_path1.setEditable(false);
		txt_path1.setBounds(251, 78, 485, 27);
		contentPane.add(txt_path1);
		txt_path1.setColumns(10);
		
		JButton btn_cancel1 = new JButton("Delete");
		btn_cancel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				designPanel.setImg1_front_large(null);
				txt_path1.setText("");
			}
		});
		btn_cancel1.setHorizontalAlignment(SwingConstants.LEFT);
		btn_cancel1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_cancel1.setBounds(162, 78, 79, 30);
		contentPane.add(btn_cancel1);
		
		JButton btn_cancel2 = new JButton("Delete");
		btn_cancel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				designPanel.setImg2_back_large(null);
				txt_path2.setText("");
			}
		});
		btn_cancel2.setHorizontalAlignment(SwingConstants.LEFT);
		btn_cancel2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_cancel2.setBounds(162, 118, 79, 30);
		contentPane.add(btn_cancel2);
		
		JButton btn_cancel3 = new JButton("Delete");
		btn_cancel3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				designPanel.setImg3_front_small(null);
				txt_path3.setText("");
			}
		});
		btn_cancel3.setHorizontalAlignment(SwingConstants.LEFT);
		btn_cancel3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_cancel3.setBounds(162, 158, 79, 30);
		contentPane.add(btn_cancel3);
		
		JButton btn_cancel4 = new JButton("Delete");
		btn_cancel4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				designPanel.setImg4_back_small(null);
				txt_path4.setText("");
			}
		});
		btn_cancel4.setHorizontalAlignment(SwingConstants.LEFT);
		btn_cancel4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_cancel4.setBounds(162, 198, 79, 30);
		contentPane.add(btn_cancel4);
		
		txt_path2 = new JTextField();
		txt_path2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_path2.setEditable(false);
		txt_path2.setColumns(10);
		txt_path2.setBounds(251, 118, 485, 27);
		contentPane.add(txt_path2);
		
		txt_path3 = new JTextField();
		txt_path3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_path3.setEditable(false);
		txt_path3.setColumns(10);
		txt_path3.setBounds(251, 158, 485, 27);
		contentPane.add(txt_path3);
		
		txt_path4 = new JTextField();
		txt_path4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_path4.setEditable(false);
		txt_path4.setColumns(10);
		txt_path4.setBounds(251, 198, 485, 27);
		contentPane.add(txt_path4);
		
		btn_actionDB = new JButton("Save New");
		btn_actionDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txt_name.getText().trim() == "") {
					JOptionPane.showMessageDialog(CustomFrame.this, "Please input name design");
					txt_name.requestFocus();
					return;
				}
					
				designDB.design_id = 0;
				designDB.customer_id = Main.customerDB.customer_id;
				designDB.design_name = txt_name.getText().trim();
				
				designDB.img1_front_large = (BufferedImage)designPanel.getImg1_front_large();
				designDB.img2_back_large = (BufferedImage)designPanel.getImg2_back_large();
				designDB.img3_front_small = (BufferedImage)designPanel.getImg3_front_small();
				designDB.img4_back_small = (BufferedImage)designPanel.getImg4_back_small();
				
				DesignManager.saveNewDesign(designDB);
				ShirtManager.saveNewShirtsize(DesignManager.getLastestDesignId());
				JOptionPane.showMessageDialog(CustomFrame.this, "Finish");
				setVisible(false);
				Main.loadMainTable();
			}
		});
		btn_actionDB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_actionDB.setBounds(986, 624, 141, 40);
		contentPane.add(btn_actionDB);
		
		txt_name = new JTextField();
		txt_name.setBounds(810, 560, 317, 30);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		lb_Name = new JLabel("Design name");
		lb_Name.setHorizontalAlignment(SwingConstants.LEFT);
		lb_Name.setForeground(Color.BLACK);
		lb_Name.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_Name.setBounds(814, 517, 236, 33);
		contentPane.add(lb_Name);
		
		btn_Exit = new JButton("Exit");
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_Exit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_Exit.setBounds(835, 624, 141, 40);
		contentPane.add(btn_Exit);
		
		JLabel lb_des = new JLabel("Input .PNG file");
		lb_des.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_des.setForeground(Color.BLACK);
		lb_des.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_des.setBounds(500, 35, 236, 33);
		contentPane.add(lb_des);
		
		lb_des_1 = new JLabel("Add New Design");
		lb_des_1.setHorizontalAlignment(SwingConstants.LEFT);
		lb_des_1.setForeground(Color.BLACK);
		lb_des_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_des_1.setBounds(32, 35, 236, 33);
		contentPane.add(lb_des_1);
		
	}
	
	public static void setFileFilter(JFileChooser fc) {
		//fc.addChoosableFileFilter(new OpenFileFilter("jpeg","Photo in JPEG format") );
		//fc.addChoosableFileFilter(new OpenFileFilter("jpg","Photo in JPEG format") );
		fc.addChoosableFileFilter(new OpenFileFilter("png","PNG image") );
		//fc.addChoosableFileFilter(new OpenFileFilter("svg","Scalable Vector Graphic") );
		fc.setAcceptAllFileFilterUsed(false);
	}
}
