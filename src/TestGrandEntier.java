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
		
		//Creation des grands entiers
		GrandEntier r0 = new GrandEntier(a);
		GrandEntier r2 = new GrandEntier(b);
		
		//Test du toString()
		System.out.println("Test du toString()");
		System.out.println(r0.toString());
		System.out.println("Resultat attendu : [ 1, 15]");
		System.out.println("_____________________________________________________________________________");

		
		//test de la somme avec retenu et de meme grandeur
		System.out.println("Test du add avec retenu et de meme grandeur");
		GrandEntier r3 = r2.add(r0);
		System.out.println(r3.toString());
		System.out.println("Resultat attendu : [0, 1, 1]");
		System.out.println("_____________________________________________________________________________");

		
		//test du shiftLeft
		System.out.println("Test du shiftLeft");
		r2.shiftLeft(4);
		System.out.println(r2.toString());
		System.out.println("Resultat attendu : [0, 0, 0, 0, 15, 1]");
		System.out.println("_____________________________________________________________________________");

		
		//test de l'addition sans retenu avec un nombre plus grand en terme de "case" que l'autre
		System.out.println("test du add sans retenu avec un nombre plus grand en terme de \"case\" que l'autre");
		r3=r2.add(r0);
		System.out.println(r3.toString());
		System.out.println("Resultat attendu : [1, 15, 0, 0, 15, 1]");
		System.out.println("_____________________________________________________________________________");


		
		//pas au point
		GrandEntier r1 = new GrandEntier(256, new Random());
	}
}
