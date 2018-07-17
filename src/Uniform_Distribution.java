import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Uniform_Distribution {
	
	
	
	  public Uniform_Distribution(List<Node> nodes, float wsp){

	List<Integer> l = new ArrayList<Integer>();
	Random rnd = new Random();
	
	for (int i = 0; i < nodes.size(); i++) 
	    l.add(rnd.nextInt(5));
	
	Collections.shuffle(l);
	
		
	for(int i=0;i<nodes.size();i++)
		nodes.get(i).traffic = ((float) l.get(i)) *wsp;
		
	/*float tmp=0;
	for(int i=0;i<l.size();i++)
		tmp+=((float)l.get(i))*wsp;
	System.out.println("śuma rozkladu to : "+ tmp/nodes.size());
		*/
	
	
}

}
