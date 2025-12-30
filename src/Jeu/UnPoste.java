/**
 * Two-player local mode for the Color Conquest game.
 * Manages the game grid, player turns, color selection, 
 * and victory conditions between two players on the same device.
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Jeu.MonoPoste.coordonees;

public class UnPoste extends JPanel implements ActionListener{
	Random random=new Random();
	private Graphic graphic= new Graphic();
	JButton boutons[];
	ArrayList<String> listcouleur=new ArrayList<String>();
	String matriceCouleur[][];
	String j1,j2;
	boolean matricej1[][] = new boolean[8][8];
	boolean matricej2[][] = new boolean[8][8];
	public int tour = 0 ;
	boolean start,montour;
	
	String listeCouleur[]={"Bleu","Bleu","Rose","Rose","Rose","Jaune","Magenta","Jaune","Verte","Rouge","Bleu","Verte","Jaune","Bleu","Verte","Rose","Rose","Bleu","Magenta","Rouge","Bleu","Rouge","Magenta","Rouge","Rose","Verte","Magenta","Rouge","Rouge","Bleu","Rouge","Verte","Rouge","Jaune","Rose","Rose","Bleu","Verte","Magenta","Bleu","Rouge","Magenta","Verte","Verte","Rouge","Rose","Rouge","Rose","Rose","Rouge","Rose","Jaune","Magenta","Rouge","Rouge","Rose","Jaune","Magenta","Magenta","Magenta","Rose","Bleu","Verte","Rose"};
	
	public UnPoste(){
		this.setVisible(true);
		this.setPreferredSize(new Dimension(560,650));
		this.setLayout(new BorderLayout());
		JPanel north = new JPanel(new GridLayout(1, 1));
		north.setBorder(new LineBorder(Color.black, 3));
		JPanel p1 = new JPanel();
		JLabel label1 = new JLabel("Two players");
		Font f = new Font("Serif", Font.PLAIN, 20);
		label1.setFont(f);
		p1.add(label1);
		north.add(p1);
		add(north,BorderLayout.NORTH);
		
		matriceCouleur = new String[8][8];
		Color couleur[] = {Color.pink,Color.blue,Color.green,Color.red,Color.magenta,Color.yellow};
		for(int i = 0 ; i < 8 ; i++)
			for(int j = 0 ; j < 8 ; j++){
				graphic.matrice[i][j] = getCouleurOriginal(listeCouleur[i*8+j]);
				matriceCouleur[i][j] = getCouleur(graphic.matrice[i][j]);
			}
		graphic.matrice[0][0] = Color.white;
		matriceCouleur[0][0] = getCouleur(Color.white);
		graphic.matrice[7][7] = Color.white;
		matriceCouleur[7][7] = getCouleur(Color.white); 
		
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
			//boutons[i] = new GradientButton(couleur[i]);
			boutons[i].setBackground(couleur[i]);
			boutons[i].addActionListener(this);
			pancouleur.add(boutons[i]);
		}
		pancouleur.add(new JLabel("       "));
		pancouleur.add(new JLabel("       "));
		add(pancouleur,BorderLayout.SOUTH);
		matricej1 = new boolean[8][8];
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		Object source = arg0.getSource();
		String couleur = null;
		for(int i = 0 ; i < boutons.length ; i++)
			if(source.equals(boutons[i]))
				couleur = getCouleur(boutons[i].getBackground());
	    Evenement(couleur,montour);
	}
	
	ArrayList<coordonees> bandej1(boolean[][] matricej){
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

	ArrayList<coordonees> bandej2(boolean[][] matricej){
		ArrayList<coordonees> bordure = new ArrayList<coordonees>();
  	  	ArrayList<Integer> indice = new ArrayList<Integer>();
  	  	for(int j = 7 ; j >=0 ; j--){
  	  		indice = new ArrayList<Integer>();
  	  		int i = 7;
  	  		if(matricej[i][j]==false){
  	  			for(i = 7 ; i >=0 ; i--)
  	  				if(matricej[i][j])
  	  					indice.add(i);
  	  		}
  	  		else{
  	  			while(i>=0 && matricej[i][j]==true)
  	  				i--;
  	  			indice.add(i+1);
  	  		}
  	  		//entourage
  	  	for(int ind = 0 ; ind < indice.size() ; ind++){
  			i = indice.get(ind);
  			if(i>=0 && i<8 && matricej[i][j]==true)
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

	public void Evenement(String couleurRecu,boolean moiDeclencheEvt){
		String couleurChoisi;
		couleurChoisi = couleurRecu;
		
		if(moiDeclencheEvt){
			if(!couleurRecu.equals(j2)){
			if(j1==null){
				matriceCouleur[0][0]=couleurChoisi;
				matricej1[0][0]=true;
				j1=couleurChoisi;
			}
			ArrayList<coordonees> cordBordure = bandej1(matricej1);
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
					//matriceCouleur[cordBordure.get(j).x][cordBordure.get(j).y]=couleurChoisi;
				}
			}
			cordBordure = bandej1(matricej1);
				couleurAutorise=false;
				for(int j = 0 ; j < cordBordure.size() ; j++){
					//System.out.println(matriceCouleur[cordBordure.get(j).x][cordBordure.get(j).y]+" "+cordBordure.get(j).x+" "+cordBordure.get(j).y  +" "+couleurChoisi+" "+couleurChoisi.equals(matriceCouleur[cordBordure.get(j).x][cordBordure.get(j).y]));
					if(couleurChoisi.equals(matriceCouleur[cordBordure.get(j).x][cordBordure.get(j).y])
							){
						couleurAutorise=true;
						break;
					}
				}
				
		}
			for(int i = 0 ; i < 8 ; i++)
				for(int j = 0 ; j < 8 ; j++){
					if(matricej1[i][j]==true){
						matriceCouleur[i][j]=couleurChoisi;
						
		    		}
				}
		j1=couleurChoisi;
			montour=false;
			if(j2!=null){
			for(int i = 0 ; i < boutons.length ; i++){
				if(boutons[i].getBackground().equals(Color.DARK_GRAY)){
					boutons[i].setEnabled(true);
					boutons[i].setBackground(getCouleurOriginal(j2));
				}}
			}
			for(int i = 0 ; i < boutons.length ; i++)
				if(getCouleur(boutons[i].getBackground()).equals(couleurChoisi)){
					boutons[i].setEnabled(false);
					boutons[i].setBackground(Color.DARK_GRAY);
				}
			
			}
		}
		else{
			if(!couleurRecu.equals(j1)){
			if(j2==null){
				matriceCouleur[7][7]=couleurChoisi;
				matricej2[7][7]=true;
				j2=couleurChoisi;
			}
			ArrayList<coordonees> cordBordure = bandej2(matricej2);
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
						|| (matricej2[cordBordure.get(j).x][cordBordure.get(j).y])
						){
					matricej2[cordBordure.get(j).x][cordBordure.get(j).y]=true;
				}
			}
			cordBordure = bandej2(matricej2);
				
				couleurAutorise=false;
				for(int j = 0 ; j < cordBordure.size() ; j++){
					if(couleurChoisi.equals(matriceCouleur[cordBordure.get(j).x][cordBordure.get(j).y])
							){
						couleurAutorise=true;
						break;
					}
				}
				
				
				
		}
			for(int i = 0 ; i < 8 ; i++)
				for(int j = 0 ; j < 8 ; j++){
					if(matricej2[i][j]==true){
						matriceCouleur[i][j]=couleurChoisi;
		    		}
				}
			j2=couleurChoisi;
			montour=true;
			}
			if(j1!=null)
				for(int i = 0 ; i < boutons.length ; i++)
					if(boutons[i].getBackground().equals(Color.DARK_GRAY)){
						boutons[i].setEnabled(true);
						boutons[i].setBackground(getCouleurOriginal(j1));
					}
			for(int i = 0 ; i < boutons.length ; i++)
				if(getCouleur(boutons[i].getBackground()).equals(couleurChoisi)){
					boutons[i].setEnabled(false);
					boutons[i].setBackground(Color.DARK_GRAY);
				}
			
		}
		
		for(int i = 0 ; i < 8 ; i++)
			for(int j = 0 ; j < 8 ; j++)
				graphic.matrice[i][j] = getCouleurOriginal(matriceCouleur[i][j]);
		repaint();
		/*
		 * point d'appuis
		 */
		      ArrayList<String> couleur = new ArrayList<String>();
		      ArrayList<Integer> nbcouleur = new ArrayList<Integer>();
			    for(int i = 0 ; i < 8 ; i++)
			    	for(int j = 0 ; j < 8 ; j++){
			    	if(!couleur.contains(matriceCouleur[i][j])){
			    		couleur.add(matriceCouleur[i][j]);
			    		nbcouleur.add(1);
			    	}
			    	else{
			    		nbcouleur.set(couleur.indexOf(matriceCouleur[i][j]), nbcouleur.get(nbcouleur.size()-1)+1);
			    	}
			    }
			    if(couleur.size()==2){
			    	String couleur1,couleur2;
			    	couleur1 = couleur.get(0);
			    	couleur2 = couleur.get(1);
			    	if(nbcouleur.get(0) > nbcouleur.get(1)) {
			    	    JOptionPane.showMessageDialog(null, "The player of color " + couleur1 + " has won", "", JOptionPane.INFORMATION_MESSAGE);
			    	} else if(nbcouleur.get(0) < nbcouleur.get(1)) {
			    	    JOptionPane.showMessageDialog(null, "The player of color " + couleur2 + " has won", "", JOptionPane.INFORMATION_MESSAGE);
			    	}
			    	else
			    		JOptionPane.showMessageDialog(null,"Egalit� !","colorised", JOptionPane.INFORMATION_MESSAGE);
			    
			    }
			    
			    
			    
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
		  if(couleur.equals("White"))
		  		_couleur=Color.white;
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
		  if(couleur.equals(Color.white))
		  		_couleur="White";
		return _couleur;
	}
	
	public class coordonees{
		public int x,y;
		public coordonees(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		UnPoste jeu = new UnPoste();
		JFrame fr= new JFrame();
		fr.setContentPane(jeu);
		fr.pack();
		fr.setVisible(true);
	}
}
