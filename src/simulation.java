
public class simulation {

	public Uniform_Distribution tmp;
	
	public simulation(){
		tmp = new Uniform_Distribution(ReadFromFile.nodes);

	for(int i=0; i<ReadFromFile.nodes.size();i++)
		System.out.println(ReadFromFile.nodes.get(i).name+" == "+ReadFromFile.nodes.get(i).request);
	
	
	}
	
	//funkcja powinna zwraca 60 wynikow, czyli symulacja godzinowa
	//kazdy wynik to pstwo odrzucenia naszego zgloszenia w kazdej stacji
	public void conductSimulation(boolean symType, int start)//0-N,1-G  start -> godizna startowa
	{
		for (int i=0; i < 60; i++)
			for (int x =0; x<ReadFromFile.nodes.size();i++){
				
				//sprawdz czy jest zgloszenie
				
			}		
	}
	
}

