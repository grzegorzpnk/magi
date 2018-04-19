import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {


	
	public static void main(String[] args) throws FileNotFoundException {

		/*Frame frame = new Frame();
		frame.setVisible(true);*/
		try{ChangeGraph add = new ChangeGraph("start");} catch (Exception f){}
		
		
		
		Uniform_Distribution tmp = new Uniform_Distribution(ReadFromFile.nodes);
		for(int i=0; i<ReadFromFile.nodes.size();i++)
		{
			System.out.println(ReadFromFile.nodes.get(i).name+" == "+ReadFromFile.nodes.get(i).request);
			
		}
		
		
	}

	
}
