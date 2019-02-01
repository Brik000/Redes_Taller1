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
	/**
	 * ServerSocket que indica el sevidor
	 */
	private static ServerSocket server;
	/**
	 * mecanismo que nos permite establecer un enlace entre dos programas que se ejecutan independientes el uno del otro
	 */
	private static Socket socketClient ;
	/**
	 * BufferedReader permite lectura de datos 
	 */
	private static BufferedReader entrada;
	/**
	 * PrintWritter permite imprimir representaciones formateadas de una salida de stream de texto
	 */
	private static PrintWriter salida;
	
	/**
	 * Constante que indica el valor por el cual se modificaran los caratreres segun la tabla ASCII en la necriptacion
	 */
	public final static int VALOR_ENCRIPTACION=2;
	/**
	 * puerto que sera utilizado en el modelo
	 */
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
	/**
	 * Server= crea el objeto server que funcionara com servidor del programa
	 * @throws IOException
	 */
	private Server() throws IOException {
		server = new ServerSocket(PORT);
		socketClient = server.accept();
	    System.out.println("Connexión exitosa: "+ socketClient);
		entrada=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		salida=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())),true);
		
	}
	
	
	public static void main(String[] args) {
			System.out.println("---servidor---");
			boolean control=true;
		 
		 try {
			 //Se crea el servidor local
		 new Server(); 
		  // se recive la cadena del cliente
		  String str = entrada.readLine();
		 
		  //se encripta la cadena
		  String encrypted = encrypt(str);
		  		  
		  //se envia la cadena encriptada
		  
		  salida.println(encrypted+" ");
		  
		  //Bucle que repite el proceso anterior, hasta que se reciva una cadena vacia
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
