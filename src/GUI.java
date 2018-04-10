import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jgraph.JGraph;


public class GUI implements ActionListener{

public JButton button_8;
public JButton button_9;
public JButton button_10;
JFrame frame ;
Graph grapH;

    public GUI() throws FileNotFoundException{
    	grapH = new Graph();
    	frame = new JFrame("Taxi location in Warsaw");
 
    	
    	showGui(grapH.graph);         
    }

	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource() == button_8){
			System.out.println("\nwcisnieto przycisk 8:00");
			try {ReadFromFile read = new ReadFromFile("graph8");} catch (FileNotFoundException e1) {}
			
			Graph.graph.getModel().beginUpdate();			
			Graph.graph.clearOffscreen();
			Graph.graph.getModel().endUpdate();
			Graph.graph.refresh();
			
			
			Graph.graph.getModel().beginUpdate();			
			Graph.utworzGraf(false);
			Graph.graph.getModel().endUpdate();
			Graph.graph.refresh();
			
			

		}
		
		if(e.getSource() == button_9){
			System.out.println("\nwczisnieto przycisk 9:00");
			try {ReadFromFile read = new ReadFromFile("graph9");} catch (FileNotFoundException e1) {}
			Graph.graph.getModel().beginUpdate();			
			Graph.utworzGraf(false);
			Graph.graph.getModel().endUpdate();
			Graph.graph.refresh();

		}
		
		if(e.getSource() == button_10){
			System.out.println("\nwczisnieto przycisk 10:00");
			try {ReadFromFile read = new ReadFromFile("graph10");} catch (FileNotFoundException e1) {}
			Graph.graph.getModel().beginUpdate();			
			Graph.utworzGraf(false);
			Graph.graph.getModel().endUpdate();
			Graph.graph.refresh();
		}
		
	}
    
	 public void showGui(JGraph graph){
	        

	    	frame.add(new JScrollPane(graph));
	    	

	    	JPanel panel2 = new JPanel();        
	        button_8 = new JButton("8:00");
	        button_9 = new JButton("9:00");
	        button_10 = new JButton("10:00");
	        button_8.addActionListener(this);
	        button_9.addActionListener(this);
	        button_10.addActionListener(this);
	        panel2.add(button_8);
	        panel2.add(button_9);
	        panel2.add(button_10);
	        frame.add(panel2, BorderLayout.SOUTH);

	        
	       frame.pack();
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setVisible(true);
	     
	        
	    }

    

    

    
}
