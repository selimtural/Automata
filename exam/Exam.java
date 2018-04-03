package exam;

import java.util.Set;
import java.util.EnumSet;
import work.Automaton;
import expr.*;

class Exam {
    static Expression f = new Function("sqrt", new Constant(36));
    static Expression b = new Binary(f, Token.MINUS, new Constant(6));
    static Expression e = new Function("cos", b);
    static Automaton nfa = new NFA3();
    static Automaton pda = new PDA4();

    public static void print(Expression x) {
        System.out.printf("%s = %s \n", x, x.fValue());
    }
    public static void Q1() {
        System.out.println("Q1. Sample Expressions");
        print(f); print(b); print(e); 
        System.out.println("");
    }
    public static void Q3() {
        System.out.println("Q3. NFA for (01+010)*");
        nfa.test("01001"); 
        nfa.test("01010"); 
        nfa.test("010110"); 
    }
    public static void Q4() {
        System.out.println("Q4. PDA for (i+c)(?+[n])a(,a)*");
        pda.test("ca");
        pda.test("ia,a,a");
        pda.test("c[n]a");
        pda.test("i[n]a,a");
        pda.test("i,a,a");
        pda.test("c[n]aa");
        pda.test("c[n]aia");
    }
    public static void main(String[] args) {
        Q1(); Q3(); Q4();
    }

    static class NFA3 extends Automaton {
    //NFA for (01+010)*
   
       enum State {a, b, c} //enumerated class

       public Set<State> delta(State q, char c) {
          if (q==State.a && c=='0') return EnumSet.of(State.b);
          if (q==State.b && c=='1') return EnumSet.of(State.a, State.c); 
          if (q==State.c && c=='0') return EnumSet.of(State.a);
          return null;  //default is null -- no transition
       } 
       public boolean accept(String w) {
          Set<State> S = EnumSet.of(State.a);
          for (int j=0; j<w.length(); j++) {
             char c = w.charAt(j);
             Set<State> T = EnumSet.noneOf(State.class); //empty set
             for (State q: S) {
                 Set<State> d = delta(q, c);
                 if (d!=null) T.addAll(d);
             }
             System.out.println(j+": ("+S+", "+c+") -> "+T);
             if (T.isEmpty()) return false;
             S = T;
          }
          return (S.contains(State.a));  //acceptable?
       } 
   }

    static class PDA4 extends work.PDA {
    //PDA for (i+c)(?+[n])a(,a)*

        public String delta(char c, char p) {
            if (c=='i' && p=='Z') return "iTaL";
            if (c=='c' && p=='Z') return "cTaL";
            if (c=='[' && p=='T') return "[n]";
            if (p=='T') return "";
            if (c==',' && p=='L') return ",aL";
            if (p=='L') return "";
            return null;  //default is null -- no transition
        } 
        public boolean accept(String w) {
            return accept(w, 'Z');
        } 
    }
}
