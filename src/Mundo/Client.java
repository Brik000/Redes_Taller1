package Mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	/**
	 * Numero del puerto que se utilizara
	 */
	public final static int PORT = 1024;
	/**
	 * Nombre del host
	 */
	public final static String HOST = "localhost";
	/**
	 * mecanismo que nos permite establecer un enlace entre dos programas que se ejecutan independientes el uno del otro
	 */
	private static Socket socket ;
	/**
	 * BufferedReader permite lectura de datos 
	 */
	private static BufferedReader entrada ;
	/**
	 * PrintWritter permite imprimir representaciones formateadas de una salida de stream de texto
	 */
	private static PrintWriter salida ;
	
	/**
	 * Constructor del objeto Client
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	private Client() throws UnknownHostException, IOException {
		 socket = new Socket(HOST, PORT);
		 entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		 salida = new PrintWriter (new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		
	}
	
	public static void main(String[] args) {
		System.out.println("---cliente---");
		System.out.println("ingrese cadena a encriptar");
		boolean control=true;
		
		try {
			//se crea el onjeto client
			new Client();
			// se inicaliza un BufferedReader que permitira la lectura de datos desde la consola
			 BufferedReader readConsole = new BufferedReader(new InputStreamReader(System.in));
			 String leido = readConsole.readLine();
			//envia el string ecrito en la consola al servidor
			salida.println(leido);
			//recive e imprime la respuesta del servidor en consola
			System.out.println("Respuesta encriptada: " + entrada.readLine());
			// repite las instrucciones anteriores hasta que se reciva una cadena vacia
			while (control) {
				System.out.println("ingrese una nueva cadena a encriptar");
				leido = readConsole.readLine();
		        salida.println(leido);
		        leido = entrada.readLine();
			System.out.println("Respuesta encriptada: " + leido);
			if (leido.isEmpty()) {
				control=false;
				}
			}
			
			
			
		} catch (UnknownHostException e) {
			System.out.println("El host no es v�lido");
		} catch (IOException e) {
			System.out.println("Ha habido un problema durante la lectura");
		}finally {
			try {
				socket.close();
				entrada.close();
				salida.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	

}
