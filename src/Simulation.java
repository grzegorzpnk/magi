
import java.util.ArrayList;
import java.util.Random;


public class Simulation {
	public static  ArrayList<ArrayList<Float>> czastkowePstwa = new ArrayList<ArrayList<Float>>();
	public static ArrayList<Float> pstwo = new ArrayList<Float>();
	public static ArrayList<Float> ruch = new ArrayList<Float>();
	static int iloscGodzin = 17;//6-22
	static int maxIloscKursow = 3;//tyle kursów zrobi taxa w ciągu godziny, bo 19 minut to średni kurs
	static int posiadaneTaxy = 9; //ile taksówek jest przypisanych do danego punktu na godzine
	float mozliweKursyNaGodzine = (float) maxIloscKursow*posiadaneTaxy*ReadFromFile.nodes.size();
	static int zapotrzebowaniePerProg = 10; //prog wynosi 1,2,3,4, ile razy mnozymy ruch zeby wyszla ilosc pasazerow
	int maksymalnyProg=4;
	float wspolczynnikPrzeliczeniaRuchu = (float) (100.0 / (ReadFromFile.nodes.size()*maksymalnyProg));
	
	
	
	//tą funkcją sprawdzamy jakie jest zapotrzebowanie na ruch przy rozkladzie rownomiernym -> wyliczamy liczbe tax potrzebnych do obsluzenia takiego ruchu
	public void checkVolume(float wsp){
		System.out.println(wspolczynnikPrzeliczeniaRuchu);
		float traffic = 0; ruch.clear();
		Uniform_Distribution distribution = new Uniform_Distribution(ReadFromFile.nodes, wsp);
				
		for (int i=0; i < iloscGodzin; i++)	{			
			for (int x =0; x<ReadFromFile.nodes.size();x++)	
				traffic+=ReadFromFile.nodes.get(x).traffic;
			
			ruch.add(traffic*wspolczynnikPrzeliczeniaRuchu);//współczynnik przeskalowania, obecnie max mozemy wyciagnac 19 nodeów * 4 ocena = 76		
			distribution = new Uniform_Distribution(ReadFromFile.nodes, wsp);	
			traffic=0;
		}		
		
		 int tmp=0;
			for(int i=0;i<ruch.size();i++)
				tmp+=ruch.get(i)*zapotrzebowaniePerProg;
			System.out.println("calkowite zapotrzebowanie w ciagu dnia: "+tmp);
			System.out.println("średnie zapotrzebowanie na godzine: "+tmp/(iloscGodzin-1));
			System.out.println("średnie ilość taksówek na godzine: "+tmp/(iloscGodzin-1)/maxIloscKursow);
			System.out.println("ilość taksówek na godzine: na punkt "+tmp/(iloscGodzin-1)/maxIloscKursow/ReadFromFile.nodes.size());
		
	}

	
	
	public void checkVolumeGoogle(){
		
		
		 ruch.clear(); pstwo.clear();
		 float traffic = 0; //zmienna pomocnicza do sumowania ruchu z każego węzła w każdej iteracji godzinnej
		 float pasazerowie = 0;//zmienna pomocnicza do sumowania ludzi (ruch *10) z każego węzła w każdej iteracji godzinne
		 float mozliweKursyNaGodzine = (float) maxIloscKursow*posiadaneTaxy*ReadFromFile.nodes.size();

				 
		 
		for(int i=6; i<6+iloscGodzin; i++){//taka petla bo mamy graf od godziny 6 rano przez nastepne 15 godzin
			try{ChangeGraph add = new ChangeGraph("graph"+i);} catch (Exception f){}
			traffic = 0; pasazerowie = 0;
			
			for (int x =0; x<ReadFromFile.nodes.size();x++)
				traffic+=ReadFromFile.nodes.get(x).traffic;
			
			ruch.add(traffic*wspolczynnikPrzeliczeniaRuchu);// do tablicy kreślącej ruch dodaj zsumowany ruch z godziny
			pasazerowie = traffic *zapotrzebowaniePerProg; //to moje zalozenie ze ruch to 10krotnosc tego podanego w node
			
			if(pasazerowie<=mozliweKursyNaGodzine){
				pstwo.add((float)0);
				System.out.println("pstwo odrzucenia wyniosło: 0");
			}
			else{
				pstwo.add((pasazerowie-mozliweKursyNaGodzine)/(pasazerowie));
				System.out.println("pstwo odrzucenia wyniosło: "+(pasazerowie-mozliweKursyNaGodzine)/pasazerowie);
			}
				
			System.out.println("Ruch o godzinie: "+i+" wyniósł: "+traffic);
			System.out.println("Pasażerowie o godzinie: "+i+" : "+pasazerowie);

		}

		
		
		/*
		int tmp=0;
		for(int i=0;i<ruch.size();i++)
			tmp+=ruch.get(i)*zapotrzebowaniePerProg;
		System.out.println("calkowite zapotrzebowanie w ciagu dnia: "+tmp);
		System.out.println("średnie zapotrzebowanie na godzine: "+tmp/(iloscGodzin-1));
		System.out.println("średnie ilość taksówek na godzine: "+tmp/(iloscGodzin-1)/maxIloscKursow);
		System.out.println("ilość taksówek na godzine: na punkt "+tmp/(iloscGodzin-1)/maxIloscKursow/ReadFromFile.nodes.size());*/
	}
	
	public void checkVolumeGoogle2(){
		 pstwo.clear();
		 Simulation.czastkowePstwa.clear();
		 //inicjacja tablicy
		 for(int i=6; i<6+iloscGodzin+6; i++)
			 czastkowePstwa.add(new ArrayList<Float>());
		 
		for(int i=6; i<6+iloscGodzin; i++){//taka petla bo mamy graf od godziny 6 rano przez nastepne 15 godzin
			try{ChangeGraph add = new ChangeGraph("graph"+i);} catch (Exception f){}
			
			for (int x = 0; x < ReadFromFile.nodes.size(); x++){
				float wszyscyPasazerowieWDanymWezle =  ReadFromFile.nodes.get(x).traffic * zapotrzebowaniePerProg;
				if(wszyscyPasazerowieWDanymWezle<=maxIloscKursow*posiadaneTaxy)
					czastkowePstwa.get(i).add((float)0);
				else
					czastkowePstwa.get(i).add((wszyscyPasazerowieWDanymWezle-maxIloscKursow*posiadaneTaxy)/(wszyscyPasazerowieWDanymWezle));					
			}
		}

		for(int i=6; i<6+iloscGodzin; i++){
			float tmp=0;			
			for (int x = 0; x < ReadFromFile.nodes.size(); x++)
				tmp += czastkowePstwa.get(i).get(x);
			tmp/=(float)ReadFromFile.nodes.size();
			pstwo.add(tmp);
			System.out.println("Pstwo o godzinie "+ i+" wynioslo: "+tmp);
			tmp=0;
		}
		
	}

	
}	


