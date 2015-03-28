package mas.globalScheduling.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alee.extended.image.WebImage;
import com.alee.extended.layout.FormLayout;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.transition.ComponentTransition;
import com.alee.extended.transition.effects.curtain.CurtainTransitionEffect;
import com.alee.extended.transition.effects.curtain.CurtainType;
import com.alee.extended.window.TestFrame;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import com.alee.utils.swing.WebTimer;

public class TransitionExample
{
	public static Logger log=LogManager.getLogger();
	  private static final GridBagConstraints gbc;
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
	    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    public static double width = screenSize.getWidth();
	    public static double height = screenSize.getHeight();
	    
/*	    public static void main(String[] args) {
	    	
	    	
	    	
	        JFrame frame = new JFrame("Background Image Panel Example");

	        // Create some GUI
//	        MigLayout layout = new MigLayout("wrap 3");
	        JPanel foregroundPanel = new JPanel();

	        foregroundPanel.setBorder(
	                BorderFactory.createEmptyBorder(100,100,100,100));
	        foregroundPanel.setOpaque(false);
	        
//	        foregroundPanel.add(new JLabel("Comment:"), BorderLayout.NORTH);
//	        foregroundPanel.add(new JScrollPane(new JTextArea(5,30)),
//	                BorderLayout.CENTER);
//	        foregroundPanel.add(
//	                new JLabel(
//	                "Please enter your comments in text box above." +
//	                " HTML syntax is allowed."), BorderLayout.SOUTH);
	        WebPanel panel = new WebPanel ( new FormLayout ( false, true, 5, 5 ) );
	        panel.add ( new WebLabel ( "Login:", WebLabel.TRAILING ) );
	        panel.add ( new WebTextField ( "nikhil" ) );
	        panel.add ( new WebLabel ( "Password:", WebLabel.TRAILING ) );
	        panel.add ( new WebPasswordField ( "iitd" ) );
//	        panel.setMargin(100, 100, 100, 100);
	        panel.setBackground(Color.blue);
//	        panel.setMaximumSize(new Dimension(1000, 1000));
//	        panel.setMinimumSize(new Dimension(500, 500));
	        foregroundPanel.add(panel, BorderLayout.CENTER);
	        
	        frame.setContentPane(wrapInBackgroundImage(foregroundPanel,
	                new ImageIcon(
	                "resources/LoginBackground.png")));
	        
	        frame.pack();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	        
	        
	    }
	}*/
	    
    public static void main ( final String[] args )
    {

    	ImageIcon IITDicon = new ImageIcon("resources/IITDlogo.png",
                "");
    	// First frame content
        final WebImage image = new WebImage (IITDicon );

    	/*ImageIcon loginBackgroundicon = new ImageIcon("resources/LoginBackground.png",
                "");*/
    	
    	
//    	final WebImage loginBackgroundimg = new WebImage (loginBackgroundicon );
        // Second frame content
    	
    	
        final WebPanel panel = new WebPanel ( new FormLayout ( false, true, 5, 5 ) );
//        log.info(center_X+" "+panel.getHeight());
//        panel.setBackground(Color.BLUE);
        
        
        panel.setMargin(300, 500, 300, 500);
        panel.add ( new WebLabel ( "Login:", WebLabel.TRAILING ) );
        panel.add ( new WebTextField ( "nikhil" ) );
        panel.add ( new WebLabel ( "Password:", WebLabel.TRAILING ) );
        panel.add ( new WebPasswordField ( "iitd" ) );
//        panel.add(loginBackgroundimg);
        
        
        // Simple button
        WebButton okButton = new WebButton ( "Ok" );
        // Disabled button
        WebButton cancelButton = new WebButton ( "Cancel" );
        
        GroupPanel gp= new GroupPanel ( 10, new GroupPanel ( okButton ), cancelButton );
        panel.add(gp);
        
        // Transition panel and frame
         final CurtainTransitionEffect effect = new CurtainTransitionEffect ();
//         final SlideTransitionEffect effect = new SlideTransitionEffect ();
//        final CurtainTransitionEffect effect = new CurtainTransitionEffect ();
        effect.setSpeed ( 20 );
        effect.setType(CurtainType.fade);
        final ComponentTransition transition = new ComponentTransition ( image, effect );
        TestFrame.show ( transition );
        log.info(TestFrame.getFrames());
        
        // Transition action
        WebTimer.delay ( 500, new ActionListener ()
        {
            @Override
            public void actionPerformed ( final ActionEvent e )
            {
                transition.performTransition ( panel );
            }
        } );
    }
    }

    

