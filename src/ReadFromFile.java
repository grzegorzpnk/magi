import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadFromFile {
	
	public ReadFromFile(String fileName) throws FileNotFoundException{
		ReadConfigFromFile(fileName);
	}

	  public static void ReadConfigFromFile(String file) throws FileNotFoundException{
	    	Graph.nodes.clear();
	    	Graph.edges.clear();
	    	Scanner fileIn = new Scanner(new File(file));
	    	 

	    	int nodesCnt = fileIn.nextInt();
	    	int edgesCnt = fileIn.nextInt();
	    
	 for (int i = 0; i<nodesCnt; i++)
	    	{
	    		int posX=0,posY=0;
	    		String name=null;
	    		posX = fileIn.nextInt();
	    		posY = fileIn.nextInt();
	    		name = fileIn.next();
	    		Node nodeTmp = new Node(posX,posY,name);
	    		Graph.nodes.add(nodeTmp);
	    	}
	    	
	    	for (int i = 0; i<edgesCnt; i++)
	    	{
	    		String source=null,target = null;
	    		source = fileIn.next();
	    		target = fileIn.next();
	    		Edge edgeTmp= new Edge(source, target);
	    		Graph.edges.add(edgeTmp);
	    	}
	    	   		
	    	fileIn.close();
	    }
	
}
