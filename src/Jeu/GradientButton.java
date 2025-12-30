/**
 * Custom JButton with a vertical gradient background.
 * The button displays a smooth gradient from a brighter to a darker shade of the specified color.
 * 
 * Author: Khaled KADRI
 * License: MIT
 */


package Jeu;

import javax.swing.*;
import java.awt.*;

public class GradientButton extends JButton {
    private Color color;

    public GradientButton(Color color) {
        this.color = color;
        setContentAreaFilled(false); // Disable the default button background
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a gradient paint effect
        GradientPaint gradient = new GradientPaint(0, 0, color.brighter(), 0, getHeight(), color.darker());
        g2d.setPaint(gradient);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0); // Rounded corners

        // Draw the button border
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 0, 0);

        // Draw button text
        super.paintComponent(g);
    }
}
