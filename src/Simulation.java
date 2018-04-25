
import java.util.ArrayList;


public class Simulation {

	public Uniform_Distribution tmp;
	public static int volume=0;
	public static ArrayList<Float> pstwa = new ArrayList<Float>();
	int cnt2=0, cnt1=0;
	
	public Simulation(){
		tmp = new Uniform_Distribution();
		tmp.losujZapotrzebowanie(ReadFromFile.nodes);

	/*for(int i=0; i<ReadFromFile.nodes.size();i++)
		System.out.println(ReadFromFile.nodes.get(i).name+" == "+ReadFromFile.nodes.get(i).request);
	*/
	
	}
	
	//funkcja powinna zwraca 60 wynikow, czyli symulacja godzinowa
	//kazdy wynik to pstwo odrzucenia naszego zgloszenia w kazdej stacji
	public void conductSimulation(boolean symType, int start)//0-N,1-G  start -> godizna startowa
	{	
		for (int i=0; i < 12*16; i++)//co 5 minut * 16 godzin (7-23)
			for (int x =0; x<ReadFromFile.nodes.size();i++){
				
				//sprawdz czy jest zgloszenie
				
			}		
	}
	
	public void checkVolume(){
		volume = 0; cnt1 = 0; cnt2 = 0; pstwa.clear();
		
		//co 5 minut * 16 godzin (7-23)
		for (int i=0; i < 12*16; i++){
			cnt1=0;
			cnt2=0;
			
			for (int x =0; x<ReadFromFile.nodes.size();x++){
				if(ReadFromFile.nodes.get(x).request){
					volume++;
					cnt1++;
				}
				else
					cnt2++;
			}
			tmp.losujZapotrzebowanie(ReadFromFile.nodes);
			pstwa.add((float) ((float)cnt1/(float)(cnt1+cnt2)));
	}

		System.out.println("Zapotrzebowanie: "+ volume +". Odrzucone: "+cnt2);
		
	}
	
}

