import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;

import com.mxgraph.view.mxGraph;


public class ChangeGraph extends Frame {	
	
	public ChangeGraph(String fileName) throws FileNotFoundException{
		
		ReadFromFile read = new ReadFromFile(fileName);
		
		//tu najpierw wyczyscmy model
		/*this.getGraph().getModel().beginUpdate();
		Object parent3 = this.getGraph().getDefaultParent();
		//this.getGraph().remo
		this.getGraph().getModel().endUpdate();*/
		
		this.getGraph().getModel().beginUpdate();
		Object parent = this.getGraph().getDefaultParent();
		
		for(int i=0; i<ReadFromFile.edges.size(); i++)
		{
			Object v1 = this.getGraph().insertVertex(parent, null, ReadFromFile.nodes.get(i).name, ReadFromFile.nodes.get(i).posX, ReadFromFile.nodes.get(i).posY, 100, 50);
			this.getM().put(ReadFromFile.nodes.get(i).name, v1);
		}
		this.getGraph().getModel().endUpdate();
		
		Object parent2 = this.getGraph().getDefaultParent();
		
		for(int x=0; x<ReadFromFile.edges.size(); x++)
		{
		Object v1 = this.getM().get(ReadFromFile.edges.get(x).source);
        Object v2 = this.getM().get(ReadFromFile.edges.get(x).target);
        this.getGraph().insertEdge(parent2, null, "Trasa", v1, v2);
		}
        	
	
	}

}