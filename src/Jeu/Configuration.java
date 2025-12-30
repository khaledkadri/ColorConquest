/**
 * This class creates a simple GUI window that allows the user to input their IP address, 
 * typically for connecting to a networked game.
 * 
 * Author: Khaled KADRI
 * License: MIT
 */

package Jeu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Configuration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuration frame = new Configuration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Configuration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVotreip = new JLabel("Votre @IP");
		lblVotreip.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVotreip.setBounds(12, 29, 76, 26);
		contentPane.add(lblVotreip);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(100, 27, 247, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnValider.setBounds(128, 78, 97, 32);
		contentPane.add(btnValider);
	}
}
