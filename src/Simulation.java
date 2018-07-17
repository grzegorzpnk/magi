
import java.util.ArrayList;
import java.util.Random;


public class Simulation {

	public static ArrayList<Float> pstwo = new ArrayList<Float>();
	public static ArrayList<Float> ruch = new ArrayList<Float>();
	int iloscGodzin = 17;//6-22
	int maxIloscKursow = 3;//tyle kursów zrobi taxa w ciągu godziny
	static int posiadaneTaxy = 9; //ile taksówek jest przypisanych do danego punktu na godzine
	float mozliweKursyNaGodzine = (float) maxIloscKursow*posiadaneTaxy*ReadFromFile.nodes.size();
	
	//tą funkcją sprawdzamy jakie jest zapotrzebowanie na ruch przy rozkladzie rownomiernym -> wyliczamy liczbe tax potrzebnych do obsluzenia takiego ruchu
	public void checkVolume(float wsp){
		float traffic = 0; ruch.clear();
		
		Uniform_Distribution distribution;
		distribution = new Uniform_Distribution(ReadFromFile.nodes, wsp);
		//distribution.losujZapotrzebowanie(ReadFromFile.nodes);
		
		//tu sprawdz
		for (int i=0; i < 17; i++)	{			
			for (int x =0; x<ReadFromFile.nodes.size();x++)	
				traffic+=ReadFromFile.nodes.get(x).traffic;
			
			ruch.add(traffic*100/76);		
			distribution=null;
			distribution = new Uniform_Distribution(ReadFromFile.nodes, wsp);	
			traffic=0;
		}		
		
		 int tmp=0;
			for(int i=0;i<ruch.size();i++)
				tmp+=ruch.get(i)*10;
			System.out.println("calkowite zapotrzebowanie w ciagu dnia: "+tmp);
			System.out.println("średnie zapotrzebowanie na godzine: "+tmp/iloscGodzin);
			System.out.println("średnie ilość taksówek na godzine: "+tmp/iloscGodzin/maxIloscKursow);
			System.out.println("ilość taksówek na godzine: na punkt "+tmp/iloscGodzin/maxIloscKursow/ReadFromFile.nodes.size());
		
	}

	
	
	public void checkVolumeGoogle(){
		 //zadeklarowane zmienne
		 ruch.clear(); pstwo.clear();
		 float traffic = 0; //zmienna pomocnicza do sumowania ruchu z każego węzła w każdej iteracji godzinnej
		 float pasazerowie = 0;//zmienna pomocnicza do sumowania ludzi (ruch *10) z każego węzła w każdej iteracji godzinne
		 float mozliweKursyNaGodzine = (float) maxIloscKursow*posiadaneTaxy*ReadFromFile.nodes.size();

		 
		for(int i=6; i<6+iloscGodzin; i++){//taka petla bo mamy graf od godziny 6 rano przez nastepne 16 godzin
			try{ChangeGraph add = new ChangeGraph("graph"+i);} catch (Exception f){}
			traffic = 0;
			pasazerowie = 0;
			for (int x =0; x<ReadFromFile.nodes.size();x++)
				traffic+=ReadFromFile.nodes.get(x).traffic;

			 
			ruch.add(traffic*100/76);// do tablicy kreślącej ruch dodaj zsumowany ruch z godziny
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


