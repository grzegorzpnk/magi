
public class Node {

	public Node(){}
	public Node(int x, int y, String _name, String _color)
	{
		posX = x;
		posY=y;
		name = _name;
		color = _color;
		request = false;
	}
	
	public int posX;
	public int posY;
	public String name;
	public String color;
	public boolean request;
	   	
	
}
