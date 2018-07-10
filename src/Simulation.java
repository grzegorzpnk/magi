
import java.util.ArrayList;
import java.util.Random;


public class Simulation {

	public Uniform_Distribution tmp;
	public static int volume=0;
	public static ArrayList<Float> pstwa = new ArrayList<Float>();
	int cnt2=0, cnt1=0;
	
	public Simulation(){
		tmp = new Uniform_Distribution();
		tmp.losujZapotrzebowanie(ReadFromFile.nodes);
	
	}
	/*
	//funkcja powinna zwraca 60 wynikow, czyli symulacja godzinowa
	//kazdy wynik to pstwo odrzucenia naszego zgloszenia w kazdej stacji
	public void conductSimulation(boolean symType, int start)//0-N,1-G  start -> godizna startowa
	{	
		for (int i=0; i < 12*15; i++)//co 5 minut * 16 godzin (7-22)
			for (int x =0; x<ReadFromFile.nodes.size();i++){
				
				//sprawdz czy jest zgloszenie
				
			}		
	}
	*/
	
	public void checkVolume(){
		volume = 0; cnt1 = 0; cnt2 = 0; pstwa.clear();
		
		//co 5 minut * 16 godzin (6-22)
		for (int i=0; i < 12*16; i++){
			cnt1=0; cnt2=0;	
			for (int x =0; x<ReadFromFile.nodes.size();x++){
				if(ReadFromFile.nodes.get(x).request){
					volume++; 
					cnt1++;
				}
				else cnt2++;
				}
				pstwa.add((float) ((float)cnt1/(float)(cnt1+cnt2)));
			tmp.losujZapotrzebowanie(ReadFromFile.nodes);
	}

		System.out.println("Zapotrzebowanie: "+ volume +". Odrzucone: "+cnt2);
		
	}

	

	public void checkVolumeGauss(){
		volume = 0; cnt1 = 0; cnt2 = 0; pstwa.clear();
		int godzina  = 6;
		int minuta = 0;
		float wsp;
		
		for (int i=0; i < 12*16; i++){
			cnt1=0; cnt2=0; wsp=0;
			
			for (int x =0; x<ReadFromFile.nodes.size();x++){
				if(ReadFromFile.nodes.get(x).request)
					cnt1++;
				else cnt2++;
			}
				
			
			//gauss
			
			if(godzina == 6){
			
				wsp = -(float)1.25*minuta/5/100;
					System.out.println(wsp);
			}

			if(godzina == 7){
				wsp = -(float)1.25*minuta/5/100;
					System.out.println(wsp);
			}
			if(godzina == 8)
				wsp = (float)2.5*minuta/5/100;
			if(godzina == 9){
				int minuta2 = 60-minuta;
				wsp = (float)2.5*minuta2/5/100;
			}
			
			if(godzina == 14)
				wsp = (float)1.25*minuta/5/100;
			if(godzina == 15)
			{
				int minuta2 = minuta+60;
				wsp = (float)1.25*minuta2/5/100;
			}
			if(godzina == 16){
				int minuta2 = 120-minuta;
				wsp = (float)1.25*minuta2/5/100;
			}
			if(godzina == 17){
				int minuta2 = 60-minuta;
				wsp = (float)1.25*minuta2/5/100;
			}
			
				//System.out.println(godzina+":"+minuta+". "+ (((float)cnt1/(float)(cnt1+cnt2))+wsp));
				pstwa.add((float) ((float)cnt1/(float)(cnt1+cnt2))+wsp);
				
			
			
			minuta +=5;
			if(minuta==60)
			{	godzina++;
				minuta=0;
			}
			tmp.losujZapotrzebowanie(ReadFromFile.nodes);
			}
		}
	
	public void checkVolumeGoogle(){
		 cnt1 = 0; cnt2 = 0; pstwa.clear();
		 float traffic = 0;
		 float podstawa=78;// to na sztywno
		for(int i=7; i<7+5; i++){
			try{ChangeGraph add = new ChangeGraph("graph"+i);} catch (Exception f){}
			traffic = 0;
			for (int x =0; x<ReadFromFile.nodes.size();x++)
				traffic+=ReadFromFile.nodes.get(x).traffic;			
			
			
			pstwa.add(traffic/podstawa);
			System.out.println("Ruch o godzinie: "+i+" wyniósł: "+pstwa);
			
		}
	}
	
}	


