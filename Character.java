import java.awt.Rectangle;

public class Character {
	private String name;
	private String type;
	private int hp;
	private int atk;
	private String bigPhotoSource;
	private String littlePhotoSource;
	private int cost;
	private Rectangle rect;
	private boolean hasMoved;

	public Character() {
		name = "Test";
		type = "Ally";
		hp = 0;
		atk = 0;
		cost = 0;
		bigPhotoSource = "";
		littlePhotoSource = "";
		rect = new Rectangle(0, 0, 0, 0);
		hasMoved = true;
	}

	public Character(String nameIn, int hpIn, int atkIn, int costIn, String bigPhotoIn, String littlePhotoIn) {
		name = nameIn;
		type = "Ally";
		hp = hpIn;
		atk = atkIn;
		cost = costIn;
		bigPhotoSource = bigPhotoIn;
		littlePhotoSource = littlePhotoIn;
		rect = new Rectangle(0, 0, 100, 100);
		hasMoved = true;
	}

	public boolean takeDamage(int damage) {
		hp -= damage;
		
		if(hp <= 0) {
			return true;
		}
		return false;
		
	}
	
	public void highlight() {
		
	}

	public void reduceCost(int costIn) {
		cost -= costIn;
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
}
