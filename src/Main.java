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
		Simulation simulation = new Simulation();
		System.out.println("zakonczylem dzialanie");
		simulation.checkVolume();
		System.out.println("zakonczylem dzialanie2");
		Chart chart = new Chart();
		chart.setVisible(true);
		System.out.println("zakonczylem dzialanie3");
		
		
		
		
		
		
	}

	
}
