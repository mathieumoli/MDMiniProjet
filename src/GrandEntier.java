import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class GrandEntier implements Comparable<GrandEntier> {

	static public GrandEntier zero = new GrandEntier(0);
	static public GrandEntier un = new GrandEntier(1);
	final static int BASE = 16;
	final static int MAXBITLENGTH = 10000000;
	private ArrayList<Integer> definition;

	private GrandEntier(int lenombre) {
		definition = new ArrayList<Integer>();
		definition.add(lenombre);
	}

	public GrandEntier(ArrayList<Integer> ge) {
		int i;
		int valeurTestee = -1;
		if (GrandEntier.clearZero(ge)) {
			ge = (ArrayList<Integer>) zero.getDefinition();
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
		if (n <= 0) {
			throw new IllegalArgumentException(
					"le nombre donné en parametre est négatif ou egal à 0 absurde !  !");
		}
		if (n >= this.length()) {
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
		int somme, i, retenu, compare;
		retenu = 0;
		compare = this.compareTo(ge);
		// la comparaison pour savoir lequel est le plus grand en terme de
		// "case"
		if (compare == -1) {
			plusGrand = ge;
			plusPetit = this;
		} else {
			plusGrand = this;
			plusPetit = ge;
		}

		// l'addition
		for (i = 0; i < plusGrand.length(); i++) {
			somme = 0;
			// la somme de deux nombres de meme poids de deux grandEntiers
			if (plusPetit.length() > i) {
				somme = plusGrand.getDefinition().get(i)
						+ plusPetit.getDefinition().get(i) + retenu;
			} else {
				// il ne reste plus qu'un seul grandentier mais il y a la
				// retenue
				somme = plusGrand.getDefinition().get(i) + retenu;
			}

			if (somme >= BASE) {
				somme %= BASE;
				retenu = 1;
			} else {
				retenu = 0;
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

		if (m.equals(zero) || this.equals(zero)) {
			return zero;
		}

		return this.multiply(m.sub(un)).add(this);

	}

	public GrandEntier multiplySchool(GrandEntier m) {
		int i, j, produit, retenu;
		GrandEntier lepremier= zero;
		ArrayList<Integer> nouveau = new ArrayList<Integer>();
		for (i = 0; i < m.length(); i++) {
			retenu=0;
			nouveau = new ArrayList<Integer>();
			for (j = 0; j < this.length(); j++) {
				produit = m.getDefinition().get(i)
						* this.getDefinition().get(j) + retenu;
				retenu = produit / BASE;
				produit = produit % BASE;
				nouveau.add(produit);
			}
			if (retenu != 0) {
				nouveau.add(retenu);
				
			}
			GrandEntier resultat = new GrandEntier(nouveau).shiftLeft(i);
			lepremier=lepremier.add(resultat);
		}
		return lepremier;
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
		int compare = this.compareTo(ge);
		if (compare == 0)
			return zero;
		if (compare == -1)
			throw new IllegalArgumentException("this<ge");

		ArrayList<Integer> sousDef = new ArrayList<Integer>();
		GrandEntier result;
		int soustraction, i, retenu;
		retenu = 0;

		for (i = 0; i < this.length(); i++) {
			soustraction = 0;
			if (i < ge.length()) {
				soustraction = this.getDefinition().get(i)
						- (ge.getDefinition().get(i) + retenu);
			} else {
				soustraction = this.getDefinition().get(i) - retenu;
			}
			if (soustraction < 0) {

				soustraction = BASE + soustraction;
				retenu = 1;
			} else {
				retenu = 0;
			}

			sousDef.add(soustraction);

		}
		while (sousDef.get(sousDef.size() - 1) == 0) {
			sousDef.remove(sousDef.size() - 1);
		}
		result = new GrandEntier(sousDef);
		return result;

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
	 * Karatsuba de manière récursive si la taille des Grandentiers >2048 sinon une multiplication simple 
	 *
	 * @param ge
	 * @return Retourne le résultat de la multiplication
	 */
	public GrandEntier multiplyFastOpti(GrandEntier ge) {
		int tailleG;
		int verification = 0;
		// couper les entier en deux
		if (this.length() < ge.length()) {
			tailleG = this.length();
		} else {
			tailleG = ge.length();
		}
		// si la taille est inferieur à 2048 multiplication simple
		if (tailleG < 2048) {
			return this.multiplySchool(ge);
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
		GrandEntier produitac = a.multiplyFast(c);
		GrandEntier produitbd = b.multiplyFast(d);
		GrandEntier differenceab;
		if (a.compareTo(b) == -1) {
			differenceab = b.sub(a);
			verification++;
		} else
			differenceab = a.sub(b);

		GrandEntier differencecd;
		if (c.compareTo(d) == -1) {
			differencecd = d.sub(c);
			verification++;
		} else
			differencecd = c.sub(d);
		GrandEntier produitDiffabEtDiffcd = differenceab
				.multiplyFast(differencecd);
		
		// acxB^tailleG+(ac+bd-(a-b)(c-d))xB^tailleGpar2+bd
		if ((verification % 2 == 0)) {
			return (produitac.shiftLeft(2 * tailleGpar2)).add(((produitac.add(
					produitbd).sub(produitDiffabEtDiffcd)
					.shiftLeft(tailleGpar2)).add(produitbd)));
		}
		return (produitac.shiftLeft(2 * tailleGpar2)).add(((produitac.add(
				produitbd).add(produitDiffabEtDiffcd).shiftLeft(tailleGpar2))
				.add(produitbd)));

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
		int verification = 0;
		// couper les entier en deux
		if (this.length() < ge.length()) {
			tailleG = this.length();
		} else {
			tailleG = ge.length();
		}
		// si la taille est egale à 1 multiplication simple
		if (tailleG <= 1) {
			return this.multiplySchool(ge);
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
		GrandEntier produitac = a.multiplyFast(c);
		GrandEntier produitbd = b.multiplyFast(d);
		GrandEntier differenceab;
		if (a.compareTo(b) == -1) {
			differenceab = b.sub(a);
			verification++;
		} else
			differenceab = a.sub(b);

		GrandEntier differencecd;
		if (c.compareTo(d) == -1) {
			differencecd = d.sub(c);
			verification++;
		} else
			differencecd = c.sub(d);
		GrandEntier produitDiffabEtDiffcd = differenceab
				.multiplyFast(differencecd);
		// acxB^tailleG+(ac+bd-(a-b)(c-d))xB^tailleGpar2+bd
		if ((verification % 2 == 0)) {
			return (produitac.shiftLeft(2 * tailleGpar2)).add(((produitac.add(
					produitbd).sub(produitDiffabEtDiffcd)
					.shiftLeft(tailleGpar2)).add(produitbd)));
		}
		return (produitac.shiftLeft(2 * tailleGpar2)).add(((produitac.add(
				produitbd).add(produitDiffabEtDiffcd).shiftLeft(tailleGpar2))
				.add(produitbd)));

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
				a.multiplySchool(b);
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
			if(simpleTime>fastTime){System.out.println("Le nombre de bits où multiplyFast est plus rapide que le multiply est : "+l);break;}
		}
		
	}
	
	/**
	 * compare experimentalement les temps de calculs moyens de multiply et
	 * multiplyFast pour des entiers générés aléatoirement
	 */

	public static void compareBigInteWithOpti(String[] args) throws Exception {
		int n = Integer.parseInt(args[0]);
		Random r = new Random(); // génère des nombres pseudo aléatoires
		long fixedSeed = r.nextLong();
		long t0; // heure initiale d'une serie de tests de multiplication
		long simpleTime; // heure finale d'une serie de tests de multiply
		long fastTime; // heure finale d'une serie de tests de multiplyFast
		GrandEntier a, b; // les nombres à multiplier
		BigInteger c,d;
		System.out.println("\n\n\n");
		System.out
				.println("Comparaison experimentale de la complexité de multiply et de multiplyFast");
		System.out
				.println("-----------------------------------------------------------\n");
		System.out.println("Nombre de répétitions pour chaque taille: " + n
				+ "\n");
		System.out.println(" || temps moyen | temps moyen ");
		System.out.println("# bits || multiplyBigInteger | multiplyFastOpti ");
		System.out.println("----------------------------------");
		for (int l = 1; l <= MAXBITLENGTH; l *= 2) {
			r.setSeed(fixedSeed);
			System.gc(); // nettoyage pour avoir des résultats plus
							// significatifs
			t0 = System.currentTimeMillis();
			for (int i = 1; i <= n; i++) {
				c = new BigInteger(l, r);
				d = new BigInteger(l, r);
				c.multiply(d);
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
				a.multiplyFastOpti(b);
			}
			fastTime = System.currentTimeMillis() - t0;
			System.out.println(l + " || " + simpleTime / n + " | " + fastTime
					/ n);
			
		}
	}

}