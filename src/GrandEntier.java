import java.util.ArrayList;
import java.util.Random;

public class GrandEntier {

	final static int BASE = 16;
	final static int MAXBITLENGTH = 10000000;

	private ArrayList<Integer> definition;

	public GrandEntier(ArrayList<Integer> ge) {
		int i;
		int valeurTestee = -1;
		for (i = 0; i < ge.size(); i++) {
			valeurTestee = ge.get(i);
			if (valeurTestee >= BASE || valeurTestee < 0) {
				throw new IllegalArgumentException(
						"La Base de l'arraylist en parametre est differente de la base du grand entier");
			}

		}
		if (ge.size() > 1) {
			if ((ge.get((ge.size() - 1))) == 0) {
				throw new IllegalArgumentException(
						"Le nombre dont le poid est le plus lourd (soit celui qui est l'index taille de l'arraylist-1) est egale à 0");

			}
		}

		definition = (ArrayList<Integer>) ge.clone();

	}

	/**
	 * 
	 * @param nombreDeBits
	 * @param rnd
	 */
	public GrandEntier(int nombreDeBits, Random rnd) {
		if (nombreDeBits < 0) {
			throw new IllegalArgumentException(
					"Le nombre de bits souhaité est négatif");
		}
		definition = new ArrayList<Integer>();

		// je compte le nombre de bits pour coder la base
		int base = BASE;
		int i;
		int compteur = 0;
		int nombreRandom;
		do {
			compteur++;

		} while ((base /= 2) >= 2);

		// je crée le nombre de nombre nécessaire en fonction des bits
		for (i = 0; nombreDeBits > compteur; i++) {
			nombreRandom = rnd.nextInt(BASE);
			definition.add(nombreRandom);
			nombreDeBits -= compteur;
		}

		// je m'occupe du dernier qui ne peut pas etre égale à 0
		int ledernier = (int) Math.pow(2, nombreDeBits);
		do {
			nombreRandom = rnd.nextInt(ledernier);
		} while (nombreRandom == 0);
		definition.add(nombreRandom);

	}

	@Override
	public String toString() {
		int i;
		int tailleDef = definition.size();
		String laDef = "";
		for (i = (tailleDef - 1); i >= 0; i--) {
			laDef += definition.get(i) + " x " + Integer.toString(BASE) + "^"
					+ i;
			if (i != 0) {
				laDef += " + ";
			}
		}
		return laDef;
	}

	public int length() {

		return definition.size();

	}

	public GrandEntier shiftLeft(int n) {
		int i;
		ArrayList<Integer> result = (ArrayList<Integer>) definition.clone();
		for (i = 0; i < n; i++) {
			result.add(0, 0);
		}
		return new GrandEntier(result);

	}

	/**
	 * 
	 * @param ge
	 * @return resultat the result of the add
	 * @throws IllegalArgumentException
	 *             if the GrandEntiers are not with the same "BASE"
	 */
	public GrandEntier add(GrandEntier ge) {
		ArrayList<Integer> sommeDef = new ArrayList<Integer>();
		GrandEntier plusGrand, plusPetit, resultat;
		int tailleG, tailleP, somme, i, retenu;
		retenu = 0;

		// la comparaison pour savoir lequel est le plus grand en terme de
		// "case"
		if (this.compareTo(ge) == -1) {
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
			// la somme de deux nombres de meme poids de deux grandEntiers
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
				// il ne reste plus qu'un seul grandentier mais il y a la
				// retenue
				somme = plusGrand.getDefinition().get(i) + retenu;
				retenu = 0;
				if (somme >= BASE) {
					somme %= BASE;
					retenu = 1;
				}
			}
			sommeDef.add(somme);

		}
		// si la retenue est encore présente la liste augmente
		if (retenu == 1)
			sommeDef.add(retenu);
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

	public GrandEntier multiply(GrandEntier m) {
		ArrayList<Integer> ze = new ArrayList<Integer>();
		ArrayList<Integer> u = new ArrayList<Integer>();

		ze.add(0);
		u.add(1);
		GrandEntier zero = new GrandEntier(ze);
		GrandEntier un = new GrandEntier(u);

		if (this.equals(zero) || m.equals(zero)) {
			return zero;
		}
		m = m.sub(un);
		GrandEntier lol = (this.multiply(m)).add(this);
		return lol;

	}

	/**
	 * 
	 * @param e
	 * @return -1, 0 ou 1 si this BigInteger est numeriquement inferieur, egal,
	 *         ou superieur à e.
	 */

	public int compareTo(GrandEntier e) {
		int i;
		// si un GrandEntier prend plus de case que l'autre alors il est plus
		// grand
		if (this.length() < e.length()) {
			return -1;
		}

		if (this.length() > e.length()) {
			return 1;
		}

		// s'il font la meme taille
		for (i = this.length() - 1; i >= 0; i--) {
			int result = this.getDefinition().get(i)
					.compareTo(e.getDefinition().get(i));
			if (result < 0) {
				return -1;
			}
			if (result > 0) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param ge
	 * @return resultat the result of the add
	 * @throws IllegalArgumentException
	 *             if the GrandEntiers are not with the same "BASE"
	 */
	public GrandEntier sub(GrandEntier ge) {
		ArrayList<Integer> sousDef = new ArrayList<Integer>();
		GrandEntier plusGrand, plusPetit, resultat;
		int tailleG, tailleP, soustraction, i, retenu;
		retenu = 0;

		if ((this.compareTo(ge)) == -1) {
			throw new IllegalArgumentException(
					"Grand Entier à soustraire est supérieur à l'autre or nous ne formons que des nombres positifs.");
		} else {
			tailleG = this.length();
			tailleP = ge.length();
			plusGrand = this;
			plusPetit = ge;
		}

		// la soustraction
		for (i = 0; i < tailleG; i++) {
			soustraction = 0;
			// la somme de deux nombres de meme poids de deux grandEntiers
			if (tailleP > i) {
				soustraction = plusGrand.getDefinition().get(i)
						- (plusPetit.getDefinition().get(i) + retenu);

				if (soustraction < 0) {
					soustraction %= BASE;
					soustraction = BASE + soustraction;
					retenu = 1;
				} else {
					retenu = 0;
				}
			} else {
				// il ne reste plus qu'un seul grandEntier mais il y a la
				// retenue
				soustraction = plusGrand.getDefinition().get(i) - retenu;
				retenu = 0;
			}
			if (i == (tailleG - 1)) {
				if (tailleG > 1) {
					if (soustraction == 0) {
						break;
					}
				}
			}
			sousDef.add(soustraction);

		}

		resultat = new GrandEntier(sousDef);

		return resultat;

	}

	public boolean equals(Object o) {
		if (!(o instanceof GrandEntier)) {
			return false;
		}
		if (((this.compareTo((GrandEntier) o)) != 0)) {
			return false;
		}
		return true;
	}
}
