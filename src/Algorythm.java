import java.util.ArrayList;

public class Algorythm {

	public void WykonajAlgorytm() {
		System.out.println("Rozpoczynam algorytm");
		Simulation.pstwo.clear();
		Simulation.czastkowePstwa.clear();
		// inicjacja tablicy
		for (int i = 6; i < 6 + Simulation.iloscGodzin + 6; i++)
			Simulation.czastkowePstwa.add(new ArrayList<Float>());

		for (int i = 6; i < 6 + Simulation.iloscGodzin; i++) {
			try {
				ReadFromFile read = new ReadFromFile("graph" + i);
			} catch (Exception f) {
			}

			System.out
					.println("Zaczynam przydzielac wezla odpowiednia ilosc taksowek o godzinie: "
							+ i);
			PrzydzielTaksy();

			for (int x = 0; x < ReadFromFile.nodes.size(); x++) {

				float wszyscyPasazerowieWDanymWezle = ReadFromFile.nodes.get(x).traffic
						* Simulation.mnoznikRuchPasazerowie;
				if (wszyscyPasazerowieWDanymWezle <= Simulation.maxIloscKursow
						* ReadFromFile.nodes.get(x).taxiNumber)
					Simulation.czastkowePstwa.get(i).add((float) 0);
				else
					Simulation.czastkowePstwa
							.get(i)
							.add((wszyscyPasazerowieWDanymWezle - Simulation.maxIloscKursow
									* ReadFromFile.nodes.get(x).taxiNumber)
									/ (wszyscyPasazerowieWDanymWezle));
			}
		}

		for (int i = 6; i < 6 + Simulation.iloscGodzin; i++) {
			float tmp = 0;
			for (int x = 0; x < ReadFromFile.nodes.size(); x++)
				tmp += Simulation.czastkowePstwa.get(i).get(x);
			tmp /= (float) ReadFromFile.nodes.size();
			Simulation.pstwo.add(tmp);
			System.out.println("Pstwo o godzinie " + i + "wynioslo: " + tmp);
			tmp = 0;

		}

	}

