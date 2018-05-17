import java.awt.Rectangle;

public class Character {
	private String name;
	private String type;
	private int hp;
	private int atk;
	private String photoSource;
	// How much the card costs
	private int cost;
	//
	private Rectangle rect;
	private boolean hasMoved;
	
	public Character() {
		name = "Test";
		type = "Ally";
		hp = 0;
		atk = 0;
		photoSource = "";
		rect = new Rectangle(0,0,0,0);
		hasMoved = false;
	}
	
	public Character(String nameIn, int hpIn, int atkIn, int costIn, String photo) {
		name = nameIn;
		type = "Ally";
		hp = hpIn;
		atk = atkIn;
		cost = costIn;
		photoSource = photo;
		rect = new Rectangle(0,0,50,50);
		hasMoved = false;
	}
	
	public void takeDamage(int damage) {
		hp -= damage;
	}
	
	public void reduceCost(int costIn) {
		cost -= costIn;
	}
	
	public void setRect(int x, int y) {
		rect.setLocation(x, y);
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void changeHasMoved() {
		hasMoved = !hasMoved;
	}
	
	public boolean getHasMoved() {
		return hasMoved;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getAtk() {
		return atk;
	}
	
//	public int getValue() {
//		return value;
//	}
	
	public String getSource() {
		return photoSource;
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
	
	public String toString() {
		return "Name: " + name + " Type: " + type + " HP: " + hp + " Atk: "+ atk + " Cost: " + cost;

	}
}
