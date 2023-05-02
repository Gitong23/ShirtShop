package M;

public class ShirtOrder {
	public int shirt_id;
	public int design_id;
	public String design_name;
	public String shirt_size;
	public int qty;
	public int price;
	
	public ShirtOrder() {
		
	}
	
	public int calPrice(DesignDB designDB) {
		int price=0;
		
		if(designDB.img1_front_large != null)price += 300;
		if(designDB.img2_back_large != null)price += 300;
		if(designDB.img3_front_small != null)price += 100;
		if(designDB.img4_back_small != null)price += 200;
		
		if(this.shirt_size.equals("S"))price += 200;
		if(this.shirt_size.equals("M"))price += 225;	
		if(this.shirt_size.equals("L"))price += 250;
		if(this.shirt_size.equals("XL"))price += 275;
		if(this.shirt_size.equals("XXL"))price += 300;
		
		return price*qty;
	}
	
}
