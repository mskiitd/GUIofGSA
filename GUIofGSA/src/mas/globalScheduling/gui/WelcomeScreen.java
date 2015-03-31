package mas.globalScheduling.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;

import mas.jobproxy.job;
import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alee.extended.label.WebHotkeyLabel;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;

public class WelcomeScreen {
	private static WebFrame welcomeScreenFrame;
	private static BorderLayout layout;
	public static WebScrollPane currentJobList;
	private static WebPanel MainPanel;
	private static WebPanel infoPanel; 
	private static Logger log=LogManager.getLogger();
	
	
	public WelcomeScreen(WebFrame frame) {
		this.welcomeScreenFrame=frame;
		this.layout=new BorderLayout();
		this.MainPanel=new WebPanel(layout);
		MigLayout migLayout=new MigLayout("","200","[30]");
		this.infoPanel=new WebPanel(migLayout);
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

	//called by dynamic menu buttons to fill up info in rightsaide of main panel
	public static void createInfoPanel(JobTile jobToShow){
		final Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		WebLabel JobNametextlbl,JobIDTxtlbl,jobCustStartDateTxtlbl,jobCustDueDateTextlbl,
		jobGSAStartDateTxtlbl,jobGSADueDateTxtlbl, durationTextlbl, priorityTextlbl;
		
		WebHotkeyLabel JobNamelbl,JobIDlbl,jobCustStartDatelbl,jobCustDueDatelbl,
		jobGSAStartDatelbl,jobGSADueDatelbl, durationlbl, prioritylbl;
		
		Font TextlblFont=UIManager.getDefaults().getFont("TabbedPane.font");
//		TextlblFont=TextlblFont.deriveFont(Font.PLAIN, 12);
		Font lblFont = TextlblFont.deriveFont(Font.PLAIN, 20);
		
		JobNametextlbl=new WebLabel("Job Name");
		JobNamelbl=new WebHotkeyLabel(jobToShow.getJobName());
//		JobNamelbl.setMinimumWidth(200);

		JobIDTxtlbl=new WebLabel("Job ID");
		JobIDlbl=new WebHotkeyLabel(jobToShow.getJobID().toString());
//		JobIDlbl.setMinimumWidth(150);
		
		jobCustStartDateTxtlbl=new WebLabel("Start Date by customer");
		jobCustStartDatelbl=new WebHotkeyLabel(formatter.format(jobToShow.getCustStartDate()));
//		jobCustStartDatelbl.setMinimumWidth(150);
		
		jobCustDueDateTextlbl=new WebLabel("Due Date by customer");
		jobCustDueDatelbl=new WebHotkeyLabel(formatter.format(jobToShow.getCustDueDate()));
//		jobCustDueDatelbl.setMinimumWidth(150);
		
		jobGSAStartDateTxtlbl=new WebLabel("Start date by MAS");
		jobGSAStartDatelbl=new WebHotkeyLabel(formatter.format(jobToShow.getStartDatebyGSA()));
//		jobGSAStartDatelbl.setMinimumWidth(150);
		
		jobGSADueDateTxtlbl=new WebLabel("Due date by MAS");
		jobGSADueDatelbl=new WebHotkeyLabel(formatter.format(jobToShow.getDueDatebyGSA()));
//		jobGSADueDatelbl.setMinimumWidth(150);
		
		durationTextlbl=new WebLabel("Duration (seconds)");
		durationlbl=new WebHotkeyLabel(Double.toString(jobToShow.getProcessingTime()));
//		durationlbl.setMinimumWidth(150);
		
		priorityTextlbl=new WebLabel("Priority");
		prioritylbl=new WebHotkeyLabel(Integer.toString(jobToShow.getPriority()));
//		prioritylbl.setMinimumWidth(150);
		
/*		
		
		JobNametextlbl.setFont(TextlblFont);
		JobIDTxtlbl.setFont(TextlblFont);
		jobCustStartDateTxtlbl.setFont(TextlblFont);
		jobCustDueDateTextlbl.setFont(TextlblFont);
		durationTextlbl.setFont(TextlblFont);
		priorityTextlbl.setFont(TextlblFont);*/
		
		JobNamelbl.setFont(lblFont);
		JobIDlbl.setFont(lblFont);
		jobCustStartDatelbl.setFont(lblFont);
		jobCustDueDatelbl.setFont(lblFont);
		durationlbl.setFont(lblFont);
		prioritylbl.setFont(lblFont);
		jobGSAStartDatelbl.setFont(lblFont);
		jobGSADueDatelbl.setFont(lblFont);
		
		infoPanel.add(JobNametextlbl,"growx");
		infoPanel.add(JobIDTxtlbl,"growx");
		infoPanel.add(jobCustStartDateTxtlbl,"growx");
		infoPanel.add(jobCustDueDateTextlbl,"wrap, growx");
		
		infoPanel.add(JobNamelbl,"growx");
		infoPanel.add(JobIDlbl,"growx");
		infoPanel.add(jobCustDueDatelbl,"growx");
		infoPanel.add(jobCustStartDatelbl,"growx");
		infoPanel.add(jobCustDueDatelbl,"wrap, growx");
		
		infoPanel.add(durationTextlbl,"growx");
		infoPanel.add(priorityTextlbl,"growx");
		infoPanel.add(jobGSAStartDateTxtlbl,"growx");
		infoPanel.add(jobGSADueDateTxtlbl,"wrap, growx");
		
		infoPanel.add(durationlbl,"growx");
		infoPanel.add(prioritylbl,"growx");
		infoPanel.add(jobGSAStartDatelbl,"growx");
		infoPanel.add(jobGSADueDatelbl,"wrap, growx");
		
		MainPanel.add(infoPanel,BorderLayout.CENTER);
		welcomeScreenFrame.validate();
		welcomeScreenFrame.repaint();
		welcomeScreenFrame.setVisible(true);
		
		
	}
	
	public static void unloadInfoPanel(){
		infoPanel.removeAll();
		MainPanel.remove(infoPanel);
		welcomeScreenFrame.validate();
		welcomeScreenFrame.repaint();
		welcomeScreenFrame.setVisible(true);
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
			public void actionPerformed(ActionEvent e){
			 	if(currentJobList!=null){			 	
			 		MainPanel.remove(currentJobList);
            		currentJobList=null;//causes memory leak
            		welcomeScreenFrame.revalidate();
            		welcomeScreenFrame.repaint();
            		welcomeScreenFrame.setVisible(true);
            	}
            	else{
            		log.info("currentJobList "+"is null");
            	}
            	
            }
        });  
	    buttons[0]=About;
	    
	    
	    JButton Negotiation = new JButton();
	    Image negotiationImg = null;
	 		try {
	 			negotiationImg = ImageIO.read (new File("resources/negotiation.png"));
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
 		Negotiation.setIcon(new ImageIcon( negotiationImg));
 		Negotiation.setPreferredSize(new Dimension(90,90));
 		Negotiation.setActionCommand("negotition");
 		Negotiation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentJobList!=null){
            		MainPanel.remove(currentJobList);
            		currentJobList=null;
            		welcomeScreenFrame.revalidate();
            		welcomeScreenFrame.repaint();
            		welcomeScreenFrame.setVisible(true);
            		log.info("currentJobList "+"is not null");
            	}
//            	else{
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
            	jobListTable.setRowHeight(90);
            			
