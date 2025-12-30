/**
 * Reception.java
 *
 * Thread to receive messages from the network for a two-player game.
 * Continuously listens to the input stream and updates the game state accordingly.
 *
 * License: MIT
 * Author: @Khaled Kadri
 */


package Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import Jeu.DoublePoste;

public class Reception extends Thread {
	public Socket socket ;
	BufferedReader in;
	PrintWriter out;
	String recu;
	DoublePoste jeu;
	Serveur serveur;
	
	public Reception(BufferedReader in,DoublePoste jeu,Serveur serveur){
		this.in=in;
		this.jeu=jeu;
		this.serveur=serveur;
	}
	
	public void run(){
		recever();
	}
	
	public String recever(){
		String message_distant=null;
		String[] separe = null;
		while(true){
		try {
				message_distant = this.in.readLine();
				if(serveur.moiserveur)
					jeu.Evenement(message_distant,false);
				else
					jeu.Evenement(message_distant,true);
				System.out.println("ubuntu : "+message_distant);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Player B had abandoned the game","", JOptionPane.INFORMATION_MESSAGE);
			System.exit(-1);
			e.printStackTrace();
		}
		}
	}
	
}