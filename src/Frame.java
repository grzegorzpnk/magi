

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jgraph.graph.GraphConstants;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;


public class Frame extends JFrame {
	protected static mxGraph graph = new mxGraph();
	protected static HashMap m = new HashMap();
	private mxGraphComponent graphComponent;
	private JTextField texto;
	private JButton button10;
	private JButton botaoDel;
	private JButton button9;
	private Object cell;	
	
	public static HashMap getM() {
		return m;
	}

	public static mxGraph getGraph() {
		return graph;
	}

	public Frame(){
		super("Warsaw taxi");
		initGUI();
	}

	private void initGUI() {
		setSize(700, 500);
		setLocationRelativeTo(null);
		
		graphComponent = new mxGraphComponent(graph);
		graphComponent.setPreferredSize(new Dimension(670, 380));
		 
		getContentPane().add(graphComponent);
		
		texto = new JTextField();
		getContentPane().add(texto);
        texto.setPreferredSize(new Dimension(420, 21));
        setLayout(new FlowLayout(FlowLayout.LEFT));
		
        button10 = new JButton("10:00");
        getContentPane().add(button10);
        button10.addActionListener(new ActionListener() {
                        
            public void actionPerformed(ActionEvent e) {        	
            	try {
					ChangeGraph add = new ChangeGraph("graph10");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                texto.setText("");
            }
        });
        
        botaoDel = new JButton("Deletar");
        getContentPane().add(botaoDel);
        botaoDel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				graph.getModel().remove(cell);
				graph.getModel().remove(this);
				
			}
		});
        
        button9 = new JButton("9:00");
        getContentPane().add(button9);
        button9.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	try {
					ChangeGraph add = new ChangeGraph("graph9");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                texto.setText("");
            	
            }
        });
        
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
		{
		
			public void mouseReleased(MouseEvent e)
			{
				cell = graphComponent.getCellAt(e.getX(), e.getY());		
			}
		});
	}

}