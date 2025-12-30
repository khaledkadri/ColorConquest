/**
 * Main menu window for the Color Conquest game.
 * Allows players to choose between single-player, local multiplayer,
 * and network modes, access help, configure network settings,
 * and view game information.
 * 
 * Author: Khaled KADRI
 * License: MIT
 */


package Jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class MenuPrinc extends JFrame implements ActionListener{
	JButton jeu1,jeu2,jeu3;
	JPanel panel;
	JMenuItem prec,Regles, config, quitter, apropos;
	String IP;
		
	public MenuPrinc(){
		this.setTitle("Color conquest");
		this.setSize(560,670);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResource("conquete.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/****************MENU********************/
		creerMenu();
		/************************************/
		
		init();
		this.repaint();
		this.validate();
	}
	
	public void init() {
		// TODO Auto-generated method stub
		panel = new JPanel();
		this.setContentPane(panel);
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		panel.setBackground(Color.white);
		panel.setBorder(new LineBorder(Color.black, 5));
		
		JLabel l = new JLabel("Color conquest");
		Font f1 = new Font("Serif", Font.PLAIN, 30);
		l.setBackground(Color.black);
		l.setFont(f1);
		l.setForeground(Color.black);
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(l);
		
		Font f2 = new Font("Serif", Font.PLAIN, 20);
		jeu1 = new JButton("One player");
		jeu2 = new JButton("Two players");
		jeu3 = new JButton("Two players via network");
		jeu1.setMaximumSize(new Dimension(300, 70));
		jeu2.setMaximumSize(new Dimension(300, 70));
		jeu3.setMaximumSize(new Dimension(300, 70));
		jeu1.setAlignmentX(Component.CENTER_ALIGNMENT);
		jeu2.setAlignmentX(Component.CENTER_ALIGNMENT);
		jeu3.setAlignmentX(Component.CENTER_ALIGNMENT);
		jeu1.addActionListener(this);
		jeu2.addActionListener(this);
		jeu3.addActionListener(this);
		jeu1.setFont(f2);
		jeu2.setFont(f2);
		jeu3.setFont(f2);
		jeu1.setForeground(Color.white);
		jeu2.setForeground(Color.white);
		jeu3.setForeground(Color.white);
		jeu1.setBackground(Color.BLACK);
		jeu2.setBackground(Color.BLACK);
		jeu3.setBackground(Color.BLACK);
		jeu1.setBorder(new BevelBorder(javax.swing.border.BevelBorder.LOWERED,Color.red,Color.blue));
		
		JPanel pan = new JPanel();
		BoxLayout layout2 = new BoxLayout(pan, BoxLayout.Y_AXIS);
		pan.setLayout(layout2);
		
		pan.setMaximumSize(new Dimension(300,180));
		pan.setBorder(BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED,Color.red,Color.blue), "Humain Vs Humain", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14)));
		pan.setAlignmentX(Component.CENTER_ALIGNMENT);
		pan.add(jeu2);
		pan.add(new JLabel("               "));
		pan.add(jeu3);
		
		for(int i = 0 ; i < 5 ; i++)
			panel.add(new JLabel("               "));
		panel.add(jeu1);
		panel.add(new JLabel("               "));
		panel.add(new JLabel("               "));
		panel.add(pan);
	}

	public void creerMenu() {
		// TODO Auto-generated method stub
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
			
		prec = new JMenuItem("Main menu");
		Regles = new JMenuItem("Help");
		config = new JMenuItem("Network configuration");
		quitter = new JMenuItem("Exit");
		menu.add(prec);prec.addActionListener(this);
		menu.add(config);config.addActionListener(this);
		menu.add(quitter);quitter.addActionListener(this);
		
		JMenu menu2 = new JMenu("Help");
		apropos = new JMenuItem("About");
		menu2.add(Regles);Regles.addActionListener(this);
		menu2.addSeparator();
		menu2.add(apropos);apropos.addActionListener(this);
		
		menuBar.add(menu);
		menuBar.add(menu2);
		this.setJMenuBar(menuBar);
	}

	public JPanel regles() {
	    JPanel pan = new JPanel();
	    JPanel centre = new JPanel();
	    Box b = Box.createVerticalBox();

	    // Title label
	    JLabel titleLabel = new JLabel("Color Conquest Game");
	    titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
	    centre.add(titleLabel);
	    b.add(centre);
	    centre.setBackground(Color.white);
	    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment for title

	    // Description label
	    JLabel descriptionLabel = new JLabel("<html><body><font size=\"+1\"></b><p></b><p> Color Conquest is an exciting strategy game that can be played <p><b>" +
	            "either solo or with a friend in two-player mode. The game board is a grid" +
	            "</b><p> filled with vibrant balls of different colors. " +
	            "</b><p>" +
	            "</b><p> Gameplay:" +
	            "</b><p> The game begins from an initial starting ball. " +
	            "</b><p> Your goal is to conquer the largest territory by selecting" +
	            "</b><p> the dominant color from neighboring balls of the same color. " +
	            "</b><p> Once chosen, all connected balls of that color will change, allowing your territory to expand. " +
	            "</b><p> The challenge is to keep growing your area before time runs out!</b><p>" +
	            "</b><p> Two-Player Mode:" +
	            "</b><p> To play against a friend, both players must be connected" +
	            "</b><p> to the same local network. Before starting a match in Double Mode," +
	            "</b><p> go to Menu --> Configuration, and enter your opponent's IP address. May the best strategist win!" +
	            "</b><p></b><p></b><p></b><p></font></body></html>");

	    JScrollPane scrollPane = new JScrollPane(descriptionLabel);
	    scrollPane.setPreferredSize(new Dimension(530,545));
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	    centre = new JPanel();
	    centre.setBackground(Color.white);
	    centre.setLayout(new BorderLayout()); 
	    centre.add(scrollPane, BorderLayout.CENTER); 
	    b.add(centre);
	    
	    pan.add(b, BorderLayout.CENTER);
	    pan.setBackground(Color.WHITE);
	    
	    return pan;
	}

	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object src = arg0.getSource();
		if(src.equals(jeu1)){
			panel.removeAll();
			panel.add(new MonoPoste());
			validate();
			panel.setVisible(true);
		}
		if(src.equals(jeu2)){
			panel.removeAll();
			panel.add(new UnPoste());
			validate();
			panel.setVisible(true);
		}
		if(src.equals(jeu3)){
			panel.removeAll();
			panel.add(new DoublePoste(IP));
			validate();
			panel.setVisible(true);
		}
		
		if(src.equals(prec)){
			panel.removeAll();
			init();
			validate();
			panel.setVisible(true);
		}
		if(src.equals(Regles)){
			panel.removeAll();
			panel.add(regles());
			validate();
			panel.setVisible(true);
		}
		if(src.equals(quitter)){
			System.exit(0);
		}
		if(src.equals(config)){
			final JFrame frame= new JFrame("Network configuration");
            frame.setVisible(true);
            frame.setSize(new Dimension(360,150));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //frame.setBackground(new Color(215, 183, 136));
            try {
				frame.setIconImage(ImageIO.read(this.getClass().getResource("conquete.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            JPanel contentPane;
			final JTextField textField;
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(null);
			
			JLabel lblVotreip = new JLabel("           @IP");
			lblVotreip.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblVotreip.setBounds(12, 29, 76, 26);
			contentPane.add(lblVotreip);
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textField.setBounds(100, 27, 247, 33);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JButton btnValider = new JButton("Submit");
			btnValider.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnValider.setBounds(128, 78, 97, 32);
			contentPane.add(btnValider);
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					IP = textField.getText();
					frame.dispose();
				}
			});

            frame.getContentPane().add(contentPane);
            frame.setResizable(false);
		}
		if(src.equals(apropos)){
			JFrame frame= new JFrame("About");
            frame.setVisible(true);
            frame.setSize(new Dimension(450,200));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //frame.setBackground(new Color(215, 183, 136));
            try {
				frame.setIconImage(ImageIO.read(this.getClass().getResource("conquete.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Font font = new Font("Courier new", Font.PLAIN, 16);
            
            JPanel p1 = new JPanel();
            p1.setBorder(new MatteBorder(5, 5, 5, 5, new Color(215, 183, 136)));
           
            //p1.setBackground(new Color(215, 183, 136));
            JTextArea tx = new JTextArea();
            tx.setOpaque(false);
            //tx.setBackground(new Color(215, 183, 136));
            tx.setFont(font);
            tx.setText("\nAuthor: Khaled KADRI\n");
            /*tx.append("T�l: (+213)772 44 64 32\n");
            tx.append("Email: khaled.kadri@outlook.com\n");*/
            tx.append("\nLinkedIn:\n");
            tx.append("https://www.linkedin.com/in/khaled-kadri/\n");
            
            final JTextPane textPane = new JTextPane();
            textPane.setContentType("text/html");
            String link = "<a href=\"https://www.linkedin.com/in/khaled-kadri/\" style=\"color: #0077B5; text-decoration: none; font-weight: bold;\">LinkedIn</a><br>"
            		+ "<a href=\"https://github.com/khaledkadri\" style=\"color: #0077B5; text-decoration: none; font-weight: bold;\">GitHub</a>";
            textPane.setText("<html><body style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333;\">" +
                    "<br><b>Author: Khaled KADRI</b><br>" +
                    "<p>Connect with me on :</p>" +
                    link +
                    "</body></html>");
            textPane.setEditable(false);
            
            textPane.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        // When a hyperlink is clicked
                        try {
                            // Open the clicked URL in the default web browser
                            Desktop.getDesktop().browse(URI.create(e.getURL().toString()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            
            tx.setEditable(false);
            p1.add(tx,BorderLayout.CENTER);
            frame.getContentPane().add(textPane);
            p1.add(tx,BorderLayout.CENTER);
            frame.setResizable(false);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MenuPrinc();
	}

}
