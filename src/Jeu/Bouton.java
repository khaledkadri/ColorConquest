/**
 * Custom colored button for a game.
 * 
 * This class extends JButton and implements Runnable. It draws a colored square inside 
 * a standard button frame. The color can be set at construction, and the button can be 
 * redrawn in a separate thread using the run() method.
 *
 * Author: Khaled KADRI
 * License: MIT
 */


package Jeu;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;

import javax.swing.JButton;

public class Bouton extends JButton implements Runnable{

	Color couleur;
	Graphics g;
	public Bouton(Color couleur){
		this.couleur = couleur;
	}
	
	public void paintComponent(Graphics g){
		
		int px = 0,py=0;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 50, 50);
		g.setColor(Color.black);
		g.drawRect(0, 0, 50, 50);
		g.setColor(couleur);
		
		
        g.fillRect(25/2, 25/2, 25, 25);
        this.g=g;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 50, 50);
		g.setColor(Color.black);
		g.drawRect(0, 0, 50, 50);
		g.setColor(couleur);
		
		
        g.fillRect(25/2, 25/2, 25, 25);
	}
}
