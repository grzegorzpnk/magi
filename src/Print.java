import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Print {

    public static void main(String[] args) throws FileNotFoundException {

    	//INICJALIZACJA GRAFU
    	JGraph graph = GraphInitiation();
        
    	//BLOK WCZYTYWANIA DANYCH
    	Scanner fileIn = new Scanner(new File("graph10"));
 
    	List<Node> nodes  = new ArrayList<Node>();
    	List<Edge> edges  = new ArrayList<Edge>();

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
    		nodes.add(nodeTmp);
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
    	
        
        //BLOK TWORZENIA GRAFU
        DefaultGraphCell[] cell = new DefaultGraphCell[nodesCnt+edgesCnt+1]; // pozniej zastap to N
        cell = createGraph(nodes,edges);  
        cell[nodesCnt+edgesCnt] = createNode("", 500, 500, 1,1, Color.BLUE, false ); // obejscie na rozmiar grafu
        graph.getGraphLayoutCache().insert(cell);
    
     
        //BLOK WYSWIETLANIA GRAFU 
        showGraph(graph);
        
        
        
    }

    
    public static DefaultGraphCell[] createGraph(List<Node> _nodes, List<Edge> _edges) {
		// TODO Auto-generated method stub
		
    	DefaultGraphCell[] cells = new DefaultGraphCell[(_nodes.size()+_edges.size())+1];
    	
    	for(int i=0; i<_nodes.size(); i++)
        	cells[i] = createNode(_nodes.get(i).name, _nodes.get(i).posX, _nodes.get(i).posY, 50, 50, Color.BLUE, false );
    
    	for(int i=0; i<_edges.size(); i++)
    	{
    		//tu trzeba sprawdzic
    		DefaultEdge edge = new DefaultEdge("foo");
    		for(int x=0; x<_nodes.size(); x++)
    		{
    			if(_edges.get(i).source.equals(_nodes.get(x).name)){
    				edge.setSource(cells[x].getChildAt(0));
    			System.out.println("Source ustawione na cells[]: "+x);}
    		}
           
    		for(int x=0; x<_nodes.size(); x++)
    		{
    			if(_edges.get(i).target.equals(_nodes.get(x).name)){
    		edge.setTarget(cells[x].getChildAt(0));
    			System.out.println("Dest ustawione na cells[]: "+x);}
    		}
    		
    		cells[_nodes.size()+i] = edge;
    	}  
       
    	
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
        
    	JFrame frame = new JFrame("Taxi location in Warsaw");
    	
    	
    	JPanel panel = new JPanel();
    	panel.setBackground(Color.BLUE);
    	panel.add(new JScrollPane(graph));

    	frame.add(panel);

    	JPanel panel2 = new JPanel();
    	panel2.setBackground(Color.YELLOW);
        
        JButton button1 = new JButton("8:00");
        JButton button2 = new JButton("9:00");
        JButton button3 = new JButton("10:00");
        button1.setSize(100, 100);
        button2.setSize(100, 100);
        button3.setSize(100, 100);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.setSize(200,500);
        frame.add(panel2, BorderLayout.SOUTH);
       // frame.setSize(1000,1000);
        
       frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    
        
    public static class Node{
    	    	
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
    
    public static class Edge{
    	
    	public Edge(){}
    	public Edge(String source,String target)
    	{
    		this.source= source;
    		this.target = target;
    	}
    	
    	public String source;
    	public String target;
    	   	
    }    
    
}
