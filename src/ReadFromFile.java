import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ReadFromFile {
	
	static List<Node> nodes  = new ArrayList<Node>();
	static List<Edge> edges  = new ArrayList<Edge>();
	static int nodesCnt=0;
	static int licznik =0;
	
	public ReadFromFile(String fileName) throws FileNotFoundException{
		ReadConfigFromFile(fileName);
	}

	  public static void ReadConfigFromFile(String file) throws FileNotFoundException{
	    	nodes.clear();
	    	edges.clear();
	    	licznik =0;
	    	Scanner fileIn = new Scanner(new File(file));
	    	 

	    	nodesCnt = fileIn.nextInt();
	    	int edgesCnt = fileIn.nextInt();
	    
	 for (int i = 0; i<nodesCnt; i++)
	    	{
	    		int posX=0,posY=0;
	    		String name=null, color=null;
	    		posY = fileIn.nextInt();
	    		posX = fileIn.nextInt();
	    		name = fileIn.next();
	    		color = fileIn.next();
	    		Node nodeTmp = new Node(posX,posY,name,color);
	    		nodes.add(nodeTmp);
	    		//System.out.println("Wczytano nowy wezel: "+name);
	    		//System.out.println(nodes.size());
	    	}
	    	
	 	
	    	for (int i = 0; i<edgesCnt; i++)
	    	{
	    		String source=null,target = null;
	    		source = fileIn.next();
	    		target = fileIn.next();
	    		Edge edgeTmp= new Edge(source, target);
	    		edges.add(edgeTmp);
	    	}
	    	   		
	    	fileIn.close();
	    }
	



}
