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
    
    public static int catalan(int n) {
        float res = 1;
        for (int i = 2; i <= n; i ++) {
            res = res * (n + i) / i;
        }
        return (int) res;
    }
}
