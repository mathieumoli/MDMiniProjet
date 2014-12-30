import java.util.ArrayList;


public class TestMotBienParenthese {

    public static void main (String [] args) {
        for (int i = 1; i < 5; i++) {
            for (String s : MotBienParenthese.enumMotsBP(i)) {
                System.out.println(s);
                }
            System.out.println("+++++++++++++++++++++++");
        }
        
        System.out.println("catalan(8): " + MotBienParenthese.catalan(8));
        System.out.println("catalan2(8): " + MotBienParenthese.catalan2(8));
        //C8=1430
        System.out.println("+++++++++++++++++++++++");
        
        MotBienParenthese.testCatalan(20);
        System.out.println("EXPONENTIEL O(e(n)). N+1 -> T*3");
        System.out.println("+++++++++++++++++++++++");
        MotBienParenthese.testCatalan2(200000);
        System.out.println("QUADRATIQUE O(n²). N*2 -> T*4");
        System.out.println("+++++++++++++++++++++++");
        
        System.out.println("(()) :" + MotBienParenthese.verifMBP("(())"));
        System.out.println("((()) :" + MotBienParenthese.verifMBP("((())"));
        System.out.println("+++++++++++++++++++++++");
        
        MotBienParenthese.ligneMBP("(())()");
        System.out.println("+++++++++++++++++++++++");
        System.out.println("profondeur de (()(((())))())) : " + MotBienParenthese.altitudeMBP("(()(((())))()))"));

    }
}
