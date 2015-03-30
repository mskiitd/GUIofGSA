package mas.globalScheduling.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mas.jobproxy.job;
import net.miginfocom.swing.MigLayout;

import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.log.Log;

public class WelcomeScreen {
	private static WebFrame welcomeScreenFrame;
	private static BorderLayout layout;
	public static WebScrollPane currentJobList;
	private static WebPanel MainPanel;
	
	public WelcomeScreen(WebFrame frame) {
		this.welcomeScreenFrame=frame;
		this.layout=new BorderLayout();
		this.MainPanel=new WebPanel(layout);
		this.currentJobList=null;
	}
	public static void load() {
		
	    WebPanel menu=new WebPanel(new FlowLayout());
	    menu.setPreferredSize(new Dimension((int)LoginScreen.width, 100));
	    JButton[] bottomButtons=getButtons();
	
	    Color panelColor = Color.decode("#A2A3A2");
	    menu.setBackground(panelColor);
	    
	    for(int i=0;i<bottomButtons.length;i++){
	    	menu.add(bottomButtons[i]);	
	    }
	    
	    MainPanel.add(menu, BorderLayout.SOUTH);
		
		welcomeScreenFrame.add(MainPanel);
		welcomeScreenFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		welcomeScreenFrame.setVisible(true);
		
	}
	
	
	
	public void unload() {
		welcomeScreenFrame.removeAll();
		
	}
	
	private static JButton[] getButtons(){
		JButton[] buttons=new JButton[5];
		JButton About = new JButton();
	    Image img = null;
		try {
			img = ImageIO.read (new File("resources/about.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(img);
	    About.setIcon(new ImageIcon(img));
	    About.setPreferredSize(new Dimension(90,90));
	    About.setActionCommand("about");
	    About.addActionListener(new ActionListener() {
	    	
            private Logger log=LogManager.getLogger();

			public void actionPerformed(ActionEvent e){
//            {	JLabel label = new JLabel("Label");
            	log.info(currentJobList);
            	job j=new job.Builder("11").jobCPN(0).
            			jobStartTimeByCust(0).jobDueDateTime(1000).build();
            	
            	List<JobTile> listOfjobTiles=new ArrayList<JobTile>();
            	for(int i=0;i<10;i++){
            		listOfjobTiles.add(new JobTile(j));	
            	}
            	JTable jobListTable=new JTable(new JobTileRenderer(listOfjobTiles));
          
            	jobListTable.setDefaultRenderer(JobTile.class, new JobTileCell());
            	jobListTable.setDefaultEditor(JobTile.class, new JobTileCell());
            	jobListTable.setRowHeight(100);
            			
            	currentJobList=new WebScrollPane(jobListTable);
            	JobList JobListPane =new JobList();
            	MainPanel.add(currentJobList,BorderLayout.WEST);
            	
/*            	if(currentJobList!=null){
            		MainPanel.remove(currentJobList);
            		currentJobList=null;
            		log.info("currentJobList "+"is not null");
            	}
            	else{
            		currentJobList=new WebScrollPane(label);
            		log.info("currentJobList "+"is null");
            		MainPanel.add(currentJobList,BorderLayout.WEST);
            	}*/
        		
            	welcomeScreenFrame.add(MainPanel);
            	welcomeScreenFrame.revalidate();
            	welcomeScreenFrame.repaint();
            	welcomeScreenFrame.setVisible(true);
            	
            }
        });  
	    buttons[0]=About;
	    
	    
	    JButton Negotiation = new JButton();
	    Image negotiationImg = null;
	 		try {
	 			negotiationImg = ImageIO.read (new File("resources/JobManager.png"));
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
 		Negotiation.setIcon(new ImageIcon( negotiationImg));
 		Negotiation.setPreferredSize(new Dimension(90,90));
 		Negotiation.setActionCommand("negotition");
 		buttons[1]=Negotiation;
 		
 		JButton JobManager = new JButton();
	    Image JobManagerImg = null;
	 		try {
	 			JobManagerImg = ImageIO.read (new File("resources/negotiation.png"));
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 		JobManager.setIcon(new ImageIcon( JobManagerImg));
	 		JobManager.setPreferredSize(new Dimension(90,90));
	 		JobManager.setActionCommand("jobManager");
 		buttons[2]=JobManager;

 		JButton signOut = new JButton();
	    Image signOutImg = null;
	 		try {
	 			signOutImg = ImageIO.read (new File("resources/signOut.png"));
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 		signOut.setIcon(new ImageIcon( signOutImg));
	 		signOut.setPreferredSize(new Dimension(90,90));
	 		signOut.setActionCommand("signOut");
 		buttons[3]=signOut;
 		
 		JButton completedJobs = new JButton();
	    Image CompletedJobsImg = null;
	 		try {
	 			CompletedJobsImg = ImageIO.read (new File("resources/completedJob.png"));
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 		completedJobs.setIcon(new ImageIcon( CompletedJobsImg));
	 		completedJobs.setPreferredSize(new Dimension(90,90));
	 		completedJobs.setActionCommand("completedJob");
 		buttons[4]=completedJobs;
 		
		return buttons;
	}

}
