/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tony
 */
public class Parentheses {
    private static final char[] symbol = {'[', '{', '(', ')', '}', ']'};
    
    private static boolean conatinsOtherSymbol(char[] a) {
        int cnt = 0;
        for (int i=0; i<a.length; i++) {
            for (int j = 0; j<symbol.length; j++) {
                if(a[i] == symbol[j]){
                    cnt++;
                }
            }
        }
        
        return cnt != a.length;
    }
    
    private static boolean checkParenthese (char[] a) {
        LinkedStack <String> s = new LinkedStack <>();
        for (int i=0; i<a.length; i++) {
            for (int j=0; j<symbol.length; j++) {
                if (a[i] == symbol[j]) {
                    if (j<symbol.length/2) {
                        s.push(String.valueOf(a[i]));
                    }
                    else {
                        if (s.isEmpty())
                            return false;
                        String b = s.pop();
                        if (!b.equals(String.valueOf(symbol[symbol.length - 1 - j])))
                            return false;
                    }
                }
            }
        }
        
        return s.isEmpty();
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);        
        char[] a = in.readString().toCharArray();        
        if (conatinsOtherSymbol(a))
            StdOut.print("input error");
        else {
            boolean result;
            result = checkParenthese(a);
            StdOut.print(result);
        }
    }
}
