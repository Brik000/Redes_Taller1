package Mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static ServerSocket server;
	private static Socket socketClient ;
	private static BufferedReader entrada;
	private static BufferedWriter salida;
	
	public final static int VALOR_ENCRIPTACION=2;
	
	public final static int PORT=126;
	
	 /**
	  * Descripcion:Se encarga de encriptar un mensaje recibido por el cliente
	  * @param original= mensaje original recibido.
	  * @return mensaje encriptado
	  */
	public static String encrypt(String original) {
        String encrypted = "";
        for (int i = 0; i < original.length(); i++) {
            int c = original.charAt(i) + VALOR_ENCRIPTACION;
            if (c > 126) {
                c -= 95;
            } else if (c < 32) {
                c += 95;
            }
            encrypted += (char) c;
        }
        return encrypted;
    }

	
	
	public static void main(String[] args) {
			
		 
		 try {
	      server = new ServerSocket(PORT);
		  socketClient = server.accept();
		  entrada=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		  salida=new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
		
		  String str = entrada.readLine();
		  
		  salida.write(encrypt(str)+"");
		  
		    salida.close();
			entrada.close();
			socketClient.close();
		   
		 } catch (IOException e) {
			// TODO Auto-generated catch block
		      System.out.println("IOException: " + e.getMessage());
		}
		   
	
	}
	
	

}
