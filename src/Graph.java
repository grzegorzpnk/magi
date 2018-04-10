import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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


public class Graph {
	
	static List<Node> nodes  = new ArrayList<Node>();
	static List<Edge> edges  = new ArrayList<Edge>();
	public static JGraph graph;
	public static DefaultGraphCell[] cell ;
	
	public Graph() throws FileNotFoundException
	{
		//INICJALIZACJA GRAFU
    	graph = GraphInitiation();

        //BLOK TWORZENIA GRAFU
    	utworzGraf(false);

	}

	public static void utworzGraf(boolean tmp){
		if(tmp == false){
		cell = new DefaultGraphCell[nodes.size()+edges.size()+1]; 
        cell = createGraph(nodes,edges);  
        cell[nodes.size()+edges.size()] = createNode("", 500, 500, 1,1, Color.BLUE, false ); // obejscie na rozmiar grafu
        graph.getGraphLayoutCache().insert(cell);
		}
		if(tmp == true){
			cell = new DefaultGraphCell[1];   
	        cell[0] = createNode("", 500, 500, 1,1, Color.BLUE, false ); // obejscie na rozmiar grafu
	        graph.getGraphLayoutCache().insert(cell);	
		}
        
	}
	
	  public static DefaultGraphCell[] createGraph(List<Node> _nodes, List<Edge> _edges) {
			// TODO Auto-generated method stub
			
	    	DefaultGraphCell[] cells = new DefaultGraphCell[(_nodes.size()+_edges.size())+1];
	    	
	    	for(int i=0; i<_nodes.size(); i++)
	        	cells[i] = createNode(_nodes.get(i).name, _nodes.get(i).posX, _nodes.get(i).posY, 50, 50, Color.BLUE, false );
	    
	    	for(int i=0; i<_edges.size(); i++)
	    	{
	    		//tu trzeba sprawdzic
	    		DefaultEdge edge = new DefaultEdge();
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
	
}
