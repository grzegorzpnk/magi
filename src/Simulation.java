
import java.util.ArrayList;
import java.util.Random;


public class Simulation {

	public static ArrayList<Float> pstwo = new ArrayList<Float>();
	public static ArrayList<Float> ruch = new ArrayList<Float>();
	
	
	public void checkVolume(){
		int volume = 0; int cnt1 = 0; int cnt2 = 0; ruch.clear();
		Uniform_Distribution distribution;
		
		distribution = new Uniform_Distribution();
		distribution.losujZapotrzebowanie(ReadFromFile.nodes);
		
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
				ruch.add((float) ((float)cnt1/(float)(cnt1+cnt2)));
			distribution.losujZapotrzebowanie(ReadFromFile.nodes);
	}

		System.out.println("Zapotrzebowanie: "+ volume +". Odrzucone: "+cnt2);
		
	}

	
	
	public void checkVolumeGoogle(){
		 //zadeklarowane zmienne
		 ruch.clear(); pstwo.clear();
		 float traffic = 0; //zmienna pomocnicza do sumowania ruchu z każego węzła w każdej iteracji godzinnej
		 float pasazerowie = 0;//zmienna pomocnicza do sumowania ludzi (ruch *10) z każego węzła w każdej iteracji godzinnej
		 int maxIloscKursow = 3;//tyle kursów zrobi taxa w ciągu godziny
		 int posiadaneTaxy = 6; //ile taksówek jest przypisanych do danego punktu na godzine
		 float mozliweKursyNaGodzine = (float) maxIloscKursow*posiadaneTaxy*ReadFromFile.nodes.size();

		 int iloscGodzin = 15;
		for(int i=7; i<7+iloscGodzin; i++){//taka petla bo mamy graf od godziny 7 rano przez nastepne 15 godzin
			try{ChangeGraph add = new ChangeGraph("graph"+i);} catch (Exception f){}
			traffic = 0;
			pasazerowie = 0;
			for (int x =0; x<ReadFromFile.nodes.size();x++)
				traffic+=ReadFromFile.nodes.get(x).traffic;

			 
			ruch.add(traffic);// do tablicy kreślącej ruch dodaj zsumowany ruch z godziny
			pasazerowie = traffic *10; //to moje zalozenie ze ruch to 10krotnosc tego podanego w node
			if(pasazerowie<=posiadaneTaxy*ReadFromFile.nodes.size()*maxIloscKursow)
				pstwo.add((float)0);
			else
			pstwo.add((pasazerowie-mozliweKursyNaGodzine)/(mozliweKursyNaGodzine));
				
			System.out.println("Ruch o godzinie: "+i+" wyniósł: "+traffic);
			System.out.println("Zapotrzebowanie o godzinie: "+i+" wyniosło: "+pasazerowie);
			if(pasazerowie<=posiadaneTaxy*ReadFromFile.nodes.size()*maxIloscKursow)
				System.out.println("pstwo odrzucenia wyniosło: 0");
			else
			System.out.println("pstwo odrzucenia wyniosło: "+(pasazerowie-mozliweKursyNaGodzine)/mozliweKursyNaGodzine);
			
		}
		 int tmp=0;
		for(int i=0;i<ruch.size();i++)
			tmp+=ruch.get(i)*10;
		System.out.println("calkowite zapotrzebowanie w ciagu dnia: "+tmp);
		System.out.println("średnie zapotrzebowanie na godzine: "+tmp/iloscGodzin);
		System.out.println("średnie ilość taksówek na godzine: "+tmp/iloscGodzin/maxIloscKursow);
		System.out.println("ilość taksówek na godzine: na punkt "+tmp/iloscGodzin/maxIloscKursow/ReadFromFile.nodes.size());
	}
	
}	


