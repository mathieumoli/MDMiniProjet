import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/***
 * 
 * @author Moli
 *
 *         Class to test the methods
 */
public class TestGrandEntier {

	public static void main(String[] args) throws Exception {
		// Creation de deux ArrayLists définissant les grands entiers
		ArrayList<Integer> attttt = new ArrayList<Integer>();
		attttt.add(15);
		attttt.add(1);
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(15);
		a.add(1);

		ArrayList<Integer> b = new ArrayList<Integer>();
		b.add(1);
		b.add(15);

		// Creation des grands entiers
		GrandEntier r0 = new GrandEntier(attttt);
		GrandEntier r2 = new GrandEntier(b);

		// Test du toString()
		System.out.println("Test du toString()");
		System.out.println(r0.toString());
		System.out.println("Resultat attendu : 1 x 16^1 + 15 x 16^0");
		System.out
				.println("_____________________________________________________________________________");

		// test de la somme avec retenu et de meme grandeur
		System.out.println("Test du add avec retenu et de meme grandeur");
		GrandEntier r3 = r2.add(r0);
		System.out.println(r3.toString());
		System.out.println("Resultat attendu : 1 x 16^2 + 1 x 16^1 + 0 x 16^0");
		System.out
				.println("_____________________________________________________________________________");

		// test de la difference avec retenue et de meme grandeur
		System.out.println("Test du sub avec retenue et de meme grandeur");
		GrandEntier r9 = r3.sub(r0);
		System.out.println(r9.toString());
		System.out.println("Resultat attendu : 15 x 16^1 + 1 x 16^0");
		System.out
				.println("_____________________________________________________________________________");

		// test du shiftLeft
		System.out.println("Test du shiftLeft");
		GrandEntier tro = r0.shiftLeft(4);
		System.out.println(r0.toString());
		System.out
				.println("Resultat attendu : 1 x 16^5 + 15 x 16^4 + 0 x 16^3 + 0 x 16^2 + 0 x 16^1 + 0 x 16^0");
		System.out
				.println("_____________________________________________________________________________");

		// test de l'addition sans retenu avec un nombre plus grand en terme de
		// "case" que l'autre
		System.out
				.println("test du add sans retenu avec un nombre plus grand en terme de \"case\" que l'autre");
		r3 = r2.add(tro);
		System.out.println(r3.toString());
		System.out
				.println("Resultat attendu : 1 x 16^5 + 15 x 16^4 + 0 x 16^3 + 0 x 16^2 + 15 x 16^1 + 1 x 16^0");
		System.out
				.println("_____________________________________________________________________________");

		// test du constructeur avec un nombre qui tombe correctement 256/4= 64
		System.out
				.println("test du constructeur GrandEntier(int numBits,Random rnd) avec  256 bits et une base de 16");
		GrandEntier r1 = new GrandEntier(256, new Random());
		System.out.println(r1.toString());
		System.out.println("Size de l'arrayList " + r1.getDefinition().size());
		System.out
				.println("Resultat attendu : 16 est codé sur 4 bits donc size = 256/4=64 soit 64 nombres definissant le grand entier ");
		System.out
				.println("_____________________________________________________________________________");

		// test du constructeur avec un nombre qui tombe pas correctement 254/4=
		// 63 + 1 nombres sur 2 bits
		System.out
				.println("test du constructeur GrandEntier(int numBits,Random rnd) avec  254 bits et une base de 16");
		GrandEntier r4 = new GrandEntier(254, new Random());
		System.out.println(r4.toString());
		System.out.println("Size de l'arrayList " + r4.getDefinition().size());
		System.out
				.println("Resultat attendu : 16 est codé sur 4 bits donc size = 254/4=63 soit 63 nombres codés sur 4 bits definissant le grand entier \nmais le dernier nombre est codé sur 2 bits donc sa valeur doit être comprise entre 1 et 3 \n(car le 0 n'est pas admis)un total de 64 nombres");
		System.out
				.println("_____________________________________________________________________________");

		// test du constructeur avec un nombre qui tombe pas correctement 253/4=
		// 63 + 1 nombres sur 1 bits qui ne prends jamais la valeur 0
		System.out
				.println("test du constructeur GrandEntier(int numBits,Random rnd) avec  253 bits et une base de 16");
		GrandEntier r5 = new GrandEntier(253, new Random());
		System.out.println(r5.toString());
		System.out.println("Size de l'arrayList " + r4.getDefinition().size());
		System.out
				.println("Resultat attendu : 16 est codé sur 4 bits donc size = 253/4=63 soit 63 nombres codés sur 4 bits definissant le grand entier \nmais le dernier nombre est codé sur 1 bit donc sa valeur doit être comprise doit être 1 \n(car le 0 n'est pas admis)un total de 64 nombres");
		System.out
				.println("_____________________________________________________________________________");

		// test du constructeur avec un nombre qui tombe correctement
		// remplissant la taille max
		System.out
				.println("test du constructeur GrandEntier(int numBits,Random rnd) avec  10000000 bits (nombre max de bits) et une base de 16");
		GrandEntier r6 = new GrandEntier(10000000, new Random());
		System.out.println("Size de l'arrayList " + r6.getDefinition().size());
		System.out
				.println("Resultat attendu : 16 est codé sur 4 bits donc size = 10000000/4=2500000 soit 2 500 000 nombres definissant le grand entier ");
		System.out
				.println("_____________________________________________________________________________");

		// test de comparaison
		System.out.println("test de comparaison et d'equals");
		// le premier plus grand (en nombre et en case) que le second
		System.out
				.println("GrandEntier(10000000, new Random()) avec 1 x 16^1 + 15 x 16^0");
		System.out.println(r6.compareTo(r0));
		System.out.println("Résultat attendu : 1");

		System.out.println("\n" + r6.equals(r0));
		System.out.println("Résultat attendu : false\n\n");

		// les mêmes
		System.out.println("1 x 16^1 + 15 x 16^0 avec 1 x 16^1 + 15 x 16^0");
		GrandEntier r7 = new GrandEntier(a);
		System.out.println(r7.toString());
		System.out.println(r7.compareTo(r7));
		System.out.println("Résultat attendu : 0");
		System.out.println("\n" + r7.equals(r7));
		System.out.println("Résultat attendu : true\n\n");

		System.out.println("1 x 16^1 + 15 x 16^0 avec 2 x 16^1 + 0 x 16^0");
		// meme longeur mais r8>r7
		ArrayList<Integer> c = new ArrayList<Integer>();
		c.add(1);
		GrandEntier r8 = new GrandEntier(c);
		r8 = r8.add(r7);
		System.out.println(r8.toString());
		System.out.println(r7.compareTo(r8));
		System.out.println("Résultat attendu : -1");
		System.out.println("\n" + r7.equals(r8));
		System.out.println("Résultat attendu : false");
		System.out
				.println("_____________________________________________________________________________");

		// test le multiply recursive de la forme vu en TD
		System.out.println("test le multiply recursive de la forme vue en TD");
		ArrayList<Integer> d = new ArrayList<Integer>();
		d.add(0);
		d.add(1);
		d.add(12);
		d.add(13);
		GrandEntier r11 = new GrandEntier(d);
		GrandEntier r10 = r11.multiply(r11);
		GrandEntier r12= r11.multiplySchool(r11);
		System.out.println(r10.toString());
		System.out.println(r12.toString());

		System.out
				.println("Resultat attendu : 1 x 16^2 + 0 x 16^1 + 0 x 16^0 ");
		ArrayList<Integer> e = new ArrayList<Integer>();

		e.add(15);
		e.add(15);
		ArrayList<Integer> f = new ArrayList<Integer>();
		f.add(14);
		f.add(14);
		GrandEntier r13 = new GrandEntier(f);
		GrandEntier r14 = new GrandEntier(e);
		GrandEntier r15 = r13.multiply(r14);
		System.out.println(r15.toString());
		System.out
				.println("Resultat attendu : 14 x 16^3 + 13 x 16^2 + 1 x 16^1 + 2 x 16^0");
		System.out
				.println("_____________________________________________________________________________");

		ArrayList<Integer> ae = new ArrayList<Integer>();
		ae.add(15);
		ae.add(1);
		ae.add(12);
		ae.add(1);
		ArrayList<Integer> be = new ArrayList<Integer>();
		be.add(1);
		be.add(15);
		be.add(2);

		GrandEntier r01 = new GrandEntier(ae);
		GrandEntier r21 = new GrandEntier(be);
		System.out.println(r01.toString());
		System.out.println(r21.toString());
		System.out.println(r01.multiplyFast(r21));
		System.out.println(r01.multiply(r21));
//		GrandEntier r101 = new GrandEntier(1000000, new Random());
//		GrandEntier r121 = new GrandEntier(1000000, new Random());
//		// System.out.println(r101.toString());
//		// System.out.println(r121.toString());
//		r101.multiplyFast(r21);
//		r101.multiply(r21);
		
		
		String[] v = { "20" };
		try {
			GrandEntier.compareSimpleWithFast(v);
		} catch (Exception w) {
			System.err
					.println("Le programme a besoin d'en entier en paramètre pour specifier le nombre de tests dans une serie de test");
			w.printStackTrace();
		}
		// nous obtenons 4096 le nombre de bits quand multiplyFast plus puissant que multiply 
		try {
			GrandEntier.compareBigInteWithOpti(v);
		} catch (Exception w) {
			System.err
					.println("Le programme a besoin d'en entier en paramètre pour specifier le nombre de tests dans une serie de test");
			w.printStackTrace();
		}
	}
}
