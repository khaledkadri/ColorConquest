/**
 * Serveur.java
 *
 * Simple server for a two-player network game.
 * Listens on port 5000 and communicates with a client to send/receive moves.
 * 
 * License: MIT
 * Author: @Khaled Kadri
 */


package Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Jeu.DoublePoste;

public class Serveur extends Thread{
	public Socket socketduserveur ;
	public BufferedReader in;
	public PrintWriter out;
	public boolean moiserveur;
	DoublePoste jeu;
	
	public Serveur(DoublePoste jeu){
		this.jeu=jeu;
	}
	
	public void run(){
		ecoute();
	}
	public void ecoute(){
		ServerSocket socketserver  ;
		try {
			socketserver = new ServerSocket(5000);
			socketduserveur = socketserver.accept();
			System.out.println("You are connected !");
			moiserveur=true;
			out = new PrintWriter(socketduserveur.getOutputStream());
			in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
			recever();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String recever(){
		String message_distant=null;
		try {
			message_distant = in.readLine();
			jeu.Evenement(message_distant,false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message_distant;
	}
}