import java.awt.Rectangle;

public class Hero {
	
	private int hp;
	private Rectangle rect;
	private String image;
	
	public Hero() {
		hp = 100;
		rect = new Rectangle(0,0,0,0);
		image = "";
	}
	
	public Hero(int hpIn, String type) {
		hp = hpIn;
		if(type.equals("player")) {
			rect = new Rectangle(450, 435,50,50);
			image = "billGates.jpg";
		}else if(type.equals("enemy")){
			rect = new Rectangle(450, 140,50,50);
			image = "steveJobs.jpg";			
		}else {
			
		}
				
	}
	
	public String getImage() {
		return image;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public int getHp() {
		return hp;
	}
	
	public boolean takeDamage(int damageIn) {
		hp -= damageIn;
		//System.out.println(hp);
		if(hp <= 0) {
			return true;
		}
		return false;
	}
	
}
