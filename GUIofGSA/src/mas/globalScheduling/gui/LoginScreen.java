package mas.globalScheduling.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

import com.alee.extended.image.WebImage;
import com.alee.extended.time.ClockType;
import com.alee.extended.time.WebClock;
import com.alee.global.StyleConstants;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import com.alee.log.Log;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.hotkey.HotkeyManager;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotificationPopup;

public class LoginScreen {

	
	private WebFrame frame;
	private JPanel foregroundPanel;
	private JPanel wrappedBackgroundImage;
	private static final GridBagConstraints gbc;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double width = screenSize.getWidth();
    public static double height = screenSize.getHeight();
	private static  WebPasswordField passwordText;
	private static  WebTextField username_text;
	private static char[] input;
	private static String enteredUsername;
	
	public LoginScreen(WebFrame frame){
		this.frame=frame;
		load();
	}
	
	static {
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
    }
	    
    public static JPanel wrapInBackgroundImage(JComponent component,
            Icon backgroundIcon) {
        return wrapInBackgroundImage(
                component,
                backgroundIcon,
                JLabel.TOP,
                JLabel.LEADING);
    }
	    
	    public static JPanel wrapInBackgroundImage(JComponent component,
	            Icon backgroundIcon,
	            int verticalAlignment,
	            int horizontalAlignment) {
	        
	        // make the passed in swing component transparent
	        component.setOpaque(false);
	        
	        // create wrapper JPanel
	        JPanel backgroundPanel = new JPanel(new GridBagLayout());
	        
	        // add the passed in swing component first to ensure that it is in front
	        backgroundPanel.add(component, gbc);
	        
	        // create a label to paint the background image
	        JLabel backgroundImage = new JLabel(backgroundIcon);
	        
	        // set minimum and preferred sizes so that the size of the image
	        // does not affect the layout size
	        backgroundImage.setPreferredSize(new Dimension(1,1));
	        backgroundImage.setMinimumSize(new Dimension(1,1));
	        
	        // align the image as specified.
	        backgroundImage.setVerticalAlignment(verticalAlignment);
	        backgroundImage.setHorizontalAlignment(horizontalAlignment);
	        
	        // add the background label
	        backgroundPanel.add(backgroundImage, gbc);
	        
	        // return the wrapper
	        return backgroundPanel;
	    }
	    
	
	public void load(){
        Image IITDimg=null;
		try {
			IITDimg = ImageIO.read (new File("resources/IIT_Delhi_logo.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        frame.setIconImage(IITDimg);
        
         foregroundPanel = new JPanel(new BorderLayout(10, 10));
        foregroundPanel.setBorder(
                BorderFactory.createEmptyBorder(200,400,150,400));
        foregroundPanel.setOpaque(false);
        
        
        MigLayout layout = new MigLayout(
        		 "", // Layout Constraints
        		 "[]40[][]", // Column constraints
        		 "[]20[][]30"); // Row constraints
        		 
        
        final WebPanel panel = new WebPanel (layout);
//        panel.setUndecorated(false);
        Color panelColor = Color.decode("#E9E9E9");
        panel.setBackground(panelColor);
        panel.setMargin(50,150, 50, 150);
        
        ImageIcon kushalLogo = new ImageIcon("resources/kushalLogo.png");
	    final WebImage kushalLogoimg = new WebImage (kushalLogo );
        
        panel.add(kushalLogoimg,"cell 0 0 8");
        
        
        panel.add ( new WebLabel ( "Login:", WebLabel.TRAILING ),"cell 1 1" );
         username_text=new WebTextField("iitd", 10);
         enteredUsername=username_text.getText();	       
         username_text.getDocument().addDocumentListener(new DocumentListener() {
        	    @Override
        	    public void insertUpdate(DocumentEvent e) {
        	    	 enteredUsername=username_text.getText();	
        	    }
        	    @Override
        	    public void removeUpdate(DocumentEvent e) {
        	    	 enteredUsername=username_text.getText();
        	    }
        	    @Override
        	    public void changedUpdate(DocumentEvent e) {
        	    	 enteredUsername=username_text.getText();
        	    }
        	});
         
        panel.add ( username_text ,"cell 2 1 2 1");
        
        panel.add ( new WebLabel ( "Password:", WebLabel.TRAILING ) ,"cell 1 2");
         passwordText=new WebPasswordField("password",10);
         input=passwordText.getPassword();
         
         passwordText.getDocument().addDocumentListener(new DocumentListener() {
        	    @Override
        	    public void insertUpdate(DocumentEvent e) {
        	    	 input=passwordText.getPassword();	
        	    }
        	    @Override
        	    public void removeUpdate(DocumentEvent e) {
        	    	 input=passwordText.getPassword();
        	    }
        	    @Override
        	    public void changedUpdate(DocumentEvent e) {
        	    	 input=passwordText.getPassword();
        	    }
        	});
         
        panel.add ( passwordText,"cell 2 2 2 1");
        
        final WebButton okButton = new WebButton ( "Ok" );
        panel.add(okButton,"cell 2 3 1 1");
        
        final WebClock clock = new WebClock ();
        clock.setClockType ( ClockType.timer );
        final WebNotificationPopup notificationPopup = new WebNotificationPopup ();
        notificationPopup.setDisplayTime ( 2000 );
       
        
        okButton.addActionListener ( new ActionListener ()
        {
        	
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(!enteredUsername.equals("iitd") && IsPasswordCorrect(input)==false){
					notificationPopup.setContent ("Incorrect username and password");
					 NotificationManager.showNotification ( notificationPopup );
			            clock.start ();
				}
				else if(!enteredUsername.equals("iitd")){
					notificationPopup.setContent ("Incorrect username");
					 NotificationManager.showNotification ( notificationPopup );
			            clock.start ();
				}
				else if(IsPasswordCorrect(input)==false){
					notificationPopup.setContent ("Incorrect password");
					 NotificationManager.showNotification ( notificationPopup );
			            clock.start ();
				}
			
				else if(enteredUsername.equals("iitd") && IsPasswordCorrect(input)==true){
//					notificationPopup.setContent ("Welcome to GSA");
//					 NotificationManager.showNotification ( notificationPopup );
			            clock.start ();
					CleanUpVars();
				}
				
			}
        } );
        
        WebButton resetButton = new WebButton ( "Reset" );
        resetButton.addActionListener ( new ActionListener ()
        {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				username_text.setText("");
				passwordText.setText("");
				
			}
        } );
        
        panel.add(resetButton, "cell 3 3 1 1");
        panel.setRound ( StyleConstants.largeRound );
        
        
        HotkeyManager.registerHotkey ( frame, resetButton, Hotkey.ESCAPE );
        HotkeyManager.registerHotkey ( frame, okButton, Hotkey.ENTER );
        
        foregroundPanel.add(panel,
                BorderLayout.CENTER);

        
        wrappedBackgroundImage=wrapInBackgroundImage(foregroundPanel,
                new ImageIcon(
                "resources/LoginBackground.jpg"));
        frame.add(wrappedBackgroundImage);
        
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
        
	}
	

	protected void CleanUpVars() {
		
//		Log.info(Integer.toString(this.frame.getComponentCount()));
		SwingUtilities.invokeLater(new Runnable() {
			
			
			@Override
			public void run() {
				Log.info("here");
//				frame.removeAll();
				frame.remove(foregroundPanel);
				frame.remove(wrappedBackgroundImage);
				
				frame.repaint();
				Log.info(Integer.toString(frame.getComponentCount()));
				WelcomeScreen ns=new WelcomeScreen(frame);
				ns.load();
				
			}
		});
		
		
	}

	protected static boolean IsPasswordCorrect(char[] passwordInput) {
		  boolean isCorrect = false;
		    char[] correctPassword = { 'p', 'a', 's', 's', 'w', 'o', 'r','d' };

		    if (passwordInput.length != correctPassword.length) {
		        isCorrect = false;
		    } else {
		        isCorrect = Arrays.equals (passwordInput, correctPassword);
		    }
		    
		    return isCorrect;
	}

}