	public void PrzydzielTaksy() {
		int dostepneTaksowki = Simulation.posiadaneTaxy
				* ReadFromFile.nodes.size();
		int WezelZRuchem1 = 0;
		int WezelZRuchem2 = 0;
		int WezelZRuchem3 = 0;
		int WezelZRuchem4 = 0;

		System.out.println("Mamy do dyspocyzji " + dostepneTaksowki
				+ " taksowek");

		for (int x = 0; x < ReadFromFile.nodes.size(); x++) {
			if (ReadFromFile.nodes.get(x).traffic == 1)
				WezelZRuchem1++;
			if (ReadFromFile.nodes.get(x).traffic == 2)
				WezelZRuchem2++;
			if (ReadFromFile.nodes.get(x).traffic == 3)
				WezelZRuchem3++;
			if (ReadFromFile.nodes.get(x).traffic == 4)
				WezelZRuchem4++;
		}

		System.out
				.println("Mamy nastepujaca ilosc wezlow: \n" + WezelZRuchem1
						+ " wezlow z ruchem 1\n" + WezelZRuchem2
						+ " wezlow z ruchem 2\n" + WezelZRuchem3
						+ " wezlow z ruchem 3\n" + WezelZRuchem4
						+ " wezlow z ruchem 4");

		// przypisz kazdemu wezlowi liczbe tax wynikajaca z rozkladu normalnego
		for (int x = 0; x < ReadFromFile.nodes.size(); x++)
			ReadFromFile.nodes.get(x).taxiNumber = Simulation.posiadaneTaxy;

		int odzyskaneTaxy = 0;
		int iloscKursow = 0;
		
		//ODZYSKAJMY NIEPOTRZEBNR TAKSÓWKI
		for (int x = 0; x < ReadFromFile.nodes.size(); x++) {
			if (ReadFromFile.nodes.get(x).traffic == 0) {
				ReadFromFile.nodes.get(x).taxiNumber = 0;
				odzyskaneTaxy += Simulation.posiadaneTaxy;
			} else {
				for (int c = 0; c < Simulation.posiadaneTaxy; c++) {
					iloscKursow = ReadFromFile.nodes.get(x).taxiNumber * 3;
					if (iloscKursow > ReadFromFile.nodes.get(x).traffic
							* Simulation.mnoznikRuchPasazerowie) {
						// System.out.println("Zbyt duzo taksowek, zabieram jedną!");
						ReadFromFile.nodes.get(x).taxiNumber--;
						odzyskaneTaxy++;
					}
				}
			}

		}
		// for (int x = 0; x < ReadFromFile.nodes.size(); x++)
		// System.out.println("dla wezla "+ReadFromFile.nodes.get(x).name+" o ruchu "+ReadFromFile.nodes.get(x).traffic+" przydzielono "+ReadFromFile.nodes.get(x).taxiNumber+" taksówek\n");
		System.out.println("Udalo sie odzyskac: " + odzyskaneTaxy + " tax");

		for (int x = 0; x < ReadFromFile.nodes.size(); x++) {
			int IloscPozostalychKlientow = (int) (ReadFromFile.nodes.get(x).traffic * 
					Simulation.mnoznikRuchPasazerowie - ReadFromFile.nodes.get(x).taxiNumber * 3);
			
			System.out.println("W wezle " + ReadFromFile.nodes.get(x).name+" o ruchu " + ReadFromFile.nodes.get(x).traffic
					+ " pozostalo do przewiezeinia " + IloscPozostalychKlientow + " klientow");
			
			if (IloscPozostalychKlientow >= 3 & odzyskaneTaxy > 0) {
				ReadFromFile.nodes.get(x).taxiNumber++;
				odzyskaneTaxy--;
			} else 
				System.out.println("Nie ma wystarczajaco klientow, badz nie ma juz taksowek!");
			
		}
		System.out.println("Po tej iteracji zostalo " + odzyskaneTaxy + " taksówek\n");

		for (int x = 0; x < ReadFromFile.nodes.size(); x++) {
			int IloscPozostalychKlientow = (int) (ReadFromFile.nodes.get(x).traffic * Simulation.mnoznikRuchPasazerowie
					- ReadFromFile.nodes.get(x).taxiNumber * 3);
			System.out.println("W wezle " + ReadFromFile.nodes.get(x).name + " o ruchu " + ReadFromFile.nodes.get(x).traffic
					+ " pozostalo do przewiezeinia " + IloscPozostalychKlientow	+ " klientow");
			
			if (IloscPozostalychKlientow >= 2 & odzyskaneTaxy > 0) {
				ReadFromFile.nodes.get(x).taxiNumber++;
				odzyskaneTaxy--;
			} else 
				System.out.println("Nie ma wystarczajaco klientow, badz nie ma juz taksowek!");
			
		}
		
		System.out.println("Po tej iteracji zostalo " + odzyskaneTaxy + " taksówek\n");

		for (int x = 0; x < ReadFromFile.nodes.size(); x++) {
			int IloscPozostalychKlientow = (int) (ReadFromFile.nodes.get(x).traffic * Simulation.mnoznikRuchPasazerowie
					- ReadFromFile.nodes.get(x).taxiNumber * 3);
			
			System.out.println("W wezle " + ReadFromFile.nodes.get(x).name + " o ruchu " + ReadFromFile.nodes.get(x).traffic
					+ " pozostalo do przewiezeinia " + IloscPozostalychKlientow	+ " klientow");
			
			if (IloscPozostalychKlientow >= 1 & odzyskaneTaxy > 0) {
				ReadFromFile.nodes.get(x).taxiNumber++;
				odzyskaneTaxy--;
			} else 
				System.out.println("Nie ma wystarczajaco klientow, badz nie ma juz taksowek!");
			
		}
		
		System.out.println("Po tej iteracji zostalo " + odzyskaneTaxy
				+ " taksówek\n");

		///////////////////// WERSJA 2
		/*
		 * //Niech traffic 1=100, 2=150, 3=200,4=250
		 * 
		 * int suma =
		 * WezelZRuchem1*100+WezelZRuchem2*150+WezelZRuchem3*200+WezelZRuchem4
		 * *250; int dostepneTaksowki2=dostepneTaksowki; for (int x = 0; x <
		 * ReadFromFile.nodes.size(); x++){ if(
		 * ReadFromFile.nodes.get(x).traffic ==1)
		 * ReadFromFile.nodes.get(x).taxiNumber = dostepneTaksowki*100/suma; if(
		 * ReadFromFile.nodes.get(x).traffic ==2)
		 * ReadFromFile.nodes.get(x).taxiNumber = dostepneTaksowki*150/suma; if(
		 * ReadFromFile.nodes.get(x).traffic ==3)
		 * ReadFromFile.nodes.get(x).taxiNumber = dostepneTaksowki*200/suma; if(
		 * ReadFromFile.nodes.get(x).traffic ==4)
		 * ReadFromFile.nodes.get(x).taxiNumber = dostepneTaksowki*250/suma;
		 * System
		 * .out.println("dla wezla "+ReadFromFile.nodes.get(x).name+" o ruchu "
		 * +ReadFromFile
		 * .nodes.get(x).traffic+" przydzielono "+ReadFromFile.nodes
		 * .get(x).taxiNumber+" taksówek"); dostepneTaksowki2 -=
		 * ReadFromFile.nodes.get(x).taxiNumber; }
		 * 
		 * System.out.println("po przydzieleniu taksowek zostalo ich: "+
		 * dostepneTaksowki2);
		 * 
		 * 
		 * for (int x = 0; x < ReadFromFile.nodes.size(); x++){ int
		 * IloscPozostalychKlientow = (int)
		 * (ReadFromFile.nodes.get(x).traffic*10 -
		 * ReadFromFile.nodes.get(x).taxiNumber*3);
		 * System.out.println("W wezle "+ ReadFromFile.nodes.get(x).name
		 * +" o ruchu "
		 * +ReadFromFile.nodes.get(x).traffic+" pozostalo do przewiezeinia "
		 * +IloscPozostalychKlientow+" klientow"); if(IloscPozostalychKlientow
		 * >= 3 & dostepneTaksowki2>0){ ReadFromFile.nodes.get(x).taxiNumber ++;
		 * dostepneTaksowki2 --;
		 * System.out.println("taksowka zostala przydzielona "); } else
		 * System.out
		 * .println("Nie ma wystarczajaco klientow, badz nie ma juz taksowek!");
		 * 
		 * } System.out.println("Po tej iteracji zostalo "+dostepneTaksowki2+
		 * " taksówek\n" );
		 * 
		 * for (int x = 0; x < ReadFromFile.nodes.size(); x++){
		 * System.out.println("Wezel"+ ReadFromFile.nodes.get(x).name
		 * +"o ruchu "+ ReadFromFile.nodes.get(x).traffic+
		 * "dostal taksowek: "+ReadFromFile.nodes.get(x).taxiNumber); }
		 */

		///////////////// WERSJA 1
		/*
		 * //przydziel kazdemu node chociaż jedną taksę for (int x = 0; x <
		 * ReadFromFile.nodes.size(); x++){ if(ReadFromFile.nodes.get(x).traffic
		 * != 0 & dostepneTaksowki > 0 ){
		 * ReadFromFile.nodes.get(x).taxiNumber++; dostepneTaksowki--; } }
		 * 
		 * System.out.println(
		 * "Kazdemu wezlowi z ruchem przydzielilismy 1 taksowke, zostalo "+
		 * dostepneTaksowki+" wolnych taksowek");
		 */
		/*
		 * int brakZapotrzebowania=0; while(dostepneTaksowki>0 &
		 * brakZapotrzebowania!=ReadFromFile.nodes.size()) {
		 * brakZapotrzebowania=0; for (int x = 0; x < ReadFromFile.nodes.size();
		 * x++){ int IloscPozostalychKlientow = (int)
		 * (ReadFromFile.nodes.get(x).traffic*10 -
		 * ReadFromFile.nodes.get(x).taxiNumber*3);
		 * System.out.println("W wezle "+ ReadFromFile.nodes.get(x).name
		 * +" o ruchu "
		 * +ReadFromFile.nodes.get(x).traffic+" pozostalo do przewiezeinia "
		 * +IloscPozostalychKlientow+" klientow"); if(IloscPozostalychKlientow
		 * >= 3 & dostepneTaksowki>0){ ReadFromFile.nodes.get(x).taxiNumber +=
		 * (int)ReadFromFile.nodes.get(x).traffic; dostepneTaksowki
		 * -=(int)ReadFromFile.nodes.get(x).traffic;
		 * System.out.println("taksowka zostala przydzielona w ilosci"+
		 * (int)ReadFromFile.nodes.get(x).traffic); } else{ System.out.println(
		 * "Nie ma wystarczajaco klientow, badz nie ma juz taksowek!");
		 * brakZapotrzebowania++;
		 * if(brakZapotrzebowania==ReadFromFile.nodes.size()){
		 * System.out.println(
		 * "Caly ruch zostal obsadzony a pomimo to zosaly wolne taksowki w liczbie: "
		 * +dostepneTaksowki); rozlokujPozostaleTaksowki(dostepneTaksowki);} } }
		 * System
		 * .out.println("Po tej iteracji zostalo "+dostepneTaksowki+" taksówek\n"
		 * ); }
		 * 
		 * 
		 * System.out.println("Po przydzieleniu taksowek dla wezlow pozostalo "+
		 * dostepneTaksowki +" taksowek do rozdysponowania\n");
		 */

	}

	public void rozlokujPozostaleTaksowki(int dostepneTaksowki) {

	}

}
