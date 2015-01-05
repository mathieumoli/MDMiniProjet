import java.util.ArrayList;

/**
 * Première partie : Mots bien parenthésés et nombres de Catalan
 * 
 * @author PARIS Simon
 * @version 2015.03.01
 */

public class MotBienParenthese {
    
    /**
     * Affiche tous les mots bien paranthésés de taille 2*n.
     * le schema inductif utilisé est 'm = (m1)m2' avec m1 et m2 mot BP. La base est le mot vide.
     * 
     * Complexité THEORIQUE exponentielle, O(e(n))
     * 
     * @param n
     *          mot de taille 2*n
     */   
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
    
    /**
     * Multiplie 2 ArrayList est ajoute (x) aux éléments de la première ArrayList.
     * ex: multiList({a,b},{c,d} -> {(a)c, (a)d, (b)c, (b)d}
     * 
     * Complexité Quadratique, O(n²)
     * 
     * @param m1
     *          ArrayList de mot BP
     * @param m2
     *          ArrayList de mot BP
     */   
    public static ArrayList<String> multiList(ArrayList<String> m1, ArrayList<String> m2) {
        ArrayList<String> res = new ArrayList<String>();
        for (String i : m1)  {
            for (String j : m2) {
                res.add("(" + i + ")" + j);
            }
        }
        return res;
    }
    
    /**
     * Calcul du nombre de Catalan à l'aide de la formule suivant:
     * C(0) = 1 et C(n+1) = SUM[0:n](C(i)*C(n-i)))
     * le nombre de catalan(n) correspond au nombre de mot BP de taille 2*n
     * 
     * Complexité exponentielle, O(e(n))
     * 
     * @param n
     *          mot de taille 2*n
     */   
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
    
    /**
     * Calcul du nombre de Catalan de manière itératif.
     * le nombre de catalan(n) correspond au nombre de mot BP de taille 2*n
     * 
     * Complexité quadratique, O(n²)
     * 
     * @param n
     *          mot de taille 2*n
     */   
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
    
    /**
     * Methode fournit par l'ennoncé.
     * Mesure le temps de calcul de la methode catalan(int) au rang de 10 à n.
     * 
     * @param n
     *          teste de 10 jusqu'à n
     */   
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
    
    /**
     * Mesure le temps de calcul de la methode catalan2(int) au rang [2500, 5000, 10000 , ... , n].
     * Ceci pour montrer la complexité quadratique de catalan2(int): N*2 -> T*4.
     * MAIS ATTENTION: pour mettre en valeur la complexité quadratique on doit aller
     * à des valeurs de n pour lesquelles le resultat de catalan n'est plus exact
     * à cause d la taille des 'long' (dernière valeur valide pour catalan(35).
     * 
     * @param n
     *          teste de 2500 jusqu'à n en multipliant par 2 à chaque fois.
     */   
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
    
    /**
     * vérifie qu'un mot de paranthése est bien paranthésé. Utillise l'altitude.
     * 
     * Complexité linéaire, O(n)
     * 
     * @param MP
     *          mot de paranthése.
     */
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
    
    /**
     * Indente 'comme il faut' les paranthéses d'un mot bien paranthésé:
     * la paranthése fermante ferme la sa paranthése ouvrante sur la même colonne.
     * 
     * Complexité linéaire, O(n)
     * 
     * @param MBP
     *          mot bien paranthésé.
     */
    public static void indentMBP(String MBP) {
        int size = MBP.length();
        String space = "";
        for (int i = 0; i < size; i++) {
            if (MBP.charAt(i) == '(') {
                System.out.println(space + MBP.charAt(i));
                space += "    ";
            }else{
                String tmp = space.substring(0, (space.length() - 4));
                space = tmp;
                System.out.println(space + MBP.charAt(i));
            }
        }
    }
    
    /**
     * Renvoi l'altitude d'un mot BP.
     * ex: (()(())) -> altitude = 3
     * 
     * Complexité linéaire, O(n)
     * 
     * @param MP
     *          mot de paranthése.
     */
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
