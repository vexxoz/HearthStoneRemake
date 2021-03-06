import java.awt.Rectangle;

public class Character implements Card{
	private String name;
	private String type;
	private int hp;
	private int maxHp;
	private int atk;
	private int cost;
	private String bigPhotoSource;
	private String littlePhotoSource;
	private Rectangle rect;
	private boolean hasMoved;
	private String pos;

	/**
	 * Constructor for a Character card
	 */
	public Character() {
		name = "Test";
		type = "Character";
		hp = 0;
		maxHp = 0;
		atk = 0;
		cost = 0;
		bigPhotoSource = "";
		littlePhotoSource = "";
		rect = new Rectangle(0, 0, 0, 0);
		hasMoved = true;
		pos = "Hand";
	}

	/**
	 * Constructor for a Character card with params
	 * @param nameIn name of the Character
	 * @param hpIn health of the character
	 * @param atkIn attack of the character
	 * @param costIn cost of the character
	 * @param bigPhotoIn big image for the character (100x100)
	 * @param littlePhotoIn smaller photo for the character (50x50)
	 */
	public Character(String nameIn, int hpIn, int atkIn, int costIn, String bigPhotoIn, String littlePhotoIn) {
		name = nameIn;
		type = "Character";
		hp = hpIn;
		maxHp = hp;
		atk = atkIn;
		cost = costIn;
		bigPhotoSource = bigPhotoIn;
		littlePhotoSource = littlePhotoIn;
		rect = new Rectangle(0, 0, 100, 100);
		hasMoved = true;
		pos = "Hand";
	}

	public boolean takeDamage(int damage) {
		hp -= damage;
		
		if(hp <= 0) {
			return true;
		}
		return false;
		
	}

	public void setName(String nameIn) {
		name = nameIn;
	}	
	
	public void reduceCost(int costIn) {
		cost -= costIn;
	}

	public void setRect(int x, int y) {
		rect.setLocation(x, y);
	}

	public void heal(int amount) {
		hp += amount;
		if(hp > maxHp) {
			hp = maxHp;
		}
	}
	
	public void setRect(int x, int y, int width, int height) {
		rect.setLocation(x, y);
		rect.setSize(width, height);
	}	

	public Rectangle getRect() {
		return rect;
	}

	public void setHp(int hpIn) {
		hp = hpIn;
	}

	public void setAtk(int atkIn) {
		atk = atkIn;
	}
	
	public void changeHasMovedTrue() {
		hasMoved = true;
	}

	public void changeHasMovedFalse() {
		hasMoved = false;
	}
	
	public boolean getHasMoved() {
		return hasMoved;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}
	

	public int getHp() {
		return hp;
	}

	public int getAtk() {
		return atk;
	}

	public String getSourceBig() {
		return bigPhotoSource;
	}

	public String getSourceLittle() {
		return littlePhotoSource;
	}

	public String toString() {
		return "Name: " + name + " Type: " + type + " HP: " + hp + " Atk: " + atk + " Cost: " + cost;

	}


	public String getType() {
		return type;
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
}
