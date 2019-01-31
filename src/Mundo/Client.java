package Mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public final static int PORT = 126;
	
	public final static String HOST = "localhost";
	
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader entrada = null;
		BufferedWriter salida = null;
		try {
			 socket = new Socket(HOST, PORT);
			 entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			BufferedReader readConsole = new BufferedReader(new InputStreamReader(System.in));
			String leido = readConsole.readLine();
			salida.write(leido);
			salida.flush();
			System.out.println("Respuesta servidor: " + entrada.readLine());
		} catch (UnknownHostException e) {
			System.out.println("El host no es válido");
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
