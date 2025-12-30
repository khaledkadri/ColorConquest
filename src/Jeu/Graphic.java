/**
 * Handles the 2D graphical rendering for the Color Conquest game board.
 * Draws the 8x8 grid with gradient-colored circles and visual effects.
 * 
 * Author: Khaled KADRI
 * License: MIT
 */


package Jeu;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Graphic extends JPanel{
	
	private static final long serialVersionUID = 1L;
	int ytranslate=0;
	int xtranslate=0;
	JSplitPane span;
	public Color matrice[][] = new Color[8][8];
	public Color matriceprec[][] = new Color[8][8];
	
	public Color color[]={Color.black,Color.blue,Color.cyan,Color.DARK_GRAY,Color.GRAY,Color.GREEN,Color.LIGHT_GRAY,Color.MAGENTA,Color.orange,Color.pink,Color.RED,Color.YELLOW};
	

	public void paintComponent(Graphics g) {
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    GradientPaint gradient = new GradientPaint(0, 0, Color.LIGHT_GRAY, 0, getHeight(), Color.WHITE);
	    g2d.setPaint(gradient);
	    g2d.fillRect(0, 0, getWidth(), getHeight());

	    int px = 55, py = 35;
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            // Set color to white and draw the background rectangle
	            g2d.setColor(Color.WHITE);
	            g2d.fillRect(px, py, 55, 55);

	            // Set color to light gray and draw the rectangle border
	            g2d.setColor(Color.LIGHT_GRAY);
	            g2d.drawRect(px, py, 55, 55);

	            // Draw shadow for 3D effect
	            g2d.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
	            g2d.fillOval(px + 3, py + 3, 50, 50); // Draw shadow offset for depth

	            // Draw main circle with gradient to simulate light
	            g2d.setColor(matrice[i][j]);
	            GradientPaint circleGradient = new GradientPaint(px, py, matrice[i][j].brighter(), px + 50, py + 50, matrice[i][j].darker());
	            g2d.setPaint(circleGradient);
	            g2d.fillOval(px + 2, py + 2, 50, 50);  // Adjust to position the circle centrally

	            // Update the previous matrix value
	            matriceprec[i][j] = matrice[i][j];

	            // Move to the next position in the grid
	            px += 55;
	        }

	        px = 55;
	        py += 55;
	    }
	}

	
}
