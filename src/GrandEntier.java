import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GrandEntier implements Comparable {

	final static int BASE = 16;
	final static int MAXBITLENGTH = 10000000;

	private ArrayList<Integer> definition;

	public GrandEntier(ArrayList<Integer> ge) throws Exception {
		int i;
		for (i = 0; i < ge.size(); i++) {
			int valeurTestee = ge.get(i);
			if (valeurTestee >= BASE || valeurTestee < 0) {
				throw new Exception(
						"La Base de l'arraylist en parametre est differente de la base du grand entier");
			}
		}
		definition = ge;
		Collections.reverse(definition);
	}

	/**
	 * 
	 * Pas encore au point voir Work1
	 */
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

	@Override
	public String toString() {

		return definition.toString();
	}

	public int length() {

		return definition.size();

	}

	public void shiftLeft(int n) {
		int i;
		for (i = 0; i < n; i++) {
			definition.add(0, 0);
		}

	}

	/**
	 * 
	 * @param ge
	 * @return resultat the result of the add
	 * @throws Exception if the GrandEntiers are not with the same "BASE"
	 */
	public GrandEntier add(GrandEntier ge) throws Exception {
		ArrayList<Integer> sommeDef = new ArrayList<Integer>();
		GrandEntier plusGrand, plusPetit, resultat;
		int tailleG, tailleP, somme, i, retenu;
		retenu = 0;

		// la comparaison pour savoir lequel est le plus grand en terme de
		// "case"
		if (ge.length() > this.length()) {
			tailleG = ge.length();
			tailleP = this.length();
			plusGrand = ge;
			plusPetit = this;
		} else {
			tailleG = this.length();
			tailleP = ge.length();
			plusGrand = this;
			plusPetit = ge;
		}

		// l'addition
		for (i = 0; i < tailleG; i++) {
			somme = 0;
			if (tailleP > i) {
				somme = plusGrand.getDefinition().get(i)
						+ plusPetit.getDefinition().get(i) + retenu;

				if (somme >= BASE) {
					somme %= BASE;
					retenu = 1;
				} else {
					retenu = 0;
				}
			} else {
				somme = plusGrand.getDefinition().get(i);
			}
			sommeDef.add(somme);

		}
		if (retenu == 1)
			sommeDef.add(retenu);
		Collections.reverse(sommeDef);
		resultat = new GrandEntier(sommeDef);

		return resultat;

	}

	/**
	 * @return the definition
	 */
	public ArrayList<Integer> getDefinition() {
		return definition;
	}

	/**
	 * @param definition
	 *            the definition to set
	 */
	public void setDefinition(ArrayList<Integer> definition) {
		this.definition = definition;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
