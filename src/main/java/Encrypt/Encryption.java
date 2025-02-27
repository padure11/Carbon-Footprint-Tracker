package Encrypt;
import java.util.Random;

public class Encryption {
	
	/**
	 * Functie pentru a cripta parola utilizatorului.
	 * Foloseste un algoritm simplu
	 * @param password    parola transmisa de utilizator (ne schimbata)
	 * @return			  parola criptata 
	 */
	public static String encrypt(String password) {
		StringBuilder encryptedPassWord = new StringBuilder();
		int offset = 10;
		for (char character : password.toCharArray()) {
		    if (character != ' ') {
		        int originalAlphabetPosition = character - 'a';
		        int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
		        char newCharacter = (char) ('a' + newAlphabetPosition);
		        encryptedPassWord.append(newCharacter);
		    } else {
		    	encryptedPassWord.append(character);
		    }
		}
		return encryptedPassWord.toString();
	}
	
	/**
	 * Metoda ce genereaza un id aleator unui user
	 * @return     un id aleator 
	 */
	public static int randomIdUser() {
		Random random = new Random();
		int randomInt = random.nextInt();
		return randomInt;
	}
	
	
}
