import java.awt.Rectangle;

public interface Card {
	public void setRect(int x, int y);
	public void setRect(int x, int y, int width, int height);
	public String getType();
	public void setType(String typeIn);
	public Rectangle getRect();
	public void setName(String nameIn);
	public String getName();
	public int getCost();
	public String getSourceBig();
	public String getPos();
	public void setPos(String posIn);
}
