package mas.globalScheduling.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.log.Log;

public class NegotiationScreen {
	private static WebFrame NegotiationScreenFrame;
	private static MigLayout layout;
	
	public NegotiationScreen(WebFrame frame) {
		this.NegotiationScreenFrame=frame;
		this.layout=new MigLayout();
	}
	public static void load() {
		WebPanel MainPanel=new WebPanel(layout);
		
		JLabel label = new JLabel("Label");
//	    label.setPreferredSize(new Dimension(400, 100));
	    WebScrollPane jScrollPane = new WebScrollPane(label);
	    jScrollPane.setVerticalScrollBarPolicy(
	    		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    jScrollPane.setHorizontalScrollBarPolicy(
	    		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    jScrollPane.setPreferredHeight(600);
	    MainPanel.add(jScrollPane, "wrap");
	    
	    WebPanel menu=new WebPanel(new FlowLayout());
	    menu.setPreferredSize(new Dimension((int)LoginScreen.width, 100));
	
	    JButton[] bottomButtons=getButtons();
	
	    Color panelColor = Color.decode("#A2A3A2");
	    menu.setBackground(panelColor);
	    
	    for(int i=0;i<bottomButtons.length;i++){
	    	menu.add(bottomButtons[i]);	
	    }
	    
	    MainPanel.add(menu);
		
		NegotiationScreenFrame.add(MainPanel);
		NegotiationScreenFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		NegotiationScreenFrame.setVisible(true);
		
	}
	public void unload() {
		NegotiationScreenFrame.removeAll();
		
	}
	
	private static JButton[] getButtons(){
		JButton[] buttons=new JButton[4];
		JButton About = new JButton();
	    Image img = null;
		try {
			img = ImageIO.read (new File("resources/about.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(img);
	    About.setIcon(new ImageIcon(img));
	    About.setPreferredSize(new Dimension(115,115));
	    buttons[0]=About;
	    
	    
	    JButton Negotiation = new JButton();
	    Image negotiationImg = null;
	 		try {
	 			negotiationImg = ImageIO.read (new File("resources/JobManager.png"));
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
 		Negotiation.setIcon(new ImageIcon( negotiationImg));
 		Negotiation.setPreferredSize(new Dimension(115,115));
 		buttons[1]=Negotiation;
 		
 		JButton JobManager = new JButton();
	    Image JobManagerImg = null;
	 		try {
	 			JobManagerImg = ImageIO.read (new File("resources/negotiation.png"));
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 		JobManager.setIcon(new ImageIcon( JobManagerImg));
	 		JobManager.setPreferredSize(new Dimension(115,115));
 		buttons[2]=JobManager;

 		JButton CompletedJobs = new JButton();
	    Image CompletedJobsImg = null;
	 		try {
	 			CompletedJobsImg = ImageIO.read (new File("resources/signOut.png"));
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 		CompletedJobs.setIcon(new ImageIcon( CompletedJobsImg));
	 		CompletedJobs.setPreferredSize(new Dimension(115,115));
 		buttons[3]=CompletedJobs;
 		
		return buttons;
	}

}
