package work;

import java.util.*;

class Puzzle extends Automaton {
   //deterministic finite automaton for CW3
   
   static enum State {s, a, b, c} //enumerated class
   public static Class getStates() { return State.class; } 

   public State delta(State q, char c) {
      if (q==State.a && c=='0') return State.b; 
      if (q==State.b && c=='0') return State.a; 
      if (q==State.c && c=='0') return State.c;
      if (q==State.s && c=='1') return State.b;
      if (q==State.a && c=='1') return State.a; 
      if (q==State.b && c=='1') return State.c; 
      if (q==State.c && c=='1') return State.b;
      return null;  //default is null -- no transition
   } 
   public boolean accept(String w) {
      State q = State.s;  //initial State
      //System.out.printf("   %s", q);
      for (int j=0; j<w.length(); j++) {
         char c = w.charAt(j);
         q = delta(q, c);
         //System.out.printf(" --> %s", q);
         if (q == null) return false;
      }
      return (q==State.b);  //acceptable?
   } 

   public static final Automaton dfa = new Puzzle();
   public static void main(String[] args) {
     String REG_EXP = "1(01*0|10*1)*";
     Map<Integer, String> a = new TreeMap<>();
     Set<Integer> r = new TreeSet<>();
     for (int n=1; n<50; n++) {
          String w = Integer.toBinaryString(n);
          if (dfa.accept(w)) a.put(n, w);
          if (w.matches(REG_EXP)) r.add(n);
     }
     System.out.println(a.values());
     System.out.println(a.keySet()); 
     System.out.println(r);
   }
}
