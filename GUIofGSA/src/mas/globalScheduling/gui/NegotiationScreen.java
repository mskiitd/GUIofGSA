package mas.globalScheduling.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;

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
	    label.setPreferredSize(new Dimension(400, 100));
	    WebScrollPane jScrollPane = new WebScrollPane(label);
	    jScrollPane.setVerticalScrollBarPolicy(
	    		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    jScrollPane.setHorizontalScrollBarPolicy(
	    		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    jScrollPane.setPreferredHeight(650);
	    MainPanel.add(jScrollPane, "wrap");
	    
	    WebPanel menu=new WebPanel();
	    menu.setPreferredSize(new Dimension((int)LoginScreen.width, 150));
	    menu.setBackground(Color.BLUE);
	    
	    MainPanel.add(menu);
		
		NegotiationScreenFrame.add(MainPanel);
		NegotiationScreenFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		NegotiationScreenFrame.setVisible(true);
		
	}
	public void unload() {
		NegotiationScreenFrame.removeAll();
		
	}
	
	

}
