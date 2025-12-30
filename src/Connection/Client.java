/**
 * Client.java
 *
 * Connects to a server for a two-player network game.
 * Sets up input and output streams to send and receive messages.
 *
 * License: MIT
 * Author: @Khaled Kadri
 */


package Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client  extends Thread{
	Socket socket;
	public BufferedReader in;
	public PrintWriter out;
	
	public void creationClient(String ip){
		try {
			socket = new Socket(ip,5000);
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	        out = new PrintWriter(socket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}