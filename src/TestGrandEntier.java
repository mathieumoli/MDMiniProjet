import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/***
 * 
 * @author Moli
 *
 *	Class to test the methods
 */
public class TestGrandEntier {

    public static void main(String[] args) throws Exception {
	ArrayList<Integer> a = new ArrayList<Integer>();
	a.add(1);
	a.add(2);
	a.add(25);
    	GrandEntier r0= new GrandEntier(a);
    	GrandEntier r1= new GrandEntier(256,new Random());
	}
}
