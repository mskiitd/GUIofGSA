package mas.globalScheduling.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.alee.laf.WebLookAndFeel;



public class CircularButton extends JButton {
	private Color COLOR1 = new Color(255, 255, 255);

	private Color COLOR2 = new Color(115, 201, 29);

	private float pct;

	private String style = "round";

	private static final int OUTER_BEVEL_SIZE = 2;

	private int size;

	private Color color;

	private Color pressedColor;

	private Color overColor;

	private Color selectedColor;

	public CircularButton() {
		super();
		color = new Color(82, 82, 82);
		pressedColor = new Color(161, 64, 0);
		overColor = new Color(115, 201, 29);
		selectedColor = pressedColor;
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
		setPreferredSize(new Dimension(68, 65));
		setRolloverEnabled(true);
		setForeground(Color.BLACK);

	}

	public CircularButton(String s) {
		super();
		color = new Color(82, 82, 82);
		pressedColor = new Color(161, 64, 0);
		overColor = new Color(115, 201, 29);
		selectedColor = pressedColor;
		setText(s);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
		setPreferredSize(new Dimension(68, 65));
		setRolloverEnabled(true);
		setForeground(Color.BLACK);

	}

	public CircularButton(String s, Color startColor, Color pressedColor) {
		super();
		color = new Color(109, 124, 135);
		pressedColor = new Color(161, 64, 0);
		overColor = new Color(225, 102, 0);
		selectedColor = pressedColor;
		setText(s);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
		setPreferredSize(new Dimension(68, 65));
		setRolloverEnabled(true);
		setForeground(Color.BLACK);

	}

	/**
	 *  This method sets the Actual Background Color of the Button
	 */
	public void setStartColor(Color color) {
		this.color = color;
	}

	/**
	 *  This method sets the Pressed Color of the Button
	 */
	public void setPressedColor(Color pressedColor) {
		this.pressedColor = pressedColor;
	}

	
	/**
	 *  This method sets the Over Color of the Button
	 */
	public void setOverColor(Color overColor) {
		this.overColor = overColor;
	}

	
	/**
	 * @return Actual Background Color of the Button
	 */
	public Color getStartColor() {
		return this.color;
	}

	/**
	 * @return Pressed Background Color of the Button
	 */
	public Color getPressedColor() {
		return this.pressedColor;
	}
	
	/**
	 * @return Pressed Background Color of the Button
	 */
	public Color getOverColor() {
		return this.overColor;
	}
	
	
	public void paintComponent(Graphics g) {
		int size = Math.min(getWidth(), getHeight()) - 4;
		int totalSize = size + 4;
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Color baseColor = color;
		ButtonModel model = getModel();
		if (model.isPressed()) {
			baseColor = pressedColor;
		} else if (model.isRollover()) {
			baseColor = overColor;
		} else if (model.isSelected()) {
			baseColor = selectedColor;
		}else if (!model.isEnabled()) {
			baseColor = new Color(192,192,192);
			setForeground(new Color(82,82,82));
		}
		GradientPaint outerBevel = new GradientPaint(0.0F, 0.0F, new Color(
				0.0F, 0.0F, 0.0F, 0.25F), 0.0F, totalSize, new Color(1.0F,
				1.0F, 1.0F, 0.25F));
		graphics.setPaint(outerBevel);
		paintInsetCircle(graphics, totalSize, 0);
		
		float colorComponents[] = baseColor.getRGBComponents(new float[4]);
		Color shadowColor = new Color(colorComponents[0] * 0.52F,
				colorComponents[1] * 0.63F, colorComponents[2] * 0.69F);
		graphics.setPaint(shadowColor);
		paintInsetCircle(graphics, totalSize, 2);
		graphics.setPaint(baseColor);
		paintInsetCircle(graphics, totalSize, 3);
		
		GradientPaint highlight = new GradientPaint(0.0F, 0.0F, new Color(1.0F,
				1.0F, 1.0F, 0.25F), 0.0F, (float) totalSize * 0.35F, new Color(
				1.0F, 1.0F, 1.0F, 0.0F));
		if (model.isPressed()) {
			highlight = new GradientPaint(0.0F, 0.0F, new Color(0.0F, 0.0F,
					0.0F, 0.25F), 0.0F, (float) totalSize * 0.35F, new Color(
					0.0F, 0.0F, 0.0F, 0.0F));
		}
		graphics.setPaint(highlight);
		paintInsetCircle(graphics, totalSize, 4);
		graphics.setPaint(baseColor);
		paintInsetCircle(graphics, totalSize, 8);
		
		Icon icon = getIcon();
		/*if(icon != null)
		 {
		 int width = icon.getIconWidth();
		 int height = icon.getIconHeight();
		 int offsetX = 0;
		 int offsetY = 0;
		 if(width < size)
		 {
		 offsetX = (totalSize - width) / 2;
		 }
		 if(height < size)
		 {
		 offsetY = (totalSize - height) / 2;
		 }
		 icon.paintIcon(this, graphics, offsetX, offsetY);
		 }*/
		super.paintComponent(graphics);

	}

	private void paintInsetCircle(Graphics2D graphics, int size, int inset) {
		graphics.fillOval(inset, inset, size - inset * 2, size - inset * 2);
	}
	public static void main(String args[]) {
		JFrame frame = new JFrame("Custom Panels Demo");
		frame.setLayout(new FlowLayout());
		CircularButton standardButton = new CircularButton("Standard Button");
		CircularButton rollOverButton = new CircularButton("Rollover Button");
		CircularButton pressedButton = new CircularButton("Pressed Button");
		CircularButton disabledButton = new CircularButton("Disabled Button");
		disabledButton.setEnabled(false);
		
		//standardButton.setDirection(GradientOvalButton.VERTICAL);
		//standardButton.setGradientType(GradientOvalButton.NORMAL);
		standardButton.setPreferredSize(new Dimension(120, 120));
		rollOverButton.setPreferredSize(new Dimension(120, 120));
		pressedButton.setPreferredSize(new Dimension(120, 120));
		disabledButton.setPreferredSize(new Dimension(120, 120));
		
		frame.add(standardButton);
		frame.add(rollOverButton);
		frame.add(pressedButton);
		frame.add(disabledButton);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setSize(700, 180);
		frame.setVisible(true);
}
}