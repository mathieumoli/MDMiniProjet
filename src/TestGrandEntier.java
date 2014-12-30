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
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(15);
		a.add(1);
		ArrayList<Integer> b = new ArrayList<Integer>();
		b.add(1);
		b.add(15);

		// Creation des grands entiers
		GrandEntier r0 = new GrandEntier(a);
		GrandEntier r2 = new GrandEntier(b);

		// Test du toString()
		System.out.println("Test du toString()");
		System.out.println(r0.toString());
		System.out.println("Resultat attendu : [ 1, 15]");
		System.out
				.println("_____________________________________________________________________________");

		// test de la somme avec retenu et de meme grandeur
		System.out.println("Test du add avec retenu et de meme grandeur");
		GrandEntier r3 = r2.add(r0);
		System.out.println(r3.toString());
		System.out.println("Resultat attendu : [0, 1, 1]");
		System.out
				.println("_____________________________________________________________________________");

		// test du shiftLeft
		System.out.println("Test du shiftLeft");
		r0.shiftLeft(4);
		System.out.println(r0.toString());
		System.out.println("Resultat attendu : [0, 0, 0, 0, 15, 1]");
		System.out
				.println("_____________________________________________________________________________");

		// test de l'addition sans retenu avec un nombre plus grand en terme de
		// "case" que l'autre
		System.out
				.println("test du add sans retenu avec un nombre plus grand en terme de \"case\" que l'autre");
		r3 = r2.add(r0);
		System.out.println(r3.toString());
		System.out.println("Resultat attendu : [1, 15, 0, 0, 15, 1]");
		System.out
				.println("_____________________________________________________________________________");

		// test du constructeur avec un nombre qui tombe correctement 256/4= 64
		System.out
				.println("test du constructeur GrandEntier(int numBits,Random rnd) avec  256 bits et une base de 16");
		GrandEntier r1 = new GrandEntier(256, new Random());
		// System.out.println(r1.toString());
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

		// test du constructeur avec un nombre qui tombe correctement remplissant la taille max
		System.out
				.println("test du constructeur GrandEntier(int numBits,Random rnd) avec  256 bits et une base de 16");
		GrandEntier r6 = new GrandEntier(10000000, new Random());
		// System.out.println(r1.toString());
		System.out.println("Size de l'arrayList " + r6.getDefinition().size());
		System.out
				.println("Resultat attendu : 16 est codé sur 4 bits donc size = 10000000/4=2500000 soit 2 500 000 nombres definissant le grand entier ");
		System.out
				.println("_____________________________________________________________________________");
	}

}
