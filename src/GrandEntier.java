import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GrandEntier {

	final static int BASE = 16;
	final static int MAXBITLENGTH = 10000000;

	private ArrayList<Integer> definition;

	public GrandEntier(ArrayList<Integer> ge) throws Exception {
		int i;
		for (i = 0; i < ge.size(); i++) {
			int valeurTestee = ge.get(i);
			if (valeurTestee > BASE || valeurTestee < 0) {
				throw new Exception(
						"La Base de l'arraylist en parametre est differente de la base du grand entier");
			}		
		}
		definition = ge;
		System.out.println(definition.toString());
	}

	public GrandEntier(int nombreDeBits, Random rnd) {
		definition = new ArrayList<Integer>();
		// récupération du nombre d'octets necessaires
		int nombreOctets = (int) ((long) nombreDeBits + 7 / 8);
		// creation d'un tableau d'octets de la longueur necessaire
		byte[] octetsAleatoires = new byte[nombreOctets];
		// Creation du nombre Aleatoires en mettant les bits des octets à 0 ou 1
		// aleatoirement
		rnd.nextBytes(octetsAleatoires);

	}

}
