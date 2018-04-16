import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.lang.*;

public class Frame extends JFrame implements MouseListener{
	protected static mxGraph graph = new mxGraph();
	protected static HashMap m = new HashMap();
	private mxGraphComponent graphComponent;
	private JTextField texto;
	private JButton button10;
	private JButton button9;
	private JSlider slide;
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
		setLayout(new FlowLayout());
		
		
		graphComponent = new mxGraphComponent(graph);
		graphComponent.setPreferredSize(new Dimension(670, 380));
		getContentPane().add(graphComponent);
		
		slide = new JSlider(8, 22, 8);
		slide.setPreferredSize(new Dimension(420, 50));
		slide.setMinorTickSpacing(1);
		slide.setMajorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		getContentPane().add(slide);
		slide.addMouseListener(this);

		
        button10 = new JButton("10:00");
        getContentPane().add(button10);
        //try{ChangeGraph add = new ChangeGraph("graph8");} catch (Exception f){}
        
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

}