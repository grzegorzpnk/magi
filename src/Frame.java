import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;

import java.lang.*;

public class Frame extends JFrame implements MouseListener, ActionListener{
	protected static mxGraph graph = new mxGraph();
	protected static HashMap m = new HashMap();
	protected static HashMap m1 = new HashMap();
	private mxGraphComponent graphComponent;
	private JTextField texto;
	private JButton button10;
	private JButton button9;
	private JSlider slide;
	private Object cell;
	private JLabel label;
	
	public static HashMap getM() {
		return m;
	}
	public static HashMap getM1() {
		return m1;
	}

	public static mxGraph getGraph() {
		return graph;
	}

	public Frame(){
		super("Warsaw taxi");

		initGUI();
	}

	private void initGUI() {
		setSize(600, 800);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		
		graphComponent = new mxGraphComponent(graph);
		
		graphComponent.getGraphControl().updatePreferredSize();
		graphComponent.setPreferredSize(new Dimension(700, 600));
		

		//getContentPane().add(JScrollPane(graphComponent));
		getContentPane().add(new JScrollPane(graphComponent));
		
		slide = new JSlider(7, 22, 7);
		slide.setPreferredSize(new Dimension(420, 50));
		slide.setMinorTickSpacing(1);
		slide.setMajorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		getContentPane().add(slide);
		slide.addMouseListener(this);

        button10 = new JButton("Symuluj");
        button10.addActionListener(this);
        getContentPane().add(button10);
        
        label = new JLabel("");
        label.setPreferredSize(new Dimension(40, 40));
        getContentPane().add(label);
       
        
	}


	public void mouseClicked(MouseEvent arg0) {
	
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent arg0) {
		int hour = slide.getValue();
		String tmp;
		tmp = Integer.toString(hour);
		System.out.println(tmp);
		try{ChangeGraph add = new ChangeGraph("graph"+tmp);} catch (Exception f){}
		
	}



	public void actionPerformed(ActionEvent arg0) {
		label.setText(Integer.toString(ReadFromFile.licznik));
		
	}

}