package V;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import M.DesignDB;

public class DesignPanel extends JPanel {
	
	public Image img_shirtBlackground;
	private Image img1_front_large;
	private Image img2_back_large;
	private Image img3_front_small;
	private Image img4_back_small;
	
	public DesignPanel() {

	}

	public DesignPanel(Image ximg) {
		img_shirtBlackground = ximg;
	}
	
	public DesignPanel(DesignDB xDesignDB) {
		
		if(xDesignDB.shirt_background_id == 1 ) {
			try {
				img_shirtBlackground = ImageIO.read(new File("whiteShirt.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(xDesignDB.shirt_background_id == 2) {
			try {
				img_shirtBlackground = ImageIO.read(new File("blackShirt.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.img1_front_large = xDesignDB.img1_front_large;
		this.img2_back_large = xDesignDB.img2_back_large;
		this.img3_front_small = xDesignDB.img3_front_small;
		this.img4_back_small = xDesignDB.img4_back_small;
	}
	
	public void setBackgroundShirt(Image ximg) {
		img_shirtBlackground = ximg;
		repaint();
	}
	
	public void paint(Graphics g) {
		
		if (img_shirtBlackground != null) {
			g.drawImage(img_shirtBlackground, 0, 0, this.getWidth(), this.getHeight(), 0, 0, img_shirtBlackground.getWidth(this), img_shirtBlackground.getHeight(this),this);
		} 
		else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		
		if(img1_front_large != null) {
			g.drawImage(img1_front_large, 100, 175, 183, 225, this);
		}
		
		if(img2_back_large != null) {
			g.drawImage(img2_back_large, 437, 175, 183, 225, this);
		}
		
		if(getImg3_front_small() != null) {
			g.drawImage(getImg3_front_small(), 215, 105, 60, 50, this);
		}
		
		
		if(getImg4_back_small() != null) {
			g.drawImage(getImg4_back_small(), 448, 100, 160, 50, this);
		}
		
	}

	public Image getImg1_front_large() {
		return img1_front_large;
	}

	public void setImg1_front_large(Image img1_front_large) {
		this.img1_front_large = img1_front_large;
		repaint();
	}

	public Image getImg2_back_large() {
		return img2_back_large;
	}

	public void setImg2_back_large(Image img2_back_large) {
		this.img2_back_large = img2_back_large;
		repaint();
	}

	public Image getImg3_front_small() {
		return img3_front_small;
	}

	public void setImg3_front_small(Image img3_front_small) {
		this.img3_front_small = img3_front_small;
		repaint();
	}

	public Image getImg4_back_small() {
		return img4_back_small;
	}

	public void setImg4_back_small(Image img4_back_small) {
		this.img4_back_small = img4_back_small;
		repaint();
	}
	
	public void setNew(DesignDB xDesignDB) {
		
		if(xDesignDB.shirt_background_id == 1 ) {
			try {
				img_shirtBlackground = ImageIO.read(new File("whiteShirt.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(xDesignDB.shirt_background_id == 2) {
			try {
				img_shirtBlackground = ImageIO.read(new File("blackShirt.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.img1_front_large = xDesignDB.img1_front_large;
		this.img2_back_large = xDesignDB.img2_back_large;
		this.img3_front_small = xDesignDB.img3_front_small;
		this.img4_back_small = xDesignDB.img4_back_small;
		repaint();
	}
	
	public void clearScreen() {
		this.img_shirtBlackground = null;
		this.img1_front_large = null;
		this.img2_back_large = null;
		this.img3_front_small = null;
		this.img4_back_small = null;
		repaint();
	}
}
