import java.util.ArrayList;


public class MotBienParenthese {
    
    public static ArrayList<String> enumMotsBP(int n) {// (m1)m2
        ArrayList<String> res = new ArrayList<String>();
        if (n == 0) {
            res.add("");
        }
        else if (n == 1) {
            res.add("()");
        }else{
            for (int i = 0; i <= n-1; i ++) {
                int j = (n-1) - i;
                res.addAll(multiList(enumMotsBP(i), enumMotsBP(j)));
            }
        }
        return res;
    }
    
    public static ArrayList<String> multiList(ArrayList<String> m1, ArrayList<String> m2) {
        ArrayList<String> res = new ArrayList<String>();
        for (String i : m1)  {
            for (String j : m2) {
                res.add("(" + i + ")" + j);
            }
        }
        return res;
    }
    
//C(0) = 1 et C(n+1) = SUM[0:n](C(i)*C(n-i))   
    public static int catalan(int n) {
        if (n == 0) {
            return 1;
        }else{
            int res = 0;
            for (int i = 0; i < n; i++) {
                res += catalan(i) * catalan((n-1) - i);
            }
            return res;
        }
    }
    
    public static long catalan2(int n) {
        long tabC[];
        tabC = new long [n+1];
        tabC[0] = 1;
        for (int i = 1; i <= n; i++) {
            long res = 0;
            for (int j = 0; j <= (i-1); j++) {
                res += tabC[j] * tabC[(i-1)-j];
            }
            tabC[i] = res;
        }
        return tabC[n];
    }
    
    public static void testCatalan(int n){
        long catalani;
        long t0;
        long temps;
        for(int i=10;i<=n;i++){
           t0 = System.currentTimeMillis();
           catalani=catalan(i);
           temps= System.currentTimeMillis()-t0;
           System.out.println("Temps de calcul du " + i + "ème nombre de Catalan : " + temps);
        }
    }
    
    public static void testCatalan2(int n){
        long catalani;
        long t0;
        long temps;
        for(int i=2500;i<=n;i = i * 2){
           t0 = System.currentTimeMillis();
           catalani=catalan2(i);
           temps= System.currentTimeMillis()-t0;
           System.out.println("Temps de calcul du " + i + "ème nombre de Catalan : " + temps);
        }
    }
    
    public static boolean verifMBP(String MP) {
        int size = MP.length();
        int altitude = 0;
        for (int i = 0; i < size; i++) {
            if (MP.charAt(i) == '(') {
                altitude++;
            }else{
                altitude--;
            }
            if (altitude < 0) {
                return false;
            }
        }
        if (altitude == 0) {
            return true;
        }else{
            return false;
        }
    }
    
    public static void ligneMBP(String MBP) {
        int size = MBP.length();
        String space = "";
        for (int i = 0; i < size; i++) {
            System.out.println(space + MBP.charAt(i));
            space += " ";
        }
    }
    
    public static int altitudeMBP(String MP) {
        int size = MP.length();
        int altitude = 0;
        int altitudeMax = 0;
        for (int i = 0; i < size; i++) {
            if (MP.charAt(i) == '(') {
                altitude++;
            }else{
                altitude--;
            }
            if (altitude > altitudeMax) {
                altitudeMax = altitude;
            }
        }
        return altitudeMax;
    }
}
