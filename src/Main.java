import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {


	
	public static void main(String[] args) throws FileNotFoundException {


	
	//stworzmy GUI
	/*ReadFromFile read = new ReadFromFile("graph9");
	GUI gui = new GUI();
	*/
		
		Frame frame = new Frame();
		frame.setVisible(true);
		 try{ChangeGraph add = new ChangeGraph("start");} catch (Exception f){}
	}

	
}
