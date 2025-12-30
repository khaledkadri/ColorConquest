/**
 * Envoi.java
 *
 * Sends messages over the network to the other player in a two-player game.
 * Uses a PrintWriter to transmit strings and flushes the stream immediately.
 *
 * License: MIT
 * Author: @Khaled Kadri
 */

package Connection;
import java.io.PrintWriter;
import java.net.Socket;

public class Envoi{
	public Socket socket ;
	PrintWriter out;
	String s;
		
	public Envoi(PrintWriter out){
		this.out=out;
	}
	
	public void envoyer(String s){
		this.out.println(s);
		this.out.flush();
	}

}