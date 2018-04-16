import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class ChangeGraph extends Frame {	
	
	public ChangeGraph(String fileName) throws FileNotFoundException{
		
		ReadFromFile read = new ReadFromFile(fileName);
		
		//tu najpierw wyczyscmy model
		for(int i = 0; i< 10;i++)
		{
					graph.selectChildCell();
					graph.removeCells();
		}
		
		Frame.getGraph().getModel().beginUpdate();
		Object parent = getGraph().getDefaultParent();
		
		for(int i=0; i<ReadFromFile.nodes.size(); i++)
		{
			Object v1 = getGraph().insertVertex(parent, null, ReadFromFile.nodes.get(i).name, ReadFromFile.nodes.get(i).posX, ReadFromFile.nodes.get(i).posY, 100, 50, "fillColor="+ReadFromFile.nodes.get(i).color);
			
			getM().put(ReadFromFile.nodes.get(i).name, v1);
		}
		getGraph().getModel().endUpdate();
		
		Object parent2 = getGraph().getDefaultParent();
		
		for(int x=0; x<ReadFromFile.edges.size(); x++)
		{
		Object v1 = getM().get(ReadFromFile.edges.get(x).source);
        Object v2 = getM().get(ReadFromFile.edges.get(x).target);
        getGraph().insertEdge(parent2, null, "Trasa", v1, v2);
		}
        	
	
	}

}