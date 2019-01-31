package Mundo;

public class Server {
	
	public final static int VALOR_ENCRIPTACION=2;
	
	public static String encrypt(String original, int shift) {
        String encrypted = "";
        for (int i = 0; i < original.length(); i++) {
            int c = original.charAt(i) + shift;
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
		
	}
	
	

}
