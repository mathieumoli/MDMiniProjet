import java.util.ArrayList;
import java.util.Random;

public class GrandEntier {

	final private static GrandEntier zero = new GrandEntier(0);
	final private static GrandEntier un = new GrandEntier(1);
	final static int BASE = 16;
	final static int MAXBITLENGTH = 10000000;
	private ArrayList<Integer> definition;

	public GrandEntier(int lenombre){
		definition=new ArrayList<Integer>();
		definition.add(lenombre);
	}
	public GrandEntier(ArrayList<Integer> ge) {
		int i;
		int valeurTestee = -1;
		if(GrandEntier.clearZero(ge))
		{
			ge=(ArrayList<Integer>) zero.getDefinition();
		}
		for (i = 0; i < ge.size(); i++) {
			valeurTestee = ge.get(i);
			if (valeurTestee >= BASE || valeurTestee < 0) {
				throw new IllegalArgumentException(
						"La Base de l'arraylist en parametre est differente de la base du grand entier"
								+ ge.toString());
			}

		}
		if (ge.size() > 1) {
			if ((ge.get((ge.size() - 1))) == 0) {
				throw new IllegalArgumentException(
						"Le nombre dont le poid est le plus lourd (soit celui qui est l'index taille de l'arraylist-1) est egale à 0"
								+ ge.toString());

			}
		}

		definition = ge;

	}


	private static boolean clearZero(ArrayList<Integer> theArray) {
		int i;
		boolean zer = true;
		for (i = 0; i < theArray.size(); i++) {
			int contenu = theArray.get(i);
			if (contenu != 0) {
				zer = false;
			}
		}
		return zer;

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
		if (n < 0) {
			throw new IllegalArgumentException(
					"le nombre donné en parametre est négatif !");
		}
		int i;
		ArrayList<Integer> result = (ArrayList<Integer>) definition.clone();
		for (i = 0; i < n; i++) {
			result.add(0, 0);
		}
		return new GrandEntier(result);

	}

	public GrandEntier shiftRight(int n) {
		if (n < 0) {
			throw new IllegalArgumentException(
					"le nombre donné en parametre est négatif !");
		}
		if (n > this.length()) {
			throw new IllegalArgumentException(
					"le nombre donné en parametre est superieur aux chiffres composants le GrandEntier shiftRight impossible!");
		}
		int i;
		ArrayList<Integer> result = (ArrayList<Integer>) definition.clone();
		for (i = 0; i < n; i++) {
			result.remove(0);
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

		if (this.equals(zero) || m.equals(zero)) {
			return zero;
		}
		m = m.sub(un);
		GrandEntier result = (this.multiply(m)).add(this);
		return result;

	}

	/**
	 * 
	 * @param e
	 * @return -1, 0 ou 1 si this GrandEntier est numeriquement inferieur, egal,
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

			} else {
				// il ne reste plus qu'un seul grandEntier mais il y a la
				// retenue
				soustraction = plusGrand.getDefinition().get(i) - retenu;
			}
			if (i == (tailleG - 1)) {
				if (tailleG > 1) {
					if (soustraction == 0) {
						break;
					}
				}
			}
			if (soustraction < 0) {
				soustraction %= BASE;
				soustraction = BASE + soustraction;
				retenu = 1;
			} else {
				retenu = 0;
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

	/**
	 * Calcule la multiplication this * ge en utilisant l'algorithme de
	 * Karatsuba de manière récursive
	 *
	 * @param ge
	 * @return Retourne le résultat de la multiplication
	 */
	public GrandEntier multiplyFast(GrandEntier ge) {
		int tailleG;
		// couper les entier en deux
		if (this.length() < ge.length()) {
			tailleG = this.length();
		} else {
			tailleG = ge.length();
		}
		// si la taille est egale à 1 alors pas de besoin de faire tout ça car
		// ça complique le calcul
		if (tailleG <= 1) {
			return this.multiply(ge);
		}
		int tailleGpar2 = (tailleG / 2) + (tailleG % 2);

		// this = a * B^tailleGpar2 + b, ge = c * B^tailleGpar2 + d selon
		// l'algorithme de Wikipedia
		// creation des termes a,b,c,d
		GrandEntier a = this.shiftRight(tailleGpar2);
		GrandEntier b = this.sub(a.shiftLeft(tailleGpar2));
		GrandEntier c = ge.shiftRight(tailleGpar2);
		GrandEntier d = ge.sub(c.shiftLeft(tailleGpar2));

		// creation des differents produits et sommes.
		GrandEntier ac = a.multiplyFast(c);
		GrandEntier bd = b.multiplyFast(d);
		GrandEntier sommeab = a.add(b);
		GrandEntier sommecd = c.add(d);
		GrandEntier abcd = sommeab.multiplyFast(sommecd);

		return ac.add(abcd.sub(ac).sub(bd).shiftLeft(tailleGpar2)).add(
				bd.shiftLeft(2 * tailleGpar2));
	}

	/**
	 * compare experimentalement les temps de calculs moyens de multiply et
	 * multiplyFast pour des entiers générés aléatoirement
	 */

	public static void compareSimpleWithFast(String[] args) throws Exception {
		int n = Integer.parseInt(args[0]);
		Random r = new Random(); // génère des nombres pseudo aléatoires
		long fixedSeed = r.nextLong();
		long t0; // heure initiale d'une serie de tests de multiplication
		long simpleTime; // heure finale d'une serie de tests de multiply
		long fastTime; // heure finale d'une serie de tests de multiplyFast
		GrandEntier a, b; // les nombres à multiplier
		System.out.println("\n\n\n");
		System.out
				.println("Comparaison experimentale de la complexité de multiply et de multiplyFast");
		System.out
				.println("-----------------------------------------------------------\n");
		System.out.println("Nombre de répétitions pour chaque taille: " + n
				+ "\n");
		System.out.println(" || temps moyen | temps moyen ");
		System.out.println("# bits || multiply | multiplyFast ");
		System.out.println("----------------------------------");
		for (int l = 1; l <= MAXBITLENGTH; l *= 2) {
			r.setSeed(fixedSeed);
			System.gc(); // nettoyage pour avoir des résultats plus
							// significatifs
			t0 = System.currentTimeMillis();
			for (int i = 1; i <= n; i++) {
				a = new GrandEntier(l, r);
				b = new GrandEntier(l, r);
				a.multiply(b);
			}
			simpleTime = System.currentTimeMillis() - t0;
			r.setSeed(fixedSeed); // pour générer les memes nombres
									// pseudoaléatoire
			System.gc(); // nettoyage pour avoir des résultats plus
							// significatifs
			t0 = System.currentTimeMillis();
			for (int i = 1; i <= n; i++) {
				a = new GrandEntier(l, r);
				b = new GrandEntier(l, r);
				a.multiplyFast(b);
			}
			fastTime = System.currentTimeMillis() - t0;
			System.out.println(l + " || " + simpleTime / n + " | " + fastTime
					/ n);
		}
	}
}