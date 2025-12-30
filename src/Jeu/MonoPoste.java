/**
 * Single-player mode for the Color Conquest game.
 * Displays the game grid, manages color selection, 
 * player moves, and time tracking.
 * 
 * Author: Khaled KADRI
 * License: MIT
 */

package Jeu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MonoPoste extends JPanel implements ActionListener{
	Random random=new Random();
	private Graphic graphic= new Graphic();
	JButton boutons[];
	ArrayList<String> listcouleur=new ArrayList<String>();
	String matriceCouleur[][];
	String j1,j2;
	boolean matricej1[][] = new boolean[8][8];
	boolean matricej2[][] = new boolean[8][8];
	public int num = 0 ;
	Chrono Chrono;
	boolean stop,debut;
	JLabel tpsjoueur1;
	double t1,t2;
	Thread ttps;
	int nbClic;
	
	public MonoPoste(){
		this.setVisible(true);
		this.setPreferredSize(new Dimension(560,650));
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		JPanel north = new JPanel();
		BoxLayout layout = new BoxLayout(north, BoxLayout.Y_AXIS);
		north.setLayout(layout);
		JPanel p1 = new JPanel();
		JLabel label1 = new JLabel("One player");
		Font f = new Font("Serif", Font.PLAIN, 16);
		label1.setFont(f);
		p1.add(label1);
		p1.setBorder(new LineBorder(Color.black, 3));
		//north.add(p1);
		p1.setPreferredSize(new Dimension(500,40));
		JPanel north2 = new JPanel();
		north2.setPreferredSize(new Dimension(500,60));
		add(north2,BorderLayout.NORTH);
		north2.setBorder(BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Time", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14)));
		Chrono = new Chrono(north2);
		north.add(north2);
		
		add(north,BorderLayout.NORTH);
		
		matriceCouleur = new String[8][8];
		Color couleur[] = {Color.pink,Color.blue,Color.green,Color.red,Color.magenta,Color.yellow};
		for(int i = 0 ; i < 8 ; i++)
			for(int j = 0 ; j < 8 ; j++){
				graphic.matrice[i][j] = couleur[random.nextInt(couleur.length)];
				matriceCouleur[i][j] = getCouleur(graphic.matrice[i][j]); 
				System.out.print((char)34+getCouleur(graphic.matrice[i][j])+(char)34+",");
			}
		graphic.matrice[0][0] = Color.white;
		matriceCouleur[0][0] = getCouleur(Color.white); 
		graphic.setBorder(BorderFactory.createLineBorder (Color.GRAY, 2));
		add(graphic,BorderLayout.CENTER);
		
		GridLayout grid = new GridLayout(1, 10, 0, 0);
		JPanel pancouleur = new JPanel(grid);
		boutons = new JButton[6];
		pancouleur.setPreferredSize(new Dimension(100, 50));
		pancouleur.setBorder(BorderFactory.createLineBorder (Color.BLUE, 1));
		
		pancouleur.add(new JLabel("       "));
		pancouleur.add(new JLabel("       "));
		for(int i = 0 ; i < 6;i++){
			boutons[i] = new JButton();
			boutons[i].setBackground(couleur[i]);
			boutons[i].addActionListener(this);
			pancouleur.add(boutons[i]);
		}
		pancouleur.add(new JLabel("       "));
		pancouleur.add(new JLabel("       "));
		add(pancouleur,BorderLayout.SOUTH);
		matricej1 = new boolean[8][8];
		nbClic=0;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		nbClic++;
		if(!debut){
			ttps = new Thread(Chrono);
			ttps.start();
			t1 = System.currentTimeMillis();
		}
		debut = true;
		Object source = arg0.getSource();
		for(int i = 0 ; i < boutons.length ; i++)
			if(source.equals(boutons[i]))
				Evenement(getCouleur(boutons[i].getBackground()));
		for(int i = 0 ; i < 8 ; i++)
			for(int j = 0 ; j < 8 ; j++)
				graphic.matrice[i][j] = getCouleurOriginal(matriceCouleur[i][j]);
		repaint();
		/*
		 * point d'appuis
		 */
		ArrayList<String> couleur = new ArrayList<String>();
		for(int i = 0 ; i < 8 ; i++)
			for(int j = 0 ; j < 8 ; j++){
				if(!couleur.contains(matriceCouleur[i][j]))
					couleur.add(matriceCouleur[i][j]);
			}
			if(couleur.size()==1){
				ttps.stop();
				JOptionPane.showMessageDialog(null, "You have won!    \nThe number of moves was : " + nbClic, "", JOptionPane.INFORMATION_MESSAGE);
			}
	}
	
	ArrayList<coordonees> bande(boolean[][] matricej){
		ArrayList<coordonees> bordure = new ArrayList<coordonees>();
  	  	ArrayList<Integer> indice = new ArrayList<Integer>();
  	  	for(int j = 0 ; j < 8 ; j++){
  	  		indice = new ArrayList<Integer>();
  	  		int i = 0;
  	  		if(matricej[i][j]==false){
  	  			for(i = 0 ; i < 8 ; i++)
  	  				if(matricej[i][j])
  	  					indice.add(i);
  	  		}
  	  		else{
  	  			while(i<8 && matricej[i][j]==true)
  	  				i++;
  	  			indice.add(i-1);
  	  		}
  	  		//entourage
  	  		for(int ind = 0 ; ind < indice.size() ; ind++){
  	  			i = indice.get(ind);
  	  			if(i>=0 && matricej[i][j]==true)
  	  				for(int i1 = i-1 ; i1 <= i+1 ; i1++)
  	  					for(int j1 = j-1 ; j1 <= j+1 ; j1++){
  	  						coordonees cord = new coordonees(i1, j1);
  	  						if(i1 !=-1 && j1 !=-1 && i1<8 && j1<8)
  	  							if(matricej[i1][j1]!=true && !bordure.contains(cord))
  	  								bordure.add(cord);
  	  					}
  	  		}
  	  	}
  	  return bordure;
    }
	
	void Evenement(String couleurRecu){
		String couleurChoisi = couleurRecu;
			if(j1==null){
				matriceCouleur[0][0]=couleurChoisi;
				matricej1[0][0]=true;
				j1=couleurChoisi;
			}
				ArrayList<coordonees> cordBordure = bande(matricej1);
				boolean couleurAutorise = false;
				for(int j = 0 ; j < cordBordure.size() ; j++){
					if(couleurChoisi.equals(matriceCouleur[cordBordure.get(j).x][cordBordure.get(j).y])
							){
						couleurAutorise=true;
						break;
					}
				}
				
				while(couleurAutorise){
						//colorer
					for(int j = 0 ; j < cordBordure.size() ; j++){
						if(couleurChoisi.equals(matriceCouleur[cordBordure.get(j).x][cordBordure.get(j).y])
								|| (matricej1[cordBordure.get(j).x][cordBordure.get(j).y])
								){
							matricej1[cordBordure.get(j).x][cordBordure.get(j).y]=true;
							matriceCouleur[cordBordure.get(j).x][cordBordure.get(j).y]=couleurChoisi;
						}
					}
						cordBordure = bande(matricej1);
						couleurAutorise=false;
						for(int j = 0 ; j < cordBordure.size() ; j++){
							if(couleurChoisi.equals(matriceCouleur[cordBordure.get(j).x][cordBordure.get(j).y])
									){
								couleurAutorise=true;
								break;
							}
						}
						
						for(int i = 0 ; i < 8 ; i++)
							for(int j = 0 ; j < 8 ; j++){
								if(matricej1[i][j]==true){
									matriceCouleur[i][j]=couleurChoisi;
									
					    		}
							}
				}
				j1=couleurChoisi;
	}
	
	Color getCouleurOriginal(String couleur){
		Color _couleur = null;
		  if(couleur.equals("Rouge"))
	  		_couleur=Color.red;
		  if(couleur.equals("Bleu"))
	  		_couleur=Color.blue;
		  if(couleur.equals("Jaune"))
	  		_couleur=Color.yellow;
		  if(couleur.equals("Verte"))
	  		_couleur=Color.green;
		  if(couleur.equals("Rose"))
	  		_couleur=Color.pink;
		  if(couleur.equals("Magenta"))
	  		_couleur=Color.magenta;
		return _couleur;
	}
	
	String getCouleur(Color couleur){
		  String _couleur="";
		  if(couleur.equals(Color.red))
	  		_couleur="Rouge";
		  if(couleur.equals(Color.blue))
	  		_couleur="Bleu";
		  if(couleur.equals(Color.yellow))
	  		_couleur="Jaune";
		  if(couleur.equals(Color.green))
	  		_couleur="Verte";
		  if(couleur.equals(Color.pink))
	  		_couleur="Rose";
		  if(couleur.equals(Color.magenta))
	  		_couleur="Magenta";
		return _couleur;
	}
	
	public class coordonees{
		public int x,y;
		public coordonees(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	
	
	
	public class Chrono extends Thread{
  	  int diffSecondes;
  	  int diffMinutes;
  	  int diffHeures;
  	  public Chrono(JPanel p){
  		  tpsjoueur1 = new JLabel("");
  		  tpsjoueur1.setFont(new Font("Courier", Font.BOLD, 22));
  		  tpsjoueur1.setText(""+0+" : "+0+" : "+0);
  		  p.add(tpsjoueur1);
  	  }
  	  public void run(){
  		  while(!stop){
  		  try {
				this.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  				t2 +=System.currentTimeMillis()-t1;
      			int duree = (int)t2 / 1000;
      			diffSecondes = duree%60;
      			if(diffSecondes>=60)
      				diffSecondes=0;
      			diffMinutes = duree / (60);       
      			if(diffMinutes>=60)
      				diffMinutes=0;
      			diffHeures = duree / (60 * 60);
      			tpsjoueur1.setText(""+diffHeures+" : "+diffMinutes+" : "+diffSecondes);
      			t1 = System.currentTimeMillis();
  		  }
  	  }
    }
	
	
	
	public static void main(String[] args) {
		MonoPoste f = new MonoPoste();
		JFrame fr= new JFrame();
		fr.setContentPane(f);
		fr.pack();
		fr.setVisible(true);
	}
}
