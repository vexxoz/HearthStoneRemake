import java.awt.Rectangle;

public class Spell implements Card{
	
	private String name;
	private String type;
	private int damage;
	private int heal;
	private int cost;
	private String bigPhotoSource;
	private String littlePhotoSource;	
	private Rectangle rect;
	
	public Spell() {
		name = "";
		type = "none";
		damage = 0;
		heal = 0;
		cost = 0;
		rect = new Rectangle(0, 0, 0, 0);
		bigPhotoSource = "";
		littlePhotoSource = "";
		
	}
	
	public Spell(String nameIn, String typeIn, int damageIn, int healIn, String bigPhotoIn, String littlePhotoIn) {
		name = nameIn;
		type = typeIn;
		damage = damageIn;
		heal = healIn;
		rect = new Rectangle(0, 0, 100, 100);
		bigPhotoSource = bigPhotoIn;
		littlePhotoSource = littlePhotoIn;
	}
	
	public void setRect(int x, int y) {
		rect.setLocation(x, y);
	}
	
	public void setRect(int x, int y, int width, int height) {
		rect.setLocation(x, y);
		rect.setSize(width, height);
	}	
	
	public Rectangle getRect() {
		return rect;
	}	


	public void setName(String nameIn) {
		name = nameIn;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}
	
	public String getType() {
		return type;
	}
	
	public int getHeal() {
		return heal;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public String getSourceBig() {
		return bigPhotoSource;
	}

	public String getSourceLittle() {
		return littlePhotoSource;
	}
	
	public String toString() {
		return "Name: " + name + " Type: " + type + " Damage: " + damage + " Heal: " + heal + " Cost: " + cost;

	}	
	
}
