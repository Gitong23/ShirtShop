package M;

import java.awt.image.BufferedImage;

public class DesignDB {
	public int design_id;
	public int customer_id;
	public int shirt_background_id;
	public String design_name;
	public BufferedImage img1_front_large;
	public BufferedImage img2_back_large;
	public BufferedImage img3_front_small;
	public BufferedImage img4_back_small;
	
	public DesignDB() {
		
	}
	
	public DesignDB(int des_id, int cus_id, int shirt_bg_id, String des_namd, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4) {
		this.design_id = des_id;
		this.customer_id = cus_id;
		this.shirt_background_id = shirt_bg_id;
		this.design_name = des_namd;
		this.img1_front_large = img1;
		this.img2_back_large = img2;
		this.img3_front_small = img3;
		this.img4_back_small = img4;
	}
}
