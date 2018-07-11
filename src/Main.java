import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {


	
	public static void main(String[] args) throws FileNotFoundException {

//		Frame frame = new Frame();
	//	frame.setVisible(true);

		try{ChangeGraph add = new ChangeGraph("graph7");} catch (Exception f){}
		Simulation simulation = new Simulation();
		simulation.checkVolumeGoogle();

		Chart chart = new Chart();
		chart.setVisible(true);

	/*	List<Double> l = new ArrayList<Double>();
		
		Random rnd = new Random ();
		
		for (int i = 0; i < 20; i++) 
		    l.add(rnd.nextGaussian()*50 +100);
		
		for (int x = 0; x < 20; x++)
		System.out.println(l.get(x));
		
		*/
		
		
		
		
		
		
		
		
	}

	
}
