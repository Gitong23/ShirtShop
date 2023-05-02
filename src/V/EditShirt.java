package V;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import M.DesignDB;
import M.DesignManager;

public class EditShirt extends CustomFrame {
	
	private JButton btn_Edit;
	
	public static void main(String [] args) {
		EditShirt editFrame = new EditShirt();
		editFrame.setVisible(true);
	}
	
	
	public EditShirt() {
		
		designDB = Main.selectedDesign;
		this.setTitle("Edit Shirt");
		lb_des_1.setText("Edit Shirt");
		designPanel.setNew(designDB);
		btn_actionDB.setVisible(false);
		txt_name.setText(designDB.design_name);
		
		btn_Edit = new JButton("Edit Confirm");
		btn_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DesignDB sentDB = new DesignDB(
						designDB.design_id, 
						designDB.customer_id, 
						designDB.shirt_background_id,
						txt_name.getText(), 
						(BufferedImage)designPanel.getImg1_front_large(), 
						(BufferedImage)designPanel.getImg2_back_large(),
						(BufferedImage)designPanel.getImg3_front_small(), 
						(BufferedImage)designPanel.getImg4_back_small()
						);
				DesignManager.editDesign(sentDB);
				Main.selectedDesign = sentDB;
				JOptionPane.showMessageDialog(EditShirt.this, "Update Success...");
				setVisible(false);
				Main.loadMainTable();
				
			}
		});
		btn_Edit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_Edit.setBounds(986, 624, 141, 40);
		btn_Edit.setBackground(Color.GREEN);
		contentPane.add(btn_Edit);
		
	}
	
	
}
