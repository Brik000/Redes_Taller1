package Mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static ServerSocket server;
	private static Socket socketClient ;
	private static BufferedReader entrada;
	private static PrintWriter salida;
	
	public final static int VALOR_ENCRIPTACION=2;
	
	public final static int PORT=1024;
	
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
			System.out.println("---servidor---");
		 
		 try {
			 boolean control=true;
	      server = new ServerSocket(PORT);
		  socketClient = server.accept();
	      System.out.println("Connexión acceptada: "+ socketClient);

		  
		  entrada=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		  salida=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())),true);
		  

		  // se recive la cadena del cliente
		  String str = entrada.readLine();

		  
		 // System.out.println(str+" cadena");
		  
		  //se encripta la cadena
		  String encrypted = encrypt(str);
		  
		//  System.out.println(encrypted+" encriptada");
		  
		  //se envia la cadena encriptada
		  
		  salida.println(encrypted+" ");
		  
		  while (control) {
			  	salida.flush();
		        str = entrada.readLine();
		        encrypted = encrypt(str);
				salida.println(encrypted);
			if (str.isEmpty()) 
				control=false;
		      }
		  
		  
		  
		    salida.close();
			entrada.close();
			socketClient.close();
		   
		 } catch (IOException e) {
			// TODO Auto-generated catch block
		      System.out.println("IOException: " + e.getMessage());
		}
		   
	
	}
	
	

}
