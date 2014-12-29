import java.util.ArrayList;


public class TestMotBienParenthese {

    public static void main (String [] args) {
        for (int i = 1; i < 5; i++) {
            for (String s : MotBienParenthese.enumMotsBP(i)) {
                System.out.println(s);
                }
            System.out.println("+++++++++++++++++++++++");
        }
        
        System.out.println(MotBienParenthese.catalan(3));
    }
}
