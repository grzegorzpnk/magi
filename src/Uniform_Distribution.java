import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Uniform_Distribution {
	
	public void losujZapotrzebowanie(List<Node> nodes){

	List<Integer> l = new ArrayList<Integer>();
	Random rnd = new Random();
	
	for (int i = 0; i < nodes.size(); i++) 
	    l.add(rnd.nextInt(2));
	
	Collections.shuffle(l);
		
	Integer tmp=0;
		
	for(int i=0;i<nodes.size();i++)
	{
		if(l.get(i) == 1){
		nodes.get(i).request = true;
		//tmp++;
		}
		else
		nodes.get(i).request = false;
		
	}
		

	//System.out.println(((float) tmp)/nodes.size());
	
	
}


}
