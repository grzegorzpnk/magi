
public class Node {

	public Node(){}
	public Node(int x, int y, String _name, String _color)
	{
		posX = x;
		posY=y;
		name = _name;
		color = _color;
		request = false;
		
		if(color.equals("silver"))
				traffic = 0;
		else if (color.equals("green"))
			traffic = 1;
		else if (color.equals("yellow"))
			traffic = 2;
		else if (color.equals("orange"))
			traffic = 3;
		else if (color.equals("red"))
			traffic = 4;
		
	}
	
	
	public int posX;
	public int posY;
	public String name;
	public String color;
	public boolean request;
	public float traffic;  //ruch w tym wezle, w skali [0,4] 	
	public int taxiNumber; //ilosc taksowek przypisana temu wezlowi
	
}
