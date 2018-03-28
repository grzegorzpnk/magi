import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;
import java.util.Scanner;

public class Print {

    public static void main(String[] args) throws FileNotFoundException {

    	//INICJALIZACJA GRAFU
    	JGraph graph = GraphInitiation();
        
    	//BLOK WCZYTYWANIA DANYCH
    	//musimy wczytywac: posX, posY, name, polaczenie z ktorym 
    	Scanner fileIn = new Scanner(new File("Graph10"));
    	
    	int nodesCnt=0;
    	int edgesCnt = 0;
    	int N;
    	
    	
    	
    	fileIn.hasNextInt(nodesCnt);
    	Node[] nodes = new Node[nodesCnt];
    	fileIn.hasNextInt(edgesCnt);
    	N=nodesCnt+edgesCnt;
    	
    	for (int i = 0; i<nodesCnt; i++)
    	{
    		int posX=0,posY=0;
    		String name=null;;
    		fileIn.nextInt(posX);
    		fileIn.nextInt(posY);
    		fileIn.next(name);
    		
    		Node nodetmp= new Node(posX,posY,name);
    		
    		
    	}
    	
    	
    	
    	
    	fileIn.close();
    	
        
        //BLOK TWORZENIA GRAFU
        DefaultGraphCell[] cell = new DefaultGraphCell[N];
        cell = createGraph(N);  
        graph.getGraphLayoutCache().insert(cell);

        //BLOK WYSWIETLANIA GRAFU 
        showGraph(graph);
        
        
        
    }

    
    public static DefaultGraphCell[] createGraph(int N){
    	
        DefaultGraphCell[] cells = new DefaultGraphCell[N];
    	
        
        // Create Hello Vertex
        cells[0] = createNode("Hello", 20, 20, 40, 20, null, false );

        // Create World Vertex
        cells[1] = createNode("World", 140, 140, 40, 20,
                Color.ORANGE, true);

        // Create Edge
        DefaultEdge edge = new DefaultEdge("foo");
        // Fetch the ports from the new vertices, and connect them with the edge
        edge.setSource(cells[0].getChildAt(0));
        edge.setTarget(cells[0].getChildAt(0));
        cells[2] = edge;
        
               
        
        
        
        return cells;
    	
    }
    
    public static JGraph GraphInitiation(){

    	GraphModel model = new DefaultGraphModel();// Construct Model and Graph
        JGraph graph = new JGraph(model);
        graph.setCloneable(true);// Control-drag should clone selection
        graph.setInvokesStopCellEditing(true);// Enable edit without final RETURN keystroke
        graph.setJumpToDefaultPort(true);// When over a cell, jump to its default port (we only have one, anyway)
        return graph;

    }
    
    public static void showGraph(JGraph graph){
        
    	JFrame frame = new JFrame();
        frame.getContentPane().add(new JScrollPane(graph));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    	
    }
    
    public static DefaultGraphCell createNode(String name, double x,
            double y, double w, double h, Color bg, boolean raised) {

        // Create vertex with the given name
        DefaultGraphCell cell = new DefaultGraphCell(name);

        // Set bounds
        GraphConstants.setBounds(cell.getAttributes(),
                new Rectangle2D.Double(x, y, w, h));

        // Set fill color
        if (bg != null) {
            GraphConstants.setGradientColor(cell.getAttributes(), bg);
            GraphConstants.setOpaque(cell.getAttributes(), true);
        }

        // Set raised border
        if (raised) {
            GraphConstants.setBorder(cell.getAttributes(),
                    BorderFactory.createRaisedBevelBorder());
        } else // Set black border
        {
            GraphConstants.setBorderColor(cell.getAttributes(),
                    Color.black);
        }
        // Add a Floating Port
        cell.addPort();

        return cell;
    }
    
        
    public class Node{
    	
    	
    	public Node(){}
    	public Node(int x, int y, String _name)
    	{
    		posX = x;
    		posY=y;
    		name = _name;
    	}
    	
    	public int posX;
    	public int posY;
    	public String name;
    	   	
    }
    
}