            	currentJobList=new WebScrollPane(jobListTable);
            	currentJobList.setPreferredWidth(350);
            	MainPanel.add(currentJobList,BorderLayout.WEST);

        
        		log.info("currentJobList "+"is null");
        		MainPanel.add(currentJobList,BorderLayout.WEST);
//            	}
        		
            	welcomeScreenFrame.add(MainPanel);
            	welcomeScreenFrame.revalidate();
            	welcomeScreenFrame.repaint();
            	welcomeScreenFrame.setVisible(true);
				
			}
		});
 		
 		buttons[1]=Negotiation;
 		
 		JButton JobManager = new JButton();
	    Image JobManagerImg = null;
	 		try {
	 			JobManagerImg = ImageIO.read (new File("resources/JobManager.png"));
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 		JobManager.setIcon(new ImageIcon( JobManagerImg));
	 		JobManager.setPreferredSize(new Dimension(90,90));
	 		JobManager.setActionCommand("jobManager");
	 		
	 		JobManager.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
				 	if(currentJobList!=null){
	            		MainPanel.remove(currentJobList);
	            		currentJobList=null;
	            		log.info("currentJobList "+"is not null");
	            	}
	            	else{
	            	}
	            }
	        });  
	 		
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
	 		
	 		signOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
				 	if(currentJobList!=null){
	            		MainPanel.remove(currentJobList);
	            		currentJobList=null;
	            		log.info("currentJobList "+"is not null");
	            	}
	            	else{
	            	}
	            }
	        });
	 		
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
	 		
	 		completedJobs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
				 	if(currentJobList!=null){
	            		MainPanel.remove(currentJobList);
	            		currentJobList=null;
	            		log.info("currentJobList "+"is not null");
	            	}
	            	else{
	            	}
	            }
	        });
	 		
 		buttons[4]=completedJobs;
 		
		return buttons;
	}

}
