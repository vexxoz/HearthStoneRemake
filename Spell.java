import java.awt.Rectangle;

public class Spell implements Card{
	
	private String name;
	private String type;
	private String spellType;
	private int damage;
	private int heal;
	private int cost;
	private String bigPhotoSource;
	private String littlePhotoSource;	
	private Rectangle rect;
	private String pos;
	
	public Spell() {
		name = "";
		type = "Spell";
		damage = 0;
		heal = 0;
		cost = 0;
		rect = new Rectangle(0, 0, 0, 0);
		bigPhotoSource = "";
		littlePhotoSource = "";
		pos = "Hand";
		spellType = "";
	}
	
	public Spell(String nameIn, String spellTypeIn, int costIn, int damageIn, int healIn, String bigPhotoIn, String littlePhotoIn) {
		name = nameIn;
		type = "Spell";
		spellType = spellTypeIn;
		cost = costIn;
		damage = damageIn;
		heal = healIn;
		rect = new Rectangle(0, 0, 100, 100);
		bigPhotoSource = bigPhotoIn;
		littlePhotoSource = littlePhotoIn;
		pos = "Hand";
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

	public void setType(String typeIn) {
		type = typeIn;
		
	}	
	
	public String getPos() {
		return pos;
	}

	public void setPos(String posIn) {
		pos = posIn;
		
	}	
	
	public String getSpellType() {
		return spellType;
	}
	
	public void setSpellType(String spellTypeIn) {
		spellType = spellTypeIn;
	}
	
}
