import java.util.ArrayList;


public class Algorythm {

	
	
	
	
	public void WykonajAlgorytm(){
		 Simulation.pstwo.clear();
		 Simulation.czastkowePstwa.clear();
		 //inicjacja tablicy
		 for(int i=6; i<6+Simulation.iloscGodzin+6; i++)
			 Simulation.czastkowePstwa.add(new ArrayList<Float>());
		 
		for(int i=6; i<6+Simulation.iloscGodzin; i++){
			try{ReadFromFile read = new ReadFromFile("graph"+i);} catch (Exception f){}
		//if(i == 15)
			PrzydzielTaksy();
			
			for (int x = 0; x < ReadFromFile.nodes.size(); x++){
				float wszyscyPasazerowieWDanymWezle =  ReadFromFile.nodes.get(x).traffic * Simulation.zapotrzebowaniePerProg;
				if(wszyscyPasazerowieWDanymWezle<=Simulation.maxIloscKursow*ReadFromFile.nodes.get(x).taxiNumber)
					Simulation.czastkowePstwa.get(i).add((float)0);
				else
					Simulation.czastkowePstwa.get(i).add((wszyscyPasazerowieWDanymWezle-Simulation.maxIloscKursow*ReadFromFile.nodes.get(x).taxiNumber)/(wszyscyPasazerowieWDanymWezle));					
			}
		}

		for(int i=6; i<6+Simulation.iloscGodzin; i++){
			float tmp=0;			
			for (int x = 0; x < ReadFromFile.nodes.size(); x++)
				tmp += Simulation.czastkowePstwa.get(i).get(x);
			tmp/=(float)ReadFromFile.nodes.size();
			Simulation.pstwo.add(tmp);
			System.out.println("Pstwo o godzinie "+ i+"wynioslo: "+tmp);
			tmp=0;
			
			
		}
		
	}

	public void PrzydzielTaksy(){
		int dostepneTakowki = Simulation.posiadaneTaxy*ReadFromFile.nodes.size();
		
		for (int x = 0; x < ReadFromFile.nodes.size(); x++){
			
			if(ReadFromFile.nodes.get(x).traffic==4)
				ReadFromFile.nodes.get(x).taxiNumber=7;
			if(ReadFromFile.nodes.get(x).traffic==3)
				ReadFromFile.nodes.get(x).taxiNumber=7;
			if(ReadFromFile.nodes.get(x).traffic==2)
				ReadFromFile.nodes.get(x).taxiNumber=5;
			if(ReadFromFile.nodes.get(x).traffic==1)
				ReadFromFile.nodes.get(x).taxiNumber=3;
			if(ReadFromFile.nodes.get(x).traffic==0)
				ReadFromFile.nodes.get(x).taxiNumber=0;
		}
		
	}
	
	
	
}
