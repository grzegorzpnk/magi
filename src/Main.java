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

		try{ChangeGraph add = new ChangeGraph("graph6");} catch (Exception f){}
		Simulation simulation = new Simulation();
		simulation.posiadaneTaxy=5;
		//simulation.checkVolume((float)(0.66666));
		//simulation.checkVolumeGoogle();
		
		//Chart chart = new Chart();
		//chart.setVisible(true);

		simulation.checkVolumeGoogle2();
		Chart chart2 = new Chart();
		chart2.setVisible(true);
		
		Algorythm alg = new Algorythm();
		alg.WykonajAlgorytm();
		
		Chart chart3 = new Chart();
		chart3.setVisible(true);
		
		
		/*Simulation simulation2 = new Simulation();
		simulation2.checkVolume((float)1.0); //UWAGA! zazwyczaj jest to losowanie z pstwem 1/2, wiec przemysl wspolczynnik!

		Chart chart2 = new Chart();
		chart2.setVisible(true);
*/			
	}

	
}
